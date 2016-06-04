package com.aswin.actorSystem.actors

import akka.actor.{ActorLogging, ActorRef, Actor}

/**
  * Created by aswin on 4/6/16.
  */
class BaseReaderActor(twitter: ActorRef, facebook: ActorRef, gitHub: ActorRef) extends Actor
  with ActorLogging {

  def receive = {
    case "Twitter" => twitter ! ""
    case "Facebook" => facebook ! ""
    case "GitHub" => gitHub ! ""
    case _ => // unknown option
      log.error("Sorry, unrecognized source!")
  }
}
