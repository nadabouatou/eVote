package eVote.model
import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class Electeur extends Personne with BulletinDeVote with Candidat{
	
	def ajouterElecteur(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
		val t = testLogin(login)
		if(t == ""){
		  majTableUtilisateur(login, motDePasse)
		  majTablePersonne(login, nom, prenoms, adresse, telephone, dateDeNaissance, sexe, region, departement, commune, canton, circonscription)
		  majTableElecteur(login, circonscription)
		}
		else {println("Ce login existe déjà")}
	}
	def deleteElecteurFromDB(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM electeur WHERE electeurid = '"+pid+"")
		prepare.executeUpdate()
		deletePersonneFromDB(pid)
		c.close()	
	}
	
	private def majTableElecteur(login:String, idCir:Int):Unit={
			var c = DBConnexion.conn()
			var id = getId(login)
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO electeur (electeurid, idcir) VALUES('"+id+"', '"+idCir+"')")
			prepare.executeUpdate()
			c.close()
	}
	  def ajouterCandidat(pElectionId:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid) VALUES('"+uid+"', '"+pElectionId+"')")
		prepare.executeUpdate()
		c.close()
	}
}