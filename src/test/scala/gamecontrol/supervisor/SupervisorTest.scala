package gamecontrol.supervisor

import gamecontrol.controller.Controller
import gamecontrol.states.PvP
import gamemodel.model.CardComponent.Card
import gamemodel.model.KIComponent.KI
import gamemodel.model.MapComponent
import gamemodel.model.PlayerComponent.Player
import org.scalatest._


class SupervisorTest extends WordSpec with Matchers{
  "A Supervisor" when { "new" should {
    val c = new Controller
    c.befehl = "r"
    val s = new supervisor()
    s.controller = c

    c.state = new PvP
    s.map = new MapComponent.Map(4,4)
    s.p1 = new Player("Peter")
    s.p2 = new Player("Gandalf")
    //s.bot = new KI()
    //s.bot2 = new KI()
    s.card = new Card(1,2,2,1)
    s.state = true
    "when otherplayer is called" in {
      s.otherplayer() should be (false)
      s.otherplayer() should be (true)
    }
    "when NewRound is called" in {
      s.rounds = 2
      s.newRound() should be (1)
      s.state = false
      s.newRound() should be (1)
      s.newRound() should be (-1)
      s.p1 = null
      s.p2 = null
      s.newRound() should be (-1)

    }
    "when newRoundActive is called" in {
      s.p1 = new Player("Peter")
      s.p2 = new Player("Gandalf")
      s.rounds = 1
      s.map = new MapComponent.Map(1,1)
      s.newRoundactive() should be (2)
      c.befehl = "i 0 0"
      s.newRoundactive() should be (1)
      s.newRoundactive() should be (2)
    }
    "when showPoints is called" in {
      val supersinnlos = new supervisor()
      supersinnlos.controller = c
      supersinnlos.showPoints() should be (false)
      s.showPoints() should be (true)
      s.otherplayer() should be (true)
    }
    "when endgame is called" in {
      s.p1.addPoints(5)
      s.p2.addPoints(3)
      s.endgame() should be ("Peter GEWINNT")
      s.p2.addPoints(3)
      s.endgame() should be ("Gandalf GEWINNT")
      s.p1.addPoints(1)
      s.endgame() should be ("UNENTSCHIEDEN")
      s.p1 = null
      s.p2 = null
      s.bot = new KI
      s.bot2 = new KI
      s.endgame()




    }
  }}
}
