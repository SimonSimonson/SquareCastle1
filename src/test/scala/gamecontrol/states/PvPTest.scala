package gamecontrol.states

import gamecontrol.controller.Controller
import gamemodel.model.CardComponent.Card
import gamemodel.model.MapComponent.Map
import gamemodel.model.PlayerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class PvPTest extends WordSpec with Matchers {

  "Pvp" when { "new" should {
    val B = new PvP
    val c = new Controller
    val playerA = new Player("p")
    val playerB = new Player("p")
    val map = new Map(4,4)
    val Card = new Card(0,1,1,0)
    "When handle is called" in{
      c.befehl = "r"
      B.handle(true,c, playerA,playerB, null,null, map, Card) should be (2)
      B.handle(false,c, playerA,playerB, null,null, map, Card) should be (2)
      B.handle(true,null,null,null, null,null,null,null) should be (-1)
      B.handle(false,null,null,null, null,null,null,null) should be (-1)

    }

  }
  }
}