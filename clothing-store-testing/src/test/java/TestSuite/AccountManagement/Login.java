package TestSuite.AccountManagement;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CSVDataProvider.LoginData;
import Common.Constant;
import Common.Initialization;
import Common.Utilities;
import CommonScreen.HomeScreen;
import CommonScreen.LoginScreen;

public class Login extends Initialization {	
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = LoginScreen.openScreen(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
		if (Utilities.checkElementVisible(driver, By.xpath(HomeScreen.USERNAME_LINK_XPATH))) {
			HomeScreen.logout(driver);
		}
		Utilities.clickObscuredElement(driver, By.className(HomeScreen.LOGIN_LINK_CLASS), By.xpath(LoginScreen.LOGIN_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
	}

	@Test(dataProvider = "loginData", dataProviderClass = LoginData.class)
	public void QLTK_02(String id, String email, String password, String expectedMsg) throws IOException {
		LoginScreen.login(driver, id, email, password, expectedMsg);
	}
}
