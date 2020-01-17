package gamecontrol.states

import gamecontrol.controller.Controller
import gamemodel.model.CardComponent.Card
import gamemodel.model.KIComponent.KI
import gamemodel.model.MapComponent.Map
import org.scalatest.{Matchers, WordSpec}

class BotvBotTest extends WordSpec with Matchers {
  "A BotvBot" when { "new" should {
      val B = new BotvBot
      val c = new Controller
      val bot = new KI
      val bot2 = new KI
      val map = new Map(4,4)
      val Card = new Card(0,1,1,0)
      "When handle is called" in{
        B.handle(true,null,null,null, null,null,null,null) should be (-1)
        B.handle(true,c,null,null, bot,bot2,map,Card) should be (1)
        B.handle(false,c,null,null, bot,bot2,map,Card) should be (1)
      }
  }}
}



