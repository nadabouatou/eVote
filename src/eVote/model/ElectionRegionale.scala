package eVote.model
import eVote.controler.DBConnexion
import java.io.IOException
import java.sql.ResultSet
import org.postgresql.util.PSQLException

class ElectionRegionale(override val pPaysId:Int, override val pRegionId:Int, override val pNomRegion:String,val pName:String,val pDateDebut:String,val pDateFin:String, val pMode:Int) extends Region(pPaysId,pRegionId,pNomRegion) with Election{
	this.nameElection = pName
	this.regionId = pRegionId
	this.nomRegion = pNomRegion
	this.paysId = pPaysId
	this.dateDebutElection = pDateDebut 
	this.dateFinElection  = pDateFin 
	this.modeDeScrutin = pMode
	
	def this(pPaysId:Int,pRegionId:Int,pName:String,pDateDebut:String ,pDateFin:String ,pMode:Int)=this(pPaysId,pRegionId,"",pName,pDateDebut ,pDateFin ,pMode)
	def this(pRegionId:Int,pName:String,pDateDebut:String ,pDateFin:String ,pMode:Int)=this(0,pRegionId,"",pName,pDateDebut ,pDateFin ,pMode)
	
	def saveToDB:Unit={
	  try{
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codereg, dateDebut, dateFin, type, modedescrutin) VALUES('"+this.nameElection+"', '"+this.regionId +"', '"+this.dateDebutElection  +"', '"+this.dateFinElection +"', 2, '"+this.modeDeScrutin +"')")
		prepare.executeUpdate()
		c.close()
		}
	catch{
		  case ex: PSQLException => {
            println("Cette région n'existe pas ou Format de date erroné (doit être:jj-mm-aaaa)")
		  	}
		}
	}
}