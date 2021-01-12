package bars

import com.github.jasync.sql.db.Connection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import io.micronaut.context.annotation.Factory
import org.testcontainers.containers.PostgreSQLContainer
import javax.annotation.PreDestroy
import javax.inject.Singleton

@Factory
class TestJasyncPostgreSQLClientFactory : AutoCloseable {

    class PostgresContainer : PostgreSQLContainer<PostgresContainer>("postgres:13.1")

    private val container by lazy {
        val underlying = PostgresContainer().withInitScript("init.sql")
        underlying.start()
        underlying
    }

    @Singleton
    fun client(): Connection {
        return PostgreSQLConnectionBuilder.createConnectionPool {
            host = container.host
            port = container.firstMappedPort
            database = container.databaseName
            username = container.username
            password = container.password
        }
    }

    @PreDestroy
    override fun close() {
        container.stop()
    }

}