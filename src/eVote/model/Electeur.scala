package eVote.model
import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet


class Electeur extends Personne(){
	def  testVote(codeElecteur:Int, election:Int):String={
		  var c = DBConnexion.conn()
		  var li = ""
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid, electeurid FROM checkvote WHERE electionid = "+election+" AND electeurid = "+codeElecteur+"")
	      while (resultSet.next()) {
	        val electionId = resultSet.getString("electionid")
	        val code = resultSet.getString("electeurid")
	        val somme = electionId +"|"+code
	        li = somme
	      }
		  c.close()
		  return li
	}
	
	def voter(codeCandidat: Int, electionId: Int, electeurId:Int):Unit ={
			val test = testVote(electeurId, electionId)
			if(test==""){
			var c = DBConnexion.conn()
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid) VALUES('"+codeCandidat+"', '"+electionId+"')")
			var prepare2 = c.prepareStatement("INSERT INTO checkvote (electionid, electeurid) VALUES('"+electionId+"', '"+electeurId+"')")
			prepare.executeUpdate()
			prepare2.executeUpdate()
			c.close()
			}
			else println("Vous avez d�j� vot�")
	}
	
	def classerCandidat(classification: Map[Int,Int]):Unit ={
	  
	}
}