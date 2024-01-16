name := "demo"

version := "0.1"

scalaVersion := "3.3.1"

resolvers ++= Resolver.sonatypeOssRepos("snapshots")

libraryDependencies ++=
  "dev.zio" %% "zio" % "2.1-RC1" ::
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion ::
    Nil

Compile / PB.targets := Seq(
  scalapb.gen(grpc = true) -> (Compile / sourceManaged).value,
  scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value
)
