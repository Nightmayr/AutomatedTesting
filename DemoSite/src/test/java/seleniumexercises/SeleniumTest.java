package seleniumexercises;

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

public class SeleniumTest {
	
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
	public void addUserTest() throws InterruptedException {
		driver.get("http://thedemosite.co.uk/addauser.php");
//		driver.get(Constants.URL);
		DemoSitePage demo = PageFactory.initElements(driver, DemoSitePage.class);
		demo.userEntry("umayr");
		demo.passwordEntry("password");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]")).click();
		demo.userLoginEntry("umayr");
		demo.passwordLoginEntry("password");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")).click();
		WebElement myElement = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("Expected Login successful", "**Successful Login**", myElement.getText());
		
		}
	
}
