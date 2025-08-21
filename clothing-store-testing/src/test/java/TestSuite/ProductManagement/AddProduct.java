package TestSuite.ProductManagement;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CSVDataProvider.AddProductData;
import Common.Constant;
import Common.Initialization;
import Common.Utilities;
import CommonScreen.AddProductScreen;
import CommonScreen.ProductListScreen;

public class AddProduct extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = AddProductScreen.openScreen(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
		if (Utilities.checkElementVisible(driver, By.xpath(ProductListScreen.ADD_BTN_XPATH))) {
			Utilities.clickObscuredElement(driver, ProductListScreen.ADD_BTN_XPATH, AddProductScreen.ADD_BTN_XPATH, Constant.WAIT_ELEMENT_EXIST);
		}
	}

	@Test(dataProvider = "addProductData", dataProviderClass = AddProductData.class)
	public void QLSP_01(String id, String name, String code, String image, String description, String price, String sale, 
			String quantity, String expectedMsg) throws Exception {
		AddProductScreen.addProduct(driver, id, name, code, image, description, price, sale, quantity, expectedMsg);
	}
}
