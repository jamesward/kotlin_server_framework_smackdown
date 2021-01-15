package bars

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.{GenericContainer, Network, PostgreSQLContainer}

import scala.util.Random

class KtorSimulation extends Simulation {

  val network = Network.newNetwork()

  class PostgresContainer extends PostgreSQLContainer[PostgresContainer]("postgres:13.1")

  val postgresContainer = new PostgresContainer()
    .withInitScript("init.sql")
    .withNetwork(network)
    .withNetworkAliases("postgres")

  postgresContainer.start()

  class KtorContainer extends GenericContainer[KtorContainer]("ktor-server")

  val ktorContainer = new KtorContainer()
    .withExposedPorts(8080)
    .withNetwork(network)
    .waitingFor(Wait.forHttp("/bars"))
    .withEnv("JASYNC_CLIENT_HOST", "postgres")
    .withEnv("JASYNC_CLIENT_PORT", "5432")
    .withEnv("JASYNC_CLIENT_DATABASE", postgresContainer.getDatabaseName)
    .withEnv("JASYNC_CLIENT_USERNAME", postgresContainer.getUsername)
    .withEnv("JASYNC_CLIENT_PASSWORD", postgresContainer.getPassword)

  ktorContainer.start()

  val httpProtocol = http.baseUrl(s"http://${ktorContainer.getHost}:${ktorContainer.getFirstMappedPort}")

  def name() = Random.alphanumeric.take(8).mkString

  val scn = scenario("Bars")
    .repeat(10)(exec(http("get").get("/bars").asJson))
    .exec(http("add").post("/bars").body(StringBody(s"""{"name": "${name()}"}""")).asJson)

  val numUsers = 1000

  setUp(scn.inject(atOnceUsers(numUsers)).protocols(httpProtocol))

  after {
    ktorContainer.stop()
    postgresContainer.stop()
  }

}