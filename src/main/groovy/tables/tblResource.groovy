package tables
import com.commercehub.griddle.*;


/**
 * Created by lucasj8 on 7/4/2016.
 */
class TblResource {
    private String excelFile
    TblResource(String theExcelFile){
        excelFile = theExcelFile
    }

   def readExcel()
    {
        def tabularDataSource = new XSSFTabularDataSource()
        tabularDataSource.withFile(this.excelFile) { Iterable<TabularData> tables ->
            for (table in tables) {
                println "Processing a table with columns ${table.columnNames}..."
                for (row in table.rows) {
                    println "Row: ${row}"
                }
                println "Completed table processing."
            }
        }
    }
}
