package eVote.view

import javax.swing._
import java.awt.Color

class Fenetre{
  private val pan = new JPanel
  private val bouton = new JButton("Se connecter")
  def fenetre():Unit={
	  val fen =  new JFrame
	  fen.setTitle("eVote")
	  fen.setSize(400, 500)
	  fen.setLocationRelativeTo(null)
	  fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
	  
	  pan.setBackground(Color.CYAN)
	  pan.add(bouton )
	  fen.setContentPane(pan)
	  fen.setVisible(true)
	}
}