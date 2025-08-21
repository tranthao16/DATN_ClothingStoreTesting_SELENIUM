package TestSuite;

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

public class SearchProduct extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = HomeScreen.openScreenWithoutLogin(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
		Utilities.clickObscuredElement(driver, By.className(HomeScreen.SEARCH_ICON_CLASS), By.xpath(HomeScreen.SEARCH_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
	}

	@Test()
	public void TC_04_01() throws IOException {
		HomeScreen.searchProduct(driver, Constant.EMPTY_STRING, true);
	}

	@Test()
	public void TC_04_02() throws IOException {
		HomeScreen.searchProduct(driver, Constant.FULL_SPACE_STRING, true);
	}

	@Test()
	public void TC_04_03() throws IOException {
		HomeScreen.searchProduct(driver, Constant.VALID_KEYWORD1, true);
	}

	@Test()
	public void TC_04_04() throws IOException {
		HomeScreen.searchProduct(driver, Constant.VALID_KEYWORD2, true);
	}

	@Test()
	public void TC_04_05() throws IOException {
		HomeScreen.searchProduct(driver, Constant.VALID_KEYWORD3, true);
	}

	@Test()
	public void TC_04_06() throws IOException {
		HomeScreen.searchProduct(driver, Constant.INVALID_KEYWORD, false);
	}
	
	@Test()
	public void TC_04_07() throws IOException {
		HomeScreen.searchProduct(driver, Constant.VALID_KEYWORD4, true);
	}
}
