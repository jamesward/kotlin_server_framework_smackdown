package bars

import io.quarkus.arc.profile.IfBuildProfile
import io.vertx.mutiny.pgclient.PgPool
import io.vertx.pgclient.PgConnectOptions
import io.vertx.sqlclient.PoolOptions
import org.testcontainers.containers.PostgreSQLContainer
import javax.annotation.PreDestroy
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.inject.Singleton

@Dependent
class TestPgPool {

    @Produces
    @IfBuildProfile("dev")
    fun pgPool(container: TestPostgresContainer): PgPool {
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

@Singleton
class TestPostgresContainer : PostgreSQLContainer<TestPostgresContainer>("postgres:13.1") {

    init {
        this.withInitScript("init.sql")
        this.start()
    }

    @PreDestroy
    fun destroy() {
        this.stop()
    }

}