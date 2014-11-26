package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

trait Personne extends Utilisateur{

   var nom:String = _
   var prenoms:String = _
   var region:Int = _
   var departement:Int = _
   var commune:Int = _
   var canton:Int = _
   var circonscription:Int = _
   var adresse:String = _
   var tel:String= _
   var dateNaissance:String=_
   var sexe:String = _
   
   
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
	
	def listeElectionEnCours:List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid, nom, datedebut, datefin, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE datedebut <= now() AND now()<= datefin AND ((type = 1) OR(type = 2 AND codereg='"+region +"') OR (type = 3 AND codecom='"+commune +"') OR (type = 4 AND codedep='"+departement+"'))")
	      while (resultSet.next()) {
	        val id = resultSet.getString("electionid")
	        val nom = resultSet.getString("nom")
	        val dateDebut= resultSet.getString("datedebut")
	        val dateFin= resultSet.getString("datefin")
	        val modeScrutin = resultSet.getString("description")
	        val somme = "Id: "+ id + " => " + nom +" | Du "+dateDebut+ " au "+ dateFin + " | "+modeScrutin
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
	        val somme = "Id: "+codeCandidat+" => "+nom +" "+prenoms
	        li = somme::li
	      }
		  c.close()
		  return li;
	}
	
	def saveToDB:Unit={
		val t = testLogin(this.pseudo)
		if(t == ""){
		  majTableUtilisateur(this.pseudo , this.mdp)
		  majTablePersonne(this.pseudo, this.nom , this.prenoms , this.adresse , this.tel , this.dateNaissance , this.sexe , this.region.toString , this.departement.toString , this.commune.toString  , this.canton.toString  , this.circonscription )
		}
		else {println("Ce login existe déjà")}
	}
	
	def deletePersonneFromDB(pid:Int):Unit={
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