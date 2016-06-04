package com.aswin.actorSystem.dao

import com.aswin.actorSystem.config.Configuration
import twitter4j.{Twitter, TwitterFactory}
import twitter4j.conf.ConfigurationBuilder

/**
  * Trait to get twitter4j instance
  */
trait TwitterBase extends Configuration{

  def getTwitterInstance: Twitter = {
    val cb = new ConfigurationBuilder()
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey(consumerKey)
      .setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret)
    new TwitterFactory(cb.build()).getInstance
  }
}
