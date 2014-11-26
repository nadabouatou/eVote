package eVote.view

import eVote.model.Electeur
import eVote.model.Personne
import eVote.controler.UtilitiesElecteur

object ElecteurMain {
	def main(args: Array[String]):Unit={
		var test = false
		var sLogin=""
		var sMdp=""
		var c:Int=0
		  
		val el = Personne("electeur")
		while (test==false){
		print("Login: ")
		sLogin = readLine();
		print("Mot de passe: ")
		sMdp = readLine()
		test = el.seConnecter(sLogin, sMdp)
		}
		
		println("Liste des elections disponibles: ")
		val listEl = el.listeElectionEnCours
			var i=0
		    for (i<-0 until listEl.size)println((i+1) + "-" + listEl.apply(i))
		    readLine
		println("Votre choix: ")
		val choix = readLine.toInt
		println("Liste des candidats de l'élection choisie: ")
		val cList = el.listerCandidat(choix)
			var j=0
		    for (j<-0 until cList.size)println((j+1) + "-" + cList.apply(j))
		readLine
		println("Votre Candidat: ")
		val choixCandidat = readLine.toInt
		el.voter(choixCandidat, choix, el.uid)
		println("Choix enregistré avec succès")
		readLine
	}
}