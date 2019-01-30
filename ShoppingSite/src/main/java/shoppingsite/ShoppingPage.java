package shoppingsite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingPage {
	
	@FindBy(id = "search_query_top")
	private WebElement searchBox;
	
	public void searchQuery(String query) {
		searchBox.sendKeys(query);
	}

}
