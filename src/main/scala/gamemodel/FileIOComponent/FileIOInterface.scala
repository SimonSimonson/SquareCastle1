package gamemodel.FileIOComponent

import gamemodel.model.MapInterface


trait FileIOInterface {

  def load: MapInterface
  def save (map: MapInterface): Unit

}
