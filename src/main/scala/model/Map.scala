
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
//prüft die Karten, legt aber noch nicht an
  def pruefen(card : Card, x:Int , y : Int): Boolean ={
    if(x+1 < mx && field(x+1)(y) != null ) {
      if(!card.passt(field(x+1)(y),1,3))
        return false

    }


    if(x-1 >= 0 && field(x-1)(y) != null) {
      if(!card.passt(field(x-1)(y),3,1)) {
        return false
      }

    }

    if(y+1 < my && field(x)(y+1) != null) {
      if(!card.passt(field(x)(y+1),2,0)) {
        return false
      }

    }

    if(y-1 >= 0 && field(x)(y-1) != null ) {
      if(!card.passt(field(x)(y-1),0,2)) {
        return false
      }
    }
    true
  }

  //gibt die Punkte an der Stelle ohne wirklich anzulegen(für den bot)
  def getpunkteohneanlegen(card: Card,x:Int, y:Int): Int ={
    if(Setcard(card,x,y) > 0){

      val points = card.getAngelegte()
      card.cleanall()
      field(x)(y) = null
      return points
    }
    return -1
  }
  //setzt eine karte an eine random stelle in der map (für den Bot)
  def setRandom(card: Card): Boolean ={
    if(card == null){
      return false
    }
    //println("setze random karte")
    val r = scala.util.Random
    var a = r.nextInt(field(0).length)

    var b = r.nextInt(field(1).length)
    while(Setcard(card,a,b) < 0)
      a = r.nextInt(field(2).length)
      b = r.nextInt(field(1).length)
    return true
  }



  var x = 0
  var y = 0

//Setzt die Karte nach dem Prüfen auf das Spielfeld.
  def Setcard(card : Card, x:Int , y : Int): Int ={
    if(x > mx-1 || y > my-1)
      return -1

    if(field(x)(y) != null)
      return -1


    //methode prüfen aufrufen!
    if(pruefen(card, x, y)){
      field(x)(y) = card
      if(y-1 >= 0)
        card.anlegen(0,field(x)(y-1))
      if(x+1 < mx)
        card.anlegen(1,field(x+1)(y))
      if(y+1 < my)
        card.anlegen(2,field(x)(y+1))
      if(x-1 >= 0)
        card.anlegen(3,field(x-1)(y))
      return 1
    } else {
      //println("Die Karte passt nicht!")
      card.cleanall()
      return -1
    }

    return 1
  }
  def cleanaround(x:Int,y:Int){
    if(x+1 < mx && field(x+1)(y) != null ) {
     field(x+1)(y).cleansides(3)
    }
    if(x-1 >= 0 && field(x-1)(y) != null) {
      field(x+1)(y).cleansides(1)
    }

    if(y+1 < my && field(x)(y+1) != null) {
      field(x+1)(y).cleansides(0)
    }

    if(y-1 >= 0 && field(x)(y-1) != null ) {
      field(x+1)(y).cleansides(2)
    }
  }


}
