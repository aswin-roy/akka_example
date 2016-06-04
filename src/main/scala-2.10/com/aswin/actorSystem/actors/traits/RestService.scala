package com.aswin.actorSystem.actors.traits

/**
  * Master rest-service trait
  */
trait RestService extends SourceService{

  val route = {
    restRoutes ~ secondRoute
  }
}
