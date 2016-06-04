package com.aswin.actorSystem.actors

import akka.actor.Actor
import com.aswin.actorSystem.actors.traits.RestService
import spray.routing.HttpService

/**
  * Base Actor
  */
class RestServiceActor extends Actor with RestService {

  def actorRefFactory = context
  def receive = HttpService.runRoute(route)
}
