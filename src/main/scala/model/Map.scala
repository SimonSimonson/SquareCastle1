
package main.scala.model

class Map(mx: Int, my: Int){
  val field = Array.ofDim[Card](mx,my)
  val mid = (mx/2,my/2)

  def Setcard(card : Card, x:Int , y : Int): Int ={
    if(x > mx-1 || y > my-1)
      return -1
    if(field(x)(y) != null)
      return -1



    //Folgende if Anweisungen schauen rechts, links, über und unter der liegeneden Karte
    //Rechts von liegender
    if(x+1 < mx &&field(x+1)(y) != null ) {
      println("karte rechts davon")
      if(!field(x + 1)(y).anlegen(3, card))
        return -1
      card.anlegen(1,field(x+1)(y))

    }

    //Links von liegender
    if(x-1 > 0 && field(x-1)(y) != null) {
      println("karte links davon")
      if(!field(x - 1)(y).anlegen(1, card))
        return -1
      card.anlegen(3,field(x-1)(y))

    }
    //Über liegender Karte
    if(y+1 < my && field(x)(y+1) != null) {
      println("karte unter davon")
      if(!field(x)(y+1).anlegen(0,card))
        return -1
      card.anlegen(2,field(x)(y+1))

    }
    //Unter liegender Karte
    if(y-1 > 0 && field(x)(y-1) != null ) {
      println("karte über davon")
      if(!field(x)(y-1).anlegen(2,card))
        return -1
      card.anlegen(0,field(x)(y-1))

    }


    field(x)(y) = card
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