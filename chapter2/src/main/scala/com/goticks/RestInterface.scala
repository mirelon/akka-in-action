package com.goticks

import akka.actor._

import spray.routing._
import spray.http.StatusCodes
import spray.httpx.SprayJsonSupport._
import spray.routing.RequestContext
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

  implicit val timeout = Timeout(10 seconds)
  import akka.pattern.ask
  import akka.pattern.pipe

  def routes: Route =

    path("events") {
      get { requestContext =>
        context.actorOf(Props[Ress]).ask(GetEvents)
      }
    }

}

class Responder(requestContext:RequestContext, ticketMaster:ActorRef) extends Actor with ActorLogging {
  import TicketProtocol._
  import spray.httpx.SprayJsonSupport._

  def receive = {

    case Events(events) =>
      requestContext.complete(StatusCodes.OK, events)
      self ! PoisonPill

  }
}
