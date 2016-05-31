/**
 * Created by lucasj8 on 5/29/2016.
 */

import GroovyExcelParser
import Connect
import groovy.sql.Sql

import java.sql.Connection
import java.sql.PreparedStatement
import java.util.Random

class Main {
    static String excel = 'C:\\Users\\lucasj8\\Downloads\\MasterProjectPortfolio.xlsx'
    static String url = "jdbc:ucanaccess://C://Users//lucasj8//Documents//MasterPortfoliov0.6.6Offline.accdb"
    static String username = ""
    static String password = ""
    static String driver = "net.ucanaccess.jdbc.UcanaccessDriver"
    static Random rand = new Random()
    static int max = 1000000
  static budgetCycle = ['M05', 'T3', 'T0']
    //static budgetCycle = [1, 2, 3]
    static budgetYear = ['2016', '2017', '2018', '2019', '2020', '2021']
    //static budgetYear = [1, 2, 3, 4, 5, 6]

    public static void main(String[] args) {
        Sql sql = Sql.newInstance(url, username, password, driver)

        /*GroovyExcelParser parser = new GroovyExcelParser()
        def (headers, rows) = parser.parse(excel, 2)
        println 'Headers'
        println '------------------'
        headers.each { header ->
            println header
        }
        println "\n"
        println 'Rows'
        println '------------------'
        rows.each { row ->
            println parser.toXml(headers, row)
        }*/

        //Connect sql = new Connect(url,username,password,driver)

       def result = populateBudgetArrayList(Sql.newInstance(url, username, password, driver))
     //   result.each{
       //     println it
      //  }
    //   deleteAllFromTable(Sql.newInstance(url, username, password, driver), "tblBUDGET")
     insertIntoBudgetTable(Sql.newInstance(url, username, password, driver), result)
            }

    def static deleteAllFromTable(Sql sql, String table){
        try {
           def re = sql.execute("DELETE * FROM tblBUDGET;")
            println re
        }finally {
            sql.close()
        }
    }
    def static insertIntoBudgetTable(Sql sql, ArrayList result){
        try{
           result.each {
            def params =[it.ProjectNo, it.Capital, it.BudgetCycle, it.BudgetYear, it.BudgetCycleYear]
             //   println params
              def re = sql.executeInsert("INSERT into tblBUDGET (ProjectNo, Capital, BudgetCycle, BudgetYear, BudgetCycleYear) values (?,?,?,?,?)",params)
               println re
            }
              // sql.execute "INSERT into tblBUDGET (ProjectNo, Capital, Expense, BudgetCycle, BudgetYear, BudgetCycleYear)" +
                //       " values (0, 0, 0 , 0, 0, 0 );"
        //   }
        }finally {
            sql.close()
        }
    }


    def static populateBudgetArrayList(Sql sql) {
        def projectNo = []
        try {
            sql.eachRow('select * from tblPORTFOLIO;') { row ->
                for (def cycle = 0; cycle < 3; cycle++) {
                    for (def year = 4; year < 10; year++) {
                        projectNo.addAll([ProjectNo      : row.ProjectNo,
                                          Capital        : rand.nextInt(max + 1),
                                          Expense        : rand.nextInt(max + 1),
                                          BudgetCycle    : getBudgetCycle(sql,cycle),
                                          BudgetYear     : getBudgetYear(4),
                                          BudgetCycleYear: getBudgetCycleYear(sql,cycle, year)])
                    }
                }
            }
        } finally {
            sql.close()
        }
        return projectNo
    }

    def static getBudgetYear(int intBudgetYear) {
        return intBudgetYear+1
    }

    def static getBudgetCycle(Sql sql,int intBudgetCycle) {
      def tmpBudgetCycle = budgetCycle[intBudgetCycle]
        def results
        sql.eachRow( "select BudgetCycleId FROM BudgetCycle where BudgetCycle=$tmpBudgetCycle;" ){ row->
                results = row.BudgetCycleId
        }
        return  results
    }

    def static getBudgetCycleYear(Sql sql,int intBudgetCycle, int intBudgetYear) {
        def tmpBudgetCycleYear = budgetCycle[intBudgetCycle] + budgetYear[intBudgetYear]
        def results
        sql.eachRow( "select tblBudgetCycleYearId FROM BudgetCycleYear where BudgetCycleYear=$tmpBudgetCycleYear;" ){ row->
            results = row.tblBudgetCycleYearId
        }
        return  (results != null) ? results : 3
    }
}