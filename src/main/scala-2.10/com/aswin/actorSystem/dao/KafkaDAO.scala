package com.aswin.actorSystem.dao

import java.util.Properties

import com.aswin.actorSystem.config.Configuration
import kafka.message.{NoCompressionCodec, DefaultCompressionCodec}
import kafka.producer.{KeyedMessage, ProducerConfig, Producer}

/**
  * Trait with Kafka data access operations defined
  */
trait KafkaDAO extends Configuration {

  val props = new Properties()

  val codec = if(compress) DefaultCompressionCodec.codec else NoCompressionCodec.codec

  props.put("compression.codec", codec.toString)
  props.put("producer.type", if(synchronously) "sync" else "async")
  props.put("metadata.broker.list", brokerList)
  props.put("request.required.acks", "1")
  props.put("client.id",clientId.toString)

  val producer = new Producer[AnyRef, AnyRef](new ProducerConfig(props))

  def kafkaMesssage(message: Array[Byte], partition: Array[Byte]): KeyedMessage[AnyRef, AnyRef] = {
    if (partition == null) {
      new KeyedMessage(topic,message)
    } else {
      new KeyedMessage(topic,partition,message)
    }
  }

  def pushToKafka(message: String, partition: String = null): Unit = pushToKafka(message.getBytes("UTF8"), if (partition == null) null else partition.getBytes("UTF8"))

  def pushToKafka(message: Array[Byte], partition: Array[Byte]): Unit = {
    try {
      producer.send(kafkaMesssage(message, partition))
    } catch {
      case e: Exception =>
        e.printStackTrace()
        System.exit(1)
    }
  }
}
