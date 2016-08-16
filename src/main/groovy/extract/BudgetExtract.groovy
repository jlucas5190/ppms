/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extract


import groovy.transform.InheritConstructors

import java.text.NumberFormat
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
                  def projectNoText = item.projectNo

          //CURR
           budgetList.add( [projectNo: projectNo ,
                            projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 2 , //2016  
                             capital: getItem(item.curr_2016_capital) ,
                              expense: getItem(item.curr_2016_expense)
                              ])
                          
            budgetList.add( [projectNo: projectNo ,
                             projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 3 , //2017  
                             capital: getItem(item.curr_2017_capital) ,
                              expense: getItem(item.curr_2017_expense)
                              ])
             
             budgetList.add( [projectNo: projectNo ,
                              projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 4 , //2019  
                             capital: getItem(item.curr_2018_capital) ,
                              expense: getItem(item.curr_2018_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 5 , //2019  
                             capital: getItem(item.curr_2019_capital) ,
                              expense: getItem(item.curr_2019_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 6 , //2020  
                             capital: getItem(item.curr_2020_capital) ,
                              expense: getItem(item.curr_2020_expense)
                              ])     
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 7 , //2021  
                             capital: getItem(item.curr_2021_capital) ,
                              expense: getItem(item.curr_2021_expense)
                              ])   
                
               budgetList.add( [projectNo: projectNo ,
                                projectNoText: projectNoText,
                             budgetCycle:  4, //CURR
                              budgetYear : 2, //2016
                             forecastYear: 8 , //2022  
                             capital: getItem(item.curr_2022_capital) ,
                              expense: getItem(item.curr_2022_expense)
                              ])  
                          
                 //t0
           budgetList.add( [projectNo: projectNo ,
                            projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 2, //2016
                             forecastYear: 2 , //2016  
                             capital: getItem(item.t0_2016_capital) ,
                              expense: getItem(item.t0_2016_expense)
                              ])
                          
            budgetList.add( [projectNo: projectNo ,
                             projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 2, //2016
                             forecastYear: 3 , //2017  
                             capital: getItem(item.t0_2017_capital) ,
                              expense: getItem(item.t0_2017_expense)
                              ])
             
             budgetList.add( [projectNo: projectNo ,
                              projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 2, //2016
                             forecastYear: 4 , //2018
                             capital: getItem(item.t0_2018_capital) ,
                              expense: getItem(item.t0_2018_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 2, //2016
                             forecastYear: 5 , //2019  
                             capital: getItem(item.t0_2019_capital) ,
                              expense: getItem(item.t0_2019_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 2, //2016
                             forecastYear: 6 , //2020  
                             capital: getItem(item.t0_2020_capital) ,
                              expense: getItem(item.t0_2020_expense)
                              ])     
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 2, //2016
                             forecastYear: 7 , //2021  
                             capital: getItem(item.t0_2021_capital) ,
                              expense: getItem(item.t0_2021_expense)
                              ])   
            //m05
           budgetList.add( [projectNo: projectNo ,
                            projectNoText: projectNoText,
                             budgetCycle:  2, //m05
                              budgetYear : 2, //2016
                             forecastYear: 2 , //2016  
                             capital: getItem(item.m05_2016_capital) ,
                              expense: getItem(item.m05_2016_expense)
                              ])
                          
            budgetList.add( [projectNo: projectNo ,
                             projectNoText: projectNoText,
                             budgetCycle:  2, //m05
                              budgetYear : 2, //2016
                             forecastYear: 3 , //2017  
                             capital: getItem(item.m05_2017_capital) ,
                              expense: getItem(item.m05_2017_expense)
                              ])
             
             budgetList.add( [projectNo: projectNo ,
                              projectNoText: projectNoText,
                             budgetCycle:  2, //m05
                              budgetYear : 2, //2016
                             forecastYear: 4 , //2018
                             capital: getItem(item.m05_2018_capital) ,
                              expense: getItem(item.m05_2018_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  2, //m05
                              budgetYear : 2, //2016
                             forecastYear: 5 , //2019  
                             capital: getItem(item.m05_2019_capital) ,
                              expense: getItem(item.m05_2019_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  2, //m05
                              budgetYear : 2, //2016
                             forecastYear: 6 , //2020  
                             capital: getItem(item.m05_2020_capital) ,
                              expense: getItem(item.m05_2020_expense)
                              ])     
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  2, //m05
                              budgetYear : 2, //2016
                             forecastYear: 7 , //2021  
                             capital: getItem(item.m05_2021_capital) ,
                              expense: getItem(item.m05_2021_expense)
                              ])   
                          
                  //t3
		budgetList.add( [projectNo: projectNo ,
                         projectNoText: projectNoText,
                             budgetCycle:  3, //t3
                              budgetYear : 1, //2015
                             forecastYear: 1 , //2015  
                             capital: getItem(item.t3_2015_capital) ,
                              expense: getItem(item.t3_2015_expense)
                              ])   
							  
           budgetList.add( [projectNo: projectNo ,
                            projectNoText: projectNoText,
                             budgetCycle:  3, //t3
                              budgetYear : 1, //2015
                             forecastYear: 2 , //2016  
                             capital: getItem(item.t3_2016_capital) ,
                              expense: getItem(item.t3_2016_expense)
                              ])
                          
            budgetList.add( [projectNo: projectNo ,
                             projectNoText: projectNoText,
                             budgetCycle:  3, //t3
                              budgetYear : 1, //2015
                             forecastYear: 3 , //2017  
                             capital: getItem(item.t3_2017_capital) ,
                              expense: getItem(item.t3_2017_expense)
                              ])
             
             budgetList.add( [projectNo: projectNo ,
                              projectNoText: projectNoText,
                             budgetCycle:  3, //t3
                              budgetYear : 1, //2015
                             forecastYear: 4 , //2018  
                             capital: getItem(item.t3_2018_capital) ,
                              expense: getItem(item.t3_2018_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  3, //t3
                              budgetYear : 1, //2015
                             forecastYear: 5 , //2019  
                             capital: getItem(item.t3_2019_capital) ,
                              expense: getItem(item.t3_2019_expense)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  3, //t3
                              budgetYear : 1, //2015
                             forecastYear: 6 , //2020  
                             capital: getItem(item.t3_2020_capital) ,
                              expense: getItem(item.t3_2020_expense)
                              ])   
                        //t0_2015
           budgetList.add( [projectNo: projectNo ,
                            projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 1, //2015
                             forecastYear: 2 , //2016  
                             capital: getItem(item.t0_2016_capital_2015) ,
                              expense: getItem(item.t0_2016_expense_2015)
                              ])
                          
            budgetList.add( [projectNo: projectNo ,
                             projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 1, //2015
                             forecastYear: 3 , //2017  
                             capital: getItem(item.t0_2017_capital_2015) ,
                              expense: getItem(item.t0_2017_expense_2015)
                              ])
             
             budgetList.add( [projectNo: projectNo ,
                              projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 1, //2015
                             forecastYear: 4 , //2018  
                             capital: getItem(item.t0_2018_capital_2015) ,
                              expense: getItem(item.t0_2018_expense_2015)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 1, //2015
                             forecastYear: 5 , //2019  
                             capital: getItem(item.t0_2019_capital_2015) ,
                              expense: getItem(item.t0_2019_expense_2015)
                              ])     
                          
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 1, //2015
                             forecastYear: 6 , //2020  
                             capital: getItem(item.t0_2020_capital_2015) ,
                              expense: getItem(item.t0_2020_expense_2015)
                              ])     
              budgetList.add( [projectNo: projectNo ,
                               projectNoText: projectNoText,
                             budgetCycle:  1, //t0
                              budgetYear : 1, //2015
                             forecastYear: 7 , //2021  
                             capital: getItem(item.t0_2021_capital_2015) ,
                              expense: getItem(item.t0_2021_expense_2015)
                              ]) 
                }
    }
     return budgetList
    }
    
    def getItem(String tmp){
        if (tmp != null) 
       tmp = removeSpecialCharacters(tmp)
        return tmp ?: '0' 
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
              def re = sql.executeInsert("INSERT into tblBudget (ProjectNo, ProjectNoText, BudgetCycle, BudgetYear, ForecastYear, Capital, Expense) values (  $item.projectNo , $item.projectNoText ,$item.budgetCycle, $item.budgetYear , $item.forecastYear, $item.capital, $item.expense);")
                }
         }

    def deleteAll(){
         return sql.execute( "delete * from tblPortfolio;")
    }
    

}

