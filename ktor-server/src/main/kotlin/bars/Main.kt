package bars

import com.github.jasync.sql.db.Connection
import com.github.jasync.sql.db.ConnectionPoolConfigurationBuilder
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import com.github.jasync_sql_extensions.mapTo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.cio.*
import io.ktor.jackson.*
import io.ktor.server.engine.*
import io.ktor.util.*
import io.ktor.webjars.*
import kotlinx.coroutines.future.await
import org.slf4j.LoggerFactory

class ConnectionFeature {
    companion object : ApplicationFeature<Application, ConnectionPoolConfigurationBuilder, Connection> {
        override val key: AttributeKey<Connection> = AttributeKey("Connection")

        override fun install(pipeline: Application, configure: ConnectionPoolConfigurationBuilder.() -> Unit): Connection {
            val config = ConnectionPoolConfigurationBuilder().apply { configure() }
            val pool = PostgreSQLConnectionBuilder.createConnectionPool(config)

            pipeline.intercept(ApplicationCallPipeline.Call) {
                this.context.attributes.put(key, pool)
            }

            return pool
        }
    }
}

fun Application.baseModule(other: () -> Any = {}) {
    other()

    install(DefaultHeaders)
    install(CallLogging)
    install(Webjars)
    install(ContentNegotiation) {
        jackson()
    }
    install(Routing) {
        static("assets") {
            resources("META-INF/resources/assets")
        }
        get("/") {
            call.respondHtml(HttpStatusCode.OK, Html.indexHTML)
        }
        route("/bars") {
            get {
                call.attributes.getOrNull(ConnectionFeature.key)?.let { client ->
                    val bars = client.sendQuery("SELECT * FROM bar").await().rows.mapTo<Bar>()
                    call.respond(bars)
                }
            }
            post {
                val bar = call.receive<Bar>()
                val sql = "INSERT INTO bar (name) VALUES (?)"
                call.attributes.getOrNull(ConnectionFeature.key)?.let { client ->
                    val result = client.sendPreparedStatement(sql, listOf(bar.name)).await()
                    if (result.rowsAffected == 1L)
                        call.respond(HttpStatusCode.NoContent)
                    else
                        call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}

fun Application.prodModule() {
    baseModule {
        install(ConnectionFeature) {
            host = System.getenv("JASYNC_CLIENT_HOST")
            port = System.getenv("JASYNC_CLIENT_PORT").toInt()
            database = System.getenv("JASYNC_CLIENT_DATABASE")
            username = System.getenv("JASYNC_CLIENT_USERNAME")
            password = System.getenv("JASYNC_CLIENT_PASSWORD")
        }
    }
}

@EngineAPI
@KtorExperimentalAPI
fun main() {
    val env = applicationEngineEnvironment {
        //developmentMode = true // todo: externalize
        log = LoggerFactory.getLogger("ktor.application")
        //watchPaths = listOf("ktor-server/build") // todo: gross
        module(Application::prodModule)
        connector {
            port = System.getenv("PORT")?.toInt() ?: 8080
        }
    }

    val server = embeddedServer(CIO, env)
    server.start(true)
}