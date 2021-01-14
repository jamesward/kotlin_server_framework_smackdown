package bars

import com.github.jasync.sql.db.Connection
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

class ConnectionFeature(configuration: Configuration) {

    // todo
    val host = configuration.host
    val port = configuration.port
    val database = configuration.database
    val username = configuration.username
    val password = configuration.password

    class Configuration {
        var host: String? = null
        var port: Int? = null
        var database: String? = null
        var username: String? = null
        var password: String? = null
    }

    companion object : ApplicationFeature<Application, Configuration, Connection> {
        override val key: AttributeKey<Connection> = AttributeKey("Connection")

        override fun install(pipeline: Application, configure: Configuration.() -> Unit): Connection {
            val configuration = Configuration().apply(configure)

            val pool = configuration.host?.let { host ->
                configuration.port?.let { port ->
                    configuration.database?.let { database ->
                        configuration.username?.let { username ->
                            configuration.password?.let { password ->
                                PostgreSQLConnectionBuilder.createConnectionPool {
                                    this.host = host
                                    this.port = port
                                    this.database = database
                                    this.username = username
                                    this.password = password
                                }
                            }
                        }
                    }
                }
            }

            pool?.let {
                pipeline.intercept(ApplicationCallPipeline.Call) {
                    this.context.attributes.put(key, pool)
                }
            }

            // todo
            return pool!!
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
    baseModule()
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