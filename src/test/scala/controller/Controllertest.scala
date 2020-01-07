package controller
import java.io.File

import controller.states.PvP
import javax.imageio.ImageIO
import javax.management.ObjectInstance
import main.scala.model.{Card, Map, Player}
import main.scala.model.KI
import org.scalatest._

import scala.Some

class Controllertest extends WordSpec with Matchers{
  "A Controller" when { "new" should {
    val c = new Controller
    val state = new PvP
    val map = new Map(4,4)
    val p1 = new Player("Peter")
    val p2 = new Player("Gandalf")
    val bot = new KI()
    val bot2 = new KI()
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
      c.showCard(p1,card) should be (true)
      c.showCard(p1,null) should be (false)
    }
    "when Options for the Player are called" in {
      c.befehl = "r"
      c.Options(card,map,p1) should be (2)
      c.befehl = "wait"
      c.Options(card,map,p1) should be (2)
      c.befehl = "l"
      c.Options(card,map,p1) should be (2)
      c.befehl = "i 0 0"
      c.Options(card,map,p1) should be (1)
      c.befehl = "undo"
      c.Options(card,map,p1) should be (2)
      c.befehl = "tipp"
      c.Options(card,map,p1) should be (2)
      c.befehl = "x"
      c.Options(card,map,p1) should be (0)
    }
    "when getPoints" in {
      p1.addPoints(3)
      p2.addPoints(2)
      c.getPoints(p1,p2) should be (3,2)
    }
    "when tipp is called" in {
      c.tipp(card, null) should be (null)
      //c.tipp(card, null) should not be (true)
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

      c.printpunkte(p1, p2, bot, bot2) should be (true)
      c.printpunkte(null, p2, bot, bot2) should be (true)
    }
    "when rotate is called" in {
      c.rotatePic(null) should not be (true)
      c.rotatePic(1,null) should not be (true)

      var tmp = ImageIO.read(new File("./src/main/scala/GUI/cardIMG/0000.png"))
      c.rotatePic(tmp) should not be (null)
      c.rotatePic(2, tmp) should not be (null)

    }

  }}


}
