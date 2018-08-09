package com.github.yandoroshenko.demo.helloservice

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 09.08.2018.
  */
class HelloServiceImpl extends HelloService {
  override def hello(id: String): ServiceCall[NotUsed, String] = ServiceCall(_ => Future.successful(s"Hello, $id!"))

  override def greeting: ServiceCall[String, String] = ServiceCall(r => Future.successful(s"Hello, $r"))
}
