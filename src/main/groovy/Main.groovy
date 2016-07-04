/**
 * Created by lucasj8 on 5/29/2016.
 */

import GroovyExcelParser
import Connect
import groovy.sql.Sql
import tables.TblResource

import java.sql.Connection
import java.sql.PreparedStatement
import java.util.Random

class Main {
    static String excel = 'C:\\Users\\lucasj8\\Downloads\\MasterProjectPortfolio.xlsx'
    static String url = "jdbc:ucanaccess://C://Users//lucasj8//Documents//ppms0.9.2.beta_OFFLINE.accdb"
    static String username = ""
    static String password = ""
    static String driver = "net.ucanaccess.jdbc.UcanaccessDriver"
    static Random rand = new Random()

    static int max = 100000
    static int reportToMax = 100.00
    static int sppmMax =  10

  static budgetCycle = ['T0','MO5', 'T3']
    //static budgetCycle = [1, 2, 3]
    static budgetYear = [ '2015', '2016', '2017', '2018','2019','2020','2021', '2022']
    //static budgetYear = [1, 2, 3, 4, 5, 6]

    public static main(args) {
        Sql sql = Sql.newInstance(url, username, password, driver)
        TblResource resource =  new TblResource("C:\\Users\\lucasj8\\Desktop\\ppms.xslx")
        resource.readExcel()
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

       //def resultExpense = populateBudgetArrayList(Sql.newInstance(url, username, password, driver))
     // def resultCapital = populateBudgetArrayList(Sql.newInstance(url, username, password, driver))
       // def resultResource = populateResourceArrayList(Sql.newInstance(url, username, password, driver))

       // def result =[]
      //  result.add([ProjectNo:244, BudgetCycle:3, BudgetYear:7, BudgetCycleYear:8, Jan:77381, Feb:42289, Mar:15994, Apr:19871, May:40422, Jun:30781, Jul:74519, Aug:57338, Sep:8463, Oct:73446, Nov:18704, Dec:98725])


    //   deleteAllFromTable(Sql.newInstance(url, username, password, driver), "tblBUDGET")
     //   insertIntoResourceTable(Sql.newInstance(url, username, password, driver),"tblResource",resultResource)
      // insertIntoCashflowTable(Sql.newInstance(url, username, password, driver),"tblCapitalCashflow",resultCapital)
      // insertIntoCashflowTable(Sql.newInstance(url, username, password, driver),"tblExpenseCashflow" ,resultExpense)
      // insertIntoBudgetTable(Sql.newInstance(url, username, password, driver),"tblBudget" ,resultCapital)

    }

    def static deleteAllFromTable(Sql sql, String table){
        try {
           def re = sql.execute("DELETE * FROM tblBUDGET;")
            println re
        }finally {
            sql.close()
        }
    }



    def static insertIntoBudgetTable(Sql sql,String tbl ,ArrayList result){
        try{

            result.each {
                def params =[it.ProjectNo, it.BudgetCycle, it.BudgetYear, it.ForecastYear, rand.nextInt(max  * 10),rand.nextInt(max  *  10) ]
                def re = sql.executeInsert( "INSERT into "+tbl+" (ProjectNo, BudgetCycle, BudgetYear, ForecastYear,Capital,Expense) values (?, ?, ? , ?, ?, ? );", params)
                println re


            }
        }finally {
            sql.close()
        }
    }

    def static insertIntoCashflowTable(Sql sql,String tbl, ArrayList result){
        try{

           result.each {
            def params =[it.ProjectNo, it.BudgetCycle, it.BudgetYear, it.ForecastYear, it.Jan, it.Feb, it.Mar, it.Apr, it.May, it.Jun, it.Jul, it.Aug, it.Sep, it.Oct, it.Nov,it.Dec ]
             //   println params
              def re = sql.executeInsert("INSERT into "+tbl+" (ProjectNo, BudgetCycle, BudgetYear, ForecastYear, JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",params)
               println re

            }
              // sql.execute "INSERT into tblBUDGET (ProjectNo, Capital, Expense, BudgetCycle, BudgetYear, BudgetCycleYear)" +
                //       " values (0, 0, 0 , 0, 0, 0 );"
        //   }
        }finally {
            sql.close()
        }
    }
	
