package com.aswin.actorSystem.actors.source_reader_actors

import akka.actor.{Actor, ActorLogging}
import com.aswin.actorSystem.config.Configuration
import com.aswin.actorSystem.dao.{KafkaDAO, TwitterBase}
import twitter4j.Query

/**
  * Actor to read data from Twitter
  */
class TwitterActor extends Actor with TwitterBase with Configuration with KafkaDAO with ActorLogging{
  def receive = {
    case _ =>
      val twitter = getTwitterInstance

      val query = new Query(twitterKeyWord)
      val tweets = twitter.search(query).getTweets
      val it = tweets.iterator
      while (it.hasNext) {
        val tweet = it.next
        log.info(tweet.getText) //each tweet
        pushToKafka(tweet.getText, null) //pushes each tweet to Kafka topic
      }
  }
}
