package com.goticks

import akka.actor.Actor

class TicketSeller extends Actor {
  import TicketProtocol._
  import spray.json._

  def receive = {

    case GetEvents => {
      println(Event(event = "E", nrOfTickets = 10).toJson)
    }

  }
}

object TicketProtocol {
  import spray.json._

  case class Event(event:String, nrOfTickets:Int)

  case object GetEvents

  case class Events(events:List[Event])

  //----------------------------------------------
  // JSON
  //----------------------------------------------

  object Event extends DefaultJsonProtocol {
    implicit val format = jsonFormat2(Event.apply)
  }

}


