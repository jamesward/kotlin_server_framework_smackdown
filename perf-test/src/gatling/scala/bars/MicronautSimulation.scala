package bars

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.{GenericContainer, Network, PostgreSQLContainer}

import scala.util.Random

class MicronautSimulation extends Simulation {

  val network = Network.newNetwork()

  class PostgresContainer extends PostgreSQLContainer[PostgresContainer]("postgres:13.1")

  val postgresContainer = new PostgresContainer()
    .withInitScript("init.sql")
    .withNetwork(network)
    .withNetworkAliases("postgres")

  postgresContainer.start()

  class MicronautContainer extends GenericContainer[MicronautContainer]("micronaut-server")

  val micronautContainer = new MicronautContainer()
    .withExposedPorts(8080)
    .withNetwork(network)
    .waitingFor(Wait.forHttp("/bars"))
    .withEnv("JASYNC_CLIENT_HOST", "postgres")
    .withEnv("JASYNC_CLIENT_PORT", "5432")
    .withEnv("JASYNC_CLIENT_DATABASE", postgresContainer.getDatabaseName)
    .withEnv("JASYNC_CLIENT_USERNAME", postgresContainer.getUsername)
    .withEnv("JASYNC_CLIENT_PASSWORD", postgresContainer.getPassword)

  micronautContainer.start()

  val httpProtocol = http.baseUrl(s"http://${micronautContainer.getHost}:${micronautContainer.getFirstMappedPort}")

  def name() = Random.alphanumeric.take(8).mkString

  val scn = scenario("Bars")
    .repeat(10)(exec(http("get").get("/bars").asJson))
    .exec(http("add").post("/bars").body(StringBody(s"""{"name": "${name()}"}""")).asJson)

  val numUsers = 1000

  setUp(scn.inject(atOnceUsers(numUsers)).protocols(httpProtocol))

  after {
    micronautContainer.stop()
    postgresContainer.stop()
  }

}