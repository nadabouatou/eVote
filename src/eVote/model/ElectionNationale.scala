package eVote.model
import eVote.controler.DBConnexion
import java.sql.ResultSet
import java.io.IOException
import org.postgresql.util.PSQLException

class ElectionNationale(override val pPaysId:Int, override val pNomPays:String,val pName:String,val pDateDebut:String,val pDateFin:String, val pMode:Int) extends Pays(pPaysId,pNomPays) with Election{
	this.nameElection = pName
	this.paysId = pPaysId
	this.nomPays = pNomPays
	this.dateDebutElection = pDateDebut 
	this.dateFinElection  = pDateFin 
	this.modeDeScrutin = pMode
	
	def saveToDB:Unit={
		var c = DBConnexion.conn()
		try{
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO election (nom, codepays, dateDebut, dateFin, type, modedescrutin) VALUES('"+this.nameElection+"', '"+this.paysId +"', '"+this.dateDebutElection  +"', '"+this.dateFinElection +"', 1, '"+this.modeDeScrutin +"')")
			prepare.executeUpdate()
			c.close()
		}
		catch{
		  case ex: PSQLException => {
            println("Erreur de BDD")
		  	}
		}
	}
}