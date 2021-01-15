package bars

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.containers.{GenericContainer, Network, PostgreSQLContainer}

import scala.util.Random

class SpringBootSimulation extends Simulation {

  val network = Network.newNetwork()

  class PostgresContainer extends PostgreSQLContainer[PostgresContainer]("postgres:13.1")

  val postgresContainer = new PostgresContainer()
    .withInitScript("init.sql")
    .withNetwork(network)
    .withNetworkAliases("postgres")

  postgresContainer.start()

  class SpringBootContainer extends GenericContainer[SpringBootContainer]("springboot-server")

  val springbootContainer = new SpringBootContainer()
    .withExposedPorts(8080)
    .withNetwork(network)
    .waitingFor(Wait.forHttp("/bars"))
    .withEnv("SPRING_R2DBC_URL", s"r2dbc:postgresql://postgres/${postgresContainer.getDatabaseName}")
    .withEnv("SPRING_R2DBC_USERNAME", postgresContainer.getUsername)
    .withEnv("SPRING_R2DBC_PASSWORD", postgresContainer.getPassword)

  springbootContainer.start()

  val springbootHttpProtocol = http.baseUrl(s"http://${springbootContainer.getHost}:${springbootContainer.getFirstMappedPort}")

  def name() = Random.alphanumeric.take(8).mkString

  val scn = scenario("Bars")
    .repeat(10)(exec(http("get").get("/bars").asJson))
    .exec(http("add").post("/bars").body(StringBody(s"""{"name": "${name()}"}""")).asJson)

  val numUsers = 1000

  setUp(scn.inject(atOnceUsers(numUsers)).protocols(springbootHttpProtocol))

  after {
    springbootContainer.stop()
    postgresContainer.stop()
  }

}