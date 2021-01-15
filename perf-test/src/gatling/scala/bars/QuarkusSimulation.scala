package bars

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.{GenericContainer, Network, PostgreSQLContainer}

import scala.util.Random

class QuarkusSimulation extends Simulation {

  val network = Network.newNetwork()

  class PostgresContainer extends PostgreSQLContainer[PostgresContainer]("postgres:13.1")

  val postgresContainer = new PostgresContainer()
    .withInitScript("init.sql")
    .withNetwork(network)
    .withNetworkAliases("postgres")

  postgresContainer.start()

  class QuarkusContainer extends GenericContainer[QuarkusContainer]("quarkus-server")

  val quarkusContainer = new QuarkusContainer()
    .withExposedPorts(8080)
    .withNetwork(network)
    .waitingFor(Wait.forHttp("/bars"))
    .withEnv("QUARKUS_DATASOURCE_REACTIVE_URL", s"postgresql://postgres:5432/${postgresContainer.getDatabaseName}")
    .withEnv("QUARKUS_DATASOURCE_USERNAME", postgresContainer.getUsername)
    .withEnv("QUARKUS_DATASOURCE_PASSWORD", postgresContainer.getPassword)

  quarkusContainer.start()

  val httpProtocol = http.baseUrl(s"http://${quarkusContainer.getHost}:${quarkusContainer.getFirstMappedPort}")

  def name() = Random.alphanumeric.take(8).mkString

  val scn = scenario("Bars")
    .repeat(10)(exec(http("get").get("/bars").asJson))
    .exec(http("add").post("/bars").body(StringBody(s"""{"name": "${name()}"}""")).asJson)

  val numUsers = 1000

  setUp(scn.inject(atOnceUsers(numUsers)).protocols(httpProtocol))

  after {
    quarkusContainer.stop()
    postgresContainer.stop()
  }

}