package eVote.controler

import eVote.model.Electeur
import eVote.model.Election
import java.sql.DriverManager
import java.sql.Connection
import eVote.model.Organisateur
import eVote.model.Personne
import eVote.model.ElectionNationale
import eVote.model.ModeScrutin
import eVote.model.ElectionNationale
import eVote.model.ElectionUtilities
import eVote.model.ElectionUtilities
import eVote.model.Candidat



class Utilities {
	def choixMenu:Int={
	  	var choix:Int=0
	  	
	  	println("MENU:")
		println("1 - Ajouter une élection")
		println("2 - Publier les résultats d'une élection")
		println("3 - Ajouter des candidats à une élection")
		println("4 - Annuler une élection")
		println("5 - Ajouter des électeurs")
		println("6 - Supprimer des électeurs")
		println("8 - Modifier des électeurs")
		println("9 - Modifier une élection")
		println("10 - Ajouter des régions")
		println("11 - Ajouter des départements")
		println("12 - Ajouter des communes")
		println("13 - Ajouter des cantons")
		println("14 - Ajouter des circonscription")
		println("15 - Lister des élections")
		println("16 - Quit")
		
		print("Votre choix: ")
		choix = readLine.toInt
		return choix
	}
	
	def menuElection:Int={
	  	var choix:Int=0
	  	println("Choisir le type d'élection: ")
        println("1 - Nationale")
        println("2 - Régionale")
        println("3 - Departementale")
        
        print("Votre choix: ")
		choix = readLine.toInt
		return choix
	}
	
	def menuElectionList:Int={
	  	var choix:Int=0
	  	println("Choisir le type d'élection: ")
        println("1 - Election terminés")
        println("2 - Election en cours")
        
        print("Votre choix: ")
		choix = readLine.toInt
		return choix
	}
	
	def matchTestPrincipale(x: Int): Unit={
      if(x==1){
    	  var choix:Int=0
    	  choix = menuElection
    	  if(choix==1) this.AddElectionNationale
      }
      if(x==15){
    	  var choix:Int=0
    	  choix = menuElectionList
    	  if(choix==1) this.ListElectionTerminees
    	  if(choix==2) this.ListElectionEnCours
      }
      if(x==3){
    	  var choix:Int=0
    	  println("Liste des élections en cours")
    	  this.ListElectionEnCours
    	  this.addCandidat
    	  readLine
      }
   }
	
   private def ListElectionEnCours:Unit={
	  val leec = new ElectionUtilities
	  val l = leec.listeElectionEnCours
	    var i=0
	    for (i<-0 until l.size)println((i+1) + "-" + l.apply(i))
	    readLine

   }
   
   private def ListElectionTerminees:Unit={
	  val leec = new ElectionUtilities
	  val l = leec.listeElectionTermines
	  var i=0
	    for (i<-0 until l.size)println((i+1) + "-" + l.apply(i))
	    readLine
   }
	  
   private def addCandidat:Unit={
	  println("Informations sur le candidat: ")
	  println("ID: ")
	  val id = readLine.toInt
	  println("Election ID: ")
	  val eid = readLine.toInt
	  val e = new Electeur
	  e.ajouterCandidat(eid)
	  println("Ajout du candidat avec succès")
   }
   
   private def AddElectionNationale:Unit={
     var nom:String=""
     var date:String=""
     var mode:Int=0
     
     println("AJOUT D'UNE ELECTION")
     print("Nom: ")
     nom = readLine
     print("Date: ")
     date = readLine
     print("Mode de Scrutin: ")
     mode = readLine.toInt
     
     val e = new ElectionNationale(1,"France",nom,date,mode)
     e.saveToDB
     println("Election ajoutée avec succès")
     readLine
   }
   
   private def ListElection:Unit={
     
   }
}