package bars

import io.ktor.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.util.*
import org.slf4j.LoggerFactory
import org.testcontainers.containers.PostgreSQLContainer


fun Application.testModule() {

    class PostgresContainer : PostgreSQLContainer<PostgresContainer>("postgres:13.1")

    val container by lazy {
        val underlying = PostgresContainer().withInitScript("init.sql")
        underlying.start()
        underlying
    }

    baseModule {
        install(ConnectionFeature) {
            host = container.host
            port = container.firstMappedPort
            database = container.databaseName
            username = container.username
            password = container.password
        }
        environment.monitor.subscribe(ApplicationStopped) {
            container.stop()
        }
    }

}

@EngineAPI
@KtorExperimentalAPI
fun main() {
    val env = applicationEngineEnvironment {
        developmentMode = true // todo: externalize
        log = LoggerFactory.getLogger("ktor.application")
        watchPaths = listOf("ktor-server/build") // todo: gross
        module(Application::testModule)
        connector {
            port = System.getenv("PORT")?.toInt() ?: 8080
        }
    }

    val server = embeddedServer(CIO, env)
    server.start(true)
}
