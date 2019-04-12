package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class IssueListingPage extends PageBase{

	public IssueListingPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(xpath = "//div[@class='fab']")
	@CacheLookup
	public
	WebElement createIssue;	
	
	public void clickCreateIssueButton() {
		createIssue.click();
	}
	
	public boolean isCreateIssueButtonDisplayed() {
		return createIssue.isDisplayed();
	}
	
	public boolean isCreateIssueButtonEnabled() {
		return createIssue.isEnabled();
	}
	
	// (//h6[@class='title']//b)[1]
	@FindBy(xpath = "(//h6[@class='title']//b)[1]")
	@CacheLookup
	public
	WebElement issueTitle;
	
	public String getIssueTitle() {
		return issueTitle.getText();
	}
}
