package com.github.yandoroshenko.demo.service

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.Service._
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

import scala.concurrent.Future

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 16.06.2018.
  */
trait WordCountService extends Service {
  def hello(id: String): ServiceCall[NotUsed, String]
  def greeting: ServiceCall[String, String]


  override def descriptor: Descriptor = {
    named("service")
      .withCalls(
        restCall(Method.GET, "/api/:id", hello _),
        restCall(Method.POST, "/api", greeting)
      )
  }
}

object WordCountServiceImpl extends WordCountService {
  override def hello(id: String): ServiceCall[NotUsed, String] = ServiceCall(_ => Future.successful(s"Hello, $id!"))

  override def greeting: ServiceCall[String, String] = ServiceCall(r => Future.successful(s"Hello, $r"))
}
