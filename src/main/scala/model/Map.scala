
package main.scala.model

class Map(mx: Int, my: Int){
  val field = Array.ofDim[Card](mx,my)
  val mid = (mx/2,my/2)

  def pruefen(card : Card, x:Int , y : Int): Boolean ={
    //Folgende if Anweisungen schauen rechts, links, über und unter der liegeneden Karte
    //Rechts von liegender
    if(x+1 < mx && field(x+1)(y) != null ) {
      if(!card.anlegen(1,field(x+1)(y)))
        return false
    }

    //Links von liegender
    if(x-1 >= 0 && field(x-1)(y) != null) {
      if(!card.anlegen(3,field(x-1)(y)))
        return false
    }
    //Über von liegender
    if(y+1 < my && field(x)(y+1) != null) {
      if(!card.anlegen(2,field(x)(y+1)))
        return false
    }
    //Unter von liegender
    if(y-1 >= 0 && field(x)(y-1) != null ) {
      if(!card.anlegen(0,field(x)(y-1)))
        return false
    }
    true
  }

  var x = 0
  var y = 0

  def tipp(card: Card): Unit = {
    println(Console.WHITE)
    for (iy <- 0 until my) {
      for (zeile <- 0 to 5) {
        for (ix <- 0 until mx) {
          if(field(ix)(iy) != null)
            field(ix)(iy).printline(zeile)
          else{
            if(pruefen(card,ix,iy))
              highlight(zeile)
            else
              nullprint(zeile)
          }
        }
        println()
      }

    }
    /*
            x = x + 1
            if (x == mx) {
              y = y + 1
              x = 0
            }*/
  }


  def Setcard(card : Card, x:Int , y : Int): Int ={
    if(x > mx-1 || y > my-1)
      return -1

    if(field(x)(y) != null)
      return -1


    //methode prüfen aufrufen!
    if(pruefen(card, x, y)){
      field(x)(y) = card
      return 1
    } else {
      //println("Die Karte passt nicht!")
      return -1
    }

    return 1
  }

  def nullprint(zeile: Int): Unit = {
    zeile match {

      case 0 => printf(" _________ ")
      case 1 => printf("|         |")
      case 2 => printf("|         |")
      case 3 => printf("|         |")
      case 4 => printf("|         |")
      case 5 => printf("|_________|")

    }
  }

  def highlight(zeile: Int): Unit = {
    zeile match {

      case 0 => printf(Console.YELLOW + " _________ ")
      case 1 => printf(Console.YELLOW + "|         |")
      case 2 => printf(Console.YELLOW + "|         |")
      case 3 => printf(Console.YELLOW + "|         |")
      case 4 => printf(Console.YELLOW + "|         |")
      case 5 => printf(Console.YELLOW + "|_________|")

    }
    printf(Console.WHITE)
  }

  def print(): Unit ={
    println(Console.WHITE)
    for(iy <- 0 until my){
      for(zeile <- 0 to 5){
        for(ix <- 0 until mx){

          if(field(ix)(iy)==null)
            nullprint(zeile)
          else
           field(ix)(iy).printline(zeile)
        }
        println()
      }

    }

  }

}
