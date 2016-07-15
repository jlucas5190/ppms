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
class BudgetExtract extends  Extract{
    def parseData(){
           def budgetList = []
          for (item in data){
            if(!item.projectNo.isEmpty()){
                  def projectNo = getProjectNo(item.projectNo)

            //Roche_curr    
           budgetList.add( [projectNo: projectNo ,
                              budgetYear : 2, //2016
                              budgetCycle: 4, //CURR
                              captial: getItem(item.r_curr_jan_2016) ,
                              expense: getItem(item.r_curr_feb_2016)
                              ])
            //External_curr             
    /*   resourceList.add( [projectNo: projectNo ,
                              projectIndicator: projectIndicator,
                              program: program,
                              resourceType: 2, //External
                              budgetYear : 2, //2016
                              budgetCycle: 4, //CURR
                              jan: getItem(item.e_curr_jan_2016) ,
                              feb: getItem(item.e_curr_feb_2016) ,
                              mar: getItem(item.e_curr_mar_2016) ,
                              apr: getItem(item.e_curr_apr_2016) ,
                              may: getItem(item.e_curr_may_2016) ,
                              jun: getItem(item.e_curr_jun_2016) ,
                              jul: getItem(item.e_curr_jul_2016) ,
                              aug: getItem(item.e_curr_aug_2016) ,
                              sep: getItem(item.e_curr_sep_2016) ,
                              oct: getItem(item.e_curr_oct_2016) ,
                              nov: getItem(item.e_curr_nov_2016) ,
                              dec: getItem(item.e_curr_dec_2016) 
                              ]) 
                          
                 //Roche_t3    
           resourceList.add( [projectNo: projectNo ,
                              projectIndicator: projectIndicator,
                              program: program,
                              resourceType: 1, //Roche
                              budgetYear : 2, //2016
                              budgetCycle: 3, //T3
                              jan: getItem(item.r_t3_jan_2016) ,
                              feb: getItem(item.r_t3_feb_2016) ,
                              mar: getItem(item.r_t3_mar_2016) ,
                              apr: getItem(item.r_t3_apr_2016) ,
                              may: getItem(item.r_t3_may_2016) ,
                              jun: getItem(item.r_t3_jun_2016) ,
                              jul: getItem(item.r_t3_jul_2016) ,
                              aug: getItem(item.r_t3_aug_2016) ,
                              sep: getItem(item.r_t3_sep_2016) ,
                              oct: getItem(item.r_t3_oct_2016) ,
                              nov: getItem(item.r_t3_nov_2016) ,
                              dec: getItem(item.r_t3_dec_2016) 
                              ])
            //External_curr             
       resourceList.add( [projectNo: projectNo ,
                              projectIndicator: projectIndicator,
                              program: program,
                              resourceType: 2, //External
                              budgetYear : 2, //2016
                              budgetCycle: 3, //t3
                              jan: getItem(item.e_t3_jan_2016) ,
                              feb: getItem(item.e_t3_feb_2016) ,
                              mar: getItem(item.e_t3_mar_2016) ,
                              apr: getItem(item.e_t3_apr_2016) ,
                              may: getItem(item.e_t3_may_2016) ,
                              jun: getItem(item.e_t3_jun_2016) ,
                              jul: getItem(item.e_t3_jul_2016) ,
                              aug: getItem(item.e_t3_aug_2016) ,
                              sep: getItem(item.e_t3_sep_2016) ,
                              oct: getItem(item.e_t3_oct_2016) ,
                              nov: getItem(item.e_t3_nov_2016) ,
                              dec: getItem(item.e_t3_dec_2016) 
                              ])      
           */ }
    }
     return budgetList
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
    

}

