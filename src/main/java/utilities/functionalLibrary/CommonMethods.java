package utilities.functionalLibrary;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.openqa.selenium.By;

import resources.TestBase;
import utilities.Constant;
import utilities.ExcelUtils;

public class CommonMethods extends TestBase{

	/**
	 * Getting todays date
	 * @return date
	 */
	public static int getTodaysDate()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.now();
		return Integer.parseInt(dtf.format(localDate).toString().substring(0, 2));
	}

	/**
	 * Random string generation
	 * @return random string
	 */
	public static String getRandomtString()
	{
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
       Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
           salt.append(SALTCHARS.charAt(index));
        }
       String saltStr = salt.toString();
       return saltStr;
	}
	
	/**
	 * Random number generation
	 * @return
	 */
	public static int getRandomNumber()
	{
		Random random = new Random(); 
		return random.nextInt(1000);
	}

	/**
	 * Setting the date - picking the value from date picker
	 * @param dateValue
	 */
	public static void setDate(int dateValue)
	{
		String DateXpath = "(//div[@class='btn-light ng-star-inserted'])[" + dateValue +  "]";
		driver.findElement(By.xpath(DateXpath)).click();
	}
	
	/**
	 * Retrieving the data for login
	 * @return login data
	 * @throws Exception 
	 */
	//function : picking the data for login
	public static Object[][] getData() 
	{
		//row stands for how many different data types should run
		//column stands for how many values for each test	   
		//array size 5  --- index should be 0,1,2,3,4

		Object[][] data = new Object[1][2];

		//0th row
		data[0][0] = "554";
		data[0][1] = "Test@123";

		//1st row
		//data[1][0] = "101";
		//data[1][1] = "Test@123";

		return data;
	}

	/**
	 * Retrieving the data for login
	 * @param testCaseName name of the test case
	 * @return
	 * @throws Exception
	 */
	public static int getRowNumberForLoginData(String testCaseName) throws Exception
	{
		String sTestCaseName = ExcelUtils.getTestCaseName(testCaseName);		
		log.info("statring test case name : " + sTestCaseName);		
		ExcelUtils.setExcelFile(Constant.PATH_TESTDATA + Constant.FILE_TESTDATA,"data");
		int iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName, Constant.COL_TESTCASENAME);		
		return iTestCaseRow;
	}
		
	/**
	 * Getting the root directory path of the project
	 * @return
	 */
	public static String getRootDirectoyPath()
	{
		File currentDirFile = new File(".");
		//getting the path for current directory
		String path = currentDirFile.getAbsolutePath();
		//removing the "." from the directory path
		path = path.substring(0, path.length()-1);	
		
		return path;
		
	}

}
