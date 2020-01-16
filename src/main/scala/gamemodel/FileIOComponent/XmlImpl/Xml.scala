package gamemodel.FileIOComponent.XmlImpl

import com.google.inject.Guice
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.model.MapInterface
import scala.xml._
import scala.io.Source
import scala.xml.{NodeSeq, PrettyPrinter}

class Xml extends FileIOInterface {

  override def load: MapInterface = {

    var map: MapInterface = null
    val file = scala.xml.XML.loadFile("map.xml")

    val injector = Guice.createInjector(new GameModule)

    return map
  }

  def mapToXml(map:MapInterface): Unit ={
    //Hier könnte ihr Code stehen
  }

  override def save(map: MapInterface): Unit = saveString(map)

  def saveString(map: MapInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("map.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(mapToXml(map))
    pw.write(xml.)
    pw.close
  }


}
