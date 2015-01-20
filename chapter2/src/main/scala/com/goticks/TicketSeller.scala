package com.goticks


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


