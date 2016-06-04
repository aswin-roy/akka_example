package com.aswin.actorSystem.actors

import akka.actor.{ActorLogging, ActorRef, Actor}

/**
  * The Base Actor which divides jobs among the various source actors.
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
