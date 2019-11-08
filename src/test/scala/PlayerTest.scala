import org.scalatest._


class PlayerTest extends WordSpec with Matchers {

  "A player" when { "new" should {
    val player = Player("Julian")
    "have a name"  in {
      player.name should be("Julian")
    }
    "have a nice String representation" in {
      player.toString should be("Julian")
    }
  }}

}