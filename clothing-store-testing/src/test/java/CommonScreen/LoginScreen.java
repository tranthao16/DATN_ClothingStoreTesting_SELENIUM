package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class LoginScreen {
	// Text field
	public static final String EMAIL_TXT_ID				= "email";
	public static final String PASSWORD_TXT_ID			= "password";
	
	// Button
	public static final String LOGIN_BTN_XPATH			= "//*[@class='button btn-login']";
	
	// Error message
	public static final String ERROR_MSG_XPATH 			= "//div[@class='alert alert-warning login-faile-msg']//ul//li";
	public static final String ERROR_MSG_XPATH2			= "//div[@class='alert alert-warning user-incorrect-msg']//ul//li";
	
	// List of messages
	public static final String EMPTY_EMAIL_MSG 			= "Email không được để trống";
	public static final String INVALID_EMAIL_MSG 		= "Email không hợp lệ";
	public static final String EMPTY_PASSWORD_MSG 		= "Mật khẩu không được để trống";
	public static final String MIN_PASSWORD_MSG 		= "Mật khẩu tối thiểu 8 ký tự";
	public static final String MAX_PASSWORD_MSG 		= "Mật khẩu tối đa 16 ký tự";
	public static final String INVALID_INFO_MSG 		= "Sai tài khoản hoặc mật khẩu!";
	
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
			Utilities.clickObscuredElement(driver, By.className(HomeScreen.LOGIN_LINK_CLASS), By.xpath(LOGIN_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
	
	public static void login(WebDriver driver, String email, String password) {
		Utilities.waitForElementVisibility(driver, By.id(EMAIL_TXT_ID));
		Utilities.inputValueAndValidate(driver, By.id(EMAIL_TXT_ID), email, email);
		Utilities.inputValueAndValidate(driver, By.id(PASSWORD_TXT_ID), password, password);
		Utilities.clickObscuredElement(driver, By.xpath(LOGIN_BTN_XPATH), By.xpath(HomeScreen.USERNAME_LINK_XPATH), Constant.WAIT_ELEMENT_EXIST);

	}
	
	public static void login(WebDriver driver, String id, String email, String password, String expectErrMsg) {
		Utilities.waitForElementVisibility(driver, By.id(EMAIL_TXT_ID));
		Utilities.inputValueAndValidate(driver, By.id(EMAIL_TXT_ID), email, email);
		Utilities.inputValueAndValidate(driver, By.id(PASSWORD_TXT_ID), password, password);
		
		if (expectErrMsg.equals(Constant.NULL_STRING)) {
			Utilities.clickObscuredElement(driver, By.xpath(LOGIN_BTN_XPATH), By.xpath(HomeScreen.USERNAME_LINK_XPATH), Constant.WAIT_ELEMENT_EXIST);
			Utilities.captureScreen(driver, id);
		}
		else if (expectErrMsg.equals(EMPTY_EMAIL_MSG) || expectErrMsg.equals(EMPTY_PASSWORD_MSG)) {
			Utilities.clickObscuredElement(driver, By.xpath(LOGIN_BTN_XPATH), By.xpath(ERROR_MSG_XPATH), Constant.WAIT_ELEMENT_EXIST);
			Utilities.captureScreen(driver, id);
			Utilities.assertTextValue(driver, By.xpath(ERROR_MSG_XPATH), expectErrMsg);
		}
		else {
			Utilities.clickObscuredElement(driver, By.xpath(LOGIN_BTN_XPATH), By.xpath(ERROR_MSG_XPATH2), Constant.WAIT_ELEMENT_EXIST);
			Utilities.captureScreen(driver, id);
			Utilities.assertTextValue(driver, By.xpath(ERROR_MSG_XPATH2), expectErrMsg);
		}
	}	
}
