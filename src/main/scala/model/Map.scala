
package main.scala.model

class Map(mx: Int, my: Int){
  val field = Array.ofDim[Card](mx,my)
  val mid = (mx/2,my/2)
  def getmx(): Int ={
    mx
  }
  def getmy(): Int ={
    my
  }

  def pruefen(card : Card, x:Int , y : Int): Boolean ={

    if(x+1 < mx && field(x+1)(y) != null ) {
      if(!card.anlegen(1,field(x+1)(y)))
        return false
    }


    if(x-1 >= 0 && field(x-1)(y) != null) {
      if(!card.anlegen(3,field(x-1)(y)))
        return false
    }

    if(y+1 < my && field(x)(y+1) != null) {
      if(!card.anlegen(2,field(x)(y+1)))
        return false
    }

    if(y-1 >= 0 && field(x)(y-1) != null ) {
      if(!card.anlegen(0,field(x)(y-1)))
        return false
    }
    true
  }

  var x = 0
  var y = 0




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



}
