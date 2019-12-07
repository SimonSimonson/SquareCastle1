package controller
import org.scalatest._

class Controllertest extends WordSpec with Matchers{
  "A Controller" when { "new" should {
    val c = new Controller
    val state = new StateA
    "change state" in {
      c.changeState(state) should be (true)
      c.changeState(null) should be (false)
    }
    "have a state" in {
      c.state should be (state)
    }
    "have a random card" in {
     // c.RandomCard() should be
    }



  }}


}
