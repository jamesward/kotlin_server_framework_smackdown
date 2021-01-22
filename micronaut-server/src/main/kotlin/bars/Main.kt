package bars

import com.github.jasync.sql.db.SuspendingConnection
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.runtime.Micronaut
import kotlinx.html.dom.serialize

fun main() {
    Micronaut.run()
}

@Controller
class WebApp(private val connection: SuspendingConnection) {

    @Get("/", produces = [MediaType.TEXT_HTML])
    fun index() = run {
        Html.index.serialize(false)
    }

    @Get("/bars")
    suspend fun getBars(): List<Bar> = run {
        BarDAO.selectAll(connection)
    }

    @Post("/bars")
    suspend fun addBar(@Body bar: Bar): HttpResponse<Unit> = run {
        val result = BarDAO.insert(bar, connection)
        if (result.rowsAffected == 1L)
            HttpResponse.noContent()
        else
            HttpResponse.serverError()
    }

}