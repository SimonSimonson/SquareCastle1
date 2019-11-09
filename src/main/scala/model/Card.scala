package main.scala.model

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

  //REKTUSIVE METHODE, DIE ALLE ANGELEGTEN KARTEN DER 3 ARRAYS OBEN DURCHLÄUFT UND DIE GLEICHEN ZÄHLT
  def getAngelegteR(kind: Int): Int = {
    var count = 0
    println(this)
    println(roads(0)+ " " + roads(1)+ " " + roads(2) +" "+ roads(3))
    println(castle(0)+" " + castle(1)+" "+castle(2)+" "+castle(3))
    println(none(0)+ " "+none(1)+ " "+none(2)+ " "+none(3))

    kind match{
      case 0 => none.indices.foreach( i =>  if(none(i) != null){count += none(i).getAngelegteR(kind)})
      case 1 => roads.indices.foreach( i =>  if(roads(i) != null) {count += roads(i).getAngelegteR(kind)})
      case 2 => castle.indices.foreach( i =>  if(castle(i) != null) {count += castle(i).getAngelegteR(kind)})
    }
    return count

  }
  def getAngelegte(): Int = {
    val kind = 0
    val sum = getAngelegteR(kind) + getAngelegteR(kind+1)*2 +getAngelegteR(kind+2)*3
    return sum
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
    /*
    if((pos - 2) <= 0){
      pos2 = pos + 2
    } else {
      pos2 = pos - 2
    }
    */
  }
  def anlegen(pos:Int, karte:Card): Boolean ={
    //pos ist die Seite der Karte
    //if(roads(pos) == 1 || castle(pos) == 1 || none(pos) == 1 ){
    // println("Da liegt schon eine Karte")
    //  return false
    //}
   var pos2 = getantipos(pos)
    if(mysides(pos) == karte.mysides(pos2)) {
      println("Die Karte passt!")

      mysides(pos) match{
        case 0 => none(pos) = karte
        case 1 => roads(pos) = karte
        case 2 => castle(pos) = karte
      }

      /*
      if (mysides(pos) == 0)
        none(pos) = karte
      if (mysides(pos) == 1)
        roads(pos) = karte
      if (mysides(pos) == 2)
        castle(pos) = karte
      */

      return true
    }
    println("Die Karte passt nicht!")

    false

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
