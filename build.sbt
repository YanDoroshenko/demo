name := "demo"

version := "0.1"

scalaVersion := "2.11.12"

enablePlugins(LagomScala)

libraryDependencies ++=
  lagomScaladslApi ::
    Nil

lagomCassandraEnabled := false
lagomKafkaEnabled := false