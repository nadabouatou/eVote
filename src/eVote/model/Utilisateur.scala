package eVote.model


trait Utilisateur{
  
	var uid:Int=_
	var pseudo:String= _
	var mdp:String = _
	var flag:Int = _
	
	def seConnecter(login: String, password: String):Boolean;
	def seDeconnecter(login: String):Boolean

}

object Utilisateur{
	def apply(s: String)= s match{
	  case "organisateur" => new Organisateur()
	}
}