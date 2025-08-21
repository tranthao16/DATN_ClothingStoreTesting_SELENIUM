package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Utilities;

public class LoginAdminScreen {
	// Text field
	public static final String EMAIL_TXT_ID				= "email";
	public static final String PASSWORD_TXT_ID			= "password";
	
	// Button
	public static final String LOGIN_BTN_XPATH			= "//a[contains(text(),'Đăng nhập')]";
	
	public static void login(WebDriver driver, String email, String password) {
		Utilities.waitForElementVisibility(driver, By.id(EMAIL_TXT_ID));
		Utilities.inputValueAndValidate(driver, By.id(EMAIL_TXT_ID), email, email);
		Utilities.inputValueAndValidate(driver, By.id(PASSWORD_TXT_ID), password, password);
		Utilities.click(driver, By.xpath(LOGIN_BTN_XPATH));
	}	
}
