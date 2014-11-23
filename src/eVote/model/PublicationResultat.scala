package eVote.model

import eVote.controler.DBConnexion
import java.text.DecimalFormat

trait PublicationResultat {
	def validerElection(election: Int):List[String]={
		var c = DBConnexion.conn()
		val df = new DecimalFormat("########.00")
		var li:List[String] = List()
		var total = ""
		var pourcentage = 0.0
		val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM bulletindevoteuni WHERE electionid = "+election+"")
		while (resultSet0.next()){
			total = resultSet0.getString("total")
		}
		
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
}