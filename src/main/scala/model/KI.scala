package mainn.scala.model
import main.scala.model.{Card, Map, Player}
import scala.language.postfixOps

case class KI(){
  var Punkte = 0



  def addPoints(punkte: Int): Unit = {
    Punkte += punkte
  }


  def getPoints(): Int = {

    Punkte
  }
  // gibt array mit Punkten für jedes Feld zurück
  def punktefeld(map:Map, card: Card): (Array[Array[Int]])={

    var punkte = Array.ofDim[Int](map.field.length, map.field(1).length)

    for (y <- 0 until map.field(1).length) {
      for (x <- 0 until map.field.length) {
        //prüft und legt an

          //geht nicht weil karte nicht angelegt ist
          punkte(x)(y) = map.getpunkteohneanlegen(card,x,y)


      }
    }
    (punkte)

  }
//gibt maximum des arrays mit index zurück
  def getxy(array: Array[Array[Int]],int: Int): (Int,Int)={
    for (y <- 0 until Array(0).length) {
      for (x <- 0 until array.length) {
        if (array(x)(y) == int) {
          return (x,y)
        }
      }
    }
    (-1,-1)
  }
  //Gibt die rollen zum Perfekten zug zurück
  def getarraymax(array: Array[Array[Array[Int]]]):(Int)={
    var rolls = 0
    var max = 0
    for(i <- 0 to 3){
      var tmp = array(i).flatten.max
      if(tmp > max){
        rolls = i
        max = tmp
      }
    }
    rolls
  }
  //GIBT DIE X,Y KOORDINATEN DER BESTEN FUNKTION UND DIE ANZAHL DER ROLLS ZURÜCK   FEEEHLER: falsche punktezahlen in arrays
  def anlegen(map:Map, card: Card):(Int,Int,Int)= {
    var all = Array.ofDim[Array[Array[Int]]](4)

    all(0)= punktefeld(map,card)
    card.rotateRight()
    println(all(0).flatten.max)
    all(1) = punktefeld(map,card)
    println(all(1).flatten.max)
    card.rotateRight()
    all(2) = punktefeld(map,card)
    println(all(2).flatten.max)
    card.rotateRight()
    all(3) = punktefeld(map,card)
    println(all(3).flatten.max)
    card.rotateRight()



    /* var rollen = getarraymax(all)
     println(rollen)
     var a = getxy(all(rollen),all(rollen).flatten.max)._1
     var b = getxy(all(rollen),all(rollen).flatten.max)._2
     println(a + " " + b)*/
    //(a,b,rollen)
    (0,0,0)
  }

  override def toString(): String = "bot 01001101"

}