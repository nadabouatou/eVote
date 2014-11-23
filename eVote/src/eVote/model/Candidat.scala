package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

trait Candidat extends Electeur with Parti{

  def ajouterCandidat(election: Int, candidat: Int, parti:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid, codeparti) VALUES('"+candidat+"', '"+election+"', '"+parti+"')")
		prepare.executeUpdate()
		c.close()
	}
  def ajouterCandidat(election: Int, candidat: Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO candidat (codecandidat, electionid) VALUES('"+candidat+"', '"+election+"')")
		prepare.executeUpdate()
		c.close()
	}
  def supprimerCandidat(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM candidat WHERE codecandidat = '"+pid+"")
		prepare.executeUpdate()
		supprimerElecteur(pid)
		c.close()	
	}
}