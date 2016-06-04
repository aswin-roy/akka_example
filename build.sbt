name := "actor_trial"

version := "1.0"

scalaVersion := "2.10.4"

resolvers ++= Seq(
  "Spray repository" at "http://repo.spray.io",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-http"    % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.2",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "org.slf4j"           % "slf4j-nop"       % "1.6.4",
    "org.apache.kafka" % "kafka_2.10" % "0.8.0"
      exclude("javax.jms", "jms")
      exclude("com.sun.jdmk", "jmxtools")
      exclude("com.sun.jmx", "jmxri"),
    "org.specs2" % "specs2_2.9.1" % "1.8.1",
    "org.twitter4j" % "twitter4j-stream" % "3.0.5"
  )
}
