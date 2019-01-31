package com.qa.para;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.param.AddUserPage;
import com.qa.param.Constants;
import com.qa.param.LoginPage;

@RunWith(Parameterized.class)
public class ExcelParameterisation {
	
	@Parameters
	public static Collection<Object[]> data() throws IOException{
		FileInputStream file = new FileInputStream(Constants.FILE);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		Object[][] ob = new Object[sheet.getPhysicalNumberOfRows()-1][4];
		
		//Reading
		
		for(int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
//			below code previously in first for loop replaced by 3
//			sheet.getRow(colNum).getPhysicalNumberOfCells()
			for(int colNum = 0; colNum < 3;colNum++) {
				ob[rowNum-1][colNum]=sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			}
			ob[rowNum-1][3]=rowNum;
		}
		
		return Arrays.asList(ob);
	}
	
	private String username;
	private String password;
	private String expected;
	private int rowNum;
	private WebDriver driver;
	
	public ExcelParameterisation(String username, String password, String expected, int rowNum) {
		this.username = username;
		this.password = password;
		this.expected = expected;
		this.rowNum = rowNum;
	}
	
	@Before
	public void setup() {
		System.setProperty("phantomjs.binary.path", Constants.PHANTOMDRIVER);
		driver = new PhantomJSDriver();
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	
	@Test
	public void login() throws IOException {
		// testing logic
//		System.out.println(username + " " + password + " " + expected );
		driver.get(Constants.ADDUSERPAGE);
		AddUserPage addAUserPage = PageFactory.initElements(driver, AddUserPage.class);
		addAUserPage.addUser(username, password);
		
		driver.get(Constants.LOGINPAGE);
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.loginUser(username, password);
		
		//Writing
				FileInputStream file = new FileInputStream(Constants.FILE);
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheetAt(0);
				
				XSSFRow row = sheet.getRow(rowNum);
				XSSFCell cell;
				cell = row.getCell(3);
				if (cell == null) {
					cell = row.createCell(3);
				}
				cell.setCellValue(loginPage.loginAttemptText());
				
				//Testing
				try {
					assertEquals("Login not successful", expected, loginPage.loginAttemptText());
					cell = row.getCell(4);
					if (cell == null) {
						cell = row.createCell(4);
					}
					cell.setCellValue("PASS");
				} catch (AssertionError e) {
					cell = row.getCell(4);
					if (cell == null) {
						cell = row.createCell(4);
					}
					cell.setCellValue("FAIL");
				}
				
				FileOutputStream fileOut = new FileOutputStream(Constants.FILE);

				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				
				workbook.close();
				file.close();	
				
				
			
		
	}
}
