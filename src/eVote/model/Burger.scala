package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class Burger extends Candidat {
	var BurgerId:Int=_
	var BurgerName:String=_
	
	def ajouterCandidat(pElectionId:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid) VALUES('"+BurgerId+"', '"+pElectionId+"')")
		prepare.executeUpdate()
		c.close()
	}
}