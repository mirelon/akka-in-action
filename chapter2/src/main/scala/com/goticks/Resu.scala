package com.goticks

import akka.actor.Actor

class Resu extends Actor {
  import TicketProtocol._
  import spray.json._

  def receive = {

    case GetEvents => {
      println(Event(event = "E").toJson)
    }
  }
}

object TicketProtocol {
  import spray.json._

  case class Event(event:String)

  case object GetEvents

  case class Events(events:List[Event])

  object Event extends DefaultJsonProtocol {
    implicit val format = jsonFormat1(Event.apply)
  }

}


