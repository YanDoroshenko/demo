package com.github

import akka.http.scaladsl.server.Directives.{complete, get, pathSingleSlash}
import akka.http.scaladsl.server.Route

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 02.08.2018.
  */
package object yandoroshenko {
  val route: Route = get {
    pathSingleSlash {
      complete("Hello world")
    }
  }
}
