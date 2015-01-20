package com.goticks

object TicketProtocol {
  import spray.json._

  case class Event(event:String, nrOfTickets:Int)

  //----------------------------------------------
  // JSON
  //----------------------------------------------

  object Event extends DefaultJsonProtocol {
    implicit val format = jsonFormat2(Event.apply)
  }

}


