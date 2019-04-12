package pageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AppCommonPage extends PageBase {
	
	private AndroidDriver<AndroidElement> driver;

	public AppCommonPage(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(.,'Yes')]")
	@CacheLookup
	public
	WebElement yes;	
	
	@FindBy(xpath = "//button[@type='No']")
	@CacheLookup
	public
	WebElement no;	
	
	@FindBy(xpath = "//button[@class='btn btn-link text-white ng-star-inserted']")
	//@FindBy(xpath = "//button[@class='button button-icon button-clear ion-navicon']")
	@CacheLookup
	public
	WebElement hamburgerMenu;	
	
	public void clickHamburgerMenu() {
		hamburgerMenu.click();
	}
	
	public boolean isHamburgerMenuDisplayed() {
		return hamburgerMenu.isDisplayed();
	}
	
	public boolean isHamburgerMenuEnabled() {
		return hamburgerMenu.isEnabled();
	}
	
	@FindBy(xpath = "//a[contains(.,'Logout')]")
	//@CacheLookup
	public
	WebElement logoutMenu;
	
	public void clickLogoutMenu() {
		logoutMenu.click();
	}
	
	public boolean isLogoutMenuDisplayed() {
		return logoutMenu.isDisplayed();
	}
	
	public boolean isLogoutMenuEnabled() {
		return logoutMenu.isEnabled();
	}
	
	@FindBy(xpath = "//a[contains(.,'Issue')]")
	@CacheLookup
	public
	WebElement issueMenu;
	
	public void clickIssueMenu() {
		issueMenu.click();
	}
	
	public boolean isIssueMenuDisplayed() {
		return issueMenu.isDisplayed();
	}
	
	public boolean isIssueMenuEnabled() {
		return issueMenu.isEnabled();
	}

	/**
	 * Logout of the application
	 */
	public void logout()
	{
		//navigating to hamburgerMenu
		hamburgerMenu.click();
		//clicking log out option from the menu
		logoutMenu.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//switching to the modal dialogue window 
		driver.switchTo().activeElement();		
		//selecting the yes option from the logout dialogue window
		yes.click();
	}
	
}
