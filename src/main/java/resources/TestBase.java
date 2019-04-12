package resources;

import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import utilities.functionalLibrary.CommonMethods;

public class TestBase {
	
	//declaring the global variables
	
	//declaring the driver 
	public static AndroidDriver<AndroidElement> driver;
	//declaring the property object
	public static Properties prop = new Properties();
	//declaring the logger object for managing the logs
	public static Logger log = LogManager.getLogger(TestBase.class.getName());
	
	public static String BROWSER_NAME = "";
	public static String DEVICE_NAME = "";
	public static String URL = "";
	
	/**
	 * Setting the capabilities
	 * @return driver
	 * @throws IOException
	 */

	public static AndroidDriver<AndroidElement> capabilities() throws IOException
	{
		//creating the object of a file 
		FileInputStream fis = new FileInputStream(CommonMethods.getRootDirectoyPath() + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		//picking the data environment data from file
		BROWSER_NAME=prop.getProperty("browser");
		DEVICE_NAME=prop.getProperty("deviceName");
		URL=prop.getProperty("url");

		//setting device capabilities
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, BROWSER_NAME);

		//Initializing the android driver
		driver = new AndroidDriver<>(new URL("http://172.26.62.28:4723/wd/hub"), cap);

		//Returning the driver
		return driver;
	}

	/**
	 * Initializing the driver
	 * @throws IOException
	 */	
	@BeforeSuite
	public void initalize() throws IOException
	{
		//Initialize the driver with capabilities
		capabilities();	
		log.info("Driver is initailize");	
		driver.get(URL);
	}
	
	/**
	 * Closing the driver and making it to null
	 */
	@AfterTest
	public void teardown()
	{
		//closing the browser
		driver.close();
		driver=null;
	}
	
	/**
	 * Taking screen shots for failure of the assertion
	 * @param result
	 * @throws IOException
	 */
	public void getScreenshot(String result) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//storing the screen shot for the mentioned path location
		FileUtils.copyFile(src, new File(CommonMethods.getRootDirectoyPath() + "screenShot" + "\\" + result +"screenshot"+ System.currentTimeMillis() +".png"));
	}
	
	
}
