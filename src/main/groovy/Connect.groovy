/**
 * Created by lucasj8 on 3/23/2016.
 */
import groovy.sql.Sql

//class Connect {}
//@GrabConfig(systemClassLoader=true)
//@Grab('com.oracle:ojdbc6:11g')

class Connect {

  def  url = "jdbc:ucanaccess://C://Users//lucasj8//Documents//MasterPortfoliov0.6.6Offline.accdb"
  def  username = "lucasj8"
  def password = "Joshlori13"
  def  driver = "net.ucanaccess.jdbc.UcanaccessDriver"
  def sql = Sql.newInstance ( url, username, password, driver )

  /*  try {
        println "connected"
        sql.eachRow('select ProjectNo from tblPORTFOLIO;') { row ->
            println row
        }
    } finally {
        sql.close()
    }*/

}