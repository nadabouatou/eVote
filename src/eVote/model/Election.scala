package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

trait Election{
  var electionId:Int=_
  var nameElection:String=_
  var dateDebutElection:String=_
  var dateFinElection:String=_
  var modeDeScrutin:Int=_
  
  def modifierElection(elction: Int):Unit={
	  
	}
  def supprimerElection(election:Int): Unit={
	  
	} 
  
}