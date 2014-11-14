package eVote.model

import eVote.controler.DBConnexion
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class Organisateur extends Utilisateur{
  
    var oid =0
    var pseudo=""
    var mdp=""
    var flag=0
    var nom=""
    var adresse=""
    var telephone=""
    
	def seConnecter(login: String, password: String):Boolean={
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
		  oid = idGetted.toInt
		  pseudo = logGetted
		  mdp  = mdpGetted
		  flag  = flagGetted.toInt
		  connect = true
		  
		  //recup infos
		  
		  val resultSet = statement.executeQuery("SELECT nom, adresse, telephone FROM organisateur WHERE organisateurid = '"+idGetted+"'")
		  while (resultSet.next()) {
	        val nomSearched = resultSet.getString("nom")
	        val adresseSearched = resultSet.getString("adresse")
	        val telephoneSearched = resultSet.getString("telephone")
	        
	        nom = nomSearched
	        adresse = adresseSearched
	        telephone = telephoneSearched
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
	
	def ajouterElecteur(login:String, motDePasse:String, nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
		val t = testLogin(login)
		if(t == ""){
		  majTableUtilisateur(login, motDePasse)
		  majTablePersonne(login, nom, prenoms, adresse, telephone, dateDeNaissance, sexe, region, departement, commune, canton, circonscription)
		  majTableElecteur(login, circonscription)
		}
		else {println("Ce login existe déjà")}
	}
	
	private def testLogin(sLogin:String):String={
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
	
	private def majTableUtilisateur(login:String,motDePasse:String):Unit={
			var c = DBConnexion.conn()
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO utilisateur (login, motdepasse,flag) VALUES('"+login+"', '"+motDePasse+"', 1)")
			prepare.executeUpdate()
			c.close()
	}
	
	private def getId(sLogin:String):Int={
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
	private def majTablePersonne(login:String,nom:String, prenoms:String, adresse:String, telephone:String, dateDeNaissance:String, sexe:String, region:String, departement:String, commune:String, canton:String, circonscription:Int):Unit={
	    var c = DBConnexion.conn()
	    var personneId=getId(login)
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO personne (personneid, nom, prenoms, adresse, telephone, datedenaissance, sexe, region, departement, commune, canton, circonscription) VALUES('"+personneId+"', '"+nom+"', '"+prenoms+"', '"+adresse+"', '"+telephone+"', '"+dateDeNaissance+"', '"+sexe+"', '"+region+"', '"+departement+"', '"+commune+"', '"+canton+"', '"+circonscription+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	private def majTableElecteur(login:String, idCir:Int):Unit={
			var c = DBConnexion.conn()
			var id = getId(login)
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO electeur (electeurid, idcir) VALUES('"+id+"', '"+idCir+"')")
			prepare.executeUpdate()
			c.close()
	}
	
	def ajouterParti(login:String, motDePasse:String, nom: String, dateDeCreation: String, siege:String, tel:String):Unit={
		val t = testLogin(login)
		if(t == ""){
		  majTableUtilisateur(login, motDePasse)
		  majTableParti(login, nom, dateDeCreation, siege, tel)
		}
		else {println("Ce Parti existe déjà")}
	}
	
	private def majTableParti(login:String, nom:String, dateDeCreation:String, siege:String, tel:String ):Unit={
		var c = DBConnexion.conn()
	    var partiId=getId(login)
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO parti (partiid, denommination, datedecreation, siege, telephone) VALUES('"+partiId+"', '"+nom+"', '"+dateDeCreation+"', '"+siege+"', '"+tel+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def creerElectionNationale(nom: String, pays: Int, sDate:String, modeDeScrutin:Int):Unit={	
	    var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, organisateur, codepays, date, type, modedescrutin) VALUES('"+nom+"', '"+oid+"', '"+pays+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def creerElectionRegionale(nom: String, region: Int, sDate:String, modeDeScrutin:Int):Unit={	 
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, organisateur, codereg, date, type, modedescrutin) VALUES('"+nom+"', '"+oid+"', '"+region+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def creerElectionDepartementale(nom: String, dep: Int, sDate:String, modeDeScrutin:Int):Unit={
	    var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, organisateur, codedep, date, type, modedescrutin) VALUES('"+nom+"', '"+oid+"', '"+dep+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	
	def creerElectionCommunale(nom: String, com: Int, sDate:String, modeDeScrutin:Int):Unit={ 
	    var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("INSERT INTO election (nom, organisateur, codecom, date, type, modedescrutin) VALUES('"+nom+"', '"+oid+"', '"+com+"', '"+sDate+"', 2, '"+modeDeScrutin+"')")
		prepare.executeUpdate()
		c.close()
	}
	def modifierElection(elction: Int):Unit={
	  
	}
	def supprimerElection(election:Int): Unit={
	  
	}
	
	def ajouterCandidat(election: Int, candidat: Int, parti:Int):Unit={
	  
	}
	
	def retirerCandidat(candidat: Int):Unit={
	}
	def validerElection(election: Int):List[String]={
		var c = DBConnexion.conn()
		var li:List[String] = List()
		var total = ""
		var pourcentage = 0.0
		val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM checkvote WHERE electionid = "+election+"")
		while (resultSet0.next()){
			total = resultSet0.getString("total")
		}
		
		val resultSet = statement.executeQuery("SELECT nom, prenoms, count(*) AS votes FROM candidat INNER JOIN personne ON candidat.codecandidat = personne.personneid WHERE electionid = "+election+" AND codeparti IS NULL  GROUP BY nom,prenoms")
		while (resultSet.next()){
			val nom = resultSet.getString("nom")
			val prenoms = resultSet.getString("prenoms")
			val votes = resultSet.getString("votes")
			pourcentage = (votes.toFloat/total.toFloat)*100
			val somme = nom + " " + prenoms + " =>"+ votes + "("+ pourcentage +"%)"
			li = somme::li
			if (pourcentage >=50) println("Victoire de: "+nom+" "+prenoms)
		}
		c.close()
		return li;
	}
	
	def listeElectionEnCours():List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE date >= now()")
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
	
	def listeElectionTermines():List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT nom, date, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE date < now()")
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
	
	
}