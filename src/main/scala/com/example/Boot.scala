package com.example

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import spray.can.Http

object Boot extends App {
  implicit val system = ActorSystem("spray-todo-system")

  val service = system.actorOf(Props[TodoRoute])

  implicit val timeout = Timeout(20)

  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}