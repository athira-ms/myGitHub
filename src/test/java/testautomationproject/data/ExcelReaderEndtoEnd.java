package testautomationproject.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderEndtoEnd {

	public boolean updateCell(File file, int rowNum, int colNum, String data)
			throws InvalidFormatException, IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(rowNum);
		if (row == null) {
			row = sheet.createRow(rowNum);
		}
		Cell ce = row.getCell(colNum);
		if (ce == null) {
			ce = row.createCell(colNum);
		}

		ce.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(file);
		workbook.write(fos);
		workbook.close();
		return true;
	}

	public int getRowNumber(File file, String name) throws IOException, InvalidFormatException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		int rowNum = 0;

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(name)) {
					rowNum = row.getRowNum();
				}
			}

		}
		return rowNum;

	}

	public int getColumnNumber(File file, String name) throws InvalidFormatException, IOException {

		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		int colNum = 0;

		Row row = rowIterator.next();

		Iterator<Cell> cellIteartor = row.cellIterator();
		while (cellIteartor.hasNext()) {
			Cell ce = cellIteartor.next();

			if (ce.getCellType() == CellType.STRING && ce.getStringCellValue().equalsIgnoreCase("price")) {
				colNum = ce.getColumnIndex();
			}
		}

		return colNum;

	}

}
