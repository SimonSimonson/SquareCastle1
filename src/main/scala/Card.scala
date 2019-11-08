package main.scala

case class Card(side0: Int, side1: Int, side2: Int, side3: Int ) {

  if(side0 > 2  || side1 > 2 || side2 > 2 || side3 > 2 ) {
    println("ungültige Eingabe")
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



  def rotate(): Boolean ={
    val rot:Array[Int] = new Array[Int](4)
    rot(0) = mysides(3)
    rot(1) = mysides(0)
    rot(2) = mysides(1)
    rot(3) = mysides(2)
    mysides = rot

    true
  }
  def anlegen(pos:Int, karte:Card): Boolean ={
    //pos ist die Seite der Karte
    if(roads(pos) == 1 || castle(pos) == 1 || none(pos) == 1 ){
      println("Da liegt schon eine Karte")
      return false
    }
    var pos2 = 0
    if((pos - 2) < 0){
      pos2 = pos + 2
    } else {
      pos2 = pos - 2
    }


    if(mysides(pos) == karte.mysides(pos2)) {
      println("Die Karte passt!")
      if (mysides(pos) == 0)
        none(pos) = karte

      if (mysides(pos) == 1)
        roads(pos) = karte
      if (mysides(pos) == 2)
        castle(pos) = karte

      return true
    }
    println("Die Karte passt nicht!")

    return false

  }

  def CheckifAlone(): Boolean ={
    if(none.isEmpty && roads.isEmpty && castle.isEmpty)
      return true


    false
  }





  def print(): Null ={
    println(" _________")
    println("|         |")
    println("|    " + mysides(0) + "    |")
    println("| "+mysides(3)+"     "+ mysides(1)+" |")
    println("|    " + mysides(2) + "    |")
    println("|_________|")

    null
  }
  def printline(zeile: Int): Unit={
    zeile match{
      case 0 => printf(" _________ ")
      case 1 => printf("|         |")
      case 2 => printf("|    " + mysides(0) + "    |")
      case 3 => printf("| "+mysides(3)+"     "+ mysides(1)+" |")
      case 4 => printf("|    " + mysides(2) + "    |")
      case 5 => printf("|_________|")

    }
  }

  override def equals(that: Any): Boolean = ???
}