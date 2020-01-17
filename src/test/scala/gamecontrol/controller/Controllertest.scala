package gamecontrol.controller

import java.io.File

import gamecontrol.states.PvP
import gamemodel.model.CardComponent.Card
import gamemodel.model.KIComponent.KI
import gamemodel.model.MapComponent
import gamemodel.model.PlayerComponent.Player
import javax.imageio.ImageIO
import org.scalatest._

class Controllertest extends WordSpec with Matchers {
  "A Controller" when {
    "new" should {
      val c = new Controller
      val state = new PvP
      val map = new MapComponent.Map(4, 4)
      val p1 = new Player("Peter")
      val p2 = new Player("Gandalf")
      val bot = new KI()
      val bot2 = new KI()
      val card = new Card(1, 2, 2, 1)
      val card2 = new Card(1, 2, 2, 1)

      "change state" in {
        c.changeState(state) should be(true)
        c.changeState(null) should be(false)
      }
      "have a state" in {
        c.state should be(state)
      }
      "have a random card" in {
        c.RandomCard() should not be (null)
      }
      "when card is shown" in {
        c.showCard(p1, card) should be(true)
        c.showCard(p1, null) should be(false)
        c.showCard(null,card) should be (true)
      }
      "when Options for the Player are called" in {
        c.befehl = "r"
        c.Options(card, map, p1) should be(2)
        c.befehl = "wait"
        c.Options(card, map, p1) should be(2)
        c.befehl = "l"
        c.Options(card, map, p1) should be(2)
        c.befehl = "i 0 0"
        c.Options(card, map, p1) should be(1)
        c.befehl = "undo"
        c.Options(card, map, p1) should be(2)
        c.befehl = "tipp"
        c.Options(card, map, p1) should be(2)
        c.befehl = "x"
        c.Options(card, map, p1) should be(2)
      }
      "when getPoints" in {
        p1.addPoints(3)
        p2.addPoints(2)
        c.getPoints(p1, p2) should be(3, 2)
      }
      "when tipp is called" in {
        c.tipp(card, null) should be(null)
        c.tipp(card, new MapComponent.Map(4,4)) should not be (null)
      }
      "when print of a card is called" in {
        c.printline(0, card) should be(true)
        c.printline(-3, card) should not be (true)
        c.printline(0, null) should not be (true)
        c.printline(0, card) should be (true)
        c.printline(1, card) should be (true)
        c.printline(2, card) should be (true)
        c.printline(3, card) should be (true)
        c.printline(4, card) should be (true)
        c.printline(5, card) should be (true)




        c.printcard(card) should be(true)
        c.printcard(null) should be(false)

        c.nullprint(3) should be(true)
        c.nullprint(-2) should be(false)
        c.highlight(0) should be(true)
        c.highlight(1) should be(true)
        c.highlight(2) should be(true)
        c.highlight(3) should be(true)
        c.highlight(4) should be(true)
        c.highlight(5) should be(true)
        c.highlight(-1) should be(false)

        c.print(map) should be(true)
        map.field(0)(0) = new Card(2,2,2,2)
        c.print(map) should be(true)

        c.print(null) should be(false)

        c.printpunkte(p1, p2, bot, bot2) should be(true)
        c.printpunkte(null, p2, bot, bot2) should be(true)
        c.printpunkte(null, null, bot, bot2) should be(true)

      }
      "When a bot is Calculated" in {
        val map = new MapComponent.Map(4, 4)
        val p1 = new Player("Peter")
        val p2 = new Player("Gandalf")
        val bot = new KI()
        val bot2 = new KI()
        val card = new Card(1, 2, 2, 1)
        val card2 = new Card(1, 2, 2, 1)
        c.Calculatebot(null, card, map) should be(false)
        map.Setcard(card, 0, 0)
        c.Calculatebot(bot, card2, map) should be(true)
      }
      "when rotate is called" in {
        c.rotatePic(null) should not be (true)
        c.rotatePic(1, null) should not be (true)


        var tmp = ImageIO.read(new File("./src/main/scala/aview/GUI/cardIMG/0000.png"))
        c.rotatePic(tmp) should not be (null)
        c.rotatePic(-1, tmp) should not be (null)
        c.rotatePic(2, tmp) should not be (null)

      }

    }
  }
}


