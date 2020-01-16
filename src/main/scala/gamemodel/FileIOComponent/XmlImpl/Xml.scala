package gamemodel.FileIOComponent.XmlImpl

import com.google.inject.Guice
import gamemodel.FileIOComponent.FileIOInterface
import gamemodel.model.MapInterface

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

  override def save(map: MapInterface): Unit = {
    //Hier könnte ihr Code stehen
  }



}
