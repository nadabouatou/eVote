package eVote.model

class Organisateur extends Utilisateur{
	def seConnecter(login: String, password: String):Boolean={
	  return true;
	}
	def seDeconnecter(login: String):Boolean={return true};
	
	def creerElectionNationale(scrutin: Int, nation: Int):Unit={	
	  
	}
	
	def creerElectionRegionale(scrutin: Int, region: Int):Unit={	 
	  
	}
	
	def creerElectionDepartementale(scrutin: Int, departement: Int):Unit={	  
	}
	
	def creerElectionCommunale(scrutin: Int, commune: Int):Unit={ 
	  
	}
	def modifierElection(elction: Int):Unit={
	  
	}
	def supprimerElection(election:Int): Unit={
	  
	}
	
	def ajouterCandidat(election: Int, candidat: Int, parti:Int):Unit={
	  
	}
	
	def retirerCandidat(candidat: Int):Unit={
	}
	def validerElection(election: Int):Boolean={
	  return true;
	}
}