package reader

import model.Palpite
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.InputStream

class PalpitesReader {

    fun obterPalpitesArquivo(inputStream: InputStream): List<Palpite> {

        var palpites: List<Palpite> = listOf()
        val workbook = XSSFWorkbook(inputStream)

        val sheet = workbook.getSheet("Respostas")
        val rows = sheet.iterator()

        var lineNumber = 0
        while (rows.hasNext()) {
            if (++lineNumber == 1) {
                rows.next()
                continue
            }

            val currentRow = rows.next()
            val cell = currentRow.getCell(1)
            if (cell == null || cell.cellType == CellType.BLANK) {
                break;
            }
            val email = cell.stringCellValue
            val classificacao = mutableMapOf<String, Int>()
            for (i in 2..21) {
                classificacao.put(currentRow.getCell(i).stringCellValue, i - 1)
            }
            palpites = palpites.plusElement(Palpite(email, classificacao))
        }

        workbook.close()
        inputStream.close()
        return palpites
    }
}