package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class AccountInfoScreen {
	// Text field
	public static final String NAME_TXT_ID 			= "getName";
	public static final String ADDRESS_TXT_ID 		= "getAddress";

	// Button
	public static final String UPDATE_BTN_XPATH 	= "//*[@class='btn btn-info btn-save']";

	// Error message
	public static final String ERROR_MSG_XPATH 		= "//div[@class='alert alert-danger error-mesage']//ul//li";
	public static final String SUCCESS_MSG_XPATH 	= "//div[@class='alert alert-success success-mesage']//ul//li";

	// List of messages
	public static final String SUCCESS_MSG 			= "Thông tin tài khoản đã được cập nhật";
	public static final String EMPTY_NAME_MSG 		= "Bạn chưa nhập họ tên!!";
	public static final String INVALID_NAME_MSG 	= "Họ tên không hợp lệ";
	public static final String EMPTY_ADDRESS_MSG 	= "Bạn chưa nhập địa chỉ!!";
		
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
			Utilities.clickObscuredElement(driver, By.className(HomeScreen.LOGIN_LINK_CLASS),
					By.xpath(LoginScreen.LOGIN_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
			LoginScreen.login(driver, Constant.BASE_EMAIL, Constant.BASE_PASSWORD);
			Utilities.clickObscuredElement(driver, HomeScreen.USERNAME_LINK_XPATH, HomeScreen.ACCOUNT_INFO_LINK_XPATH,
					Constant.WAIT_ELEMENT_EXIST);
			Utilities.clickObscuredElement(driver, By.xpath(HomeScreen.ACCOUNT_INFO_LINK_XPATH),
					By.xpath(UPDATE_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
	
	public static void updateAccountInfo(WebDriver driver, String name, String address, String expectErrMsg) {
		Utilities.waitForElementVisibility(driver, By.id(NAME_TXT_ID));
		Utilities.inputValueAndValidate(driver, By.id(NAME_TXT_ID), name, name);	
		Utilities.inputValueAndValidate(driver, By.id(ADDRESS_TXT_ID), address, address);	
		if (expectErrMsg == Constant.EMPTY_STRING) {
			Utilities.clickObscuredElement(driver, By.xpath(UPDATE_BTN_XPATH), By.xpath(SUCCESS_MSG_XPATH), Constant.WAIT_ELEMENT_EXIST);
			Utilities.assertTextValue(driver, By.xpath(SUCCESS_MSG_XPATH), SUCCESS_MSG);
		}
		else {
			Utilities.clickObscuredElement(driver, By.xpath(UPDATE_BTN_XPATH), By.xpath(ERROR_MSG_XPATH), Constant.WAIT_ELEMENT_EXIST);
			Utilities.assertTextValue(driver, By.xpath(ERROR_MSG_XPATH), expectErrMsg);
		}
	}
}