package shoppingtestsite;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import shoppingsite.Constants;
import shoppingsite.ShoppingPage;

public class ShoppingTest {
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
	public void searchTest() throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(Constants.URL);
		ShoppingPage shop = PageFactory.initElements(driver, ShoppingPage.class);
		shop.searchQuery("dress");
		driver.findElement(By.xpath("//*[@id=\"searchbox\"]/button")).click();
		WebElement result = driver.findElement(By.xpath("//*[@id=\"center_column\"]/h1/span[1]"));
		assertEquals("Error: Incorrect result", "\"DRESS\"", result.getText());
		Thread.sleep(1000);
	}
}
	
	
