package testautomationproject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import testautomationproject.Seleniumprojectjava.LandingPagePOM;

public class BaseTest {

	public WebDriver driver;
	public LandingPagePOM landingpage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\testautomationproject\\resources\\GlobalData.properties");
		prop.load(fis);
		String browsername = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browsername = prop.getProperty("browser");
		if (browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();

			if (browsername.contains("headless")) {
//				options.addArguments("headless");
				options.addArguments("--headless=new"); // Use new headless mode
				options.addArguments("--window-size=1920,1080"); // Ensure elements are visible
				options.addArguments("--disable-gpu"); // Prevent rendering issues
				options.addArguments("--no-sandbox"); // Fix permissions issue in Jenkins
				options.addArguments("--disable-dev-shm-usage"); // Prevent crashes
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();

		return driver;
	}

	public List<HashMap<String, String>> getJsonDatatoMap(String filePath) throws IOException {

		// Read json file and convert to String
		String jsonCntent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Hashmap using jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonCntent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPagePOM LaunchApplication() throws IOException {
		driver = initializeDriver();
		landingpage = new LandingPagePOM(driver);
		landingpage.goTourl();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
