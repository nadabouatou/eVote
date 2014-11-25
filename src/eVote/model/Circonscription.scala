package eVote.model
import eVote.controler.DBConnexion

class Circonscription(_idCir: Int=0, _numero:Int=0) {

  var idCir: Int = _idCir
  var numero: Int = _numero
  
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