package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class HomeScreen {
	public static final String LOGIN_LINK_CLASS				= "link-account";
	public static final String REGISTER_LINK_CLASS			= "em-register-link";
	public static final String HOME_LINK_XPATH				= "(//*[@class='em-menu-link'])[1]";
	
	public static final String USERNAME_LINK_XPATH 			= "//a[contains(text(),'Chào ')]";
	public static final String ACCOUNT_INFO_LINK_XPATH		= "//a[contains(text(),'Tài khoản của tôi')]";
	public static final String ORDERED_PRODUCTS_LINK_XPATH	= "//a[contains(text(),'Đơn hàng của tôi')]";
	public static final String LOGOUT_LINK_XPATH 			= "//a[contains(text(),'Đăng xuất')]";
	
	public static final String SEARCH_ICON_CLASS 			= "em-search-icon";
	public static final String SEARCH_TXT_ID				= "search";
	public static final String SEARCH_BTN_XPATH				= "(//button[@title='Search'])[2]";
	public static final String SEARCH_TITLE_XPATH 			= "//p[contains(text(),'Kết quả tìm kiếm cho')]";
	
	public static final String NO_RESULT_MSG_XPATH			= "//body/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/p[1]";
	
	public static final String PRODUCT_LINK_XPATH			= "(//div[@class='product-shop-top'])[INDEX]";
	
	public static final String CART_MANAGEMENT_LINK_XPATH	= "//a[@class='em-amount-js-topcart em-amount-topcart']";
	
	public static final String ADD_TO_CART_BTN_XPATH 		= "(//button[@class='button btn-cart btn-add-to-cart'])[INDEX]";
	public static final String MSG_XPATH					= "//div[@class='swal-text']";
	public static final String ADD_PRODUCT_TO_CART_MSG		= "Sản phẩm của bạn đã được thêm vào giỏ hàng";
	public static final String CONTINUE_SHOPPING_BTN_XPATH	= "//*[@class='swal-button swal-button--cancel']";
	public static final String TOTAL_QUANTITY_XPATH			= "(//span[@class='em-topcart-qty'])[2]";
	public static final String ORDER_NOW_BTN_XPATH			= "//button[contains(text(),'Gửi đơn hàng ngay!')]";
	
	
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
			Utilities.clickObscuredElement(driver, By.className(HomeScreen.LOGIN_LINK_CLASS), By.xpath(LoginScreen.LOGIN_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
			LoginScreen.login(driver, Constant.BASE_EMAIL, Constant.BASE_PASSWORD);
		}
		return driver;
	}
	
	public static WebDriver openScreenWithoutLogin(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
		}
		return driver;
	}

	public static void searchProduct(WebDriver driver, String keyword, boolean isSuccess) {
		Utilities.inputValueAndValidate(driver, By.id(SEARCH_TXT_ID), keyword, keyword);
		Utilities.clickObscuredElement(driver, SEARCH_BTN_XPATH, SEARCH_TITLE_XPATH, Constant.WAIT_ELEMENT_EXIST);
		if (isSuccess) {
			Utilities.assertElementNotVisible(driver, By.xpath(NO_RESULT_MSG_XPATH));
		} 
		else {
			Utilities.assertElementVisible(driver, By.xpath(NO_RESULT_MSG_XPATH));
		}
	}

	public static void logout(WebDriver driver) {
		Utilities.clickObscuredElement(driver, HomeScreen.USERNAME_LINK_XPATH, HomeScreen.LOGOUT_LINK_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.clickObscuredElement(driver, By.xpath(HomeScreen.LOGOUT_LINK_XPATH), By.className(HomeScreen.LOGIN_LINK_CLASS), Constant.WAIT_ELEMENT_EXIST);
	}
}