	    def static insertIntoResourceTable(Sql sql,String tbl, ArrayList result){
        try{

           result.each {
            def params =[it.ProjectNo, it.BudgetCycle, it.BudgetYear, it.ForecastYear,rand.nextInt( sppmMax  ) ,it.Jan, it.Feb, it.Mar, it.Apr, it.May, it.Jun, it.Jul, it.Aug, it.Sep, it.Oct, it.Nov,it.Dec ]
             //   println params
              def re = sql.executeInsert("INSERT into "+tbl+" (ProjectNo, BudgetCycle, BudgetYear, ForecastYear, ReportTo ,JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",params)
               println re

            }

        }finally {
            sql.close()
        }
    }




    def static populateBudgetArrayList(Sql sql) {
        def projectNo = []
        try {
            sql.eachRow('select * from tblPortfolio;') { row ->
                for (def cycle = 0; cycle < 3; cycle++) {
                    for (def cycleYear = 0; cycleYear <= 3; cycleYear++) {
                        for (def forecastYear = 0; forecastYear < 8; forecastYear++) {
                        if ( forecastYear >=  cycleYear){
                            projectNo.addAll([ProjectNo  : row.ID,
                                          BudgetCycle    : getBudgetCycle(sql,cycle),
                                          BudgetYear     : getBudgetYear(sql, cycleYear),
                                          ForecastYear: getBudgetYear(sql, forecastYear),
                                          Jan            : rand.nextInt(max + 1),
                                          Feb            : rand.nextInt(max + 1),
                                          Mar            : rand.nextInt(max + 1),
                                          Apr            : rand.nextInt(max + 1),
                                          May            : rand.nextInt(max + 1),
                                          Jun            : rand.nextInt(max + 1),
                                          Jul            : rand.nextInt(max + 1),
                                          Aug            : rand.nextInt(max + 1),
                                          Sep            : rand.nextInt(max + 1),
                                          Oct           : rand.nextInt(max + 1),
                                          Nov            : rand.nextInt(max + 1),
                                          Dec            : rand.nextInt(max + 1)

                            ])
                        }
                    }
                }
            }
            }
        } finally {
            sql.close()
        }
        return projectNo
    }


    def static populateResourceArrayList(Sql sql) {
        def projectNo = []
        try {
            sql.eachRow('select * from tblPortfolio;') { row ->
                for (def cycle = 0; cycle < 3; cycle++) {
                    for (def cycleYear = 1; cycleYear <= 3; cycleYear++) {
                        for (def forecastYear = 1; forecastYear < 8; forecastYear++) {
                            if ( forecastYear >=  cycleYear) {
                                projectNo.addAll([ProjectNo   : row.ID,
                                                  BudgetCycle : getBudgetCycle(sql, cycle),
                                                  BudgetYear  : getBudgetYear(sql, cycleYear),
                                                  ForecastYear: getBudgetYear(sql, forecastYear),
                                                  ReportTo    : rand.nextInt(reportToMax + 1),
                                                  Jan         : rand.nextInt(reportToMax + 1),
                                                  Feb         : rand.nextInt(reportToMax + 1),
                                                  Mar         : rand.nextInt(reportToMax + 1),
                                                  Apr         : rand.nextInt(reportToMax + 1),
                                                  May         : rand.nextInt(reportToMax + 1),
                                                  Jun         : rand.nextInt(reportToMax + 1),
                                                  Jul         : rand.nextInt(reportToMax + 1),
                                                  Aug         : rand.nextInt(reportToMax + 1),
                                                  Sep         : rand.nextInt(reportToMax + 1),
                                                  Oct         : rand.nextInt(reportToMax + 1),
                                                  Nov         : rand.nextInt(reportToMax + 1),
                                                  Dec         : rand.nextInt(reportToMax + 1)

                                ])
                            }
                        }
                    }
                }
            }
        } finally {
            sql.close()
        }
        return projectNo
    }

    def static getBudgetYear(Sql sql,int intBudgetYear) {
        def tmpBudgetYear = budgetYear[intBudgetYear]
        def results
        sql.eachRow( "select ID FROM tblBudgetYear where BudgetYear=$tmpBudgetYear;" ){ row->
            results = row.ID
        }
        return  results
    }

    def static getBudgetCycle(Sql sql,int intBudgetCycle) {
      def tmpBudgetCycle = budgetCycle[intBudgetCycle]
        def results
        sql.eachRow( "select ID FROM tblBudgetCycle where BudgetCycle=$tmpBudgetCycle;" ){ row->
                results = row.ID
        }
        return  results
    }


}