
package gamemodel.FileIOComponent.XmlImpl

import com.google.inject.Guice
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.model.{CardInterface, MapInterface}

import scala.io.Source
import scala.xml._
import scala.xml.{NodeSeq, PrettyPrinter}

class Xml extends FileIOInterface {

  override def load: MapInterface = {

    var playingField: MapInterface = null
    val file = scala.xml.XML.loadFile("playingField.xml")
    val sizeAttr = (file \ "playingField" \ "@size")
    val size = sizeAttr.text.toInt

    val injector = Guice.createInjector(new GameModule)
    playingField = injector.getInstance((classOf[MapInterface]))
    val cellNodes = (file \\ "playingField")
    for (playingField <- cellNodes) {
      val line: Int = (playingField \ "@line").text.toInt
      val row: Int = (playingField \ "@row").text.toInt
      val cell: String = (playingField \ "@cell").toString
      val ar = cell.split("")
      val card = injector.getInstance(classOf[CardInterface])
      card.mysides(0) = ar(0).toInt
      card.mysides(1) = ar(1).toInt
      card.mysides(2) = ar(2).toInt
      card.mysides(3) = ar(3).toInt
      playingField.setCell(line, row, card)
    }
    playingField
  }

  def fieldToXml(playingField: MapInterface): Unit ={
    <playingField size={ playingField.field.size.toString }>
      {
      for {
        line <- 0 until playingField.field.size
        row <- 0 until playingField.field.size
      } yield cellToXml(playingField, line, row, card)
      }
    </playingField>
  }

  override def save(playingField: MapInterface): Unit = saveString(playingField)

  def saveString(playingField: MapInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("map.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(playingField))
    pw.write(xml)
    pw.close
  }

  def cellToXml(playingField: MapInterface, line: Int, row: Int, card: CardInterface) ={
    <cell line={ line.toString } row={ row.toString } given={ playingField.setCell(line, row, card).toString } >
      { playingField.setCell(line, row, card) }
    </cell>
  }

}

