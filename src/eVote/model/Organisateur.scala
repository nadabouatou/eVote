package eVote.model
import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class Organisateur extends Electeur{
  
   def ajouterOrganisateur(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
		val t = testLogin(login)
		if(t == ""){
		  majTableUtilisateur(login, motDePasse)
		  majTablePersonne(login, nom, prenoms, adresse, telephone, dateDeNaissance, sexe, region, departement, commune, canton, circonscription)
		  majTableOrganisateur(login)
		}
		else {println("Ce login existe déjà")}
	}
	def supprimerOrganisateur(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM personne WHERE organisateurid = '"+pid+"")
		prepare.executeUpdate()
		supprimerPersonne(pid)
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
}