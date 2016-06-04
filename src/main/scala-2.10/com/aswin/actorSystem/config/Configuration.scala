package com.aswin.actorSystem.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Created by aswin on 4/6/16.
  */
trait Configuration {

  val config = ConfigFactory.load()

  /** Host name/address to start service on. */
  lazy val serviceHost = Try(config.getString("service.host")).getOrElse("localhost")
  /** Port to start service on. */
  lazy val servicePort = Try(config.getInt("service.port")).getOrElse(8080)

  /** All the kafka-related configs*/
  lazy val compress = Try(config.getBoolean("kafka.compress")).getOrElse(true)
  lazy val synchronously = Try(config.getBoolean("kafka.synchronously")).getOrElse(true)
  lazy val brokerList = Try(config.getString("kafka.brokerList")).getOrElse("localhost")
  lazy val batchSize = Try(config.getString("kafka.batchSize"))
  lazy val messageSendMaxRetries = Try(config.getInt("kafka.messageSendMaxRetries"))
  lazy val requestRequiredAcks = Try(config.getInt("kafka.requestRequiredAcks"))
  lazy val clientId = Try(config.getString("kafka.clientId")).getOrElse("client")
  lazy val topic = Try(config.getString("kafka.topic")).getOrElse("topic")

  /** All the Twitter related configs*/
  lazy val consumerKey = Try(config.getString("twitter.consumerKey")).getOrElse("")
  lazy val consumerSecret = Try(config.getString("twitter.consumerSecret")).getOrElse("")
  lazy val accessToken = Try(config.getString("twitter.accessToken")).getOrElse("")
  lazy val accessTokenSecret = Try(config.getString("twitter.accessTokenSecret")).getOrElse("")
  lazy val twitterUsername = Try(config.getString("twitter.twitterUsername")).getOrElse("")
  lazy val twitterKeyWord = Try(config.getString("twitter.keyword")).getOrElse("")

}
