package com.github.yandoroshenko.demo

import com.github.yandoroshenko.demo.service.{WordCountService, WordCountServiceImpl}
import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader, LagomServer}
import play.api.libs.ws.ahc.AhcWSComponents

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 14.06.2018.
  */
class AppLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext): LagomApplication = new LagomApplication(context) with AhcWSComponents {
    override def lagomServer: LagomServer = serverFor[WordCountService](WordCountServiceImpl)

    override def serviceLocator: ServiceLocator = NoServiceLocator
  }
}
