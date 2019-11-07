case class Map(x: Int, y: Int){
  val field = Array.ofDim[Card](x,y)
  val mid = (x/2,y/2)
  def Setcard(card : Card, x:Int , y : Int): Int ={
    if(field(x)(y) != null)
      return -1
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

        null
    }
  }
  def print(): Unit ={
    for(iy <- 0 until y){
      for(zeile <- 0 to 5){
        for(ix <- 0 until x){

          if(field(ix)(iy)==null)
            nullprint(zeile)
          else
           field(ix)(iy).printline(zeile)
        }
        println()
      }
      println()
    }




  }

}
