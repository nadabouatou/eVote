package eVote.model

import eVote.controler.DBConnexion

class Commune(_codeDep: Int=0, _codeCom:Int=0,_nom: String="") {
  
  var codeDep: Int = _codeDep
  var codeCom: Int = _codeCom
  var codeCan: Int = _
  var nom: String = _nom 
  
  val cant = new Canton();
  
  // pour ajouter dans la table commune
  def ajouterCommune(codeDepartement:Int,codeCommunale:Int,nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into commune VALUES(" + codeCommunale + "," + codeDepartement + ",'" + nom + "')";
    st.execute(req);
    st.close();
  }
  
  // pour supprimer dans la table Commune
  def supprimerCommune(codeCommune:Int):Unit={
    val st = DBConnexion.conn().createStatement();
    
    val rs = st.executeQuery("SELECT codecan FROM canton WHERE codecom=" + codeCommune )
      while (rs.next()) {
        val can = rs.getString("codecan")
        codeCan  = can.toInt
        cant.supprimerCanton(codeCan);
      }
    
    val req="DELETE from commune where codecom=" + codeCommune;
    st.execute(req);
    st.close();
  }

   // pour modifier le code d'un departement dans la table commune
  def modifierCodeRegionDepartement(codeCommune: Int,codeDep: Int):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE commune set codedep=" + codeDep + "where codereg=" + codeCommune;
    st.execute(req);
    st.close();
    
  }
  
  // pour modifier le nom d'une commune
   def modifierNomDepartement(codeCommune: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE commune set denommination='" + nom + "'where codedep=" + codeCommune;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code d'une commune
   def modifierCodeDepartement(codeDepar: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE commune set codecom=" + codeDepar + " where denomination=" + nom;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code et le nom d'une commune
   def modifierRegion(codeCommune: Int, nomDepar: String):Unit={
     
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE commune set codecom=" + codeCommune + ",denommination='" + nomDepar + "'where codecom=" + codeCommune;
    st.execute(req);
    st.close();
     
   }
  
}