package GUI

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import controller.Controller
import javax.imageio.ImageIO
import main.scala.model.Card
import scala.swing.Swing.LineBorder
import scala.swing.event.MouseClicked
import scala.swing.{BorderPanel, Dimension, Font, GridPanel, Label}
import javax.swing.ImageIcon
import supervisor.supervisor


case class GuiCell(x: Int, y: Int, supervisor: supervisor, controller: Controller) extends GridPanel(1, 1) {
  preferredSize = new Dimension(50, 150)
  background = java.awt.Color.WHITE
  var myCard: Card = _
  var myPicture: BufferedImage =_
  val path = "./src/main/scala/GUI/cardIMG/"
  //val path =  "/home/simon/IdeaProjects/SquareCastle1/src/main/scala/GUI/cardIMG/"

  val label: Label =
    new Label {
      // text = getCellText
      foreground = java.awt.Color.YELLOW
      font = new Font("Verdana", 1, 10)
      //horizontalAlignment = Alignment.Center
    }


  val cell: BorderPanel = new BorderPanel() {
    add(label, BorderPanel.Position.Center)
    preferredSize = new Dimension(80, 80)
    listenTo(mouse.clicks)
    //listenTo(controller)

    reactions += {
      case MouseClicked(src, pt, mod, clicks, pops) =>
        //println("X:   "+ x + " |Y:   "+y)
        controller.befehl = ("i " + x + " " + y)

        if(supervisor.newRoundactive() != 2){
          myCard = supervisor.card
          setCellPicture
          border = LineBorder(java.awt.Color.GREEN.darker(), 4)
          supervisor.otherplayer()
          supervisor.newRound()
        }
        //redrawCell


    }
  }
  //FALSCHE RUNDENANZAHL, SPIELER DER GEWINNT FALSCH ANGEZEIGT, BOT REPARIEREN

  def setCard(card:Card): Unit ={
    myCard=card
    //redrawCell
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
  def highlight():Unit={
    contents.clear()
      myPicture = ImageIO.read(new File(path + "Tipp.png"))
      label.icon = new ImageIcon(myPicture.getScaledInstance(label.size.width, label.size.height, Image.SCALE_SMOOTH))
      contents.clear()
      contents += cell
    repaint
  }

  def setCellPicture: Unit = {
    if(supervisor.card == null || myCard == null){
      myPicture = ImageIO.read(new File(path + "Empty.png"))
      label.icon= new ImageIcon(myPicture)
      //println("KEINE KARTE GEFUNDEN")
      return
    }
    //CODE DER DIE BILDER ZUORDNET
    val numbers = (myCard.mysides(0),myCard.mysides(1),myCard.mysides(2),myCard.mysides(3))
    var tmp = findImage(numbers)
    var numrotates = 0
    var num = numbers
    while (tmp == null && numrotates < 3) {
      num = rotateNumbers(num)
      tmp = findImage(num)
      numrotates += 1
    }
    if(tmp != null) {
      tmp = controller.rotatePic(numrotates, tmp)
      myPicture = tmp
      try {
        var dimg = myPicture.getScaledInstance(label.size.width, label.size.height, Image.SCALE_SMOOTH)
        label.icon= new ImageIcon(dimg)
        return
      }catch {
        case e =>label.icon = new ImageIcon(myPicture)
      }

    }
    //println("kein passendes Bild")
  }

  def findImage(x: (Int, Int, Int, Int)): BufferedImage = {
    x match {
      case (0, 0, 0, 0) => ImageIO.read(new File(path + "0000.jpeg"))
      case (0, 0, 1, 0) => ImageIO.read(new File(path + "0010.png"))
      case (0, 0, 1, 1) => ImageIO.read(new File(path + "0011.png"))
      case (2, 0, 0, 1) => ImageIO.read(new File(path + "2001.png"))
      case (2, 1, 0, 0) => ImageIO.read(new File(path + "2100.png"))
      case (1, 0, 1, 0) => ImageIO.read(new File(path + "1010.png"))
      case (0, 1, 1, 1) => ImageIO.read(new File(path + "0111.png"))
      case (2, 0, 1, 2) => ImageIO.read(new File(path + "2012.png"))
      case (0, 2, 0, 1) => ImageIO.read(new File(path + "0201.png"))
      case (0, 2, 0, 2) => ImageIO.read(new File(path + "0202.png"))
      case (0, 2, 1, 2) => ImageIO.read(new File(path + "0212.png"))
      case (2, 1, 0, 2) => ImageIO.read(new File(path + "2102.png"))
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
      case _ => null
    }
  }

  def rotateNumbers(x: (Int, Int, Int, Int)): (Int, Int, Int, Int) = {
    (x._2, x._3, x._4, x._1)
  }

  // 0100 , 1000, 0001, 0010

}
