package model

case class Game () {

  def start(p1: Player, p2: Player, runden: Int): Unit= {
    val map = new Map(10,5)
    map.print()


    /*
    val karte1 = new Card(0, 2, 1, 2)
    val karte2 = new Card(0, 2, 1, 2)
    val karte3 = new Card(0, 2, 1, 2)

    //karte2.rotate()
    karte2.rotate()
    //karte2.rotate()

    var er = map.Setcard(karte1,map.mid._1,map.mid._2)
    er = map.Setcard(karte3,map.mid._1+1,map.mid._2-1)

    if(er == -1)
      println("Karte konnte nicht gesetzt werden")
    map.print()


    er = map.Setcard(karte2,map.mid._1,map.mid._2-1)

    if(er == -1)
      println("Karte konnte nicht gesetzt werden")
    map.print()

     */
  }
  def RandomCard(): Card= {
    val r = scala.util.Random
    var s1 = r.nextInt(3)
    var s2 = r.nextInt(3)
    var s3 = r.nextInt(3)
    var s4 = r.nextInt(3)

    return new Card(s1,s2,s3,s4)
  }


}
