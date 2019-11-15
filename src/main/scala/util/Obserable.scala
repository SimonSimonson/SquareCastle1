package main.scala.util

//import scala.swing.event.Event

trait Observer {
  def update(string: String): Unit
}

class Observable {
  var subscribers: Vector[Observer] = Vector()

  def add(s: Observer): Unit = subscribers = subscribers :+ s

  def remove(s: Observer): Unit =
    subscribers = subscribers.filterNot(o => o == s)
    //beide parameter
  def notifyObservers(string: String): Unit = subscribers.foreach(o => o.update(string))
}
