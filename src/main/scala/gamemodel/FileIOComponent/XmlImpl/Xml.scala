/*

package gamemodel.FileIOComponent.XmlImpl

import com.google.inject.Guice
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.model.CardComponent.Card
import gamemodel.model.{CardInterface, MapInterface}

import scala.io.Source
import scala.xml._
import scala.xml.{NodeSeq, PrettyPrinter}

class Xml extends FileIOInterface {

  override def load: MapInterface = {

    val file = scala.xml.XML.loadFile("map.xml")
    val sizeAttr = (file \ "playingField" \ "@size")
    println("SIZE = "+ sizeAttr.text)
    //val size = sizeAttr.text.toInt
    //val playingField = new Map(size,size)
    val cellNodes = (file \\ "playingField")
    for (x <- cellNodes) {
      try {
        val line: Int = (x \ "@line").text.toInt
        val row: Int = (x \ "@row").text.toInt
      }catch {
        case e => return null
      }



      val cell: String = (x \ "@cell").toString
      val ar = cell.split("\\s+")
      val card = new Card( ar(0).toInt,ar(1).toInt,ar(2).toInt,ar(3).toInt)
      if(ar(0).toInt != 4) {
        //playingField.setCell(line, row, card)
        println("KARTE GEFUNDEN "+ card)
      }//playingField.setCell(line, row, card)
    }
    //playingField
    null
  }

  def fieldToXml(playingField: MapInterface): Elem ={
    <playingField size={playingField.field.size.toString}>
      {
      for {
        line <- 0 until playingField.field.size
        row <- 0 until playingField.field.size
      } yield cellToXml(line, row, playingField.getFieldString(line,row))
      }
    </playingField>
  }

  override def save(playingField: MapInterface): Boolean = {
    saveString(playingField)
    true
  }

  def saveString(playingField: MapInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("map.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(playingField))
    pw.write(xml)
    pw.close
  }

  def cellToXml(line: Int, row: Int, string: String):Elem ={
    <cell line={ line.toString } row={ row.toString }>
      { string }
    </cell>
  }

}

*/
