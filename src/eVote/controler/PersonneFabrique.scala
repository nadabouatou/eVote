package eVote.controler

import eVote.model.Electeur

trait PersonneFabrique {
  
	def ajouterElecteur(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={}
}

/*
 * By Ranto to Nada: Erreur dans la manière de faire une fabrique. L'objet singleton pour la fabrique est
 * dans la classe Personne dans le package model
 * 
 */

object PersonneFabrique {
  
  //def apply(s: String):PersonneFabrique = {
    /*if (s == "Electeur") return new Electeur{}
    else return new Candidat{}
    * 
    */
  //}
}
