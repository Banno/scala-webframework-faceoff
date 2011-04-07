package com.banno
import unfiltered.filter.Plan
import unfiltered.jetty.Http
import unfiltered.request.GET
import unfiltered.request.Path
import unfiltered.request.Seg
import unfiltered.response.{ResponseString, Ok}

object Main {
  def main(args: Array[String]) {
    Http(8080) filter (HelloPlan) run
  }
}

object HelloPlan extends Plan {
  def intent = {
    case GET(Path(Seg("hello" :: name :: Nil))) =>
      Ok ~> ResponseString("Hello " + name)
  }
}
