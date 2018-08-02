name := "demo"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++=
  "com.typesafe.akka" %% "akka-stream" % "2.5.14" ::
    "com.typesafe.akka" %% "akka-http" % "10.1.3" ::
    "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.3" ::
    "com.typesafe.akka" %% "akka-http-testkit" % "10.1.3" % Test ::
    "org.scalatest" %% "scalatest" % "3.0.5" % Test ::
    Nil

enablePlugins(JavaAppPackaging)

dockerBaseImage := "openjdk"
dockerExposedPorts ++= Seq(8000)
