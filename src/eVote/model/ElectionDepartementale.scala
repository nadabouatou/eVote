package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class ElectionDepartementale(override val pRegionId:Int, override val pDepartementId:Int, override val pNomDepartement:String,val pName:String,val pDate:String, val pMode:Int) extends Departement(pRegionId,pDepartementId,pNomDepartement) with Election{
		this.nameElection = pName
	    this.departementId  = pDepartementId 
	    this.nomDepartement = pNomDepartement 
	    this.regionId = pRegionId 
	    this.dateElection = pDate
	    this.modeDeScrutin = pMode
	    
	def saveToDB:Unit={
	    var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codedep, date, type, modedescrutin) VALUES('"+this.nameElection+"', "+this.departementId  +"', '"+this.dateElection +"', 3, '"+this.modeDeScrutin +"')")
		prepare.executeUpdate()
		c.close()
	}
}