package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class IssueCreatePage extends PageBase {

	public AndroidDriver<AndroidElement> driver;
	
	public IssueCreatePage(AndroidDriver<AndroidElement> driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@formcontrolname='title']")
	@CacheLookup
	public 
	WebElement title;
	
	public void enterTitle(final String titleValue) {
		title.sendKeys(titleValue);
	}
	
	public boolean isTitleFieldDisplayed() {
		return title.isDisplayed();
	}
	
	public boolean isTitleFieldEnabled() {
		return title.isEnabled();
	}
		
	@FindBy(xpath = "//textarea[@formcontrolname='description']")
	@CacheLookup
	public
	WebElement description;
	
	public void enterDescription(final String descriptionValue) {
		description.sendKeys(descriptionValue);
	}
	
	public boolean isDescriptionFieldDisplayed() {
		return description.isDisplayed();
	}
	
	public boolean isDescriptionFieldEnabled() {
		return description.isEnabled();
	}
	
	@FindBy(xpath = "//input[@formcontrolname='impact']")
	@CacheLookup
	public
	WebElement impact;
		
	public void enterImpact(final String impactValue) {
		impact.sendKeys(impactValue);
	}
	
	public boolean isImpactFieldDisplayed() {
		return impact.isDisplayed();
	}
	
	public boolean isImpactFieldEnabled() {
		return impact.isEnabled();
	}
	
	@FindBy(xpath = "//ec-datepicker[@formcontrolname='plannedStartDate']")
	@CacheLookup
	public
	WebElement startDate;
	
	public boolean isStartDateFieldDisplayed() {
		return startDate.isDisplayed();
	}
	
	public boolean isStartDateFieldEnabled() {
		return startDate.isEnabled();
	}
	
	public void clickStartDate() {
		startDate.click();
	}
	
		
	@FindBy(xpath = "//ec-datepicker[@formcontrolname='plannedEndDate']")
	@CacheLookup
	public
	WebElement dueDate;
	
	public boolean isDueDateFieldDisplayed() {
		return dueDate.isDisplayed();
	}
	
	public boolean isDueDateFieldEnabled() {
		return dueDate.isEnabled();
	}
	
	public void clickDueDate() {
		dueDate.click();
	}
	
	@FindBy(xpath = "//select[@formcontrolname='assignedTo']")
	@CacheLookup
	public
	WebElement assignedOwner;
	
	public boolean isAssignedOwnerFieldDisplayed() {
		return assignedOwner.isDisplayed();
	}
	
	public boolean isAssignedOwnerFieldEnabled() {
		return assignedOwner.isEnabled();
	}
	
	public void clickAssignedOwner() {
		assignedOwner.click();
	}
	
	public List<AndroidElement> assignedOwnerList()
	{
		return  driver.findElements(By.xpath("//select[@formcontrolname='assignedTo']//option[@class='ng-star-inserted']"));
	}
	
	@FindBy(xpath = "//select[@formcontrolname='severity']")
	@CacheLookup
	public
	WebElement severity;
	
	public boolean isSeverityFieldDisplayed() {
		return severity.isDisplayed();
	}
	
	public boolean isSeverityFieldEnabled() {
		return severity.isEnabled();
	}
	
	public void clickSeverity() {
		severity.click();
	}
	
	@FindBy(xpath = "//select[@formcontrolname='severity']//option[@value='Critical']")
	@CacheLookup
	public
	WebElement severityValue;	
		
	public List<AndroidElement> severityList()
	{
		return  driver.findElements(By.xpath("//select[@formcontrolname='severity']//option[@class='ng-star-inserted']"));
	}

	@FindBy(xpath = "//select[@formcontrolname='milestoneList']")
	@CacheLookup
	public
	WebElement milestone;	
	
	public boolean isMilestoneFieldDisplayed() {
		return milestone.isDisplayed();
	}
	
	public boolean isMilestoneFieldEnabled() {
		return milestone.isEnabled();
	}
	
	public void clickMilestone() {
		milestone.click();
	}
	
	public List<AndroidElement> milestoneList()
	{
		return  driver.findElements(By.xpath("//select[@formcontrolname='milestoneList']//option[@class='ng-star-inserted']"));
	}

	@FindBy(xpath = "//button[@type='submit']")
	@CacheLookup
	public
	WebElement submit;	
	
	public void clickSubmitButton() {
		submit.click();
	}
	
	public void isSubmitButtonDisplayed() {
		submit.isDisplayed();
	}
	
	public void isSubmitButtonEnabled() {
		submit.isEnabled();
	}

	public void setAssignedOwnerDropDown()
	{
		List<AndroidElement> assignedOwnerList = assignedOwnerList();
		selectByText(assignedOwner, assignedOwnerList.get(1).getText());
	}
	
	public void setSeverityDropDown()
	{
		selectByText(severity, severityValue.getText());		
	}
	
	public void setMilestoneDropDown()
	{
		if(milestoneList().size() > 0) {		
			List<AndroidElement> milestoneList = milestoneList();			
			selectByText(milestone, milestoneList.get(1).getText());
		}
	}
}
