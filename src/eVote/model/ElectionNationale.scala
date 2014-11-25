package eVote.model
import eVote.controler.DBConnexion
import java.sql.ResultSet

class ElectionNationale(override val pPaysId:Int, override val pNomPays:String,val pName:String,val pDate:String, val pMode:Int) extends Pays(pPaysId,pNomPays) with Election{
	this.nameElection = pName
	this.paysId = pPaysId
	this.nomPays = pNomPays
	this.dateElection = pDate
	this.modeDeScrutin = pMode
	
	def saveToDB:Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codepays, date, type, modedescrutin) VALUES('"+this.nameElection+"', '"+this.paysId +"', '"+this.dateElection +"', 1, '"+this.modeDeScrutin +"')")
		prepare.executeUpdate()
		c.close()
	}
}