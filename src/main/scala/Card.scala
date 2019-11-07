case class Card(side1: Int, side2: Int, side3: Int, side4: Int ) {

  if(side1 > 3  || side2 > 3 || side3 > 3 || side4 > 3 ) {
    println("ungültige Eingabe")
    throw new Exception
  }
  //STRUKTUR DER KARTE
  var mysides:Array[Int] = new Array[Int](4)
  mysides(0)= side1;
  mysides(1)= side2;
  mysides(2)= side3;
  mysides(3)= side4;

  //ANGELEGTE KARTEN UND WAS ANGELEGT IST
  val none:Array[Int] = new Array[Int](4)
  val roads:Array[Int] = new Array[Int](4)
  val castle:Array[Int] = new Array[Int](4)



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
      if (mysides(pos) == 0)
        none(pos) = 1

      if (mysides(pos) == 1)
        roads(pos) = 1
      if (mysides(pos) == 2)
        castle(pos) = 1

      return true
    }

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

  override def equals(that: Any): Boolean = ???
}