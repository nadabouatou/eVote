/*Projet Scala*/
package eVote.view
import eVote.controler.DBConnexion
import eVote.model.Election
import java.sql.DriverManager
import java.sql.Connection
import eVote.model.Personne
import scala.swing._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import javax.swing.JFrame
import eVote.model.ElectionNationale
import eVote.model.ModeScrutin
import eVote.model.ElectionNationale
import eVote.controler.UtilitiesOrganisateur
import eVote.model.Organisateur


object OrganisateurMain {
	def main(args: Array[String]):Unit={
		var test = false
		var sLogin=""
		var sMdp=""
		var c:Int=0
		  
		val p = new Organisateur()
		while (test==false){
		print("Login: ")
		sLogin = readLine();
		print("Mot de passe: ")
		sMdp = readLine()
		test = p.seConnecter(sLogin, sMdp)
		}
		
		val m = new UtilitiesOrganisateur
		while(c!=16){
			c = m.choixMenu	
			m.matchTestPrincipale(c)
		}	
	}
}