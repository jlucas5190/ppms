/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extract
import com.xlson.groovycsv.CsvParser

import groovy.sql.Sql
/**
 *
 * @author za802e
 */
class ProjectExtract {
   def csvFile
   def sql
   ProjectExtract(String csvFile, Sql sql){
       this.csvFile = csvFile
       this.sql = sql
   }
	
   def getData(){
        // File file = new File (fileName)
       BufferedReader bufferReader = new BufferedReader(new FileReader(csvFile))         
       def data  = CsvParser.parseCsv(bufferReader)
       
         def projectList = []
        for(item in data){
            projectList.add( [ProjectNo: item.projectNo , 
                              ProjectTitle:item.projectTitle, 
                               BuisnessCase:item.businessCase, 
                                CAPrefix: item.Prefix,
                                BuisnessDriver: getBuisnessDriver(item.businessDriver)
                            ])
        }
     return   projectList 
   }
   
    def getBuisnessDriver(String tmpDriver){
      sql.eachRow( "select ID FROM tblBuisnessDriver where BuisnessDriver=$tmpDriver;" ){ row->
                results = row.ID
        }
        return  results   
    }
    

}

