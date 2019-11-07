
object main {
  def main(args: Array[String]): Unit = {
    val karte = new Card(0,2,1,3)

    karte.print()
    karte.rotate()
    karte.print()

  }
}