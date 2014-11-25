package eVote.model
import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class Organisateur() extends Utilisateur{
	
   def seConnecter(login: String, password: String):Boolean={
     return true
   }
   def seDeconnecter(login: String):Boolean={
     return true
   }
  
   def ajouterOrganisateur(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
		val t = testLogin(login)
		if(t == ""){
		  majTableOrganisateur(login)
		}
		else {println("Ce login existe déjà")}
	}
	def supprimerOrganisateur(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM personne WHERE organisateurid = '"+pid+"")
		prepare.executeUpdate()
		c.close()	
	}
	
	private def majTableOrganisateur(login:String):Unit={
			var c = DBConnexion.conn()
			var id = getId(login)
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO organisateur (organisateurid) VALUES('"+id+"')")
			prepare.executeUpdate()
			c.close()
	}
	
	private def getId(sLogin:String):Int={
	  var id = 0
	  var c = DBConnexion.conn()
      val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT userid FROM utilisateur WHERE login = '"+sLogin+"'")
      while (resultSet.next()) {
        val idSearched = resultSet.getString("userid")
        id = idSearched.toInt
      }
	  c.close()
	  return id
	}	
	
	private def testLogin(sLogin:String):String={
	  var c = DBConnexion.conn()
	  var li = ""
      val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT login FROM utilisateur WHERE login = '"+sLogin+"'")
      while (resultSet.next()) {
        val loginSearched = resultSet.getString("login")
        li = loginSearched
      }
	  c.close()
	  return li
	}
}