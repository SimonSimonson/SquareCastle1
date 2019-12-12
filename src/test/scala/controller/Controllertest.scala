package controller
import javax.management.ObjectInstance
import main.scala.model.{Card, Map, Player}
import mainn.scala.model.KI
import org.scalatest._

import scala.Some

class Controllertest extends WordSpec with Matchers{
  "A Controller" when { "new" should {
    val c = new ControllerTui
    val state = new StateA
    val map = new Map(4,4)
    val p1 = new Player("Peter")
    val p2 = new Player("Gandalf")
    val bot = new KI()
    val card = new Card(1,2,2,1)
    "change state" in {
      c.changeState(state) should be (true)
      c.changeState(null) should be (false)
    }
    "have a state" in {
      c.state should be (state)
    }
    "have a random card" in {
     c.RandomCard() should not be (null)
    }
    "when card is shown" in {
      c.Kartezeigen(p1,card) should be (true)
      c.Kartezeigen(p1,null) should be (false)
    }
    "when Options for the Player are called" in {
      c.befehl = "r"
      c.Optionen(card,map,p1) should be (2)
      c.befehl = "wait"
      c.Optionen(card,map,p1) should be (2)
      c.befehl = "l"
      c.Optionen(card,map,p1) should be (2)
      c.befehl = "i 0 0"
      c.Optionen(card,map,p1) should be (1)
      c.befehl = "undo"
      c.Optionen(card,map,p1) should be (2)
      c.befehl = "tipp"
      c.Optionen(card,map,p1) should be (2)
      c.befehl = "x"
      c.Optionen(card,map,p1) should be (2)
    }
    "when getPoints" in {
      p1.addPoints(3)
      p2.addPoints(2)
      c.getPoints(p1,p2) should be (3,2)
    }
    "when tipp is called" in {
      c.tipp(card, map) should be (true)
      c.tipp(card, null) should not be (true)
    }
    "when print of a card is called" in{
      c.printline(0,card) should be (true)
      c.printline(-3,card) should not be (true)
      c.printline(0,null) should not be (true)

      c.printcard(card) should be (true)
      c.printcard(null) should be (false)

      c.nullprint(3) should be (true)
      c.nullprint(-2) should be (false)

      c.highlight(3) should be (true)
      c.highlight(-1) should be (false)

      c.print(map) should be (true)
      c.print(null) should be (false)

      c.printpunkte(p1, p2, bot) should be (true)
      c.printpunkte(null, p2, bot) should be (false)
    }

  }}


}
