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
                    if(item.projectNo){
                       projectList.add( [projectNo: item.projectNo ,
                              projectTitle:item.projectTitle,
                              businessCase: removeSpecialCharacters(item.businessCase),
                                CAPrefix: item.prefix,
                              businessDriver: getBuisnessDriver(item.businessDriver),
                              deprecCC: getDeprecCC(item.deprecCC),
                               prefix: getPrefix(item.prefix),
                               CRB: getCrb(item.CRB),
                                PD: getPd(item.PD),
                              category: getCategory(item.category),
                                globalType: getGlobalType(item.globalType),
                                SPPM: getSPPM(item.SPPM),
                              SME: getSME(item.SME),
                              approvedCA: getItem(item.approvedCA)
                            ])}
        }
     return projectList      
    }
    def getBuisnessDriver(String tmp){
        def results =sql.firstRow( "select ID FROM tblBuisnessDriver where BuisnessDriver=${tmp}") ?: sql.firstRow( "select ID FROM tblBuisnessDriver where BuisnessDriver='TBD'")
        return results.ID
    }

    def getDeprecCC(String tmp){
        def results =sql.firstRow( "select ID FROM tblDeprecCC where DeprecCC=${tmp}") ?: [ID:11]
        return results.ID
    }

    def getPrefix(String tmp){
        def results =sql.firstRow( "select ID FROM tblPrefix where Prefix=${tmp}") ?: [ID:6]
        return results.ID
    }

    def getCrb(String tmp){
        def results =sql.firstRow( "select ID FROM tblSPPM where SPPM=${tmp}")?: [ID:10]
        return  results.ID
    }

    def getPd(String tmp){
        def results =sql.firstRow( "select ID FROM tblSPPM where SPPM=${tmp}")?: [ID:10]
        return  results.ID
    }

    def getCategory(String tmp){
        def results =sql.firstRow( "select ID FROM tblCategory where Category=${tmp}") ?: sql.firstRow( "select ID FROM tblCategory where Category='TBD'")
        return results.ID
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

    def insertIntoTable(ArrayList items){
         items.each { item ->
              def re = sql.executeInsert("INSERT into tblPortfolio (ProjectNo, ProjectTitle, BuisnessCase, CAPrefix, BuisnessDriver, Category, GlobalType, SPPM, SME, DeprecCC, Prefix, CRB, PD, ApprovedCA) values ($item.projectNo , $item.projectTitle, $item.businessCase, $item.CAPrefix, $item.businessDriver, $item.category, $item.globalType, $item.SPPM, $item.SME, $item.deprecCC, $item.prefix, $item.CRB, $item.PD, $item.approvedCA);")
                }
         }

    def deleteAll(){
         return sql.execute( "delete * from tblPortfolio;")
    }
    

}

