package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

trait Election extends Candidat with PublicationResultat{
  var electionId:Int=_
  var codePays:Int=_
  var codeRegion:Int=_
  var codeDep:Int=_
  var codeCommune:Int=_
  var name:String=_
  var dateElection:String=_
  var modeDeScrutin:Int=_
	
  //def this(name:String,codePays:Int,dateElection:String,modeDeScrutin:Int) = this(name,1,dateElection,modeDeScrutin)
  
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
	
	def modifierElection(elction: Int):Unit={
	  
	}
	def supprimerElection(election:Int): Unit={
	  
	} 
  
}