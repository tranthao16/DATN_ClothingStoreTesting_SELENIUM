package TestSuite.AccountManagement;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CSVDataProvider.RegisterData;
import Common.Constant;
import Common.Initialization;
import Common.Utilities;
import CommonScreen.HomeScreen;
import CommonScreen.RegisterScreen;

public class Register extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = RegisterScreen.openScreen(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
		if (Utilities.checkElementVisible(driver, By.xpath(HomeScreen.USERNAME_LINK_XPATH))) {
			HomeScreen.logout(driver);
		}
		Utilities.clickObscuredElement(driver, By.className(HomeScreen.REGISTER_LINK_CLASS), By.xpath(RegisterScreen.REGISTER_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
	}

	@Test(dataProvider = "registerData", dataProviderClass = RegisterData.class)
	public void QLTK_01(String id, String email, String name, String password, String address, String expectedMsg) throws IOException {
		RegisterScreen.register(driver, id, email, name, password, address, expectedMsg);
	}
}
