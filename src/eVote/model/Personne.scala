package eVote.model

class Personne extends Utilisateur{
	
	def ajouterPersonne(nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe: String, pays: Int, region: Int, departement: Int, commune: Int, canton: Int, circonscription: Int):Unit={}
	def modifierPersonne(personne:Int,nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe: String, pays: Int, region: Int, departement: Int, commune: Int, canton: Int, circonscription: Int):Unit={}
	def seConnecter(login: String, password: String):Boolean = {
	  return true
	}
	def seDeconnecter(login: String):Boolean={return true};
}