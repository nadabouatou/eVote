package eVote.model
import eVote.controler.DBConnexion
import java.sql.ResultSet

class ElectionRegionale(override val pPaysId:Int, override val pRegionId:Int, override val pNomRegion:String,val pName:String,val pDate:String, val pMode:Int) extends Region(pPaysId,pRegionId,pNomRegion) with Election{
	this.nameElection = pName
	this.regionId = pRegionId
	this.nomRegion = pNomRegion
	this.paysId = pPaysId
	this.dateElection = pDate
	this.modeDeScrutin = pMode
	
	def saveToDB:Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codereg, date, type, modedescrutin) VALUES('"+this.nameElection+"', "+this.regionId +"', '"+this.dateElection +"', 2, '"+this.modeDeScrutin +"')")
		prepare.executeUpdate()
		c.close()
		}
}