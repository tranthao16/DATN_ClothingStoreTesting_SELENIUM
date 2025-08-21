package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class OrderedProductsScreen {
	public static final String ORDERED_PRODUCTS_TBL_CLASS			= "data-table";
	
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
			Utilities.clickObscuredElement(driver, By.className(HomeScreen.LOGIN_LINK_CLASS),
					By.xpath(LoginScreen.LOGIN_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
			LoginScreen.login(driver, Constant.BASE_EMAIL, Constant.BASE_PASSWORD);
			Utilities.clickObscuredElement(driver, HomeScreen.USERNAME_LINK_XPATH, HomeScreen.ORDERED_PRODUCTS_LINK_XPATH,
					Constant.WAIT_ELEMENT_EXIST);
			Utilities.clickObscuredElement(driver, By.xpath(HomeScreen.ORDERED_PRODUCTS_LINK_XPATH),
					By.className(ORDERED_PRODUCTS_TBL_CLASS), Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
}
