package com.github.yandoroshenko.demo

import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.{Done, NotUsed}

import scala.concurrent._

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 04.08.2018.
  */
object Application extends App {

  sealed trait Egg

  class Omelet extends Egg

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  def fry = (_: Egg) => new Omelet()

  def eat = (e: Egg) => println(s"Fucking tasky ${e.getClass().getSimpleName()}")

  val source: Source[Egg, NotUsed] = Source(1 to 100 map (_ => new Egg {}))

  val flow: Flow[Egg, Omelet, NotUsed] = Flow.fromFunction(fry)

  val sink: Sink[Omelet, Future[Done]] = Sink.foreach(eat)

  source.via(flow).to(sink).run()

}
