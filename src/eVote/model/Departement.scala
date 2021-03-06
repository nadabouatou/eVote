package eVote.model


import eVote.controler.DBConnexion

class Departement(val pRegionId: Int=0, val pDepartementId:Int=0,val pNomDepartement: String="") {
  
  
  var departementId: Int = pRegionId
  var regionId: Int = pDepartementId
  var nomDepartement: String = pNomDepartement 
  
  
  // pour ajouter dans la table departement
  def ajouterDepartement(codeRegion:Int,codeDepartement:Int,nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into departement VALUES(" + codeDepartement + "," + codeRegion + ",'" + nom + "')";
    st.execute(req);
    st.close();
  }
  
  // pour supprimer dans la table departement
  /*
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
  * 
  */
  
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

}