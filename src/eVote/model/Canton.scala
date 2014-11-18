package eVote.model

import eVote.controler.DBConnexion

class Canton {
  
  // pour ajouter dans la table canton
  def ajouterCanton(codeCommunale:Int,codeCanton:Int,nom:String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req = "INSERT into canton VALUES(" + codeCanton + "," + codeCommunale + ",'" + nom + "')";
    st.execute(req);
    st.close();
  }
  
  // pour supprimer dans la table canton
  def supprimerCanton(codeCanton:Int):Unit={
    val st = DBConnexion.conn().createStatement();
    val req="DELETE from canton where codecan=" + codeCanton;
    st.execute(req);
    st.close();
  }

  // pour modifier le code d'une commune dans la table canton
  def modifierCodeCommuneCanton(codeCommune: Int,codeCanton:Int):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE canton set codecom=" + codeCommune + "where codecan=" + codeCanton;
    st.execute(req);
    st.close();
    
  }
  
  // pour modifier le nom d'un canton
   def modifierNomCanton(codeCanton: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE canton set denommination='" + nom + "'where codecan=" + codeCanton;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code d'un canton
   def modifierCodeCanton(codeCanton: Int,nom: String):Unit={
    
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE canton set codecan=" + codeCanton + " where denomination=" + nom;
    st.execute(req);
    st.close();
    
  }
   
   // pour modifier le code et le nom d'un canton
   def modifierCanton(codeCanton: Int, nom: String):Unit={
     
    val st = DBConnexion.conn().createStatement();
    val req="UPDATE canton set codecan=" + codeCanton + ",denommination='" + nom + "'where codecan=" + codeCanton;
    st.execute(req);
    st.close();
     
   }
   
}