package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

trait Candidat{
  var candidatId:Int=_
  var electionId:Int=_
  
  def ajouterCandidat(pElectionId:Int):Unit
}