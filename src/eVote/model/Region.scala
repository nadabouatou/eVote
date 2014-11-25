package eVote.model

import eVote.controler.DBConnexion

class Region(val pPaysId: Int=0, val pRegionId:Int=0,val pNomRegion: String="") {
  
  var paysId: Int = pPaysId
  var regionId: Int = pRegionId
  var nomRegion: String = pNomRegion 
  
  // Pour ajouter dans la table region
  def ajouterRegion(codePays: Int, codeRegion: Int, nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into region VALUES(" + codeRegion + "," + codePays + ",'" + nom + "')";
    st.execute(req);
    st.close();
  }
  /*
  // pour supprimer dans la table region
  def supprimerRegion(codeRegion: Int):Unit={
    val st = DBConnexion.conn().createStatement();
    
    val rs = st.executeQuery("SELECT codedep FROM departement WHERE codereg=" + codeRegion )
      while (rs.next()) {
        val dp = rs.getString("codedep")
        codeDep = dp.toInt
        dep.supprimerDepartement(codeDep);
      }
    
    val req="DELETE from region where codereg=" + codeRegion;
    st.execute(req);
    st.close();
  }
  * 
  */
  
  // pour modifier le code d'un pays dans la table region
  def modifierCodePaysRegion(codeRegion: Int,codePays: Int):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE region set codepays=" + codePays + "where codereg=" + codeRegion;
    st.execute(req);
    st.close();
    
  }
  
  // pour modifier le nom d'une region
   def modifierNomRegion(codeRegion: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE region set denommination='" + nom + "'where codereg=" + codeRegion;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code d'une region
   def modifierCodeRegion(codeRegion: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE region set codereg=" + codeRegion + " where denomination=" + nom;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code et le nom d'une region
   def modifierRegion(codeRegion: Int, nomRegion: String):Unit={
     
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE region set codereg=" + codeRegion + ",denommination='" + nomRegion + "'where codereg=" + codeRegion;
    st.execute(req);
    st.close();
     
   }

}