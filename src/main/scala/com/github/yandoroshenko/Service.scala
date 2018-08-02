package com.github.yandoroshenko

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.github.yandoroshenko.Routes._

import scala.concurrent.ExecutionContextExecutor

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 02.08.2018.
  */
object Service extends App {
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = actorSystem.dispatcher

  val server = Http().bindAndHandle(route, "0.0.0.0", 8000).recoverWith {
    case _ => sys.exit(1)
  }

  sys.addShutdownHook(server.map(_.unbind()))
}
