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
    
    def parseData(budgetYear, forecastYear, key){
           
                def projectNo
                def budgetCycle
                def projectNoText
              //  def budgetYear
                //def forecastYear
          for (item in data){         
            
            if(!item.projectNo.isEmpty()){
                   projectNo = getProjectNo(item.projectNo)
                    projectNoText = item.projectNo
              }
              
                budgetCycle = 4
                budgetYear = budgetYear
                forecastYear = forecastYear
             def type = item.type.toUpperCase()
            if ( type == "CAPITAL"){
            //expense_    
           this.cashflowData.add( [
                              projectNo: projectNo ,
                              projectNoText: projectNoText ,
                              budgetCycle: budgetCycle,
                              budgetYear: budgetYear,
                              forecastYear: forecastYear,
                              expenseType: 1,
                              jan: getItem(item[key+ "-jan"]),
                              feb: getItem(item[key+ "-feb"]),
                              mar: getItem(item[key+ "-mar"]),
                              apr: getItem(item[key+ "-apr"]),
                              may: getItem(item[key+ "-may"]),
                              jun: getItem(item[key+ "-jun"]),
                              jul: getItem(item[key+ "-jul"]),
                              aug: getItem(item[key+ "-aug"]),
                              sep: getItem(item[key+ "-sep"]),
                              oct: getItem(item[key+ "-oct"]),
                              nov: getItem(item[key+ "-nov"]),
                              dec: getItem(item[key+ "-dec"])
                              ])
                          
      
            } else if (type == "EXPENSE") {
            this.cashflowData.add( [
                              projectNo: projectNo ,
                              projectNoText: projectNoText ,
                               budgetCycle: budgetCycle,
                              budgetYear: budgetYear,
                              forecastYear: forecastYear,
                              expenseType: 2,
                              jan: getItem(item[key+ "-jan"]),
                              feb: getItem(item[key+ "-feb"]),
                              mar: getItem(item[key+ "-mar"]),
                              apr: getItem(item[key+ "-apr"]),
                              may: getItem(item[key+ "-may"]),
                              jun: getItem(item[key+ "-jun"]),
                              jul: getItem(item[key+ "-jul"]),
                              aug: getItem(item[key+ "-aug"]),
                              sep: getItem(item[key+ "-sep"]),
                              oct: getItem(item[key+ "-oct"]),
                              nov: getItem(item[key+ "-nov"]),
                              dec: getItem(item[key+ "-dec"])
                              ])   
                    }
             }
             return this.cashflowData;
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
                 items.each { item ->
                def re = sql.executeInsert("INSERT into tblCashflow (ProjectNo, ProjectNoText, BudgetCycle, BudgetYear, ForecastYear, ExpenseType, jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec) values (  $item.projectNo , $item.projectNoText ,$item.budgetCycle, $item.budgetYear,  $item.forecastYear , $item.expenseType ,$item.jan, $item.feb, $item.mar, $item.apr, $item.may, $item.jun, $item.jul, $item.aug, $item.sep, $item.oct, $item.nov, $item.dec);")
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

