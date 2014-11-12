package eVote.model
import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet


class Electeur extends Personne{
	
	def listeElectionEnCours():	List[String] ={
		
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid")
	      while (resultSet.next()) {
	        val nom = resultSet.getString("nom")
	        val dateScrutin = resultSet.getString("date")
	        val modeScrutin = resultSet.getString("description")
	        val somme = nom+"|"+dateScrutin+"|"+modeScrutin
	        li = somme::li
	      }
	  c.close()
	  return li
	}
	
	def listerCandidat(codeElection: Int):List[String]={
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, prenoms FROM candidat INNER JOIN personne ON  candidat.codecandidat = personne.personneid WHERE candidat.electionid="+codeElection+" AND codeelecteur IS NULL")
	      while (resultSet.next()) {
	        val nom = resultSet.getString("nom")
	        val prenoms = resultSet.getString("prenoms")
	        val somme = nom +"|"+prenoms
	        li = somme::li
	      }
	  c.close()
	  return li;
	}
	
	def voter(codeCandidat: Int, electionId: Int, electeurId:Int):Unit ={
			var c = DBConnexion.conn()
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid, codeelecteur) VALUES('"+codeCandidat+"', '"+electionId+"', '"+electeurId+"')")
			prepare.executeUpdate()
			c.close()
	}
	
	def classerCandidat(classification: Map[Int,Int]):Unit ={
	  
	}
}