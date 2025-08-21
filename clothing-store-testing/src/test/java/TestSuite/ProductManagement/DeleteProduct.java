package TestSuite.ProductManagement;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Initialization;
import Common.Utilities;
import CommonScreen.ProductListScreen;

public class DeleteProduct extends Initialization {
	@BeforeClass()
	public void setUpClass() throws Exception {
		driver = ProductListScreen.openScreen(browser);
	}

	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
	}

	@Test()
	public void QLSP_02_01() throws Exception {
		ProductListScreen.deleteProduct(driver, "4", true);
	}
	
	@Test()
	public void QLSP_02_02() throws Exception {
		ProductListScreen.deleteProduct(driver, "1", false);
	}
}
