package eVote.model

<<<<<<< HEAD
class Departement(val pRegionId:Int, val pDepartementId:Int, val pNomDepartement:String) {
	var regionId = pRegionId 
	var departementId = pDepartementId 
	var nomDepartement = pNomDepartement 
  
	def ajouterDepartement(Region:Int,codeDepartement:Int,nom:String):Unit={}
	def modfierDepartement(codeDepartement:Int, CodeRegion:Int, nomDepartement:String):Unit={}
	def supprimerDepartement(codeDepartement:Int):Unit={}
=======
import eVote.controler.DBConnexion

class Departement(_codeReg: Int=0, _codeDep:Int=0,_nom: String="") {
  
  
  var codeDep: Int = _codeDep
  var codeReg: Int = _codeReg
  var codeCom: Int = _
  var nom: String = _nom 
  
  val commune = new Commune()
  
  // pour ajouter dans la table departement
  def ajouterDepartement(codeRegion:Int,codeDepartement:Int,nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into departement VALUES(" + codeDepartement + "," + codeRegion + ",'" + nom + "')";
    st.execute(req);
    st.close();
  }
  
  // pour supprimer dans la table departement
  def supprimerDepartement(codeDepartement:Int):Unit={
    val st = DBConnexion.conn().createStatement();
    
    val rs = st.executeQuery("SELECT codecom FROM commune WHERE codedep=" + codeDepartement )
      while (rs.next()) {
        val com = rs.getString("codecom")
        codeCom   = com.toInt
        commune.supprimerCommune(codeCom);
      }
    
    val req="DELETE from departement where codedep=" + codeDepartement;
    st.execute(req);
    st.close();
  }
  
   // pour modifier le code d'une region dans la table departement
  def modifierCodeRegionDepartement(codeDepar: Int,codeRegion: Int):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE departement set codereg=" + codeRegion + "where codedep=" + codeDepar;
    st.execute(req);
    st.close();
    
  }
  
  // pour modifier le nom d'un departement
   def modifierNomDepartement(codeDepar: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE departement set denommination='" + nom + "'where codedep=" + codeDepar;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code d'un departement
   def modifierCodeDepartement(codeDepar: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE departement set codedep=" + codeDepar + " where denomination=" + nom;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code et le nom d'un departement
   def modifierRegion(codeDepar: Int, nomDepar: String):Unit={
     
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE departement set codedep=" + codeDepar + ",denommination='" + nomDepar + "'where codedep=" + codeDepar;
    st.execute(req);
    st.close();
     
   }
  
>>>>>>> origin/master
}