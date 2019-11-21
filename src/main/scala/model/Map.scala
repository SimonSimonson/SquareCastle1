
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
//llegt zuviele Karten an, auch wenn diese nicht passen
    if(x+1 < mx && field(x+1)(y) != null ) {
      if(!card.anlegen(1,field(x+1)(y)))
        return false

    }


    if(x-1 >= 0 && field(x-1)(y) != null) {
      if(!card.anlegen(3,field(x-1)(y))) {
        card.cleansides(1)
        return false
      }

    }

    if(y+1 < my && field(x)(y+1) != null) {
      if(!card.anlegen(2,field(x)(y+1))) {
        card.cleansides(3)
        card.cleansides(1)
        return false
      }

    }

    if(y-1 >= 0 && field(x)(y-1) != null ) {
      if(!card.anlegen(0,field(x)(y-1))) {
        card.cleansides(1)
        card.cleansides(2)
        card.cleansides(3)
        return false
      }
    }
    true
  }
  //gibt die Punkte an der Stelle ohne wirklich anzulegen
  def getpunkteohneanlegen(card: Card,x:Int, y:Int): Int ={
    if(Setcard(card,x,y)>0){
      //komischer weise werden angelegte karten gezeigt wo keine sind
      //falsche Punkteauswertung
      //akzeptiert sich selbst als angelegte karte

      val points = card.getAngelegte()
      card.cleansides(0)
      card.cleansides(1)
      card.cleansides(2)
      card.cleansides(3)
      field(x)(y) = null
      return points
    }
    return -1
  }
  //setzt eine karte an eine random stelle in der map (für den Bot)
  def setRandom(card: Card): Unit ={
    println("setze random karte")
    val r = scala.util.Random
    var a = r.nextInt(field(0).length)

    var b = r.nextInt(field(1).length)
    while(Setcard(card,a,b) < 0)
      a = r.nextInt(field(2).length)
      b = r.nextInt(field(1).length)



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
