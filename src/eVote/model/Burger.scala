package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class Burger extends Candidat {
	var BurgerId:Int=_
	var BurgerName:String=_
	
	def ajouterCandidat(pCandidatId:Int,pElectionId:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid) VALUES('"+pCandidatId+"', '"+pElectionId+"')")
		prepare.executeUpdate()
		c.close()
	}
}