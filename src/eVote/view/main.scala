/*Projet Scala*/
package eVote.view
import eVote.controler.DBConnexion
import eVote.model.Electeur
import eVote.model.Election
import java.sql.DriverManager
import java.sql.Connection
import eVote.model.Organisateur
import eVote.model.Personne
import scala.swing._
import event._
import java.awt.{ Color, Graphics2D }
import scala.util.Random
import javax.swing.JFrame
import eVote.model.ElectionPresidentielle
import eVote.model.ModeScrutin
import eVote.model.ElectionPresidentielle


object main {
	def main(args: Array[String]):Unit={
		var test = false
		var sLogin=""
		var sMdp=""
		
		val p = new Organisateur()
		while (test==false){
		print("Login: ")
		sLogin = readLine();
		print("Mot de passe: ")
		sMdp = readLine()
		test = p.seConnecter(sLogin, sMdp)
		}
		println("->" + p.nom)
		println("->" + p.prenoms)
		println("->" + p.dateNaissance)
		println("->" + p.adresse)
		println("->" + p.sexe)
		
		
		
		/*
		p.voter(24, 26, 24)
		p.voter(24, 26, 25)
		p.voter(26, 26, 26)
		p.voter(25, 26, 27)
		p.voter(24, 26, 28)
		p.voter(24, 26, 28)
		* 
		*/
		/*
		val o = new Organisateur()
		while (test==false){
		print("Login: ")
		sLogin = readLine();
		print("Mot de passe: ")
		sMdp = readLine()
		test = o.seConnecter(sLogin, sMdp)
		}
		val op = o.validerElection(26)
		var i=0
		for (i<-0 until op.size)println((i+1) + "-" + op.apply(i))
		* 
		*/
		
		/*
		p.ajouterElecteur("electeur2", "electeur2", "NomElecteur2", "PrenomsElecteur2", "", "", "12-12-1985", "M", "11", "75", "56", "15", 2)
		p.ajouterElecteur("electeur3", "electeur3", "NomElecteur3", "PrenomsElecteur3", "", "", "12-12-1985", "M", "11", "75", "56", "15", 2)
		p.ajouterElecteur("electeur4", "electeur4", "NomElecteur4", "PrenomsElecteur4", "", "", "12-12-1985", "M", "11", "75", "56", "15", 2)
		p.ajouterElecteur("electeur5", "electeur5", "NomElecteur5", "PrenomsElecteur5", "", "", "12-12-1985", "M", "11", "75", "56", "15", 2)
		* 
		*/
		/*
		p.ajouterCandidat(26, 24)
		p.ajouterCandidat(26, 25)
		p.ajouterCandidat(26, 26)
		* 
		*/
		
		/*p.bgCreerElectionNationale("Best Burger Ever", 1, "23-11-2014", 1)
		
		val ec = p.listeElectionEnCours
		var i=0
		for (i<-0 until ec.size)println((i+1) + "-" + ec.apply(i))
		
		val ps = new Electeur
		ps.ajouterElecteur("electeur1", "electeur1", "NomElecteur1", "PrenomsElecteur1", "", "", "12-12-1985", "M", "11", "75", "56", "15", 2)
		* 
		*/
		/*
		println("Elections en cours: ")
		val ec = p.listeElectionEnCours("electeur1")
		var i=0
		for (i<-0 until ec.size)println((i+1) + "-" + ec.apply(i))
		* 
		*/
	}
}