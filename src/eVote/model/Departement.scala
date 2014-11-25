package eVote.model

class Departement(val pRegionId:Int, val pDepartementId:Int, val pNomDepartement:String) {
	var regionId = pRegionId 
	var departementId = pDepartementId 
	var nomDepartement = pNomDepartement 
  
	def ajouterDepartement(Region:Int,codeDepartement:Int,nom:String):Unit={}
	def modfierDepartement(codeDepartement:Int, CodeRegion:Int, nomDepartement:String):Unit={}
	def supprimerDepartement(codeDepartement:Int):Unit={}
}