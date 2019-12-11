package GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import scala.io.Source._
import java.awt.image.BufferedImage

import javax.swing.{JFrame, JPanel, JScrollPane}
import java.io.File

import javax.imageio.ImageIO
import javax.swing.ImageIcon
import java.awt.Image

import controller.Controller
import javax.swing.JOptionPane
import javax.swing.JPasswordField
import javax.swing.JTextField
import supervisor.supervisor


class GUI(supervisor:supervisor, controller: Controller) extends MainFrame {

  title = "Square Castle"
  background = java.awt.Color.WHITE
  preferredSize = new Dimension(1000, 700)
  //val castleIMG = ImageIO.read(new File("/Users/julian/Desktop/SE/SquareCastle/src/main/scala/GUI/graphics/SQ2.png"))
  var cells: Array[Array[GuiCell]] = Array.ofDim[GuiCell](10, 10)
  /*val panel = new Panel {
    override def paint(g: Graphics2D): Unit = {
      g.drawImage(castleIMG, 200, 225, null)
    }
  }*/

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

  def grid: GridPanel = new GridPanel(cells.length, cells.length) {
    //border = LineBorder(java.awt.Color.GREEN.darker(), 3)
    background = java.awt.Color.BLACK
    for {
      line <- cells.indices
      row <- cells.indices
    } {
      val guicell = new GuiCell(line, row, supervisor, controller)
      cells(line)(row) = guicell
      listenTo(guicell)
      contents += guicell
    }
  }

  println(cells(1)(1))
  //draw()
  visible = true

  def draw(): Unit = {
    for {
      line <- cells.indices
      row <- cells.indices
    } cells(line)(row).redrawCell
    repaint
  }


  var statusPanel = new GridPanel(1, 1) {

    var n = new JFrame()
    n.setSize(new Dimension(30, 30))

    //var statusline = new TextField(controller.gameStatus.toString, 1)
    var nameField = new TextField("Name")
    nameField.preferredSize = new Dimension(45, 30)
    nameField.font = new Font("Verdana", 1, 35)
    //nameField.columns = 45
    nameField.horizontalAlignment = Alignment.Center
    nameField.editable = false
    nameField.background = java.awt.Color.WHITE


    //var credits = new TextField(controller.players("Punkte: " + controller.gameStatus.id).credits.toString, 1)
    var pointField = new TextField("Punkte: ")
    //pointField.preferredSize = new Dimension(45, 30)
    pointField.font = new Font("Dialog", 1, 35)
    //pointField.columns = 45
    pointField.foreground = java.awt.Color.GREEN.darker().darker()
    pointField.editable = false
    pointField.horizontalAlignment = Alignment.Center
    pointField.background = java.awt.Color.WHITE


    var cardField = new TextField("CARD")
    //cardField.preferredSize = new Dimension(45, 30)
    cardField.font = new Font("Verdana", 1, 35)
    //cardField.columns = 45
    cardField.horizontalAlignment = Alignment.Center
    cardField.background = java.awt.Color.WHITE
    cardField.maximumSize

    contents += nameField
    contents += pointField
    contents += cardField

  }




