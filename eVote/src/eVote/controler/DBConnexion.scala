package eVote.controler
import java.sql.DriverManager
import java.sql.Connection

//Pattern singleton pour la connexion à la BDD

object DBConnexion {
    private var connection:Connection = null
	def conn():Connection = {
    // connect to the database
    val driver = "org.postgresql.Driver"
    val url = "jdbc:postgresql://localhost:5432/eVote"
    val username = "postgres"
    val password = "andrianjohany"
 
    try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)
    } catch {
      case e => e.printStackTrace
    }   
    return connection
  }
}