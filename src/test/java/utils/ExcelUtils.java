package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {

    public static List<Map<String, String>> getData(String path, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            XSSFRow headerRow = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(i);
                Map<String, String> map = new HashMap<>();

                for (int j = 0; j < row.getLastCellNum(); j++) {
                    map.put(
                            headerRow.getCell(j).toString(),
                            row.getCell(j).toString()
                    );
                }
                data.add(map);
            }

            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}