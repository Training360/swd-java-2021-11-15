package excel;

import extension.SeleniumTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@SeleniumTest
class ExcelTest {

    @Test
    void testExcel(WebDriver driver, URL url) throws IOException {
        driver.get(url + "/grid");

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");

        int rowCount = 0;

        var rows = driver.findElements(By.tagName("tr"));
        for (var row: rows) {
            Row excelRow = sheet.createRow(++rowCount);
            var cells = row.findElements(By.tagName("td"));
            int columnCount = 0;
            for (var cell: cells) {
                Cell excelCell = excelRow.createCell(++columnCount);
                System.out.println(cell.getText());
                excelCell.setCellValue(cell.getText());
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("./target/grid.xlsx")) {
            workbook.write(outputStream);
        }

    }
}
