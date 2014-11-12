package eVote.model

import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class Organisateur extends Utilisateur{
	def seConnecter(login: String, password: String):Boolean={
	  return true;
	}
	def seDeconnecter(login: String):Boolean={return true};
	
	def creerElectionNationale(scrutin: Int, nation: Int):Unit={	
	  
	}
	
	def creerElectionRegionale(scrutin: Int, region: Int):Unit={	 
	  
	}
	
	def creerElectionDepartementale(scrutin: Int, departement: Int):Unit={	  
	}
	
	def creerElectionCommunale(scrutin: Int, commune: Int):Unit={ 
	  
	}
	def modifierElection(elction: Int):Unit={
	  
	}
	def supprimerElection(election:Int): Unit={
	  
	}
	
	def ajouterCandidat(election: Int, candidat: Int, parti:Int):Unit={
	  
	}
	
	def retirerCandidat(candidat: Int):Unit={
	}
	def validerElection(election: Int):List[String]={
		var c = DBConnexion.conn()
		var li:List[String] = List()
		var total = ""
		var pourcentage = 0.0
		val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM candidat WHERE electionid = "+election+" AND codeelecteur IS NOT NULL")
		while (resultSet0.next()){
			total = resultSet0.getString("total")
		}
		
		val resultSet = statement.executeQuery("SELECT nom, prenoms, count(*) AS votes FROM candidat INNER JOIN personne ON candidat.codecandidat = personne.personneid WHERE electionid = "+election+" AND codeelecteur IS NOT NULL  GROUP BY nom,prenoms")
		while (resultSet.next()){
			val nom = resultSet.getString("nom")
			val prenoms = resultSet.getString("prenoms")
			val votes = resultSet.getString("votes")
			pourcentage = (votes.toFloat/total.toFloat)*100
			val somme = nom + " " + prenoms + " =>"+ votes + "("+ pourcentage +"%)"
			li = somme::li
			if (pourcentage >=50) println("Victoire de: "+nom+" "+prenoms)
		}
		c.close()
		return li;
	}
}