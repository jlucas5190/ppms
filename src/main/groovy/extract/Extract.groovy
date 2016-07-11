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
    def removeSpecialCharacters(String s){
        return s.replace("[ ](?=[ ])|[^-_,A-Za-z0-9 ]+", "");
    }
    
      def getProjectNo (String tmp){
        def results =sql.firstRow( "select ID FROM tblPortfolio where ProjectNo=${tmp}") ?: [ID:'N\"A']
        results.ID
    }
}
