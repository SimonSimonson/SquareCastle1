package gamemodel.model.PlayerComponent

import com.google.inject.Inject
import com.google.inject.name.Named
import gamemodel.model.PlayerInterface

case class Player @Inject()(@Named("name")name: String ) extends PlayerInterface{

    var Points = 0

    def addPoints(punkte: Int ): Unit ={
      Points += punkte
    }
    def getPoints(): Int={
        return Points
    }


    override def toString(): String = name

}
