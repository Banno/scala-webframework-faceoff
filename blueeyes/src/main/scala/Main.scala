package com.banno
import blueeyes.BlueEyesServer
import blueeyes.BlueEyesServiceBuilder
import blueeyes.concurrent.Future
import blueeyes.core.http.HttpResponse

trait HelloService extends BlueEyesServiceBuilder {
  val helloService = service("hello", "1.0") { context =>
    request {
      path("/hello/'name") {
        get { request =>
          val name = request.parameters('name)
          Future(HttpResponse(content = Some(("Hello " + name).getBytes())))
        }
      }
    }
  }
}

object AppServer extends BlueEyesServer with HelloService
