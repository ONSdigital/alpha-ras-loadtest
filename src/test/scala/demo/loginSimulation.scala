// To run, change to the top level and run:
// mvn gatling:execute

package demo


import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._


class loginSimulation extends Simulation {


  // Suck environmental vars
  val environment = if (System.getProperty("ENVIRONMENT") != null)
    System.getProperty("ENVIRONMENT") else "Playpen"

  val domain = if (System.getProperty("DOMAIN") != null)
    System.getProperty("DOMAIN") else "localhost"

  // Hopefully we have been passed something that will convert to an integer
  val port = if (System.getProperty("PORT") != null)
    System.getProperty("PORT").toInt else 8080

  val users = if (System.getProperty("USERS") != null)
    System.getProperty("USERS").toInt else 500

  val seconds = if (System.getProperty("SECONDS") != null)
    System.getProperty("SECONDS").toInt else 10

  val mins = if (System.getProperty("MINUTES") != null)
    System.getProperty("MINUTES").toInt else 1

  before {
    println("Simulation is about to start!")
  }

  after {
    println("Simulation is finished!")
  }

  //
  val conf = http.baseURL("http://%s:%s".format(domain, port))

  val scn = scenario("Gatling").during(30 seconds) {
    exec(http("index2")
      .get("/service-instances/microservice")
      .check(regex("MICROSERVICE").find(1).exists)
      .check(regex("vevedvcadscwdqssSs").notExists))
  }
    .exec(http("index1").get("/").check(status.is(session => 200)))


  setUp(scn.inject(atOnceUsers(users)))
    .protocols(conf)

}

