package eVote.model

import eVote.controler.DBConnexion

class Personne() extends Utilisateur{
	
	//def ajouterPersonne(nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe: String, pays: Int, region: Int, departement: Int, commune: Int, canton: Int, circonscription: Int):Unit={}
	//def modifierPersonne(personne:Int,nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe: String, pays: Int, region: Int, departement: Int, commune: Int, canton: Int, circonscription: Int):Unit={}  
   var uid = 0
   var pseudo=""
   var mdp=""
   var flag=0
   var nom = ""
   var prenoms = ""
   var region = ""
   var departement = ""
   var commune:String = _
   var canton = ""
   var circonscription = ""
  
   def seConnecter(login: String, password: String):Boolean = {
	  var connect = false
	  var c = DBConnexion.conn()
	  var logGetted = ""
	  var mdpGetted = ""
	  var idGetted = ""
	  var flagGetted = ""
      val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT userid, login, flag FROM utilisateur WHERE login = '"+login+"'")
      while (resultSet.next()) {
        val loginSearched = resultSet.getString("login")
        val idSearched = resultSet.getString("userid")
        val flagSearched = resultSet.getString("userid")
        logGetted = loginSearched
        idGetted = idSearched
        flagGetted = flagSearched
      }
	  
	  if (logGetted==""){
	    println("Ce login n'existe pas")
	    connect = false
	  }
	  else{
		val resultSet = statement.executeQuery("SELECT motdepasse FROM utilisateur WHERE login = '"+login+"'")
		while (resultSet.next()) {
        val loginSearched = resultSet.getString("motdepasse")
        mdpGetted = loginSearched
		}
		if(mdpGetted==password){
		  println ("Connexion OK")
		  uid = idGetted.toInt
		  pseudo = logGetted
		  mdp  = mdpGetted
		  flag  = flagGetted.toInt
		  connect = true
		  
		  //recup infos
		  
		  val resultSet = statement.executeQuery("SELECT nom, prenoms, region, departement, commune, canton, circonscription FROM personne WHERE personneid = '"+idGetted+"'")
		  while (resultSet.next()) {
	        val nomSearched = resultSet.getString("nom")
	        val prenomsSearched = resultSet.getString("prenoms")
	        val regionSearched = resultSet.getString("region")
	        val departementSearched = resultSet.getString("departement")
	        val communeSearched = resultSet.getString("commune")
	        val cantonSearched = resultSet.getString("canton")
	        val circonscriptionSearched = resultSet.getString("circonscription")
	        
	        nom = nomSearched
	        prenoms = prenomsSearched
	        region = regionSearched
	        departement = departementSearched
	        commune  = communeSearched
	        canton = cantonSearched
	        circonscription = circonscriptionSearched
	        
			}
		  }
		 else {
		  println("Votre mot de passe est erroné")
		  connect = false
		}
	  }
	  
	  c.close()
	  return connect
	}
	def seDeconnecter(login: String):Boolean={return true};
	def getFlag(login:String):Int={
	  var f=0
	  
	  return f 
	}
	
	def listeElectionEnCours(login:String):List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE date >= now() AND ((type = 2 AND codereg='"+region.toInt +"') OR (type = 3 AND codecom='"+commune.toInt +"') OR (type = 4 AND codedep='"+departement.toInt+"'))")
	      while (resultSet.next()) {
	        val nom = resultSet.getString("nom")
	        val dateScrutin = resultSet.getString("date")
	        val modeScrutin = resultSet.getString("description")
	        val somme = nom+" | "+dateScrutin+" | "+modeScrutin
	        li = somme::li
	      }
	  c.close()
	  return li
	}
	
	def listerCandidat(codeElection: Int):List[String]={
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, prenoms FROM candidat INNER JOIN personne ON  candidat.codecandidat = personne.personneid WHERE candidat.electionid="+codeElection+" GROUP BY nom,prenoms")/*eto misy diso*/
	      while (resultSet.next()) {
	        val nom = resultSet.getString("nom")
	        val prenoms = resultSet.getString("prenoms")
	        val somme = nom +" "+prenoms
	        li = somme::li
	      }
		  c.close()
		  return li;
	}	
}