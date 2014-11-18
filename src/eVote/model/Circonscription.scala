package eVote.model

import eVote.controler.DBConnexion

class Circonscription {

  // pour ajouter dans la table circonscription
  def ajouterCircons(idCir:Int,numero:Int):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into circonscription VALUES(" + idCir + "," + numero + ")";
    st.execute(req);
    st.close();
  }
  
  //pour supprimer dans la table circonscription
  def supprimerCirsonc(idCir:Int):Unit={
    val st = DBConnexion.conn().createStatement();
    val req="DELETE from circonscription where idcir=" + idCir;
    st.execute(req);
    st.close();
  }
  
  
  
}