package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class RegisterScreen {
	// Text field
	public static final String EMAIL_TXT_ID				= "email";
	public static final String NAME_TXT_ID 				= "name";
	public static final String PASSWORD_TXT_ID 			= "password";
	public static final String ADDRESS_TXT_ID 			= "address";
	
	// Button
	public static final String REGISTER_BTN_XPATH		= "//span[contains(text(),'ĐĂNG KÝ')]";
	
	// Error message 
	public static final String ERROR_MSG_XPATH 			= "//div[@class='unsuccess-mesage']//ul//li";

	// List of messages
	public static final String EMPTY_EMAIL_MSG 			= "Email không được để trống";
	public static final String INVALID_EMAIL_MSG 		= "Email không hợp lệ";
	public static final String REGISTERED_EMAIL_MSG 	= "Email đã đăng kí.";
	public static final String EMPTY_NAME_MSG 			= "Tên đăng nhập không được để trống";
	public static final String INVALID_NAME_MSG 		= "Tên đăng nhập không hợp lệ";
	public static final String EMPTY_PASSWORD_MSG 		= "Mật khẩu không được để trống";
	public static final String MIN_PASSWORD_MSG 		= "Mật khẩu tối thiểu 8 ký tự";
	public static final String MAX_PASSWORD_MSG 		= "Mật khẩu tối đa 16 ký tự";
		
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
			Utilities.clickObscuredElement(driver, By.className(HomeScreen.REGISTER_LINK_CLASS), By.xpath(RegisterScreen.REGISTER_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
	
	public static void register(WebDriver driver, String id, String email, String name, String password, String address, String expectErrMsg) {
		Utilities.waitForElementVisibility(driver, By.id(EMAIL_TXT_ID));
		Utilities.inputValueAndValidate(driver, By.id(EMAIL_TXT_ID), email, email);	
		Utilities.inputValueAndValidate(driver, By.id(NAME_TXT_ID), name, name);	
		Utilities.inputValueAndValidate(driver, By.id(PASSWORD_TXT_ID), password, password);	
		Utilities.inputValueAndValidate(driver, By.id(ADDRESS_TXT_ID), address, address);	
		if (expectErrMsg == Constant.NULL_STRING) {
			Utilities.clickObscuredElement(driver, By.xpath(REGISTER_BTN_XPATH), By.xpath(HomeScreen.USERNAME_LINK_XPATH), Constant.WAIT_ELEMENT_EXIST);
			Utilities.captureScreen(driver, id);
		}
		else {
			Utilities.clickObscuredElement(driver, By.xpath(REGISTER_BTN_XPATH), By.xpath(ERROR_MSG_XPATH), Constant.WAIT_ELEMENT_EXIST);
			Utilities.captureScreen(driver, id);
			Utilities.assertTextValue(driver, By.xpath(ERROR_MSG_XPATH), expectErrMsg);
		}
	}	
}
