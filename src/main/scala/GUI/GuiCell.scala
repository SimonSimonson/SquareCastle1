package GUI

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File

import controller.Controller
import javax.imageio.ImageIO
import main.scala.model.Card

import scala.swing.Swing.LineBorder
import scala.swing.event.MouseClicked
import scala.swing.{BorderPanel, Dimension, Font, GridPanel, Label}
import java.awt.geom.AffineTransform
import java.awt.image.AffineTransformOp

import javax.swing.ImageIcon
import supervisor.supervisor



case class GuiCell(x: Int, y: Int, supervisor: supervisor, controller: Controller) extends GridPanel(1, 1) {
  preferredSize = new Dimension(50, 150)
  background = java.awt.Color.WHITE
  var myCard: Card = _
  var myPicture: BufferedImage =_
  //val path =  "/home/simon/IdeaProjects/SquareCastle1/src/main/scala/GUI/cardIMG/"
  val path =  "/Users/julian/Desktop/SE/SquareCastle/src/main/scala/GUI/cardIMG/"

  val label: Label =
    new Label {
      // text = getCellText
      foreground = java.awt.Color.YELLOW
      font = new Font("Verdana", 1, 10)
      //horizontalAlignment = Alignment.Center
    }


  val cell: BorderPanel = new BorderPanel() {
    add(label, BorderPanel.Position.Center)

    //contents += label
    //contents += hp
    preferredSize = new Dimension(80, 80)

    listenTo(mouse.clicks)
    //listenTo(controller)

    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) =>
        //controller.showCandidates(row, column)
        //supervisor.map.Setcard(supervisor.card, x, y)
        myCard = supervisor.card
        setCellPicture
        border = LineBorder(java.awt.Color.GREEN.darker(), 4)
        redrawCell
    }
  }



  def redrawCell: Unit = {
    setCellPicture

    contents.clear()
    contents += cell
    cell.background = java.awt.Color.WHITE
    cell.border = LineBorder(java.awt.Color.BLACK, 1)
    //BILD IST ZWAR GESPEICHERT ABER NICHT IN DER ZELLE
    //label.text = getCellText
    repaint
  }

  def setCellPicture: Unit = {
    if(supervisor.card == null || myCard == null){
      myPicture = ImageIO.read(new File(path + "Empty.png"))
      return
    }
    //CODE DER DIE BILDER ZUORDNET
    val numbers = (myCard.side0, myCard.side1, myCard.side2, myCard.side3)
    var tmp = findImage(numbers)
    var numrotates = 0
    while (tmp == null) {
      rotateNumbers(numbers)
      tmp = findImage(numbers)
      numrotates += 1
    }
    tmp = rotatePic(numrotates, tmp)
    myPicture = tmp
    val img = new ImageIcon (myPicture)
    label.icon = img
  }


/*
  def setCellTexture: Unit = {
    myCell.cellType.id match {
      case 0 => label.icon = TEXTURE_SAND
      case 1 => label.icon = TEXTURE_PALM
      case 2 =>
        if (line == 0)
          label.icon = resizedTexture("textures/sandcolloseum_small.png", dimWidth, dimHeight - 10)
        else
          label.icon = resizedTexture("textures/sandtemple_small.png", dimWidth, dimHeight - 10)
      case 3 => label.icon = TEXTURE_GOLD
    }
  }
 */








  new ImageIcon(ImageIO.read(getClass.getResource("graphics/rechts.png")))

  def findImage(x: (Int, Int, Int, Int)): BufferedImage = {
    x match {
      case (0, 0, 0, 0) => ImageIO.read(new File(path + "0000.png"))
      case (0, 0, 1, 0) => ImageIO.read(new File(path + "0010.png"))
      case (0, 0, 1, 1) => ImageIO.read(new File(path + "0011.png"))
      case (0, 0, 1, 2) => ImageIO.read(new File(path + "0012.png"))
      case (0, 0, 2, 1) => ImageIO.read(new File(path + "0021.png"))
      case (0, 1, 0, 1) => ImageIO.read(new File(path + "0101.png"))
      case (0, 1, 1, 1) => ImageIO.read(new File(path + "0111.png"))
      case (0, 1, 2, 2) => ImageIO.read(new File(path + "0122.png"))
      case (0, 2, 0, 1) => ImageIO.read(new File(path + "0201.png"))
      case (0, 2, 0, 2) => ImageIO.read(new File(path + "0202.png"))
      case (0, 2, 1, 2) => ImageIO.read(new File(path + "0212.png"))
      case (0, 2, 2, 1) => ImageIO.read(new File(path + "0221.png"))
      case (1, 1, 1, 1) => ImageIO.read(new File(path + "1111.png"))
      case (1, 2, 1, 2) => ImageIO.read(new File(path + "1212.png"))
      case (2, 0, 0, 0) => ImageIO.read(new File(path + "2000.png"))
      case (2, 0, 0, 2) => ImageIO.read(new File(path + "2002.png"))
      case (2, 0, 1, 1) => ImageIO.read(new File(path + "2011.png"))
      case (2, 1, 0, 1) => ImageIO.read(new File(path + "2101.png"))
      case (2, 1, 1, 0) => ImageIO.read(new File(path + "2110.png"))
      case (2, 1, 1, 1) => ImageIO.read(new File(path + "2111.png"))
      case (2, 1, 1, 2) => ImageIO.read(new File(path + "2112.png"))
      case (2, 2, 0, 2) => ImageIO.read(new File(path + "2202.png"))
      case (2, 2, 1, 2) => ImageIO.read(new File(path + "2212.png"))
      case (2, 2, 2, 2) => ImageIO.read(new File(path + "2222.png"))
    }
  }

  def rotateNumbers(x: (Int, Int, Int, Int)): (Int, Int, Int, Int) = {
    (x._4, x._1, x._2, x._3)
  }

  def rotatePic(image:BufferedImage): BufferedImage={
    val transform = new AffineTransform
    transform.rotate(1.5708, image.getWidth / 2, image.getHeight / 2)
    val op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR)
    return op.filter(image, null)
  }
  def rotatePic(rot: Int, image: BufferedImage): BufferedImage = {
    var tmp: BufferedImage = null
    for (i <- 0 to rot)
      tmp = rotatePic(image)
    return tmp
  }

}
