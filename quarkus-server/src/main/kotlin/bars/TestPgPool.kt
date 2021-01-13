package bars

import io.vertx.mutiny.pgclient.PgPool
import io.vertx.pgclient.PgConnectOptions
import io.vertx.sqlclient.PoolOptions
import org.testcontainers.containers.PostgreSQLContainer
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces

@Dependent
class TestPgPool {

    class PostgresContainer : PostgreSQLContainer<PostgresContainer>("postgres:13.1")

    private val container by lazy {
        val underlying = PostgresContainer().withInitScript("init.sql")
        underlying.start()
        underlying
    }

    @Produces
    fun pgPool(): PgPool {
        val pgConnectOptions = PgConnectOptions()
        pgConnectOptions.host = container.host
        pgConnectOptions.port = container.firstMappedPort
        pgConnectOptions.database = container.databaseName
        pgConnectOptions.user = container.username
        pgConnectOptions.password = container.password

        val poolOptions = PoolOptions()

        return PgPool.pool(pgConnectOptions, poolOptions)
    }

}