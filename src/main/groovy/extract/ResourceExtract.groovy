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
    def parseData(budgetCycle, budgetYear, key){
           def resourceList = []
        def  projectNo
        def  projectNoText
          for (item in data){
              if(!item.projectNo.isEmpty()){
                   projectNo = getProjectNo(item.projectNo)
                   projectNoText = item.projectNo
              }
                  def projectIndicator = getProjectIndicator(item.projectIndicator)
                  def program = getProgram(item.program)
            //Roche_curr
                def type = item.type.toUpperCase()

                if ( type == "I") {
                    resourceList.add([projectNo       : projectNo,
                                      projectIndicator: projectIndicator,
                                      projectNoText   : projectNoText,
                                      program         : program,
                                      resourceType    : 1, //Internal
                                      budgetYear      : budgetYear, //2016
                                      budgetCycle     : budgetCycle, //CURR
                                      jan: getItem(item[key+ "-Jan"]),
                                      feb: getItem(item[key+ "-Feb"]),
                                      mar: getItem(item[key+ "-Mar"]),
                                      apr: getItem(item[key+ "-Apr"]),
                                      may: getItem(item[key+ "-May"]),
                                      jun: getItem(item[key+ "-Jun"]),
                                      jul: getItem(item[key+ "-Jul"]),
                                      aug: getItem(item[key+ "-Aug"]),
                                      sep: getItem(item[key+ "-Sep"]),
                                      oct: getItem(item[key+ "-Oct"]),
                                      nov: getItem(item[key+ "-Nov"]),
                                      dec: getItem(item[key+ "-Dec"])
                    ])
                }else if (type=="E") {
                    resourceList.add([projectNo       : projectNo,
                                      projectIndicator: projectIndicator,
                                      projectNoText   : projectNoText,
                                      program         : program,
                                      resourceType    : 2, //External
                                      budgetYear      : budgetYear, //2016
                                      budgetCycle     : budgetCycle, //CURR
                                      jan: getItem(item[key+ "-Jan"]),
                                      feb: getItem(item[key+ "-Feb"]),
                                      mar: getItem(item[key+ "-Mar"]),
                                      apr: getItem(item[key+ "-Apr"]),
                                      may: getItem(item[key+ "-May"]),
                                      jun: getItem(item[key+ "-Jun"]),
                                      jul: getItem(item[key+ "-Jul"]),
                                      aug: getItem(item[key+ "-Aug"]),
                                      sep: getItem(item[key+ "-Sep"]),
                                      oct: getItem(item[key+ "-Oct"]),
                                      nov: getItem(item[key+ "-Nov"]),
                                      dec: getItem(item[key+ "-Dec"])
                    ])

             }
    }
     return resourceList      
    }
    
    def getItem(String tmp){
        return tmp.isNumber() ? tmp : '0.0'
    }
 
    def getProjectIndicator(String tmp){
        def results =sql.firstRow( "select ID FROM tblProjectIndicator where ProjectIndicator LIKE(${tmp})") ?: sql.firstRow( "select ID FROM tblProjectIndicator where ProjectIndicator LIKE('TBD')")
        return results.ID
    }

      def getProgram(String tmp){
        def results =sql.firstRow( "select ID FROM tblProgramType where ProgramType LIKE(${tmp})") ?: [ID:8]
        return results.ID
    }
    
    def insertIntoTable(ArrayList items){
         items.each { item ->
           //  println ( " $item.projectNo , $item.projectNoText ,$item.projectIndicator, $item.program , $item.resourceType, $item.budgetYear, $item.budgetCycle, $item.jan, $item.feb, $item.mar, $item.apr, $item.may, $item.jun, $item.jul, $item.aug, $item.sep, $item.oct, $item.nov, $item.dec")
              def re = sql.executeInsert("INSERT into tblResource (ProjectNo, ProjectNoText, ProjectIndicator, ProgramType, ResourceType, BudgetYear, BudgetCycle, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec) values (  $item.projectNo , $item.projectNoText ,$item.projectIndicator, $item.program , $item.resourceType, $item.budgetYear, $item.budgetCycle, $item.jan, $item.feb, $item.mar, $item.apr, $item.may, $item.jun, $item.jul, $item.aug, $item.sep, $item.oct, $item.nov, $item.dec);")
                }
         }

    def deleteAll(){
         return sql.execute( "delete * from tblPortfolio;")
    }
    

}

