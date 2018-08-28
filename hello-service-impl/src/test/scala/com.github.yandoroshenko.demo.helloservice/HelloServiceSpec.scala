package com.github.yandoroshenko.demo.helloservice

import java.util.UUID

import com.lightbend.lagom.scaladsl.server.{LagomApplication, LocalServiceLocator}
import com.lightbend.lagom.scaladsl.testkit.{ServiceTest, TestTopicComponents}
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import play.api.libs.ws.ahc.AhcWSComponents

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 28.08.2018.
  */
class HelloServiceSpec extends AsyncWordSpec with Matchers with BeforeAndAfterAll {

  private val server = ServiceTest.startServer(ServiceTest.defaultSetup) {
    ctx =>
      new LagomApplication(ctx) with HelloComponents with LocalServiceLocator with AhcWSComponents with TestTopicComponents {
        lazy val helloService = new HelloServiceImpl
      }
  }

  val helloService: HelloService = server.serviceClient.implement[HelloService]

  override def afterAll: Unit = server.stop()

  "The hello service" should {
    val name = UUID.randomUUID().toString()

    "greet from path" in {
      for (greeting <- helloService.hello(name).invoke())
        yield greeting shouldEqual s"Hello, $name!"
    }

    "greet from request" in {
      for (greeting <- helloService.greeting.invoke(name))
        yield greeting shouldEqual s"Hello, $name"
    }
  }
}
