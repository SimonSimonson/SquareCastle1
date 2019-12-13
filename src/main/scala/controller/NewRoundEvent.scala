package controller

import scala.swing.event.Event

class NewRoundEvent extends Event {

}
class InsertedEvent extends Event {

}
class DoesntFitEvent extends Event {

}
class PlayerEvent extends Event {

}
class BotEvent extends Event {

}
class GameOverEvent extends Event {

}
class CardChangedEvent extends Event {

}
class TippEvent extends Event {

}
class WaitEvent extends Event {

}
class updateEvent(string: String, int: Int) extends Event {
  val word = string
  val code = int
}