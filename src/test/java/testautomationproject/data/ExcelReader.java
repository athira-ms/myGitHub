package testautomationproject.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public double readFile(File file, String fruitName) throws IOException, InvalidFormatException {
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);

		int fruitColindex = -1;
		int priceColindex = -1;
		XSSFRow row = sheet.getRow(0);
		if (row != null) {
			for (Cell cell : row) {

				String cellValue = cell.getStringCellValue();
				if (cellValue.equalsIgnoreCase("fruit_name")) {
					fruitColindex = cell.getColumnIndex();
				}
				if (cellValue.equalsIgnoreCase("price")) {
					priceColindex = cell.getColumnIndex();
				}
			}
		}
		System.out.println(fruitColindex);
		System.out.println(priceColindex);
		HashMap<String, Double> hm = new HashMap<>();

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			double price = 0.0;
			if (row != null) {
				Cell fruitCell = row.getCell(fruitColindex);
				String fruit = fruitCell.getStringCellValue();

				Cell priceCell = row.getCell(priceColindex);
				double priceValue = 0.0;
				if (priceCell.getCellType() == CellType.NUMERIC) {
					priceValue = priceCell.getNumericCellValue();
				} else {
					// fallback if price was stored as text
					try {
						priceValue = Double.parseDouble(priceCell.getStringCellValue().trim());
					} catch (Exception e) {
						continue;
					}
				}

				hm.put(fruit, priceValue);

			}

		}

		System.out.println(hm);

		return hm.get(fruitName);

	}

}
