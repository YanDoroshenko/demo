package com.github.yandoroshenko

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 02.08.2018.
  */
class HttpSpec extends WordSpec with Matchers with ScalatestRouteTest {

  "Service" should {
    "respond on a specified port" in {
      Get() ~> route ~> check {
        status shouldEqual StatusCodes.OK
      }
    }

    "respond with a correct message" in {
      Get() ~>route ~>check {
        responseAs[String] shouldEqual "Hello world"
      }
    }
  }
}
