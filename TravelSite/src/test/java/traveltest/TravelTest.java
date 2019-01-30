package traveltest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TravelTest {
	WebDriver driver;
	Actions action;
	
	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Applications\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@Test
	public void travelSearch() {
		
	}
}
