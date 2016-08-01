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
    
    def parseData(){
           
                def projectNo
                def budgetCycle
                def budgetYear
                def forecastYear
          for (item in data){         
            
            if(!item.projectNo.isEmpty()){
                   projectNo = getProjectNo(item.projectNo)
              }
              
                budgetCycle = 4
                budgetYear = 2
                forecastYear = 2 
             def type = item.type.toUpperCase()
            if ( type == "CAPITAL"){
            //expense_    
           this.cashflowData.add( [
                              projectNo: projectNo ,
                              budgetCycle: budgetCycle,
                              budgetYear: budgetYear,
                              forecastYear: forecastYear,
                              expenseType: 1,
                              jan: getItem(item.jan),
                              feb: getItem(item.feb),
                              mar: getItem(item.mar),
                              apr: getItem(item.apr),
                              may: getItem(item.may),
                              jun: getItem(item.jun),
                              jul: getItem(item.jul),
                              aug: getItem(item.aug),
                              sep: getItem(item.sep),
                              oct: getItem(item.oct),
                              nov: getItem(item.nov),
                              dec: getItem(item.dec)
                              ])
                          
      
            } else if (type == "EXPENSE") {
            this.cashflowData.add( [
                              projectNo: projectNo ,
                              budgetCycle: budgetCycle,
                              budgetYear: budgetYear,
                              forecastYear: forecastYear,
                              expenseType: 2,
                              jan: getItem(item.jan),
                              feb: getItem(item.feb),
                              mar: getItem(item.mar),
                              apr: getItem(item.apr),
                              may: getItem(item.may),
                              jun: getItem(item.jun),
                              jul: getItem(item.jul),
                              aug: getItem(item.aug),
                              sep: getItem(item.sep),
                              oct: getItem(item.oct),
                              nov: getItem(item.nov),
                              dec: getItem(item.dec)
                              ])   
                    }
             }
    }
    
    def getItem(String s){
         s = s.replace(" ", "")
        s = s. replace(",", "")
        s = s.replace("-\$","-")
        s = s.replace("\$", "")     
         s = s.replace("k", "000")        
        return   s.isLong() ? s.toLong() : '0' 
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
                    this.cashflowData.each { item ->
                def re = sql.executeInsert("INSERT into tblCashflow (ProjectNo, BudgetCycle, BudgetYear, ForecastYear, ExpenseType, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec) values (  $item.projectNo , $item.budgetCycle, $item.budgetYear,  $item.forecastYear , $item.expenseType ,$item.jan, $item.feb, $item.mar, $item.apr, $item.may, $item.jun, $item.jul, $item.aug, $item.sep, $item.oct, $item.nov, $item.dec);")
                }
         }

    
    def deleteAll(){
         return sql.execute( "delete * from tblPortfolio;")
    }
    
  
         def getCashflowData(){
        return this.cashflowData
    }
    
     def getExpenseData(){
       return this.expenseData 
    }


}

