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
import CommonScreen.CartScreen;
import CommonScreen.HomeScreen;

public class UpdateProductInCart extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = HomeScreen.openScreenWithoutLogin(browser);
		Utilities.mouseHover(driver, HomeScreen.PRODUCT_LINK_XPATH.replace(Constant.INDEX_TEXT, "1"));
		Utilities.clickObscuredElement(driver, HomeScreen.ADD_TO_CART_BTN_XPATH.replace(Constant.INDEX_TEXT, "1"), HomeScreen.MSG_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.clickObscuredElementToNotVisible(driver, By.xpath(HomeScreen.CONTINUE_SHOPPING_BTN_XPATH), By.xpath(HomeScreen.CONTINUE_SHOPPING_BTN_XPATH), Constant.WAIT_ELEMENT_NOT_EXIST);
		Utilities.click(driver, By.xpath(HomeScreen.CART_MANAGEMENT_LINK_XPATH));
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
	}
	
	@Test()
	public void QLGH_02_01() throws IOException {
		CartScreen.updateProduct(driver, "1", true);
	}

	@Test()
	public void QLGH_02_02() throws IOException {
		CartScreen.updateProduct(driver, "1", false);
	}
}