/*Projet Scala*/
package eVote.view
import eVote.controler.DBConnexion
import eVote.model.Electeur
import eVote.model.Election
import java.sql.DriverManager
import java.sql.Connection
import eVote.model.Organisateur
import eVote.model.Personne


object main {
	def main(args: Array[String]):Unit={
	  
	  /*
	  var test = false
	  var sLogin=""
	  var sMdp=""
	  val p = new Electeur()
	  while (test==false){
	  print("Login: ")
	  sLogin = readLine();
	  print("Mot de passe: ")
	  sMdp = readLine()
	  test = p.seConnecter(sLogin, sMdp)
	  }
	  println("Liste des élections qui vous sont disponibles:")
	  
	  val l = p.listeElectionEnCours(sLogin)
	  
	  var i=0
	  for (i<-0 until l.size)println((i+1)+"-" + l.apply(i))
	  
	  print("Seléctionner une élection: ")
	  var elec = readLine()
	  println("Liste des candidats de l'élection selectionnée: ")
	  val candidat = p.listerCandidat(elec.toInt)
	  
	  var j=0
	  for (j<-0 until candidat.size)println((j+1)+"-" + candidat.apply(j))
	  
	  print("Sélectionner votre candidat: ")
	  var cand = readLine()
	  val id = p.uid
	  if(cand.toInt==1) p.voter(20, 2, id)
	  if(cand.toInt==2) p.voter(21, 2, id)
	  */
	  var test = false
	  var sLogin=""
	  var sMdp=""
	  val organisateur = new Organisateur()
	  while (test==false){
	  print("Login: ")
	  sLogin = readLine();
	  print("Mot de passe: ")
	  sMdp = readLine()
	  test = organisateur.seConnecter(sLogin, sMdp)
	  }
	  /*organisateur.creerElectionRegionale("Election du conseil régional", 11, "25-12-2014", 1)
	  organisateur.creerElectionNationale("Election législative", 1, "25-12-2014", 1)
	  organisateur.creerElectionDepartementale("Election départementale", 75, "25-12-2014", 1)
	  organisateur.creerElectionCommunale("Election com", 56, "25-12-2014", 1)*/
	  val ec = organisateur.listeElectionEnCours()
	  println("LISTE DES ELECTIONS EN COURS")
	  var i=0
	  for (i<-0 until ec.size)println(ec.apply(i))
	  val et = organisateur.listeElectionTermines()
	  println("")
	  println("LISTE DES ELECTION TERMINES")
	  var j=0
	  for (j<-0 until et.size)println(et.apply(j))
	  println("")
	  //val o = new Organisateur();
	  //o.ajouterElecteur("peter", "peter", "Peter Pan", "", "37 Boulevard Jourdan", "", "27-03-1972", "", "", "", "", "", 1)
	  //o.ajouterParti("Parti R", "partir", "La Team Rocket", "24-11-2003", "Pokemon Land", "")
	  /*
	  //val e = new Electeur()
	  //val l = p.listeElectionEnCours(sLogin)
	  //val candidat = p.listerCandidat(2)
	  //var i=0
	  //for (i<-0 until l.size)println(l.apply(i))
	  
	  //var j=0
	  //for (j<-0 until candidat.size)println(candidat.apply(j))
	
	 
	 e.voter(4, 2,1)
	 e.voter(2, 2,2)
	 e.voter(2, 2,3)
	 e.voter(2, 2,4)
	 
	 val o = new Organisateur()
	 val ol = o.validerElection(2)
	 var oli=0
	 for (oli<-0 until ol.size)println(ol.apply(oli))
	 */
	}
}