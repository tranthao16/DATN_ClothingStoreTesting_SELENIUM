package TestSuite.CartManagement;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Constant;
import Common.Initialization;
import Common.Utilities;
import CommonScreen.HomeScreen;

public class AddProductToCart extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = HomeScreen.openScreenWithoutLogin(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
		if (Utilities.checkElementVisible(driver, By.xpath(HomeScreen.CONTINUE_SHOPPING_BTN_XPATH))) {
			Utilities.clickObscuredElementToNotVisible(driver, By.xpath(HomeScreen.CONTINUE_SHOPPING_BTN_XPATH), By.xpath(HomeScreen.CONTINUE_SHOPPING_BTN_XPATH), Constant.WAIT_ELEMENT_NOT_EXIST);
		}
	}
	
	@Test()
	public void QLGH_01_01() throws IOException {
		Utilities.mouseHover(driver, HomeScreen.PRODUCT_LINK_XPATH.replace(Constant.INDEX_TEXT, "1"));
		Utilities.clickObscuredElement(driver, HomeScreen.ADD_TO_CART_BTN_XPATH.replace(Constant.INDEX_TEXT, "1"), HomeScreen.MSG_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.assertTextValueVisible(driver, By.xpath(HomeScreen.MSG_XPATH), HomeScreen.ADD_PRODUCT_TO_CART_MSG);
		Utilities.assertTextValueVisible(driver, By.xpath(HomeScreen.TOTAL_QUANTITY_XPATH), "1");
	}

	@Test()
	public void QLGH_01_02() throws IOException {
		Utilities.mouseHover(driver, HomeScreen.PRODUCT_LINK_XPATH.replace(Constant.INDEX_TEXT, "2"));
		Utilities.clickObscuredElement(driver, HomeScreen.ADD_TO_CART_BTN_XPATH.replace(Constant.INDEX_TEXT, "2"), HomeScreen.MSG_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.assertTextValueVisible(driver, By.xpath(HomeScreen.MSG_XPATH), HomeScreen.ADD_PRODUCT_TO_CART_MSG);
		Utilities.assertTextValueVisible(driver, By.xpath(HomeScreen.TOTAL_QUANTITY_XPATH), "2");
	}

	@Test()
	public void QLGH_01_03() throws IOException {
		Utilities.mouseHover(driver, HomeScreen.PRODUCT_LINK_XPATH.replace(Constant.INDEX_TEXT, "2"));
		Utilities.clickObscuredElement(driver, HomeScreen.ADD_TO_CART_BTN_XPATH.replace(Constant.INDEX_TEXT, "2"), HomeScreen.MSG_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.assertTextValueVisible(driver, By.xpath(HomeScreen.MSG_XPATH), HomeScreen.ADD_PRODUCT_TO_CART_MSG);
		Utilities.assertTextValueVisible(driver, By.xpath(HomeScreen.TOTAL_QUANTITY_XPATH), "3");
	}
}