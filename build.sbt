version in ThisBuild := "0.1"

scalaVersion in ThisBuild := "2.12.6"

lazy val root = (project in file("."))
  .settings(name := "demo")
  .aggregate(helloServiceApi, helloServiceImpl)
  .settings(commonSettings: _*)

lazy val helloServiceApi = (project in file("hello-service-api"))
  .settings(libraryDependencies += lagomScaladslApi)
  .settings(commonSettings: _*)

lazy val helloServiceImpl = (project in file("hello-service-impl"))
  .dependsOn(helloServiceApi)
  .settings(libraryDependencies ++= Seq(lagomScaladslApi))
  .enablePlugins(LagomScala, SbtReactiveAppPlugin)
  .settings(commonSettings: _*)

def commonSettings: Seq[Setting[_]] = Seq()
