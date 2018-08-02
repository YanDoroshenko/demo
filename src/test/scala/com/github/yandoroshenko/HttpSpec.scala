package com.github.yandoroshenko

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.github.yandoroshenko.Model._
import com.github.yandoroshenko.Routes._
import org.scalatest.{Matchers, WordSpec}

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 02.08.2018.
  */
class HttpSpec extends WordSpec with Matchers with ScalatestRouteTest {

  "Service" should {
    "respond on a specified port" in {
      Get() ~> route ~> check {
        status shouldEqual OK
      }
    }

    "respond with a correct message" in {
      Get() ~> route ~> check {
        responseAs[String] shouldEqual "Hello world"
      }
    }

    "respond the POST request" in {
      Post(s"/item/123") ~> route ~> check {
        status shouldEqual OK
      }
    }

    "return a given ID on post" in {
      val i = Math.random().longValue()
      Post(s"/item/$i") ~> route ~> check {
        responseAs[Item] shouldEqual Item(i)
      }
    }
  }
}
