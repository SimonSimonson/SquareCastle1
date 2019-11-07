class Game () {

  def start(): Unit= {
    val map = new Map(5,5)


    val karte1 = new Card(0, 2, 1, 3)
    val karte2 = new Card(0, 2, 1, 3)

    map.Setcard(karte1,map.mid._1,map.mid._2)
    map.print()

  }
}