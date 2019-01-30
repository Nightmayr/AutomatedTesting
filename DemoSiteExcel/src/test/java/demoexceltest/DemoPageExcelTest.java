package demoexceltest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import demoexcel.Constants;
import demoexcel.DemoSitePage;

public class DemoPageExcelTest {

	public WebDriver driver = null;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", Constants.DRIVER);
		driver = new ChromeDriver();
	}

	@Test
	public void loginTest() throws Exception {
		FileInputStream file = new FileInputStream(Constants.FILE);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		driver.get("http://thedemosite.co.uk/addauser.php");
		
		DemoSitePage demo = PageFactory.initElements(driver, DemoSitePage.class);
		//Reading
		for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
			List<String> dataList = new ArrayList<String>();
			for (int colNum = 0; colNum < sheet.getRow(rowNum).getPhysicalNumberOfCells(); colNum++) {
				XSSFCell cell = sheet.getRow(rowNum).getCell(colNum);
				String userCell = cell.getStringCellValue();
//				System.out.println(userCell);
				dataList.add(userCell);
				System.out.println(dataList);
			}
			demo.userEntry(dataList.get(0));
			demo.passwordEntry(dataList.get(1));
			driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
//			Thread.sleep(3000);
			WebElement loginLink = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
			loginLink.click();
			demo.userEntry(dataList.get(0));
			demo.passwordEntry(dataList.get(1));
			WebElement loginButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
			loginButton.click();
			WebElement addUserLink = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
			addUserLink.click();
//			Thread.sleep(3000);
		}
		
		
		//Writing
//		XSSFRow row = sheet.getRow(1);
//		XSSFCell cell = row.getCell(1);
//		if (cell == null) {
//			cell = row.createCell(1);
//		}
//		cell.setCellValue("hello");
//		
//	
//		FileOutputStream fileOut = new FileOutputStream(Constants.FILE);
//
//		workbook.write(fileOut);
//		fileOut.flush();
//		fileOut.close();
//		
//		file.close();		
		
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}