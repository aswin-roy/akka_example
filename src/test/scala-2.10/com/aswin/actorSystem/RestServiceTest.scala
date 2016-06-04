package com.aswin.actorSystem

import com.aswin.actorSystem.actors.traits.RestService
import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest

/**
  * Unit Test Class
  */
abstract class RestServiceTest extends Specification with Specs2RouteTest with RestService {
  def actorRefFactory = system

  "The RestService" should {

    "return a 'Source data being pushed to Kafka Successfully' for GET requests to /source " in {
      Get("/source/Twitter") ~> route ~> check {
        responseAs[String] === "Source data being pushed to Kafka Successfully!"
      }
    }

    "return a 'News event data being pushed to Kafka Successfully!' for GET requests to /source " in {
      Get("/event/Event") ~> route ~> check {
        responseAs[String] === "News event data being pushed to Kafka Successfully!"
      }
    }
  }

}
