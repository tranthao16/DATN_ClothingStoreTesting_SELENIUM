package TestSuite.Order;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CSVDataProvider.OrderNotLoginData;
import Common.Initialization;
import Common.Utilities;
import CommonScreen.HomeScreen;
import CommonScreen.PaymentInfoScreen;

public class OrderProductWithoutLogin extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = HomeScreen.openScreenWithoutLogin(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		PaymentInfoScreen.openScreen(driver);
		Utilities.testID = method.getName();
	}

	@Test(dataProvider = "orderNotLoginData", dataProviderClass = OrderNotLoginData.class)
	public void DH_01(String id, String name, String phone, String email, String address, String note, String expectedMsg) throws IOException {
		PaymentInfoScreen.orderProductWithoutLogin(driver, id, name, phone, email, address, note, expectedMsg);
	}
}
