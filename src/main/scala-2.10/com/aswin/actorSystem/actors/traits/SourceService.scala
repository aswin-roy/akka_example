package com.aswin.actorSystem.actors.traits

import akka.actor.{ActorRef, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.aswin.actorSystem.actors.BaseReaderActor
import com.aswin.actorSystem.actors.source_reader_actors.{FacebookActor, GitActor, TwitterActor}
import spray.http.MediaTypes._
import spray.routing.HttpService

import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * Service class to pass message to different source-actors
  */
trait SourceService extends HttpService {
  val twitterActor = actorRefFactory.actorOf(Props[TwitterActor], name = "twitterActor")
  val fbActor = actorRefFactory.actorOf(Props[FacebookActor], name = "fbActor")
  val gitActor = actorRefFactory.actorOf(Props[GitActor], name = "gitActor")

  val baseReader = actorRefFactory.actorOf(Props(new BaseReaderActor(twitterActor,fbActor, gitActor)),
    name = "mediaActor")

  val restRoutes =
    path("source" /  Segment) {
      mediaName =>
        get{
          respondWithMediaType(`application/json`) {
            complete {
              baseReader ! mediaName
              "Source data being pushed to Kafka Successfully!"
            }
          }
        }
    }

  val secondRoute =
    path("event" / Segment){
      mediaName =>
        get{
          respondWithMediaType(`application/json`) {
            complete {
              fetchEventData() //logic to ingest event news data
              "News event data being pushed to Kafka Successfully!"
            }
          }
        }
    }

  /**
    * Method to get a Future of Actor processing completion.
    * @param sourceReaderActor Source Actor
    * @param message The message to be passed on to the Actor
    */
  def getSummary(sourceReaderActor: ActorRef, message: String): Future[String] = {
    implicit val timeout = Timeout(1 seconds)
    ask(sourceReaderActor,message).mapTo[String]
  }

  /**
    * Fetch event based data with custom logic
    */
  def fetchEventData(): Unit ={
    //logic for news data
  }
}
