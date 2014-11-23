package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class ElectionPresidentielle extends Election{
  
    
  
	def bgCreerElectionNationale(nom: String, pays: Int, sDate:String, modeDeScrutin:Int):Unit={	
	    var c = DBConnexion.conn()
	    this.name = nom
	    this.codePays = pays
	    this.dateElection = sDate
	    this.modeDeScrutin = modeDeScrutin
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codepays, date, type, modedescrutin) VALUES('"+nom+"', '"+pays+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def bgCreerElectionRegionale(nom: String, region: Int, sDate:String, modeDeScrutin:Int):Unit={	 
		var c = DBConnexion.conn()
		this.name = nom
	    this.codeRegion  = region
	    this.dateElection = sDate
	    this.modeDeScrutin = modeDeScrutin
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codereg, date, type, modedescrutin) VALUES('"+nom+"', "+region+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def bgCreerElectionDepartementale(nom: String, dep: Int, sDate:String, modeDeScrutin:Int):Unit={
	    var c = DBConnexion.conn()
	    this.name = nom
	    this.codeDep  = dep
	    this.dateElection = sDate
	    this.modeDeScrutin = modeDeScrutin
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codedep, date, type, modedescrutin) VALUES('"+nom+"', "+dep+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def bgCreerElectionCommunale(nom: String, com: Int, sDate:String, modeDeScrutin:Int):Unit={ 
	    var c = DBConnexion.conn()
	    this.name = nom
	    this.codeCommune  = com
	    this.dateElection = sDate
	    this.modeDeScrutin = modeDeScrutin
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, codecom, date, type, modedescrutin) VALUES('"+nom+"', "+com+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
  
}