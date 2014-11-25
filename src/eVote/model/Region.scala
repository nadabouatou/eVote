package eVote.model

class Region(val pPaysId:Int, val pRegionId:Int, val pNomRegion:String) {
	
	var paysId = pPaysId 
	var regionId = pRegionId 
	var nomRegion = pNomRegion 
  
	def ajouterRegion(pays: Int, codeRegion: Int, nom: String):Unit={}
	def modifierRegion(codeRegion: Int,codePays: Int, nomRegion: String):Unit={}
	def supprimerRegion(codeRegion: Int):Unit={}
}