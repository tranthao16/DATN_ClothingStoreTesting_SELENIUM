package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class ProductListScreen {
	public static final String ADD_BTN_XPATH 			= "//a[@class='btn btn-info btn-add text-white']";
	public static final String RECORD_PRODUCTS_XPATH	= "//tbody/tr";
	public static final String UPDATE_BTN_XPATH 		= "(//a[@class='btn btn-warning btn-edit'])[INDEX]";
	public static final String DELETE_BTN_XPATH 		= "(//a[@class='btn btn-danger btn-delete'])[INDEX]";
	
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL_ADMIN);
			LoginAdminScreen.login(driver, Constant.BASE_EMAIL_ADMIN, Constant.BASE_PASSWORD_ADMIN);
			Utilities.clickObscuredElement(driver, HomeAdminScreen.CATEGORY_LINK_XPATH, HomeAdminScreen.CATEGORY_LIST_LINK_XPATH, Constant.WAIT_ELEMENT_EXIST);
			Utilities.clickObscuredElement(driver, HomeAdminScreen.CATEGORY_LIST_LINK_XPATH, ADD_BTN_XPATH, Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
	
	public static void deleteProduct(WebDriver driver, String index, boolean isOK) throws Exception {
		int recordBefore = Utilities.getXpathCount(driver, RECORD_PRODUCTS_XPATH);
		Utilities.click(driver, By.xpath(DELETE_BTN_XPATH.replace(Constant.INDEX_TEXT, index)));
		if (isOK) {
			driver.switchTo().alert().accept();
			Thread.sleep(3000);
			int recordAfter = Utilities.getXpathCount(driver, RECORD_PRODUCTS_XPATH);
			Utilities.assertString(Integer.toString(recordBefore - 1), Integer.toString(recordAfter));
		}
		else {
			driver.switchTo().alert().dismiss();
			Thread.sleep(3000);
			int recordAfter = Utilities.getXpathCount(driver, RECORD_PRODUCTS_XPATH);
			Utilities.assertString(Integer.toString(recordBefore), Integer.toString(recordAfter));
		}
	}
}
