package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class ElectionUtilities{

def listeElectionEnCours():List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid, nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE date >= now()")
	      while (resultSet.next()) {
	        val electionId = resultSet.getString("electionid")
	        val nom = resultSet.getString("nom")
	        val dateScrutin = resultSet.getString("date")
	        val modeScrutin = resultSet.getString("description")
	        val somme = electionId+"-"+nom+"-"+dateScrutin+"-"+modeScrutin
	        li = somme::li
	      }
		 c.close()
		 return li
	}
	
	def choisirUneElection(election:Int):Int={
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid,nom, prenoms FROM candidat INNER JOIN personne ON  candidat.codecandidat = personne.personneid WHERE candidat.electionid="+election+" GROUP BY electionid,nom,prenoms")
	      while (resultSet.next()) {
	        val electionId = resultSet.getString("electionid").toInt
	        val nom = resultSet.getString("nom")
	        val prenoms = resultSet.getString("prenoms")
	        val somme = nom +" "+prenoms
	        li = somme::li
	      }
		  println("Liste des candidats de l'élection choisie")
		  var j=0
		  for (j<-0 until li.size)println((j+1)+"-" + li.apply(j))
		  c.close()
		  return election
	}
	
	def listeElectionTermines():List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE date < now()")
	      while (resultSet.next()) {
	        val nom = resultSet.getString("nom")
	        val dateScrutin = resultSet.getString("date")
	        val modeScrutin = resultSet.getString("description")
	        val somme = nom+" | "+dateScrutin+" | "+modeScrutin
	        li = somme::li
	      }
	  c.close()
	  return li
	}
	def supprimerCandidat(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM candidat WHERE codecandidat = '"+pid+"")
		prepare.executeUpdate()
		c.close()	
	}
}