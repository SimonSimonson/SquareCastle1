package model.Decorator

trait Easymode {
  def addPoints(punkte: Int ): Unit

  def getPoints(): Int

  def addEasyMode(): Boolean

}
