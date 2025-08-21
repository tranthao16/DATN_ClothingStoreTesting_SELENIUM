package CommonScreen;

import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class HomeAdminScreen {
	public static final String CATEGORY_LINK_XPATH 		= "//span[contains(text(),'Danh mục')]";
	public static final String ADD_CATEGORY_LINK_XPATH 	= "//a[contains(text(),'Thêm danh mục')]";
	public static final String CATEGORY_LIST_LINK_XPATH = "//a[contains(text(),'Danh sách danh mục')]";
	
	public static final String PRODUCT_LINK_XPATH 		= "//span[contains(text(),'Sản phẩm')]";
	public static final String ADD_PRODUCT_LINK_XPATH 	= "//a[contains(text(),'Thêm sản phẩm')]";
	public static final String PRODUCT_LIST_LINK_XPATH 	= "//a[contains(text(),'Danh sách sản phẩm')]";

	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL_ADMIN);
			LoginAdminScreen.login(driver, Constant.BASE_EMAIL_ADMIN, Constant.BASE_PASSWORD_ADMIN);
		}
		return driver;
	}
}
