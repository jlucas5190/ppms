package tables
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import static org.apache.poi.ss.usermodel.Cell.*

import java.nio.file.Paths
import groovy.json.JsonOutput




/**
 * Created by lucasj8 on 7/4/2016.
 */
class TblResource {
    private String excelFile
    def header = []
    def values = []
    TblResource(String theExcelFile){
        excelFile = theExcelFile
    }

   def readExcel(){
       Paths.get(this.excelFile).withInputStream { input ->

           def workbook = new XSSFWorkbook(input)
           def sheet = workbook.getSheetAt(0)

           for (cell in sheet.getRow(0).cellIterator()) {
               header << cell.stringCellValue
           }

           def headerFlag = true
           for (row in sheet.rowIterator()) {
               if (headerFlag) {
                   headerFlag = false
                   continue
               }
               def rowData = [:]
               for (cell in row.cellIterator()) {
                   def value = ''

                   switch(cell.cellType) {
                       case CELL_TYPE_STRING:
                           value = cell.stringCellValue
                           break
                       case CELL_TYPE_STRING:
                           value = cell.numericCellValue
                           break
                       default:
                           value = ''
                   }
                   rowData << ["${header[cell.columnIndex]}" : value]
               }
               values << rowData
           }
       }


   }
   /* {
        def tabularDataSource = new StreamingXSSFTabularDataSource()
        def file = new File(this.excelFile)
        tabularDataSource.withFile(file) { Iterable<TabularData> tables ->
            for (table in tables) {
                println "Processing a table with columns ${table.columnNames}..."
                for (row in table.rows) {
                    println "Row: ${row}"
                }
                println "Completed table processing."
            }
        }
    }*/
}
