package CommonScreen;

import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class ProductInfoScreen {
	public static final String PRODUCT_INFO_TITLE_XPATH 		= "//h2[contains(text(),'Thông tin sản phẩm')]";
	
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL);
			Utilities.clickObscuredElement(driver, HomeScreen.PRODUCT_LINK_XPATH, PRODUCT_INFO_TITLE_XPATH, Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
}