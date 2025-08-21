package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
/*import java.util.concurrent.TimeUnit;*/

import org.apache.logging.log4j.*;
/*import org.apache.logging.log4j.core.config.Configurator;*/
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utilities {
	// Test output folder
	public static String resultFolder = "";
	// List of driver
	public static String IEDriverPath = System.getProperty("user.dir") + "/src/test/resources/IEDriverServer.exe";
	public static String EdgeDriverPath = System.getProperty("user.dir") + "/src/test/resources/MicrosoftWebDriver.exe";
	public static String ChromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe";
	public static String MSEdgeDriverPath = System.getProperty("user.dir") + "/src/test/resources/msedgedriver.exe";
	// Log function
	public static final Logger logger = LogManager.getLogger(Utilities.class);
	public static final String LOGGER_CONF_PATH = System.getProperty("user.dir")
			+ "/src/test/resources/log4j2.properties";

	public static String testID = "NotSet";

	public Utilities() {

		try {
			// Initialize log
			Properties prop = new Properties();
			prop.load(new FileInputStream(LOGGER_CONF_PATH)); // FileInputStream
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
			resultFolder = System.getProperty("user.dir") + File.separator + "result" + File.separator
					+ sdf.format(Calendar.getInstance().getTime()) + File.separator;
			createFolder(resultFolder);
			prop.setProperty("log4j.appender.file.File", resultFolder + "ResultLog.log");
			prop.store(new FileOutputStream(LOGGER_CONF_PATH), null);
		} catch (IOException e) {
			e.printStackTrace();
		} // FileOutputStream
	}

	public static void createFolder(String folderPath) {
		// Tạo một đối tượng File với đường dẫn của thư mục mới
		File folder = new File(folderPath);
		// Kiểm tra xem thư mục đã tồn tại hay không
		if (!folder.exists()) {
			// Tạo thư mục mới
			boolean created = folder.mkdirs(); // Dùng mkdirs để tạo các thư mục cha nếu chưa tồn tại
			if (!created) {
				System.out.println("Không thể tạo thư mục: " + folderPath);
			}
		} else {
			System.out.println("Thư mục đã tồn tại.");
		}
	}

	// Write information to log file
	public static void printWithTestID(String msg, Level level, Throwable... e) {
		switch (level.intLevel()) {
		case 0:
			logger.debug("[TestID:" + testID + "]" + msg);
			break;
		case 1:
			logger.info("[TestID:" + testID + "]" + msg);
			break;
		case 3:
			if (0 == e.length) {
				logger.error("[TestID:" + testID + "]" + msg);
			} else {
				logger.error("[TestID:" + testID + "]" + msg, e[0]);
			}
			break;
		default:
			Assert.fail("Wrong log level");
			break;
		}
	}

	// Assert fail test case
	public static void assertFail(WebDriver driver, String message) {
		Assert.fail(message);
		printWithTestID(message, Level.ERROR);
		LocalDateTime now = LocalDateTime.now();
		captureScreen(driver, testID + now.toString());
	}

	// Close browser
	public static void closeDriver(WebDriver driver) {
		try {
			driver.quit();
			wait(Constant.SIMILITY_THRESHOLD);
		} catch (Exception e) {
			printWithTestID(e.getMessage(), Level.ERROR);
		}
	}

	// Sleep time
	public static void wait(double waitSecond) {
		try {
			Thread.sleep((long) (waitSecond * 1000));
		} catch (InterruptedException e) {
			printWithTestID(e.toString(), Level.ERROR);
		}
	}

	// Wait for element visible during timeout
	public static boolean waitForElementVisibility(WebDriver driver, By locator, int timeInSecond) {
		boolean isSuccess = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSecond));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			isSuccess = true;
		} catch (Exception e) {
			printWithTestID(e.toString(), Level.ERROR);
		}
		return isSuccess;
	}

	// Wait for element visible during timeout. In this case, timeout =
	// Constant.WAIT_ELEMENT_EXIST
	public static boolean waitForElementVisibility(WebDriver driver, By locator) {
		boolean isSuccess = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constant.WAIT_ELEMENT_EXIST));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			isSuccess = true;
		} catch (Exception e) {
			printWithTestID(e.toString(), Level.ERROR);
		}
		return isSuccess;
	}

	// Wait for element can clickable
	public static boolean waitForElementClickable(WebDriver driver, By locator, int timeInSecond) {
		boolean isSuccess = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSecond));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			isSuccess = true;
		} catch (Exception e) {
			printWithTestID(e.toString(), Level.ERROR);
		}
		return isSuccess;
	}

	// Click one element and wait one element visible one time
	public static void clickObscuredElement(WebDriver driver, By locator, By newLocator, double waitSecond) {
		long startTime = System.currentTimeMillis();
		long timeout = System.currentTimeMillis() - startTime;
		int waitClick = (int) (Constant.WAIT_INTERVAL * 4);
		waitForElementClickable(driver, locator, waitClick / 2);
		while (timeout < waitClick * 1000) {
			try {
				WebElement element = driver.findElement(locator);
				Actions actions = new Actions(driver);
				actions.moveToElement(element);
				actions.perform();
				element.click();
				break;
			} catch (Exception e) {
				printWithTestID(e.toString(), Level.ERROR);
			}
			timeout = System.currentTimeMillis() - startTime;
		}
		int waitExist = (int) (waitSecond * 1000 - (System.currentTimeMillis() - startTime)) / 1000;
		new WebDriverWait(((WebDriver) driver), Duration.ofSeconds(waitExist))
				.until(ExpectedConditions.visibilityOfElementLocated(newLocator));
		assertElementVisible(driver, newLocator);
	}

	// Click one element and wait one element visible, if this element is not
	// visible, click again
	public static void clickObscuredElement(WebDriver driver, String elementXPath, String expectedElement,
			int timeInSecond) {
		clickObscuredElement(driver, By.xpath(elementXPath), By.xpath(expectedElement), timeInSecond);
	}

	// Click one element and wait one expected element disappear
	public static void clickObscuredElementToNotVisible(WebDriver driver, By locator, By expectedElementLocator,
			int timeInSecond) {
		long startTime = System.currentTimeMillis();
		long timeout = System.currentTimeMillis() - startTime;
		waitForElementClickable(driver, locator, 1);
		while (timeout < timeInSecond * 1000) {
			try {
				driver.findElement(locator).click();
				new WebDriverWait(((WebDriver) driver), Duration.ofSeconds(3))
						.until(ExpectedConditions.invisibilityOfElementLocated(expectedElementLocator));
				break;
			} catch (Exception e) {
				printWithTestID(e.getMessage(), Level.ERROR);
			}
			timeout = System.currentTimeMillis() - startTime;
		}
		assertElementNotVisible(driver, expectedElementLocator);
	}

	// Input data into edit field
	public static void sendKeys(WebDriver driver, By locator, String inputData) {
		waitForElementVisibility(driver, locator);
		try {
			WebElement element = driver.findElement(locator);
			element.clear();
			if (!inputData.isEmpty()) {
				element.sendKeys(inputData);
			}
		} catch (Exception e) {
			Assert.fail("Could not input data: " + e.getMessage());
		}
	}

	// Input data into edit field
	public static void sendKeys(WebDriver driver, String xpath, String inputData) {
		sendKeys(driver, By.xpath(xpath), inputData);
	}

	// Clear input data
	public static void clearInput(WebDriver driver, By locator) {
		try {
			WebElement element = driver.findElement(locator);
			element.sendKeys(Keys.CONTROL + "a");
			element.sendKeys(Keys.DELETE);
		} catch (Exception e) {
			Assert.fail("Could not clear data");
		}
	}

	// Input data into field and validate data after input
	public static void inputValueAndValidate(WebDriver driver, By locator, String inputData, String expectedValue) {
		if (inputData != null) {
			clearInput(driver, locator);
			sendKeys(driver, locator, inputData);
			assertInputValue(driver, locator, expectedValue);
		}
	}

	// Input data into field and NOT validate data after input
	public static void inputValue(WebDriver driver, By locator, String inputData) {
		if (inputData != null) {
			sendKeys(driver, locator, inputData);
		}
	}

	// Click element
	public static void click(WebDriver driver, By locator) {
		try {
			WebElement clickedElement = driver.findElement(locator);
			clickedElement.click();
		} catch (Exception e) {
			Assert.fail("Could not click element: " + locator + ": " + e.getMessage());
		}

	}

	// Validate one element visible
	public static void assertElementNotVisible(Object object, By locator) {
		WebElement element = null;
		try {
			if (object instanceof WebDriver) {
				element = ((WebDriver) object).findElement(locator);
			} else if (object instanceof WebElement) {
				element = ((WebElement) object).findElement(locator);
			}

		} catch (Exception e) {

		}
		if (element != null) {
			assertElementNotVisible(element);
		}
	}

	// Validate one element visible
	public static void assertElementVisible(Object object, By locator) {
		try {
			WebElement element = null;
			if (object instanceof WebDriver) {
				element = ((WebDriver) object).findElement(locator);
			} else if (object instanceof WebElement) {
				element = ((WebElement) object).findElement(locator);
			}
			assertElementVisible(element);
		} catch (Exception e) {
			Assert.fail("Element with locator " + locator + " doesn't exist");
		}
	}

	// Validate one element invisible
	public static void assertElementNotVisible(WebElement element) {
		Assert.assertFalse(element.isDisplayed(), element.toString() + " is still displayed");
	}

	// Validate one element visible
	public static void assertElementVisible(WebElement element) {
		Assert.assertTrue(element.isDisplayed(), element.toString() + " is not displayed");
	}

	// Validate text valued of checked element is same with expected value
	public static void assertTextValue(Object object, By locator, String expectedValue) {
		String textValue = null;
		WebElement element = null;
		if (object instanceof WebDriver) {
			element = ((WebDriver) object).findElement(locator);
		} else if (object instanceof WebElement) {
			element = ((WebElement) object).findElement(locator);
		}
		textValue = element.getText();
		assertString(expectedValue, textValue);
	}

	// Validate text valued of checked element is same with expected value and this
	// element is visible
	public static void assertTextValueVisible(WebDriver driver, By locator, String expectedValue) {
		boolean isVisible = waitForElementVisibility(driver, locator, Constant.WAIT_ELEMENT_EXIST);
		Assert.assertEquals(isVisible, true, locator + "is not visible");
		assertTextValue(driver, locator, expectedValue);
	}

	// Validate value of text field
	public static void assertInputValue(Object object, By locator, String expectedValue) {
		String textValue = null;
		WebElement element = null;
		if (object instanceof WebDriver) {
			element = ((WebDriver) object).findElement(locator);
		} else if (object instanceof WebElement) {
			element = ((WebElement) object).findElement(locator);
		}
		textValue = element.getAttribute("value");
		assertString(expectedValue, textValue);
	}

	// Validate expected value with actual value
	public static void assertString(WebDriver driver, String expectedValue, String actualValue) {
		String msg = "";
		if (expectedValue == actualValue && actualValue == null) {
			msg = "Value is correct: " + expectedValue;
			Assert.assertTrue(true, msg);
		} else if (expectedValue.equals(actualValue)) {
			msg = "Value is correct: " + expectedValue;
			Assert.assertTrue(true, msg);
		} else {
			msg = "Value is not correct: Expected: \"" + expectedValue + "\"; Actual: \"" + actualValue + "\"";
			assertFail(driver, msg);

		}
	}

	// Assert 2 strings
	public static void assertString(String expectedValue, String actualValue) {
		String msg = "";
		if (expectedValue == actualValue && actualValue == null) {
			msg = "Value is correct: " + expectedValue;
			Assert.assertTrue(true, msg);
		} else if (expectedValue.equals(actualValue)) {
			msg = "Value is correct: " + expectedValue;
			Assert.assertTrue(true, msg);
		} else {
			msg = "Value is not correct: Expected: \"" + expectedValue + "\"; Actual: \"" + actualValue + "\"";
			Assert.fail(msg);
		}
	}

	// Capture screen for UI element
	public static void captureScreen(Object object, String testcase) {
		File scrFile = null;
		try {
			if (object instanceof WebDriver) {
				scrFile = ((TakesScreenshot) ((WebDriver) object)).getScreenshotAs(OutputType.FILE);
			} else if (object instanceof WebElement) {
				scrFile = ((TakesScreenshot) ((WebElement) object)).getScreenshotAs(OutputType.FILE);
			}
			try {
				String filePath = resultFolder + testcase + ".jpg";
				Files.copy(scrFile.toPath(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				Assert.fail("Could not capture screen: " + e.getMessage());
			}
		} catch (Exception e) {
			printWithTestID(e.getMessage(), Level.ERROR, e);
		}
	}

	// Get WebDriver based on browser type
	public static WebDriver getDriverOneTime(String browserType) {
		WebDriver driver = null;
//		killBrowser(browserType);
		switch (browserType) {
		case Constant.EDGE_BROWSER: {
			/* System.setProperty("webdriver.edge.driver", EdgeDriverPath); */
			driver = new EdgeDriver();
			break;
		}
		
	    case Constant.CHROME_BROWSER: 
	    { 
	      System.setProperty("webdriver.chrome.driver",ChromeDriverPath); 
	      ChromeOptions options = new ChromeOptions();
			/* options.addArguments("--headless"); */ // Chạy ẩn
		  options.addArguments("--no-sandbox");
		  options.addArguments("--disable-dev-shm-usage");
		  options.addArguments("--disable-gpu"); // (Linux thường cần)
		  options.addArguments("--window-size=1920,1080"); // Đảm bảo đủ hiển thị layout 
		  WebDriverManager.chromedriver().setup(); 
		  driver = new ChromeDriver(options); 
		  break; 
		}
		 
		case Constant.FIREFOX_BROWSER: {
		}
		case Constant.MSEDGE_BROWSER:
			/* System.setProperty("webdriver.edge.driver", MSEdgeDriverPath); */
			driver = new EdgeDriver();
			break;

		case Constant.IE_BROWSER: {
			/* System.setProperty("webdriver.ie.driver", IEDriverPath); */
			driver = new InternetExplorerDriver();
			break;
		}
		default:
		}
		driver.manage().window().maximize();
		return driver;
	}

	// Initialize driver and try again if initialize fail
	public static WebDriver getDriver(String browserType) {
		WebDriver driver;
		try {
			driver = getDriverOneTime(browserType);
		} catch (Exception e) {
			driver = getDriverOneTime(browserType);
		}
		return driver;
	}

	// Kill any process
	public static void killProcess(String process) {
		// Default browser is IE
		String cmd = "taskkill /F /IM " + process + " /T";
		try {
			Runtime.getRuntime().exec(cmd);
			wait(Constant.WAIT_INTERVAL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Kill browser
	public static void killBrowser(String browserType) {
		switch (browserType) {
		case Constant.EDGE_BROWSER: {
			killProcess("MicrosoftEdge.exe");
			killProcess("MicrosoftWebDriver.exe");
			break;
		}
		case Constant.MSEDGE_BROWSER: {
			killProcess("msedgedriver.exe");
			killProcess("msedge.exe");
			break;
		}
		case Constant.CHROME_BROWSER: {
			killProcess("chrome.exe");
			killProcess("chromedriver.exe");
			break;
		}
		case Constant.FIREFOX_BROWSER: {
			killProcess("firefox.exe");
			break;
		}
		case Constant.IE_BROWSER: {
			killProcess("iexplore.exe");
			break;
		}
		default:
		}
	}

	// Refresh screen by press F5 key
	public static void refreshScreen(WebDriver driver) {
//		driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
		driver.get(driver.getCurrentUrl());
		Utilities.wait(Constant.WAIT_REFRESH_SCREEN);
	}

	// Check element visible
	public static boolean checkElementVisible(Object object, By locator) {
		WebElement element = null;
		try {
			if (object instanceof WebDriver) {
				element = ((WebDriver) object).findElement(locator);
			} else if (object instanceof WebElement) {
				element = ((WebElement) object).findElement(locator);
			}

		} catch (Exception e) {

		}
		if (element != null) {
			return element.isDisplayed();
		}
		return false;
	}

	// Move the mouse cursor to a WebElement
	public static void mouseHover(WebDriver driver, String elementXpath) {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(By.xpath(elementXpath));
		actions.moveToElement(element).perform();
	}

	// Count elements by xpath
	public static int getXpathCount(WebDriver driver, String elementXpath) {
		List<WebElement> listElements = driver.findElements(By.xpath(elementXpath));
		int count = 0;
		for (WebElement element : listElements) {
			count++;
		}
		return count;
	}

	// Scroll to an element
	public static void scrollToElement(WebDriver driver, String elementXpath) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Locating element by link text and store in variable "Element"
		WebElement element = driver.findElement(By.xpath(elementXpath));

		// Scrolling down the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", element);
	}
}
