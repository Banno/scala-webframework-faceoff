package com.banno
import unfiltered.netty.cycle.Plan
import unfiltered.netty.Http
import unfiltered.request.{GET, POST}
import unfiltered.request.Path
import unfiltered.request.Seg
import unfiltered.response.{ResponseString, Ok}

object Main {
  def main(args: Array[String]) {
    Http(8080) handler (HelloPlan) run
  }
}

object HelloPlan extends Plan {
  def intent = {
    case POST(Path(Seg("hello" :: name :: Nil))) =>
      Ok ~> ResponseString("Hello " + name)
    case GET(Path(Seg("hello" :: name :: Nil))) =>
      Ok ~> ResponseString("Hello " + name)
    case GET(Path(Seg("goodbye" :: name :: Nil))) =>
      Ok ~> ResponseString("Goodbye " + name)
  }
}
