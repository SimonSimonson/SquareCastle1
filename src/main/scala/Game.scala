class Game () {

  def start(): Unit= {
    val map = new Map


    val karte = new Card(0, 2, 1, 3)
    karte.print()
    karte.rotate()
    karte.print()



  }
}