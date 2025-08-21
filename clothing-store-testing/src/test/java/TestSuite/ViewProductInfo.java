package TestSuite;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Common.Initialization;
import Common.Utilities;
import CommonScreen.ProductInfoScreen;

public class ViewProductInfo extends Initialization {	
	@BeforeMethod()
	public void setUpMethod(Method method) throws Exception {
		Utilities.testID = method.getName();
	}

	@Test()
	public void TC_05_01() throws IOException {
		driver = ProductInfoScreen.openScreen(browser);
	}
}
