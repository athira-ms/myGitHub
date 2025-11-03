package testautomationproject.Seleniumprojectjava;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testautomationproject.AbstractComponents.AbstractComponent;

public class dataFromExcel extends AbstractComponent {

	WebDriver driver;

	public dataFromExcel(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(id = "login")
	WebElement login;

	public void Login(String userEmail, String userPassword) {
		username.sendKeys(userEmail);
		password.sendKeys(userPassword);
		login.click();

	}

	public Object[][] getDataFromExcel() throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ems01\\eclipse-workspace\\Seleniumprojectjava\\TestDataExcel.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("login");

		int row = sh.getPhysicalNumberOfRows();
		int col = sh.getRow(0).getPhysicalNumberOfCells();
		Object[][] data = new Object[row - 1][col];
		for (int i = 1; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Cell cell = sh.getRow(i).getCell(j);
				data[i - 1][j] = cell.toString();

			}

		}

		wb.close();
		fis.close();
		return data;

	}

}
