/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extract
import com.xlson.groovycsv.CsvParser
import groovy.transform.InheritConstructors


/**
 *
 * @author za802e
 */
@InheritConstructors
class ProjectExtract extends  Extract{
  
    
    def parseData(){
       
         def projectList = []
                for(item in data){
            projectList.add( [projectNo: item.projectNo ,
                              projectTitle:item.projectTitle,
                              businessCase: item.businessCase,
                                CAPrefix: item.Prefix,
                              businessDriver: getBuisnessDriver(item.businessDriver),
                              category: getCategory(item.category),
                                globalType: getGlobalType(item.globalType),
                                SPPM: getSPPM(item.SPPM),
                              SME: getSME(item.SME)
                            ])
        }
     return projectList      
    }
    def getBuisnessDriver(String tmp){
        def results =sql.firstRow( "select ID FROM tblBuisnessDriver where BuisnessDriver=${tmp}") ?: sql.firstRow( "select ID FROM tblBuisnessDriver where BuisnessDriver='TBD'")
        results.ID
    }

    def getCategory(String tmp){
        def results =sql.firstRow( "select ID FROM tblCategory where Category=${tmp}") ?: sql.firstRow( "select ID FROM tblCategory where Category='TBD'")
        results.ID
    }

    def getGlobalType(String tmp){
        def results =sql.firstRow( "select ID FROM tblGlobalType where GlobalType=${tmp}") ?: sql.firstRow( "select ID FROM tblGlobalType where GlobalType='TBD'")
        return  results.ID
    }


    def getSPPM(String tmp){
        def results =sql.firstRow( "select ID FROM tblSPPM where SPPM=${tmp}")?: [ID:10]
        return  results.ID
    }

    def getSME(String tmp){
        def results =sql.firstRow( "select ID FROM tblSME where SME=${tmp}")?: [ID:9]
         return  results.ID
    }

    def insertIntoProjectTable(ArrayList items){
         items.each { item ->
              def re = sql.executeInsert("INSERT into tblPortfolio (ProjectNo, ProjectTitle, BuisnessCase, CAPrefix, BuisnessDriver, Category, GlobalType, SPPM, SME) values (  $item.projectNo , $item.projectTitle, $item.businessCase, $item.CAPrefix, $item.businessDriver, $item.category, $item.globalType, $item.SPPM, $item.SME);")
                }
         }

    def deleteAll(){
         return sql.execute( "delete * from tblPortfolio;")
    }
    

}

