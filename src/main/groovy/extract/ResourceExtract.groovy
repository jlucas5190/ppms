/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extract


import groovy.transform.InheritConstructors
/**
 *
 * @author za802e
 */
@InheritConstructors
class ResourceExtract extends  Extract{
    def parseData(){
           def resourceList = []
      
           for (i = 0; i < data.projectNo.size(); i++){
                  def projectNo = data.projectNo[1]
                  def projectIndicator = 2
                  def program = 3
             
        
             for(resource in data){
                 
            //Roche_curr    
           resourceList.add( [projectNo: projectNo ,
                              projectIndicator:projectIndicator,
                              program: program,
                              jan: resource.r_curr_jan_2016,
                             ])
            //External_curr             
            resourceList.add( [projectNo: projectNo ,
                              projectIndicator:projectIndicator,
                              program: program,
                              jan: resource.e_curr_jan_2016,
                             ])     
        }
    }
     return resourceList      
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

