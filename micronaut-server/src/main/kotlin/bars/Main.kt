package bars

import com.github.jasync.sql.db.Connection
import com.github.jasync_sql_extensions.mapTo
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.runtime.Micronaut
import kotlinx.coroutines.future.await
import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize

fun main() {
    Micronaut.run()
}

@Controller
class WebApp(private val connection: Connection) {

    @Get("/", produces = [MediaType.TEXT_HTML])
    fun index() = run {
        indexHtml.serialize(false)
    }

    @Get("/bars")
    suspend fun getBars() = run {
        connection.sendQuery("SELECT * FROM bar").await().rows.mapTo<Bar>()
    }

    @Post("/bars")
    suspend fun addBar(@Body bar: Bar): HttpResponse<Unit> = run {
        val sql = "INSERT INTO bar (name) VALUES (?)"
        val result = connection.sendPreparedStatement(sql, listOf(bar.name)).await()
        if (result.rowsAffected == 1L)
            HttpResponse.noContent()
        else
            HttpResponse.serverError()
    }

}

val indexHtml = createHTMLDocument().html {
    head {
        link("/webjars/bootstrap/4.5.3/css/bootstrap.min.css", LinkRel.stylesheet)
        link("/assets/index.css", LinkRel.stylesheet)
        script(ScriptType.textJavaScript) {
            src = "http://localhost:8081/js-client.js"
        }
    }
    body {
        nav("navbar fixed-top navbar-light bg-light") {
            a("/", classes = "navbar-brand") {
                +"Micronaut Bars"
            }
        }

        div("container-fluid") {
            ul {
                id = "bars"
            }

            form("/bars") {
                id = "form"
                input {
                    id = "name"
                }
                submitInput { }
            }
        }
    }
}