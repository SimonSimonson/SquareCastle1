class Game () {

  def start(): Unit= {
    val map = new Map(5,5)


    val karte1 = new Card(0, 2, 1, 3)
    val karte2 = new Card(0, 2, 1, 3)
    karte2.rotate()
    karte2.rotate()

    var er = map.Setcard(karte1,map.mid._1,map.mid._2)

    if(er == -1)
      println("Karte konnte nicht gesetzt werden")
    map.print()
    er = map.Setcard(karte2,map.mid._1,map.mid._2-1)
    if(er == -1)
      println("Karte konnte nicht gesetzt werden")
    map.print()
  }
}