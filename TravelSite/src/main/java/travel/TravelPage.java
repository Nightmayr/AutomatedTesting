package travel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelPage {

	@FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
	private WebElement locationSearch;
	
	@FindBy(xpath = "//*[@id=\"dpd1\"]/div/input")
	private WebElement checkinInput;
	
	@FindBy(xpath = "//*[@id=\"dpd1\"]/div/input")
	private WebElement checkoutInput;
	
	@FindBy(xpath = "")
	private WebElement 
}
