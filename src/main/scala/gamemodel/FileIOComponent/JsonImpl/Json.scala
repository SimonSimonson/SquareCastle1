package JsonImpl

import com.google.inject.Guice
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.model.{CardInterface, MapInterface}

import play.api.libs.json._
import scala.io.Source


class Json extends FileIOInterface {

  override def load: MapInterface = {
    var playingField: MapInterface = null
    val source: String = Source.fromFile("playingfield.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "playingfield" \ "size").get.toString().toInt//.get.toString.toInt
    val injector = Guice.createInjector(new GameModule)

    playingField = injector.getInstance((classOf[MapInterface]))
    playingField.field = Array.ofDim[CardInterface](size,size)
      for (index <- 0 until (size * size)) {
      val line = (json \\ "line")(index).as[Int]
      val row = (json \\ "row")(index).as[Int]
      val cell = (json \\ "cell")(index).as[String]
      val ar = cell.split("\\s+")
      val card = injector.getInstance(classOf[CardInterface])
      card.mysides(0) = ar(0).toInt
      card.mysides(1) = ar(1).toInt
      card.mysides(2) = ar(2).toInt
      card.mysides(3) = ar(3).toInt
      if(ar(0).toInt != 4) {
        playingField.setCell(line, row, card)
        println("KARTE GEFUNDEN "+ card)
      }
      }
    playingField
  }

  override def save(playingField: MapInterface): Boolean = {
    import java.io._
    val pw = new PrintWriter(new File("playingfield.json"))
    pw.write(Json.stringify(fieldToJson(playingField)))
    pw.close()
    true
  }

  def fieldToJson(playingField: MapInterface) = {

    Json.obj(
      "playingfield" -> Json.obj(
        "size" -> JsNumber(playingField.field.length),
        "cells" -> Json.toJson(
          for {
            line <- 0 until playingField.field.length
            row <- 0 until playingField.field.length
          } yield {
            Json.obj(
              "line" -> line,
              "row" -> row,
              "cell" -> Json.toJson(playingField.getFieldString(line,row)))
          }
        )
      )
    )
  }
}