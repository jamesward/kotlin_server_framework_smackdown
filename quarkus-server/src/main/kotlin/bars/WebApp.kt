package bars

import io.smallrye.mutiny.Multi
import kotlinx.html.dom.serialize
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import io.smallrye.mutiny.Uni
import io.vertx.mutiny.pgclient.PgPool
import io.vertx.mutiny.sqlclient.Tuple
import java.lang.Exception

@Path("/")
class WebApp(val pgPool: PgPool) {

    @GET
    @Produces(MediaType.TEXT_HTML)
    fun index(): String {
        return Html.index.serialize(false)
    }

    @GET
    @Path("/bars")
    @Produces(MediaType.APPLICATION_JSON)
    // todo: Uni<List<Bar>> ?
    fun getBars(): Multi<Bar> {
        return pgPool.query("SELECT * FROM bar").execute()
            .onItem().transformToMulti(Multi.createFrom()::iterable)
            .onItem().transform { Bar(it.getString("name")) }
    }

    @POST
    @Path("/bars")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addBar(bar: Bar): Uni<Unit> {
        val result = pgPool.preparedQuery("INSERT INTO bar (name) VALUES ($1)").execute(Tuple.of(bar.name))
        return result.flatMap {
            if (it.rowCount() == 1)
                Uni.createFrom().nullItem()
            else
                Uni.createFrom().failure(Exception("Could not create Bar"))
        }
    }

}