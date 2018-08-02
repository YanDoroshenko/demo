package com.github.yandoroshenko

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.{complete, get, pathPrefix, pathSingleSlash, post, _}
import akka.http.scaladsl.server.Route
import com.github.yandoroshenko.Model._
import spray.json.DefaultJsonProtocol.{jsonFormat1, _}
import spray.json.RootJsonFormat

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 02.08.2018.
  */
object Routes {
  implicit val itemFormat: RootJsonFormat[Item] = jsonFormat1(Item)

  val route: Route = get {
    pathSingleSlash {
      complete("Hello world")
    }
  } ~ post {
    pathPrefix("item" / LongNumber) {
      id => complete(Item(id))
    }
  }
}
