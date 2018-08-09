package com.github.yandoroshenko.demo.helloservice

import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, _}
import com.lightbend.rp.servicediscovery.lagom.scaladsl.LagomServiceLocatorComponents
import play.api.Environment
import play.api.libs.ws.ahc.AhcWSComponents

import scala.concurrent.ExecutionContext

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 09.08.2018.
  */
trait HelloComponents extends LagomServerComponents {

  implicit def executionContext: ExecutionContext

  def environment: Environment

  override lazy val lagomServer = serverFor[HelloService](new HelloServiceImpl)
}

class HelloServiceLoader extends LagomApplicationLoader {
  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new LagomApplication(context) with HelloComponents with LagomDevModeComponents with AhcWSComponents {

      lazy val helloService = new HelloServiceImpl

    }

  override def load(context: LagomApplicationContext): LagomApplication =
    new LagomApplication(context) with HelloComponents with LagomServiceLocatorComponents with AhcWSComponents {

      lazy val helloService = new HelloServiceImpl

    }

  override def describeService = Some(readDescriptor[HelloService])
}
