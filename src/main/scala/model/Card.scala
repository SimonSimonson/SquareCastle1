package main.scala.model

import java.util

case class Card(side0: Int, side1: Int, side2: Int, side3: Int ) {

  if(side0 > 2  || side1 > 2 || side2 > 2 || side3 > 2 ) {
    //println("ungültige Eingabe")

    throw new Exception
  }
  //STRUKTUR DER KARTE
  var mysides:Array[Int] = new Array[Int](4)
  mysides(0)= side0;
  mysides(1)= side1;
  mysides(2)= side2;
  mysides(3)= side3;

  //ANGELEGTE KARTEN UND WAS ANGELEGT IST
  val none:Array[Card] = new Array[Card](4)
  val roads:Array[Card] = new Array[Card](4)
  val castle:Array[Card] = new Array[Card](4)


  //REKTUSIVE METHODE, DIE ALLE ANGELEGTEN KARTEN DER 3 ARRAYS OBEN DURCHLÄUFT UND DIE GLEICHEN ZÄHLT
  def getAngelegteR(kind: Int, l: Int, prev: Card, list: util.ArrayList[Card]): Int = {
    list.add(prev)
    var sum = 0
    kind match{
      case 0 =>  {
        if(none.isEmpty)
          return 1
        for(y <- 0 to 3 if none(y) != null && !list.contains(none(y))) {
          //list.add(none(y))

          sum += none(y).getAngelegteR(kind, l, this, list)
        }
      }
      case 1 =>  {
        if(roads.isEmpty)
          return 1
        for(y <- 0 to 3 if roads(y) != null && !list.contains(roads(y))) {
          //list.add(roads(y))
          //println("Untersuche Object " + this.toString + "  Seite "+ y)
           sum += roads(y).getAngelegteR(kind, l, this, list)

        }
      }
      case 2 =>  {
        if(castle.isEmpty)
          return 1
        for(y <- 0 to 3 if castle(y) != null && !list.contains(castle(y))) {
          //list.add(castle(y))
          //println("Untersuche Object " + this.toString + "  Seite "+ y)
          sum += castle(y).getAngelegteR(kind, l, this, list)
        }
      }
    }
    sum + 1
  }

  def getAngelegte(): Int = {
    var sum = 0
    //println("Wege angelegt: "+ getAngelegteR(1, 0, this) + " Burgen angelegt: " + getAngelegteR(2, 0,this))
    var wege = getAngelegteR(1, 0, null, new util.ArrayList[Card]())

    if(wege == 1)
      wege = 0
    //println("PUNKTE FÜR WEGE : " + wege)


    var burgen = getAngelegteR(2, 0,null, new util.ArrayList[Card]())
    if(burgen == 1)
      burgen = 0
    //println("PUNKTE FÜR BURGEN : " + burgen)

    wege + burgen*2
  }

  def rotateRight(): Boolean ={
    val rotRight:Array[Int] = new Array[Int](4)
    rotRight(0) = mysides(3)
    rotRight(1) = mysides(0)
    rotRight(2) = mysides(1)
    rotRight(3) = mysides(2)
    mysides = rotRight
    true
  }

  def rotateLeft(): Boolean ={
    val rotLeft:Array[Int] = new Array[Int](4)
    rotLeft(0) = mysides(1)
    rotLeft(1) = mysides(2)
    rotLeft(2) = mysides(3)
    rotLeft(3) = mysides(0)
    mysides = rotLeft
    true
  }

  def getantipos(pos:Int): Int ={
    var pos2 = 0
    pos match{
      case 0 => pos2 = 2
      case 1 => pos2 = 3
      case 2 => pos2 = 0
      case 3 => pos2 = 1
    }
    return pos2
  }
  def passt(card: Card, pos1: Int, pos2: Int): Boolean = {
    //println("Vergleiche "+ this.mysides(pos1) + " mit "+ card.mysides(pos2))
    return this.mysides(pos1) == card.mysides(pos2)
  }
  //Schreibt Verbundene Karten in die Instanzvariablen
  def anlegen(pos:Int, karte:Card): Boolean ={

    if(karte != null) {

      var pos2 = getantipos(pos)
      if (this.passt(karte, pos, pos2)) {

        mysides(pos) match {
          case 0 => none(pos) = karte;  karte.none(pos2)=this
          case 1 => roads(pos) = karte; karte.roads(pos2)=this
          case 2 => castle(pos) = karte; karte.castle(pos2)=this
        }
        return true

      }
      //println("Die Karte passt nicht!")
    }
    false
  }

  //bereinigen einer seite
  def cleansides(pos:Int) :Boolean={
    val pos2 = getantipos(pos)
    if(none(pos)!=null )//&& none(pos).none(pos2)==this)
      none(pos).none(pos2)=null
    if(castle(pos)!=null)// && castle(pos).castle(pos2)==this)
      castle(pos).castle(pos2)=null
    if(roads(pos)!=null)// && castle(pos).castle(pos2)==this)
      roads(pos).roads(pos2)=null
    none(pos)=null
    castle(pos)=null
    roads(pos)=null

    true
  }
  def cleanall() : Boolean ={
    cleansides(0)
    cleansides(1)
    cleansides(2)
    cleansides(3)
    true
  }



  //override def equals(card: Card): Boolean = ???
}
