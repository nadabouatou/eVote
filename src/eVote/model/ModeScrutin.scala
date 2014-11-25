package eVote.model

import java.util.Observable
import java.util.Observer
import eVote.controler.DBConnexion
import java.sql.ResultSet

class ModeScrutin(pNum:Int, pModeName:String){
	var modeScrutinNumero:Int=pNum
	var modeScrutinName:String=pModeName
	
	def SaveToDB:Unit={
		var c = DBConnexion.conn()
		val t = testId(this.modeScrutinNumero)
		if(t!=0){
			var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
			var prepare = c.prepareStatement("INSERT INTO scrutin (scrutinid,description) VALUES('"+this.modeScrutinNumero  +"', '"+this.modeScrutinName+"')")
			prepare.executeUpdate()
		}
		c.close()
	}
	
	def DeleteFromDB(pId:Int):Unit={
		var c = DBConnexion.conn()
		var statement = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)
		var prepare = c.prepareStatement("DELETE FROM scrutin WHERE scrutinid = '"+pId+"")
		prepare.executeUpdate()
		c.close()	
	}
	
	def testId(pId:Int):Int={
	  var c = DBConnexion.conn()
	  var li = 0
      val statement = c.createStatement()
      val resultSet = statement.executeQuery("SELECT scrutinid FROM scrutin WHERE scrutinid = '"+pId+"'")
      while (resultSet.next()) {
        val idSearched = resultSet.getString("scrutinid")
        li = idSearched.toInt
      }
	  c.close()
	  return li
	}
}