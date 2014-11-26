package eVote.model

import eVote.controler.DBConnexion
import java.text.DecimalFormat

class PublicationResultat {
	 def ElectionDeuxTours(election: Int):List[String]={
		var c = DBConnexion.conn()
		val df = new DecimalFormat("########.00")
		var li:List[String] = List()
		var total:Int = 0
		var pourcentage = 0.0
		val eu = new ElectionUtilities
		val typeElection = eu.SelectType(election)
		if(typeElection==1) total = eu.NombreElecteurNationale
		if(typeElection==2){
		  val regionId = eu.SelectElectionRegion(election)
		  total = eu.NombreElecteurRegionale(regionId)
		} 
		if(typeElection==3){
		  val depId = eu.SelectElectionDepartement(election)
		  total = eu.NombreElecteurDepartementale(depId) 
		} 
		if(typeElection==4){
		  val comId = eu.SelectElectionCommune(election)
		  total = eu.NombreElecteurCommunale(comId)
		} 
		
		val statement = c.createStatement()
		val resultSet = statement.executeQuery("SELECT nom, prenoms, count(*) AS votes FROM bulletindevoteuni INNER JOIN personne ON bulletindevoteuni.candidatid = personne.personneid WHERE electionid = "+election+" GROUP BY nom,prenoms")
		while (resultSet.next()){
			val nom = resultSet.getString("nom")
			val prenoms = resultSet.getString("prenoms")
			val votes = resultSet.getString("votes")
			pourcentage = (votes.toFloat/total.toFloat)*100
			val somme = nom + " " + prenoms + " =>"+ votes + "("+ df.format(pourcentage) +"%)"
			li = somme::li
			if (pourcentage >=50) println("Victoire de: "+nom+" "+prenoms)
		}
		c.close()
		return li;
	}
	
	 def ElectionUntour(election: Int):List[String]={
		var c = DBConnexion.conn()
		val df = new DecimalFormat("########.00")
		var li:List[String] = List()
		var total:Int = 0
		var pourcentage = 0.0
		
		val eu = new ElectionUtilities
		val typeElection = eu.SelectType(election)
		if(typeElection==1) total = eu.NombreElecteurNationale
		if(typeElection==2){
		  val regionId = eu.SelectElectionRegion(election)
		  total = eu.NombreElecteurRegionale(regionId)
		} 
		if(typeElection==3){
		  val depId = eu.SelectElectionDepartement(election)
		  total = eu.NombreElecteurDepartementale(depId) 
		} 
		if(typeElection==4){
		  val comId = eu.SelectElectionCommune(election)
		  total = eu.NombreElecteurCommunale(comId)
		} 
		val statement = c.createStatement()
		val resultSet = statement.executeQuery("SELECT nom, prenoms, count(*) AS votes FROM bulletindevoteuni INNER JOIN personne ON bulletindevoteuni.candidatid = personne.personneid WHERE electionid = "+election+" GROUP BY nom,prenoms")
		while (resultSet.next()){
			val nom = resultSet.getString("nom")
			val prenoms = resultSet.getString("prenoms")
			val votes = resultSet.getString("votes")
			pourcentage = (votes.toFloat/total.toFloat)*100
			val somme = nom + " " + prenoms + " =>"+ votes + "("+ df.format(pourcentage) +"%)"
			li = somme::li
		}
		c.close()
		return li;
	}
}