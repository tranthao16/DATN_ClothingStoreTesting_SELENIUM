package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class CartScreen {
	public static final String CHECKOUT_BTN_XPATH 			= "//button[@class='button btn-checkout']";
	
	public static final String RECORD_PRODUCTS_XPATH		= "//table[@id='shopping-cart-table']//tbody//tr";
	public static final String REMOVE_BTN_XPATH				= "(//*[@class='btn-remove btn-remove2 remove-cart'])[INDEX]";
	public static final String INCREASE_BTN_XPATH			= "(//*[@class='increase btn-increment'])[INDEX]";
	public static final String DECREASE_BTN_XPATH			= "(//*[@class='decrease btn-decrement'])[INDEX]";
	
	public static final String CART_EMPTY_MSG_XPATH			= "//p[contains(text(),'Giỏ hàng của bạn đang trống. Mời bạn tiếp tục mua ')]";
	
	public static void updateProduct(WebDriver driver, String index, boolean isIncrease) {
		if (isIncrease) {
			Utilities.click(driver, By.xpath(INCREASE_BTN_XPATH.replace(Constant.INDEX_TEXT, index)));
		}
		else {
			Utilities.click(driver, By.xpath(DECREASE_BTN_XPATH.replace(Constant.INDEX_TEXT, index)));
		}
	}
	
	public static void removeProduct(WebDriver driver, String index) throws InterruptedException {
		int recordBefore = Utilities.getXpathCount(driver, RECORD_PRODUCTS_XPATH);
		Utilities.click(driver, By.xpath(REMOVE_BTN_XPATH.replace(Constant.INDEX_TEXT, index)));
		Thread.sleep(1000);
		if (index == "1") {
			Utilities.assertElementVisible(driver, By.xpath(CART_EMPTY_MSG_XPATH));
		}
		else {
			int recordAfter = Utilities.getXpathCount(driver, RECORD_PRODUCTS_XPATH);
			Utilities.assertString(Integer.toString(recordBefore - 1), Integer.toString(recordAfter));
		}
	}
}
