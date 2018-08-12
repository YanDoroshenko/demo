package controllers

import com.github.yandoroshenko.demo.helloservice.HelloService
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.ExecutionContext

/**
  * Created by Yan Doroshenko (yandoroshenko@protonmail.com) on 12.08.2018.
  */
class Main(
            helloService: HelloService,
            controllerComponents: ControllerComponents
          )(implicit ec: ExecutionContext)
  extends AbstractController(controllerComponents) {
  
  def index = Action {
    Ok(views.html.index())
  }

  def hello(greeting: String) = Action.async {
    helloService.hello(greeting).handleRequestHeader(a => a).invoke().map(s => Ok(views.html.hello(s)))
  }
}
