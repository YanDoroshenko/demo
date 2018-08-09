package com.github.yandoroshenko.demo.helloservice

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 16.06.2018.
  */
trait HelloService extends Service {

  def hello(id: String): ServiceCall[NotUsed, String]

  def greeting: ServiceCall[String, String]


  final override def descriptor = {

    import Service._

    named("service")
      .withCalls(
        restCall(Method.GET, "/api/:id", hello _),
        restCall(Method.POST, "/api", greeting)
      )
  }
}
