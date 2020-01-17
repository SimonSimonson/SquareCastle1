package gamecontrol

import gamemodel.model.CardInterface

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
class SaveEvent extends Event {

}
class GameOverEvent extends Event {

}
class CardChangedEvent(card: CardInterface) extends Event {
  val newcard:CardInterface = card
}

class TippEvent extends Event {

}
class WaitEvent extends Event {

}
class updateEvent(string: String, int: Int) extends Event {
  val word = string
  val code = int
}