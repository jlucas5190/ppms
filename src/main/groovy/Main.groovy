
/**
 * Created by lucasj8 on 5/29/2016.
 */
import groovy.sql.Sql
import org.apache.poi.openxml4j.opc.OPCPackage
import extract.*
import tables.TblResource
import extract.ReadWriteExcelFile
import extract.XLSX2CSV



class Main {
    
    static String url = "jdbc:ucanaccess://"+System.getProperty("user.dir")+"\\resources\\ppms.accdb"
    static String username = ""
    static String password = ""
    static String driver = "net.ucanaccess.jdbc.UcanaccessDriver"
    static Random rand = new Random()
    static String projectFile = System.getProperty("user.dir") +"\\resources\\projects.csv"
    static String resourcesFile = System.getProperty("user.dir") +"\\resources\\resources.csv"
    static String budgetFile = System.getProperty("user.dir") +"\\resources\\budget.csv"
        static String cashflowFile = System.getProperty("user.dir") +"\\resources\\cashflow.csv"

   
    static int max = 100000
    static int reportToMax = 100.00
    static int sppmMax =  10

  static budgetCycle = ['T0','MO5', 'T3']
    //static budgetCycle = [1, 2, 3]
    static budgetYear = [ '2015', '2016', '2017', '2018','2019','2020','2021', '2022']
    //static budgetYear = [1, 2, 3, 4, 5, 6]

    public static main(args) {
        Sql sql = Sql.newInstance(url, username, password, driver)
      // new ExcelBuilder(currentFile).eachLine {
       //    println "First column on row ${it.rowNum} = ${cell(0)}"
      // }
      //  TblResource resource =  new TblResource(currentFile)
       // resource.readExcel()
       //   ReadWriteExcelFile file = new ReadWriteExcelFile();
        //file.readXLSXFile(currentFile);
      //  File xlsxFile = new File(currentFile);
      /*  if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return;
        }*/
          
       /* ProjectExtract project =  new ProjectExtract(projectFile, sql)
        def projectData = project.parseData()
      //  project.insertIntoTable(projectData)
        projectData.each {println it}

        ResourceExtract resource = new ResourceExtract(resourcesFile, sql)
        def resourceData = resource.parseData()
        resource.insertIntoTable(resourceData)
         resourceData.each {println it}


        BudgetExtract budget = new BudgetExtract(budgetFile, sql)
        def budgetData = budget.parseData()
        budgetData.each {println it}
        budget.insertIntoTable(budgetData)*/
          
        
        CashflowExtract cashflow = new CashflowExtract(cashflowFile, sql)
        cashflow.parseData()
        cashflow.getCashflowData().each{println it}
       
        cashflow.insertIntoTable([])
       
        
        
        sql.close()

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
 /*
   public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Use:");
            System.err.println("  XLSX2CSV <xlsx file> [min columns]");
            return;
        }

        File xlsxFile = new File(args[0]);
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return;
        }

        int minColumns = -1;
        if (args.length >= 2)
            minColumns = Integer.parseInt(args[1]);

        // The package open is instantaneous, as it should be.
        OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
		XLSX2CSV xlsx2csv = new XLSX2CSV(p, System.out, minColumns);
		xlsx2csv.process();
		p.close();
	}
         */
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