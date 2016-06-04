package com.aswin.actorSystem.actors.source_reader_actors

import akka.actor.Actor
import com.aswin.actorSystem.dao.KafkaDAO

/**
  * Actor to read data from Fb
  */
class FacebookActor extends Actor with KafkaDAO {
  def receive = {
    case _ =>
      //include logic to ingest from git
      pushToKafka("", null) //pass the ingested data as string param
  }
}
