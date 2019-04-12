package edgeConnectTest;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pageObjects.AppCommonPage;
import pageObjects.LoginPage;
import resources.TestBase;
import utilities.functionalLibrary.CommonMethods;

public class MTC001Login extends TestBase {

	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[1][2];
		log.info("Picking data login");	
		data = CommonMethods.getData();	
		log.info("Login data successfully picked");	
		return data;
	}
	
	@Test(dataProvider="getData", priority=4, enabled=true)	
	public void loginToEC(String username, String password) throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		AppCommonPage appCoPg = new AppCommonPage(driver);
		log.info("Logging to the application");	
		lp.login(username, password);		
	    assertEquals(driver.getCurrentUrl(), "http://172.26.62.18:9090/dashboard", "Validating the URL in order to know that user is "
					+ "successfully logged in to the application");
	
		log.info("Successfully logged in to the appliction");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Logging out of the application");	
		appCoPg.logout();
		log.info("Successfully logged out of the application");	
		log.info("User has navigated to the dashboard page");			
	}

	
	@Test(enabled=false)
	public void loginToECWithSSO() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		AppCommonPage appCoPg = new AppCommonPage(driver);
		log.info("Logging to the application");	
		lp.loginWithSSO("pdantulwar@xpanxion.com", "April@2019");
		log.info("Successfully logged in to the appliction with SSO login");	
		assertEquals(driver.getCurrentUrl(), "http://172.26.62.18:9090/dashboard", "Validating the URL in order to know that user is "
				+ "successfully logged in to the application");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Logging out of the application");	
		appCoPg.logout();
		log.info("Successfully logged out of the application");	
		log.info("User has navigated to the dashboard page");	
	}

	
	@Test(priority=5, enabled=true)	
	public void loginToECToCheckFaliureInvalidData() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		AppCommonPage appCoPg = new AppCommonPage(driver);
		log.info("Logging to the application");	
		lp.login("usernameInvalid", "passwordInvalid");
		assertEquals(driver.getCurrentUrl(), "http://172.26.62.18:9090/dashboard", "Validating the URL in order to know that user is "
				+ "successfully logged in to the application");
		log.info("Successfully logged in to the appliction");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		log.info("Logging out of the application");	
		appCoPg.logout();
		log.info("Successfully logged out of the application");	
		log.info("User has navigated to the dashboard page");	
	}

	@Test(priority=2, enabled=true)	
	public void loginToECInivalidId() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("username", "password");
		assertEquals(lp.warningMessage.getText(), "Please enter valid username/email/password", "Validating the error for invalid id and password");
		log.info("Validated the error message for invalid id");
	}
	
	@Test(priority=1, enabled=true)	
	public void loginToECBlankPwd() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("554", "");
		assertEquals(lp.warningMessage.getText(), "Please enter valid password", "Validating the error for blank pwd");
		log.info("Validated the error message for blank password");	
	}
		
	@Test(priority=3, enabled=true)	
	public void loginToECBlankId() throws IOException, InterruptedException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login("", "password");	
		assertEquals(lp.warningMessage.getText(), "Please enter valid username/email", "Validating the error for blank id");
		log.info("Validated the error message for blank id");
	}
}
