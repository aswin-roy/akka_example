package com.aswin.actorSystem.application

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import com.aswin.actorSystem.actors.RestServiceActor
import com.aswin.actorSystem.config.Configuration
import spray.can.Http
import scala.concurrent.duration._

/**
  * Main class.
  */
object Boot extends App with Configuration {
  implicit val system = ActorSystem("Actor-System")
  val restService = system.actorOf(Props(new RestServiceActor), "REST-Service")

  implicit val timeout = Timeout(5.seconds)

  IO(Http) ? Http.Bind(restService, interface = serviceHost, port = servicePort)
}
