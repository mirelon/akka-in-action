package com.goticks

import akka.actor._

import spray.routing._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.language.postfixOps

class RestInterface extends HttpServiceActor
                    with RestApi {
  def receive = runRoute(routes)
}

trait RestApi extends HttpService with ActorLogging { actor: Actor =>
  import context.dispatcher
  import com.goticks.TicketProtocol._
  import spray.json._

  implicit val timeout = Timeout(10 seconds)

  def routes: Route =

    path("events") {
      get { requestContext =>
        println(Event(event = "E", nrOfTickets = 10).toJson)
      }
    }

}
