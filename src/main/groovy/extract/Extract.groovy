package extract
import groovy.sql.Sql
import com.xlson.groovycsv.CsvParser

/**
 * Created by lucasj8 on 7/10/2016.
 */
abstract class Extract {
       def csvFile
   def sql
   def data
     Extract(String csvFile, Sql sql){
       this.csvFile = csvFile
       this.sql = sql
         BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))         
       this.data = CsvParser.parseCsv(bufferReader)        
         }
   abstract parseData()
   abstract insertIntoTable(ArrayList items)
    def removeSpecialCharacters(String s){
        s = s.replace("[ ](?=[ ])|[^-_,A-Za-z0-9 ]+", "");
        s = s.replace(" ", "")
        s = s. replace(",", "")
        s = s.replace("\$", "")       
        s = s.replace("(", "-")   
        s = s.replace(")", "")   
        return s.toString()
    }
    
      def getProjectNo (String tmp){
        def results =sql.firstRow( "select ID FROM tblPortfolio where ProjectNo=${tmp}") ?: sql.firstRow( "select ID FROM tblPortfolio where ProjectNo='TBD'") 
        results.ID
    }

    def getData(){
        return this.data
    }
}
