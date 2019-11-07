case class Map(x: Int, y: Int){
  val field = Array.ofDim[Card](x,y)
  val mid = (x/2,y/2)
  def Setcard(card : Card, x:Int , y : Int): Int ={
    if(field(x)(y) != null)
      return -1
    field(x)(y) = card
    return 1
  }


}
