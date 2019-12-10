package GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._
import java.awt.image.BufferedImage

import javax.swing.{JFrame, JOptionPane, JPanel, JScrollPane}
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import java.awt.Image


class GUI extends MainFrame {

  title = "Square Castle"
  background = java.awt.Color.WHITE
  preferredSize = new Dimension(1000, 700)

  val castleIMG = ImageIO.read(new File("/Users/julian/Desktop/SE/SquareCastle/src/main/scala/GUI/graphics/SQ2.png"))

  val panel = new Panel {
    override def paint(g: Graphics2D): Unit = {
      g.drawImage(castleIMG, 200, 225, null)
    }
  }








  val actionPanel = new GridBagPanel() {
    preferredSize = new Dimension(110, 400)
    background = java.awt.Color.WHITE

    val header = new Label("Actions")

    val rotateRight = new Button("")
    val rotateLeft = new Button("")
    val tipp = new Button("")
    val pause = new Button("")

    rotateRight.background = java.awt.Color.GRAY.brighter().brighter()
    rotateRight.preferredSize = new Dimension(75, 90)
    rotateRight.verticalAlignment = Alignment.Center
    rotateRight.verticalTextPosition = Alignment.Bottom

    rotateLeft.background = java.awt.Color.WHITE
    rotateLeft.preferredSize = new Dimension(75, 90)
    rotateLeft.verticalAlignment = Alignment.Center
    rotateLeft.verticalTextPosition = Alignment.Bottom

    tipp.background = java.awt.Color.WHITE
    tipp.preferredSize = new Dimension(75, 90)
    tipp.verticalAlignment = Alignment.Center
    tipp.verticalTextPosition = Alignment.Bottom

    pause.background = java.awt.Color.WHITE
    pause.preferredSize = new Dimension(75, 90)
    pause.verticalAlignment = Alignment.Center
    pause.verticalTextPosition = Alignment.Bottom

    val rotateRight_l = new Label("")
    val rotateLeft_l = new Label("")
    val tipp_l = new Label("")
    val pause_l = new Label("")

    header.font = new Font("Verdana", 1, 20)

    rotateRight_l.verticalAlignment = Alignment.Top

    add(header, constraints(0, 0, 1, 1, 0, 0.1))

    add(rotateRight, constraints(0, 1))
    add(rotateRight_l, constraints(0, 2, 1, 1, 0, 0.1))

    add(rotateLeft, constraints(0, 4))
    add(rotateLeft_l, constraints(0, 5, 1, 1, 0, 0.1))

    add(tipp, constraints(0, 7))
    add(tipp_l, constraints(0, 8, 1, 1, 0, 0.1))

    add(pause, constraints(0, 10))
    add(pause_l, constraints(0, 11, 1, 1, 0, 0.1))

    rotateRight.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/rechts.png")))
    rotateLeft.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/links.png")))
    tipp.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/tipp.png")))
    pause.icon = new ImageIcon(ImageIO.read(getClass.getResource("graphics/pause.png")))

    /*
    rotateRight.text = "<html><body>R" + "<br> I" + "<br>G" + "<br>H" + "<br>T" + "</body></html>\""
    rotateLeft.text = "turn left"
    tipp.text = "hint"
    pause.text = "wait"
     */



    listenTo(rotateRight, rotateLeft, tipp, pause)
    //code









    // Quelle : http://otfried.org/scala/index_42.html
    def constraints(x: Int, y: Int,
                    gridwidth: Int = 1, gridheight: Int = 1,
                    weightx: Double = 0.0, weighty: Double = 0.0,
                    fill: GridBagPanel.Fill.Value = GridBagPanel.Fill.None)
    : Constraints = {

      val c = new Constraints
      c.gridx = x
      c.gridy = y
      c.gridwidth = gridwidth
      c.gridheight = gridheight
      c.weightx = weightx
      c.weighty = weighty
      c.fill = fill
      c.anchor = GridBagPanel.Anchor.North
      c

    }
  }





  var statusPanel = new GridPanel(1, 4) {

    var next = new Button("NEXT")
    next.font = new Font("Verdana", 1, 30)
    next.background = java.awt.Color.BLACK
    listenTo(next)


    contents += next
  }







  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(scala.swing.Action("New Game") {
      })
      contents += new MenuItem(scala.swing.Action("Change Playernames") {
        var nameInput = JOptionPane.showInputDialog(
          null,
          "Player One",
          "Change Names",
          JOptionPane.QUESTION_MESSAGE
        )
        nameInput = JOptionPane.showInputDialog(
          null,
          "Player Two",
          "Change Names",
          JOptionPane.QUESTION_MESSAGE
        )

      })
      contents += new Separator()
      contents += new MenuItem(scala.swing.Action("Exit") {
        System.exit(0)
      })
    }
  }


  contents = new BorderPanel {

    add(actionPanel, BorderPanel.Position.West)
    add(statusPanel, BorderPanel.Position.North)
    //add(gridPanel, BorderPanel.Position.Center)
    add(menuBar, BorderPanel.Position.North)
  }


  centerOnScreen
  visible = true

  def getCardIcon(): Unit = {

  }

  def changePlayerName(): Unit = {

  }

}
