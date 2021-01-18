package bars

import com.github.jasync.sql.db.QueryResult
import com.github.jasync.sql.db.SuspendingConnection
import io.zeko.db.sql.ANSIQuery
import io.zeko.model.DataMapper
import io.zeko.model.MapperConfig
import io.zeko.model.declarations.toMaps

object BarDAO {

    val mapper = DataMapper()
    val mapperConfig = MapperConfig("id", true).table("bar").mapTo { Bar(it["name"] as String) }

    suspend fun insert(bar: Bar, connection: SuspendingConnection): QueryResult {
        val sql = "INSERT INTO bar (name) VALUES (?)"
        return connection.sendPreparedStatement(sql, listOf(bar.name))
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun selectAll(connection: SuspendingConnection): List<Bar> {
        val query = ANSIQuery().table("bar").fields("id", "name").from("bar")
        val (sql, columns) = query.compile()
        val queryResult = connection.sendPreparedStatement(sql)
        val results = queryResult.rows.toMaps(columns)
        return mapper.mapStruct(mapperConfig, results) as List<Bar>
    }

}