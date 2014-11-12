/*Projet Scala*/
package eVote.view
import eVote.controler.DBConnexion
import eVote.model.Electeur
import eVote.model.Election
import java.sql.DriverManager
import java.sql.Connection
import eVote.model.Organisateur


object main {
	def main(args: Array[String]):Unit={
	  
	  var c = DBConnexion.conn()
	  
	  val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT login, motDePasse FROM utilisateur")
      while (resultSet.next()) {
    	  val host = resultSet.getString("login")
    	  val user = resultSet.getString("motDePasse")
          println("user, pass = " + host + ", " + user)
      }
      
	  
	  val e = new Electeur()
	  val l = e.listeElectionEnCours()
	  val candidat = e.listerCandidat(2)
	  var i=0
	  for (i<-0 until l.size)println(l.apply(i))
	  
	  var j=0
	  for (j<-0 until candidat.size)println(candidat.apply(j))
	  
	 e.voter(4, 2,1)
	 e.voter(2, 2,2)
	 e.voter(2, 2,3)
	 e.voter(2, 2,4)
	 
	 val o = new Organisateur()
	 val ol = o.validerElection(2)
	 var oli=0
	  for (oli<-0 until ol.size)println(ol.apply(oli))
	}
}