package eVote.model

trait Election {
  var electionId:Int=_
  var organisateurId:Int=_
  var codePays:Int=_
  var codeRegion:Int=_
  var codeDep:Int=_
  var codeCommune:Int=_
  var name:String=_
  var typeElection:Int=_
  var dateElection:String=_
  var modeDeScrutin:Int=_
}