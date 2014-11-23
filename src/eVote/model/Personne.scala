package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class Personne(
   val _uid:Int,
   val _pseudo:String,
   val _mdp:String,
   val _flag:Int,
   val _nom:String,
   val _prenoms:String,
   val _region:Int,
   val _departement:Int,
   val _commune:Int,
   val _canton:Int,
   val _circonscription:Int,
   val _adresse:String,
   val _tel:String,
   val _dateNaissance:String,
   val _sexe:String
	) 
	extends Utilisateur
	{
	
	//def ajouterPersonne(nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe: String, pays: Int, region: Int, departement: Int, commune: Int, canton: Int, circonscription: Int):Unit={}
	//def modifierPersonne(personne:Int,nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe: String, pays: Int, region: Int, departement: Int, commune: Int, canton: Int, circonscription: Int):Unit={}  

   var nom:String = _nom
   var prenoms:String = _prenoms
   var region:Int = _region
   var departement:Int = _departement 
   var commune:Int = _commune
   var canton:Int = _canton 
   var circonscription:Int = _circonscription 
   var adresse:String = _adresse 
   var tel:String= _tel 
   var dateNaissance:String=_dateNaissance
   var sexe:String = _sexe
   
   def this() = this(0,"","",0,"","",0,0,0,0,0,"","","","")
   
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
		  
		  val resultSet = statement.executeQuery("SELECT nom, prenoms, region, departement, commune, canton, circonscription,adresse,telephone,sexe, datedenaissance FROM personne WHERE personneid = '"+idGetted+"'")
		  while (resultSet.next()) {
	        val nomSearched = resultSet.getString("nom")
	        val prenomsSearched = resultSet.getString("prenoms")
	        val regionSearched = resultSet.getString("region")
	        val departementSearched = resultSet.getString("departement")
	        val communeSearched = resultSet.getString("commune")
	        val cantonSearched = resultSet.getString("canton")
	        val circonscriptionSearched = resultSet.getString("circonscription")
	        val adresseSearched = resultSet.getString("adresse")
	        val telSearched = resultSet.getString("telephone")
	        val sexeSearched = resultSet.getString("sexe")
	        val bithSearched = resultSet.getString("datedenaissance")
	        
	        nom = nomSearched
	        prenoms = prenomsSearched
	        region = regionSearched.toInt
	        departement = departementSearched.toInt
	        commune  = communeSearched.toInt
	        canton = cantonSearched.toInt
	        circonscription = circonscriptionSearched.toInt
	        adresse  = adresseSearched
	        tel = telSearched
	        sexe = sexeSearched
	        dateNaissance  = bithSearched
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
	      val resultSet = statement.executeQuery("SELECT nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE date >= now() AND ((type = 1) OR(type = 2 AND codereg='"+region.toInt +"') OR (type = 3 AND codecom='"+commune.toInt +"') OR (type = 4 AND codedep='"+departement.toInt+"'))")
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
	      val resultSet = statement.executeQuery("SELECT codecandidat,nom, prenoms FROM candidat INNER JOIN personne ON  candidat.codecandidat = personne.personneid WHERE candidat.electionid="+codeElection+" GROUP BY codecandidat,nom,prenoms")
	      while (resultSet.next()) {
	        val codeCandidat = resultSet.getString("codecandidat")
	        val nom = resultSet.getString("nom")
	        val prenoms = resultSet.getString("prenoms")
	        val somme = nom +" "+prenoms
	        li = somme::li
	      }
		  c.close()
		  return li;
	}
	
	def ajouterPersonne(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
		val t = testLogin(login)
		if(t == ""){
		  majTableUtilisateur(login, motDePasse)
		  majTablePersonne(login, nom, prenoms, adresse, telephone, dateDeNaissance, sexe, region, departement, commune, canton, circonscription)
		}
		else {println("Ce login existe déjà")}
	}
	
	def supprimerPersonne(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM personne WHERE personneid = '"+pid+"")
		var prepare2 = c.prepareStatement("DELETE FROM utilisateur WHERE userid = '"+pid+"")
		prepare.executeUpdate()
		prepare2.executeUpdate()
		c.close()	
	}
	
	def testLogin(sLogin:String):String={
	  var c = DBConnexion.conn()
	  var li = ""
      val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT login FROM utilisateur WHERE login = '"+sLogin+"'")
      while (resultSet.next()) {
        val loginSearched = resultSet.getString("login")
        li = loginSearched
      }
	  c.close()
	  return li
	}
	
	def majTableUtilisateur(login:String,motDePasse:String):Unit={
			var c = DBConnexion.conn()
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO utilisateur (login, motdepasse,flag) VALUES('"+login+"', '"+motDePasse+"', 1)")
			prepare.executeUpdate()
			c.close()
	}
	
	def getId(sLogin:String):Int={
	  var id = 0
	  var c = DBConnexion.conn()
      val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT userid FROM utilisateur WHERE login = '"+sLogin+"'")
      while (resultSet.next()) {
        val idSearched = resultSet.getString("userid")
        id = idSearched.toInt
      }
	  c.close()
	  return id
	}	
	def majTablePersonne(login:String,nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
	    var c = DBConnexion.conn()
	    var personneId=getId(login)
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO personne (personneid, nom, prenoms, adresse, telephone, datedenaissance, sexe, region, departement, commune, canton, circonscription) VALUES('"+personneId+"', '"+nom+"', '"+prenoms+"', '"+adresse+"', '"+telephone+"', '"+dateDeNaissance+"', '"+sexe+"', '"+region+"', '"+departement+"', '"+commune+"', '"+canton+"', '"+circonscription+"')")
		prepare.executeUpdate()
		c.close()
	}
	
}