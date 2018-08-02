package com.github.yandoroshenko

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.github.yandoroshenko.Routes._

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 02.08.2018.
  */
object Service extends App {
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  Http().bindAndHandle(route, "localhost", 8000)
}
