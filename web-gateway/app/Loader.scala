import com.github.yandoroshenko.demo.helloservice.HelloService
import com.lightbend.lagom.scaladsl.api.{LagomConfigComponent, ServiceAcl, ServiceInfo}
import com.lightbend.lagom.scaladsl.client.LagomServiceClientComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.rp.servicediscovery.lagom.scaladsl.LagomServiceLocatorComponents
import com.softwaremill.macwire._
import controllers.{AssetsComponents, Main}
import play.api.ApplicationLoader.Context
import play.api._
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes

import scala.collection.immutable

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 12.08.2018.
  */
abstract class WebApplication(context: Context)
  extends BuiltInComponentsFromContext(context)
    with AssetsComponents
    with HttpFiltersComponents
    with AhcWSComponents
    with LagomConfigComponent
    with LagomServiceClientComponents {


  override def router: Router = {
    val prefix = "/"
    wire[Routes]
  }

  lazy val helloService = serviceClient.implement[HelloService]

  lazy val controller = wire[Main]

  override lazy val serviceInfo: ServiceInfo = ServiceInfo(
    "web-gateway",
    Map(
      "web-gateway" -> immutable.Seq(ServiceAcl.forPathRegex("(?!/api/).*"))
    )
  )
}

class Loader extends ApplicationLoader {
  override def load(context: Context): Application = context.environment.mode match {
    case Mode.Dev =>
      (new WebApplication(context) with LagomDevModeComponents).application
    case _ =>
      (new WebApplication(context) with LagomServiceLocatorComponents).application
  }
}