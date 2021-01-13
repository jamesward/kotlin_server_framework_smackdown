package bars

import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.html.dom.serialize
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.awaitRowsUpdated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@RestController
class WebApp(val client: DatabaseClient) {

    @GetMapping("/")
    fun index(): String {
        return Html.index.serialize(false)
    }

    @GetMapping("/bars")
    suspend fun getBars() = run {
        val mapToBar: (Map<String, Any>) -> Bar = { Bar(it["name"] as String) }

        client.sql("SELECT * FROM bar").fetch().all().map(mapToBar).collectList().awaitFirst()
    }

    @PostMapping("/bars")
    suspend fun addBar(@RequestBody bar: Bar): Unit = run {
        val sql = "INSERT INTO bar (name) VALUES (:name)"
        val rowsUpdated = client.sql(sql).bind("name", bar.name).fetch().awaitRowsUpdated()
        if (rowsUpdated == 1)
            ResponseEntity<Unit>(HttpStatus.NO_CONTENT)
        else
            ResponseEntity<Unit>(HttpStatus.INTERNAL_SERVER_ERROR)
    }

}

fun main(args: Array<String>) {
    runApplication<WebApp>(*args)
}