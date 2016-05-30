/**
 * Created by lucasj8 on 5/29/2016.
 */

import GroovyExcelParser
import Connect

class Main {

    public static void main(String[]args) {
        def filename = 'C://Users//lucasj8//Downloads//MasterProjectPortfolio.xslm'
        GroovyExcelParser parser = new GroovyExcelParser()
        def (headers, rows) = parser.parse(filename)
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
        }
    }
}
