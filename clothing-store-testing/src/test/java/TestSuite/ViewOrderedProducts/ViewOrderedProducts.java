package TestSuite.ViewOrderedProducts;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Initialization;
import Common.Utilities;
import CommonScreen.OrderedProductsScreen;

public class ViewOrderedProducts extends Initialization {	
	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
	}

	@Test()
	public void XLSDH_01_01() throws IOException {
		driver = OrderedProductsScreen.openScreen(browser);
	}
}
