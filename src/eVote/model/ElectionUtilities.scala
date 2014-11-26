package eVote.model

import eVote.controler.DBConnexion
import java.sql.ResultSet

class ElectionUtilities{

	def listeElectionEnCours():List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid, nom, dateFin, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE dateFin >= now()")
	      while (resultSet.next()) {
	        val electionId = resultSet.getString("electionid")
	        val nom = resultSet.getString("nom")
	        val dateScrutin = resultSet.getString("dateFin")
	        val modeScrutin = resultSet.getString("description")
	        val somme = electionId+"-"+nom+"-"+dateScrutin+"-"+modeScrutin
	        li = somme::li
	      }
		 c.close()
		 return li
	}
	
	def choisirUneElection(election:Int):Int={
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid,nom, prenoms FROM candidat INNER JOIN personne ON  candidat.codecandidat = personne.personneid WHERE candidat.electionid="+election+" GROUP BY electionid,nom,prenoms")
	      while (resultSet.next()) {
	        val electionId = resultSet.getString("electionid").toInt
	        val nom = resultSet.getString("nom")
	        val prenoms = resultSet.getString("prenoms")
	        val somme = nom +" "+prenoms
	        li = somme::li
	      }
		  println("Liste des candidats de l'élection choisie")
		  var j=0
		  for (j<-0 until li.size)println((j+1)+"-" + li.apply(j))
		  c.close()
		  return election
	}
	
	def listeElectionTermines():List[String] ={
		  
		  var c = DBConnexion.conn()
		  var li:List[String] = List()
	      val statement = c.createStatement()
	      val resultSet = statement.executeQuery("SELECT electionid,nom, dateFin, description FROM election INNER JOIN scrutin ON  election.modedescrutin = scrutin.scrutinid WHERE dateFin < now()")
	      while (resultSet.next()) {
	        val electionId = resultSet.getString("electionid")
	        val nom = resultSet.getString("nom")
	        val dateFin = resultSet.getString("dateFin")
	        val modeScrutin = resultSet.getString("description")
	        val somme = electionId+"-"+nom+" | Fin: "+dateFin+" | "+modeScrutin
	        li = somme::li
	      }
	  c.close()
	  return li
	}
	def supprimerCandidat(pid:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM candidat WHERE codecandidat = '"+pid+"")
		prepare.executeUpdate()
		c.close()	
	}
	
	def NombreElecteurNationale():Int={
	  var total:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM personne")
		while (resultSet0.next()){
			total = resultSet0.getString("total").toInt
		}
	  return total
	}
	
	def NombreElecteurRegionale(regionId:Int):Int={
	  var total:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM personne WHERE region='"+regionId+"'")
		while (resultSet0.next()){
			total = resultSet0.getString("total").toInt
		}
	  return total
	}
	
	def NombreElecteurDepartementale(depId:Int):Int={
	  var total:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM personne WHERE region='"+depId+"'")
		while (resultSet0.next()){
			total = resultSet0.getString("total").toInt
		}
	  return total
	}
	
	def NombreElecteurCommunale(comId:Int):Int={
	  var total:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT count(*) AS total FROM personne WHERE region='"+comId+"'")
		while (resultSet0.next()){
			total = resultSet0.getString("total").toInt
		}
	  return total
	}
	
	def SelectType(ei:Int):Int={
	  var typeEletion:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT type FROM election WHERE electionid='"+ei+"'")
		while (resultSet0.next()){
			typeEletion = resultSet0.getString("type").toInt
		}
	  return typeEletion
	}
	
	def SelectElectionRegion(ei:Int):Int={
	 var reg:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT codereg FROM election WHERE electionid='"+ei+"'")
		while (resultSet0.next()){
			reg = resultSet0.getString("codereg").toInt
		}
	  return reg 
	}
	
	def SelectElectionDepartement(ei:Int):Int={
	 var dep:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT codedep FROM election WHERE electionid='"+ei+"'")
		while (resultSet0.next()){
			dep = resultSet0.getString("codedep").toInt
		}
	  return dep 
	}
	def SelectElectionCommune(ei:Int):Int={
	 var com:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT codecom FROM election WHERE electionid='"+ei+"'")
		while (resultSet0.next()){
			com = resultSet0.getString("codecom").toInt
		}
	  return com 
	}
	
	def SelectElectionMode(ei:Int):Int={
	 var mode:Int=0
	  var c = DBConnexion.conn()
	  val statement = c.createStatement()
		val resultSet0 = statement.executeQuery("SELECT modedescrutin FROM election WHERE electionid='"+ei+"'")
		while (resultSet0.next()){
			mode = resultSet0.getString("modedescrutin").toInt
		}
	  return mode 
	}
}