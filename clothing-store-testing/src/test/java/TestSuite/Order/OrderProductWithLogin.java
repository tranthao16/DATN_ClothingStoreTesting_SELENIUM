package TestSuite.Order;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CSVDataProvider.OrderData;
import Common.Initialization;
import Common.Utilities;
import CommonScreen.HomeScreen;
import CommonScreen.PaymentInfoScreen;

public class OrderProductWithLogin extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = HomeScreen.openScreen(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		PaymentInfoScreen.openScreen(driver);
		Utilities.testID = method.getName();
	}

	@Test(dataProvider = "orderData", dataProviderClass = OrderData.class)
	public void DH_02(String id, String phone, String address, String note, String expectedMsg) throws IOException {
		PaymentInfoScreen.orderProductWithLogin(driver, id, phone, address, note, expectedMsg);
	}
}
