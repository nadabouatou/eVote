package eVote.controler

class UtilitiesElecteur {
	def choixMenu:Int={
	  	var choix:Int=0
	  	
	  	println("MENU:")
		println("1 - Elections Diponibles")
		println("2 - Quit")
		
		print("Votre choix: ")
		choix = readLine.toInt
		return choix
	}

}