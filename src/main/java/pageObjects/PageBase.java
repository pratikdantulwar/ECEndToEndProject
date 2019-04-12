package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

	/**
	 * selecting the values from dropdown
	 * @param element webElement for which action needs to perform
	 * @param text value which needs to be get selected from dropdown 
	 */

	public void selectByText(WebElement element, String text) {
		Select selectElement = new Select(element);
		selectElement.selectByVisibleText(text);
	}
}
