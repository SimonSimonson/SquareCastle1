
package gamemodel.model.MapComponent

import com.google.inject.Inject
import gamemodel.model.{CardInterface, MapInterface}
import javax.inject.Named

import scala.util.{Success, Try}

class Map @Inject()(@Named("num")mx: Int,@Named("num")my: Int) extends MapInterface{
  override var field = Array.ofDim[CardInterface](mx,my)
  override def getmx(): Int ={
    mx
  }
  override def getmy(): Int ={
    my
  }
//prüft die Karten, legt aber noch nicht an
  override def check(card : CardInterface, x:Int, y : Int): Boolean ={
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
  override def getpointswithoutputting(card: CardInterface, x:Int, y:Int): Int ={
    if(Setcard(card,x,y).get != -1){

      val points = card.getAngelegte()
      card.cleanall()
      field(x)(y) = null
      return points
    }
    return -1
  }
  //setzt eine karte an eine random stelle in der map (für den Bot)
  override def setRandom(card: CardInterface): Boolean ={
    if(card == null){
      return false
    }
    //println("setze random karte")
    val r = scala.util.Random
    var a = r.nextInt(field(0).length)

    var b = r.nextInt(field(1).length)
    while(Setcard(card,a,b).get == -1) {
      a = r.nextInt(field(2).length)
      b = r.nextInt(field(1).length)
    }
    return true
  }


  var x = 0
  var y = 0

//Setzt die Karte nach dem Prüfen auf das Spielfeld.
  override def Setcard(card : CardInterface, x:Int , y : Int): Try[Int] ={
    if(x > mx-1 || y > my-1)
      return new Success[Int](-1)

    if(field(x)(y) != null)
      return new Success[Int](-1)


    //methode prüfen aufrufen!
    if(check(card, x, y)){
      field(x)(y) = card
      if(y-1 >= 0)
        card.anlegen(0,field(x)(y-1))
      if(x+1 < mx)
        card.anlegen(1,field(x+1)(y))
      if(y+1 < my)
        card.anlegen(2,field(x)(y+1))
      if(x-1 >= 0)
        card.anlegen(3,field(x-1)(y))
      return Success(1)
    } else {
      //println("Die Karte passt nicht!")
      card.cleanall()
      return new Success[Int](-1)
    }
  }
  override def cleanaround(x:Int,y:Int): Boolean ={
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
    true
  }
  override def isFull(): Boolean={
    for {
      i <- field.indices
      j <- field.indices
    } {
      if (field(i)(j) == null)
        return false
    }
    true
  }

  override def setCell(x: Int, y: Int, card: CardInterface): Unit ={
    field(x)(y) = card
  }

  override def getFieldString(x:Int,y:Int):String={
    val a = field(x)(y)
    if(a!= null)
      return a.toString
    else
      return "4 4 4 4"
  }
}
