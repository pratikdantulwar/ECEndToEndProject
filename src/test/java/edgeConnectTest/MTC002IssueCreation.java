package edgeConnectTest;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.functions.ExpectedCondition;
import junit.framework.Assert;
import pageObjects.LoginPage;
import pageObjects.AppCommonPage;
import pageObjects.IssueCreatePage;
import pageObjects.IssueListingPage;
import resources.TestBase;
import utilities.Constant;
import utilities.ExcelUtils;
import utilities.functionalLibrary.CommonMethods;

public class MTC002IssueCreation extends TestBase {
	
    private int iTestCaseRow;	
    private String userName;    
    private String passowrd;
    private String issueDescription;
    private String issueImpact;
    
	@BeforeTest
	public void before() throws Exception
	{	
		iTestCaseRow = CommonMethods.getRowNumberForLoginData(this.toString());
		userName = ExcelUtils.getCellData(iTestCaseRow, Constant.COL_USRNAME);
		passowrd = ExcelUtils.getCellData(iTestCaseRow, Constant.COL_PASSWORD);		
		issueDescription = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_IssueDescription);	
		issueImpact = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_IssueImpact);	
	}
	
	@Test (priority=6)	
	public void IssueCreation() throws IOException, InterruptedException
	{
		//creating the object of login page		
		LoginPage lp = new LoginPage(driver); 
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//invoking the browser and login the application
		lp.login(userName, passowrd);		
		log.info("Successfully login to the application");
				
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		//creating object of AppCommonPage
		AppCommonPage AppCoPg = new AppCommonPage(driver); 
			
		//navigating to hamburgerMenu
		AppCoPg.clickHamburgerMenu();
		
		log.info("tapping on issue option");
		AppCoPg.clickIssueMenu();
		log.info("navigated to issue listing page");
		
		IssueListingPage ilp = new IssueListingPage(driver);
		ilp.clickCreateIssueButton();
		log.info("navigated to issue creation page");
		
		IssueCreatePage icp = new IssueCreatePage(driver);
		
		log.info("Filling the issue creation form");
		
		String issueTitle = "Issue" + CommonMethods.getRandomNumber();
		
		icp.enterTitle(issueTitle);
		log.info("issue title set : " + issueTitle);
		System.out.println("issue title : " + issueTitle);
		
		icp.enterDescription(issueDescription);
		log.info("entered description");
		
		icp.enterImpact(issueImpact);
		log.info("entered impact");
	
		//picking out the indetfiedDate as 2 days before of todays date
		int indentfiedDate = CommonMethods.getTodaysDate() - 2;    
	    icp.clickStartDate();
	    CommonMethods.setDate(indentfiedDate);
	    log.info("entered indentfiedDate");

		Thread.sleep(2000);
		
		//for selecting due date same as identified date, need to provide default value as 1 i.e. starting of open calendar		
		icp.clickDueDate();		
		CommonMethods.setDate(1);
		log.info("entered dueDate");

		Thread.sleep(2000);
		
		//selecting assigned owner from the dropdown
		icp.setAssignedOwnerDropDown();
		log.info("selected assigned owner");
		
		//selecting severity from the dropdown
		icp.setSeverityDropDown();
		log.info("selected severity");
		
		//selecting milestone from the dropdown
		icp.setMilestoneDropDown();
		log.info("selected milestone");
	
		Thread.sleep(2000);
		
		//clicking submit button
		icp.clickSubmitButton();
		log.info("Submitted the issue");	
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//assertEquals(driver.getCurrentUrl(), "http://172.26.62.18:9090/issues", "To check user is navigated back to login issue listing screen");
		
		Thread.sleep(5000);
		final String currentURL1 = driver.getCurrentUrl();		
		driver.get(currentURL1);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		ilp = new IssueListingPage(driver);
		assertEquals(ilp.getIssueTitle().trim(), issueTitle, "Validating newly created issue on issue listing screen");
		
		AppCoPg = new AppCommonPage(driver);	
		
		//logging out of the application
		AppCoPg.logout();
	}
}
