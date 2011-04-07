package com.banno
import akka.actor.Actor
import Actor._
import akka.actor.Supervisor
import akka.config.Supervision._
import cc.spray._
import utils.ActorHelpers._

class Boot {
  // start the root service actor (and any service actors you want to specify supervision details for)
  Supervisor(
    SupervisorConfig(
      OneForOneStrategy(List(classOf[Exception]), 3, 100),
      List(
        Supervise(actorOf[RootService], Permanent)
      )
    )
  )

  actor[RootService] ! Attach(HttpService(HelloService.service))
}

object HelloService extends ServiceBuilder {
  val service = {
    pathPrefix("hello" / "\\w+".r) { name =>
      get {
        _.complete("Hello " + name)
      }
    }
  }
}
