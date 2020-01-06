package controller
import java.io.File

import controller.states.PvP
import javax.imageio.ImageIO
import main.scala.model.{Card, Map, Player}
import main.scala.model.KI
import org.scalatest._
import supervisor.supervisor


class SupervisorTest extends WordSpec with Matchers{
  "A Supervisor" when { "new" should {
    val c = new Controller
    c.befehl = "r"
    val s = new supervisor(c)

    c.state = new PvP
    s.map = new Map(4,4)
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
      s.rounds = 1
      s.newRound() should be (1)
      s.newRound() should be (-1)

    }
    "when newRoundActive is called" in {
      s.rounds = 1
      s.newRoundactive() should be (2)
      c.befehl = "i 0 0"
      s.newRoundactive() should be (1)
    }
    "when showPoints is called" in {
      val supersinnlos = new supervisor(c)
      supersinnlos.showPoints() should be (false)
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
    }
  }}
}
