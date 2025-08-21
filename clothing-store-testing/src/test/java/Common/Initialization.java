package Common;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class Initialization {
	public WebDriver driver = null;
	Utilities utils;
	protected String browser;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws Exception{
		utils = new Utilities();
	}
		
	@BeforeClass(alwaysRun = true)
	@Parameters("browser")
	public void beforeClass(String browserTest) throws Exception{
		browser = browserTest;
	}
		
	@AfterClass(alwaysRun = true)
	public void afterClass() throws Exception{	
		Utilities.closeDriver(driver);
	}
}
