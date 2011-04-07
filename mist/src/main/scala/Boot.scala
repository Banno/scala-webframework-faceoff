package com.banno

import akka.actor.Actor
import Actor._
import akka.actor.Supervisor
import akka.config.Supervision._
import akka.http._

class Boot {
  // start the root service actor (and any service actors you want to specify supervision details for)
  Supervisor(
    SupervisorConfig(
      OneForOneStrategy(List(classOf[Exception]), 3, 100),
      List(
        Supervise(actorOf[RootEndpoint], Permanent)
      )
    )
  )

  Actor.registry.actorFor[RootEndpoint].get ! Endpoint.Attach((str) => true,
                                                              (str) => actorOf[HelloActor].start)
  
}

class HelloActor extends Actor {
  def receive = {
    case get: Get => get OK "it works"
  }
}
