import org.scalatest._
import main.scala.Player


class PlayerTest extends WordSpec with Matchers {

  "A player" when { "new" should {
    val player = Player("Julian")
    "have a name"  in {
      player.name should be("Julian")
    }
    "have a nice String representation" in {
      player.toString should be("Player(Julian)")
    }
  }}

}