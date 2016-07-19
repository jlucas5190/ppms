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
class CashflowExtract extends  Extract{
    def cashflowData = []
    def expenseData = []
    
    def parseData(){
           
                def projectNo
                    def creditType
          for (item in data){         
            
            if(!item.projectNo.isEmpty()){
                   projectNo = getProjectNo(item.projectNo)
                  creditType = item.creditType}
              
             def type = item.type.toUpperCase()
            if ( type == "CAPITAL"){
            //expense_    
           this.cashflowData.add( [projectNo: projectNo ,
                              projectTitle: projectTitle                             
                              ])
                          
      
            } else if (type == "EXPENSE") {
            this.expenseData.add( [projectNo: projectNo ,
                              projectIndicator: projectTitle                             
                              ])   
            }
    }
     //setCashflowlist(cashflowList)
    // setExpenselist(expenseList)
    }
    
    def getItem(String tmp){
        return tmp ?: '0.0' 
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
              def re = sql.executeInsert("INSERT into tblResource (ProjectNo, ProjectIndicator, ProgramType, ResourceType, BudgetYear, BudgetCycle, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec) values (  $item.projectNo , $item.projectIndicator, $item.program , $item.resourceType, $item.budgetYear, $item.budgetCycle, $item.jan, $item.feb, $item.mar, $item.apr, $item.may, $item.jun, $item.jul, $item.aug, $item.sep, $item.oct, $item.nov, $item.dec);")
                }
         }

    def deleteAll(){
         return sql.execute( "delete * from tblPortfolio;")
    }
    
    
     def setCashflowlist(ArrayList data){
        this.cashflowData = data
    }
    
     def setExpenselist(ArrayList data){
        this.expenseData = data
    }

         def getCashflowData(){
        return this.cashflowData
    }
    
     def getExpenseData(){
       return this.expenseData 
    }


}