  val rightPanel = new GridBagPanel() {
    preferredSize = new Dimension(150, 400)
    background = java.awt.Color.WHITE

    val nameLabel = new Label("Player Name")
    val pointsName = new Label("Points:")
    val points = new Label("88")
    val cardLabel = new Label("Your card:")

    val imgPanel = new JPanel()
    imgPanel.setAlignmentX(4)
    imgPanel.setAlignmentY(4)


    nameLabel.background = java.awt.Color.GRAY.brighter().brighter()
    nameLabel.preferredSize = new Dimension(100, 90)
    nameLabel.verticalAlignment = Alignment.Center
    nameLabel.verticalTextPosition = Alignment.Bottom

    pointsName.background = java.awt.Color.WHITE
    //pointsName.preferredSize = new Dimension(100, 90)
    pointsName.verticalAlignment = Alignment.Center
    pointsName.verticalTextPosition = Alignment.Bottom

    points.background = java.awt.Color.WHITE
    points.preferredSize = new Dimension(100, 90)
    points.verticalAlignment = Alignment.Center
    points.verticalTextPosition = Alignment.Bottom

    cardLabel.background = java.awt.Color.WHITE
    cardLabel.preferredSize = new Dimension(100, 90)
    cardLabel.verticalAlignment = Alignment.Center
    cardLabel.verticalTextPosition = Alignment.Bottom

    nameLabel.verticalAlignment = Alignment.Top
    nameLabel.font = new Font("Verdana", 1, 20)
    pointsName.font = new Font("Verdana", 1, 20)
    points.font = new Font("Verdana", 1, 15)
    cardLabel.font = new Font("Verdana", 1, 20)


    add(nameLabel, constraints(0, 2, 1, 1, 0, 0.1))

    add(points, constraints(0, 5, 1, 1, 0, 0.1))

    add(pointsName, constraints(0, 5, 1, 1, 0, 0.1))

    add(cardLabel, constraints(0, 8, 1, 1, 0, 0.1))


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






















  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(scala.swing.Action("PvP") {
        val mapX = new JTextField
        val mapY = new JTextField
        val rundenAnzahl = new JTextField
        val player1 = new JTextField
        val player2 = new JTextField

        val message = Array(" Set mapsize (Bsp: X*Y): ", " x", mapX, " y", mapY , "   ", " Set number of Rounds:", rundenAnzahl, "   ", " Player 1:", player1, "   ", " Player 2:", player2)
        val option: Int = JOptionPane.showConfirmDialog(null, message, "SquareCastle", JOptionPane.OK_CANCEL_OPTION)

        val intX = mapX.getText().toInt
        val intY = mapY.getText().toInt
        //supervisor.map = new Map(intX, intY)
      })
      contents += new MenuItem(scala.swing.Action("PvBot") {
        val mapSize = new JTextField
        val rundenAnzahl = new JTextField
        val player1 = new JTextField
        val message = Array(" Set mapsize (Bsp: 2x5): ", mapSize, " Set number of Rounds:", rundenAnzahl, " Player:", player1)
        val option: Int = JOptionPane.showConfirmDialog(null, message, "SquareCastle", JOptionPane.OK_CANCEL_OPTION)
      })
      contents += new MenuItem(scala.swing.Action("Change Playernames") {
        var input = JOptionPane.showInputDialog(
          null,
          "Player One",
          "Change Names",
          JOptionPane.QUESTION_MESSAGE
        )
        input = JOptionPane.showInputDialog(
          null,
          "Player Two",
          "Change Names",
          JOptionPane.QUESTION_MESSAGE
        )
      })
      contents += new MenuItem(scala.swing.Action("Test"){

        var input = JOptionPane.showInputDialog(
          null,
          "Set Mapsize (Bsp.: 2x5)",
          "SquareCastle",
          JOptionPane.QUESTION_MESSAGE
        )
        input = JOptionPane.showInputDialog(
          null,
          "Set name for Player 1",
          "SquareCastle",
          JOptionPane.QUESTION_MESSAGE
        )
        input = JOptionPane.showInputDialog(
          null,
          "Set name for Player 2",
          "SquareCastle",
          JOptionPane.QUESTION_MESSAGE
        )
        input = JOptionPane.showInputDialog(
          null,
          "Set number of rounds",
          "SquareCastle",
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

    add(menuBar, BorderPanel.Position.North)
    //add(statusPanel, BorderPanel.Position.East)
    add(actionPanel, BorderPanel.Position.West)
    //add(gridPanel, BorderPanel.Position.Center)
    add(rightPanel, BorderPanel.Position.East)

  }
  centerOnScreen
  visible = true



  /*
  reactions += {
    case event: GladChanged => redraw()
    case event: PlayingFieldChanged => redraw()
    case event: GameStatusChanged => refreshStatus
    case event: GameOver =>
      val string = controller.players(controller.gameStatus.id)
      JOptionPane.showMessageDialog(
        null,
        controller.players(controller.gameStatus.id) + " won",
        "Game Over",
        JOptionPane.INFORMATION_MESSAGE
      )
      redraw()
    case event: CellClicked =>
      redraw()
      if (controller.checkGladiator(controller.selectedCell._1, controller.selectedCell._2)) {
        val selectedGlad: Gladiator = controller.getGladiator(controller.selectedCell._1, controller.selectedCell._2)
        if (!selectedGlad.moved && selectedGlad.player == controller.players(controller.gameStatus.id)) {
          for {
            i <- 0 until controller.playingField.size
            j <- 0 until controller.playingField.size
          } {
            if (controller.checkMovementPoints(selectedGlad, selectedGlad.line, selectedGlad.row, i, j)
              && controller.checkforPalm(selectedGlad.line, selectedGlad.row, i, j)) {
              if (controller.checkCellEmpty(i, j)) {
                cells(i)(j).setHighlightedSand()
              }
            }
            if (controller.checkMovementPointsAttack(selectedGlad, selectedGlad.line, selectedGlad.row, i, j)) {
              if //(cells(i)(j).myCell.cellType != CellType.PALM &&
              (!(i == selectedGlad.line && j == selectedGlad.row)) {
                // && !((i, j) == controller.getBase(controller.players(controller.gameStatus.id)))) {
                cells(i)(j).cell.border = LineBorder(java.awt.Color.RED.darker(), 4)
              }
            }

          }
        }
      }*/

}
