package eVote.model

import eVote.controler.DBConnexion

class Pays(val pPaysId: Int=0, val pNomPays:String="") {
 
  var paysId: Int = pPaysId 
  var nomPays: String = pNomPays 
  
  def ajouterPays(codePays: Int, nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into pays VALUES(" + codePays + ",'" + nom + "')";
    st.execute(req);
    st.close();
    
	}
 /* 
  def supprimerPays(codePays: Int):Unit={
    val st = DBConnexion.conn().createStatement();
    
     val rs = st.executeQuery("SELECT codereg FROM region WHERE codepays=" + codePays )
      while (rs.next()) {
        val rg = rs.getString("codereg")
        codeRegion  = rg.toInt
        reg.supprimerRegion(codeRegion)
      }
    
    val req="DELETE from pays where codepays=" + codePays;
    st.execute(req);
    st.close();
  }
  * 
  */
  

  def modifierNomPays(codePays:Int,nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE pays set nom='" + nom + "'where codepays=" + codePays ;
    st.execute(req);
    st.close();
    
  }
  
  def modifierCodePays(codePays:Int,nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE pays set codepays=" + codePays + "where nom='" + nom + "'";
    st.execute(req);
    st.close();
    
  }
  
}