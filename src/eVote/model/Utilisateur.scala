package eVote.model

trait Utilisateur {
	def seConnecter(login: String, password: String):Boolean;
	def seDeconnecter(login: String):Boolean
}