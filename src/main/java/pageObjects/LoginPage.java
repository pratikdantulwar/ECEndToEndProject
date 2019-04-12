package pageObjects;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class LoginPage extends PageBase{

	public AndroidDriver<AndroidElement> driver; // = new AndroidDriver<AndroidElement>();

	public LoginPage(AndroidDriver<AndroidElement> driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='email']")
	@CacheLookup
	public WebElement userName;

	public void enterUserName(final String username) {
		userName.sendKeys(username);
	}
	
	public void clearUserName() {
		userName.clear();
	}
	
	public boolean isUserNameFieldDisplayed() {
		return userName.isDisplayed();
	}
	
	public boolean isUserNameFieldEnabled() {
		return userName.isEnabled();
	}
	
	@FindBy(xpath = "//input[@id= 'pwd']")
	@CacheLookup
	public WebElement password;

	public void enterPassword(final String passWord) {
		password.sendKeys(passWord);
	}
	
	public void clearPassword() {
		password.clear();
	}
	
	public boolean isPasswordFieldDisplayed() {
		return password.isDisplayed();
	}
	
	public boolean isPasswordFieldEnabled() {
		return password.isEnabled();
	}
	
	@FindBy(xpath = "//button[@id='loadButton']")
	@CacheLookup
	public WebElement signIn;
	

	public void clickSubmitButton() {
		signIn.click();
	}

	@FindBy(xpath = "//h6[@class='text-warning text-center']")
	@CacheLookup
	public WebElement warningMessage;

	@FindBy(linkText = "Forget Password")
	@CacheLookup
	public WebElement forgotPassword;
	
	@FindBy(linkText = "Login with SSO")
	@CacheLookup
	public WebElement loginWithSSO;
	
	public void clickLinkLoginWithSSO() {
		loginWithSSO.click();
	}	
	
	
	@FindBy(xpath = "//input[@type='email' and @name='loginfmt' and @id='i0116']")
	@CacheLookup
	public WebElement loginWithSSOEmail;
	
	public void enterEmailSSO(final String email) {
		loginWithSSOEmail.sendKeys(email);
	}
	
	public void clearEmailSSO() {
		loginWithSSOEmail.clear();
	}
	
	public boolean isEmailSSOFieldDisplayed() {
		return loginWithSSOEmail.isDisplayed();
	}
	
	public boolean isEmailSSOFieldEnabled() {
		return loginWithSSOEmail.isEnabled();
	}
	
	
	@FindBy(xpath = "//input[@type='submit' and @id='idSIButton9']")
	@CacheLookup
	public WebElement loginWithSSONextButton;
	
	public void ClickLoginWithSSONextButton() {
		loginWithSSONextButton.click();
	}	
	
	
	@FindBy(xpath = "//input[@type='password' and @name='passwd' and @id='i0118']")
	@CacheLookup
	public WebElement loginWithSSOPassword;
	
	public void enterPasswordSSO(final String email) {
		loginWithSSOPassword.sendKeys(email);
	}
	
	public void clearPasswordSSO() {
		loginWithSSOPassword.clear();
	}
	
	public boolean isPasswordSSOFieldDisplayed() {
		return loginWithSSOPassword.isDisplayed();
	}
	
	public boolean isPasswordSSOFieldEnabled() {
		return loginWithSSOPassword.isEnabled();
	}
	
	@FindBy(xpath = "//input[@type='submit' and @id='idSIButton9']")
	@CacheLookup
	public WebElement loginWithSSOSignInButton;
	
	public void ClickLoginWithSSOSignInButton() {
		loginWithSSOSignInButton.click();
	}	
	
	@FindBy(xpath = "//input[@type='button' and @id='idBtn_Back'] ")
	@CacheLookup
	public WebElement SSOStaySignInNoButton;
	
	public void ClickSSOStaySignInNoButton() {
		SSOStaySignInNoButton.click();
	}
	
	/**
	 * Login to the application
	 * @param username username
	 * @param passWord passWord
	 * @param URL URL
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void login(String username, String passWord) throws IOException, InterruptedException {
	
		//entering the user name
		clearUserName();
		enterUserName(username);
		// entering the password
		clearPassword();
		enterPassword(passWord);
		// clicking on sign in button
		clickSubmitButton();
		Thread.sleep(2000);
	}
	
	
	/**
	 * Login to the application with SSO
	 * @param username username
	 * @param passWord passWord
	 * @param URL URL
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public void loginWithSSO(String username, String passWord) throws IOException, InterruptedException {
	
		clickLinkLoginWithSSO();	
		Thread.sleep(2000);
		//entering the user name
		clearEmailSSO();
		enterEmailSSO(username);
		Thread.sleep(2000);
		ClickLoginWithSSONextButton();
		Thread.sleep(2000);
		// entering the password
		clearPasswordSSO();
		enterPasswordSSO(passWord);
		// clicking on sign in button
		ClickLoginWithSSOSignInButton();
		Thread.sleep(2000);
		ClickSSOStaySignInNoButton();
		Thread.sleep(10000);
	}
}