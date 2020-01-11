package gamecontrol.states

import gamecontrol.controller.Controller
import gamemodel.model.{Card, KI, Map, Player}
import org.scalatest.{Matchers, WordSpec}

class PvBotTest extends WordSpec with Matchers {
  "A PvBot" when { "new" should {
    val B = new PvBot
    val c = new Controller
    val playerA = new Player("p")
    val bot = new KI
    val map = new Map(4,4)
    val Card = new Card(0,1,1,0)
    "When handle is called" in{
      c.befehl = "r"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)
      B.handle(true,null,null,null, null,null,null,null) should be (-1)
      

      c.befehl = "l"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)
      c.befehl = "wait"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)
      c.befehl = "i 0 0"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (1)
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)
      c.befehl = "undo"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)
      c.befehl = "tipp"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)
      c.befehl = "Peter"
      B.handle(true,c,playerA,null, bot,null,map,Card) should be (2)



      B.handle(false,c,playerA,null, bot,null,map,Card) should be (1)
    }
  }}
}