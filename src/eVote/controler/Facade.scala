package eVote.controler

trait newFacade {
  
  def ajouter(pCandidatId:Int,pElectionId:Int):Unit={}
  def ajouter(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={}
  def deleteElecteurFromDB(pid:Int):Unit={}
  def saveToDB:Unit={}
  
}