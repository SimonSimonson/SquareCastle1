import org.scalatest._


class PlayerTest extends WordSpec with Matchers {

  "A player" when { "new" should {
    val player = Player("Name")
    "have a name"  in {
      player.value should be("Julian")
    }
    "have a nice String representation" in {
      player.toString should be("Julian")
    }
  }}

}