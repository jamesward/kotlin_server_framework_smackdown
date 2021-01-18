package bars

import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.postgresql.PostgreSQLConnection
import com.github.jasync.sql.db.postgresql.PostgreSQLConnectionBuilder
import com.github.jasync.sql.db.util.head
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.testcontainers.perTest
import io.kotest.matchers.shouldBe
import org.testcontainers.containers.PostgreSQLContainer

class PostgresContainer : PostgreSQLContainer<PostgresContainer>("postgres:13.1")

fun pool(container: PostgresContainer): ConnectionPool<PostgreSQLConnection> {
    return PostgreSQLConnectionBuilder.createConnectionPool {
        host = container.host
        port = container.firstMappedPort
        database = container.databaseName
        username = container.username
        password = container.password
    }
}

class BarDAOSpec : FunSpec({
    val container = PostgresContainer().withInitScript("init.sql")
    listener(container.perTest())

    test("write and read") {
        pool(container).asSuspending.inTransaction { connection ->
            val name = (1..8).map { ('a'..'z').random() }.joinToString("")
            val bar = Bar(name)

            BarDAO.insert(bar, connection).rowsAffected shouldBe 1

            BarDAO.selectAll(connection).head shouldBe bar
        }
    }

})