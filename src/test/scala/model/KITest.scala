package test.model
import main.scala.model
import main.scala.model.Card
import main.scala.model.Map
import main.scala.model.KI
import org.scalatest.
_
class KITest extends WordSpec with Matchers {

  "A Bot" when { "new" should {
    val bot1 = new KI
  "have Points"  in {
    bot1.Punkte should be (0)
}
  "when addPoints"  in {
    bot1.addPoints(1) should be (true)
    bot1.addPoints(-1) should be (false)
    bot1.Punkte should be (1)
}
  "when getPoints"  in {
    bot1.getPoints() should be (1)
}
  "when Punktefeld " in {
    val karte1 = Card(0,2,1,2)
    val m1 = new Map(2,2)
    val pf = bot1.punktefeld(m1,karte1)
    pf(0)(0) should be (0)
}
  "when anlegen" in {
    val karte1 = Card(0,2,1,2)
    val karte2 = Card(0,1,2,1)
    val m2 = new Map(2,2)
    bot1.anlegen(m2,karte1) should be (-1,-1,-1)
}
  "when toString" in{
    bot1.toString() should be ("BOT")

  }
}}
}

