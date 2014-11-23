package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

trait Parti {
	var partiId:Int = _
	var partiName:String = _
	var partiAdresse:String =_
	var partiCreation:String =_
	var partiTel:String =_

	private def majTableParti(partiId:Int, nom:String, dateDeCreation:String, siege:String, tel:String ):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO parti (partiid, denommination, datedecreation, siege, telephone) VALUES('"+partiId+"', '"+nom+"', '"+dateDeCreation+"', '"+siege+"', '"+tel+"')")
		prepare.executeUpdate()
		c.close()
	}
}