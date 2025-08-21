package CommonScreen;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class AddProductScreen {
	public static final String NAME_TXT_ID = "name";
	public static final String CODE_TXT_ID = "code";
	public static final String UPLOAD_BTN_XPATH = "//button[contains(text(),'Upload')]";
	public static final String DESCRIPTION_TXT_ID = "description";
	public static final String AUTHOR_TXT_ID = "author";
	public static final String PUBLISHER_TXT_ID = "publisher";
	public static final String YEAR_TXT_ID = "yearOfPublication";
	public static final String PAGE_TXT_ID = "totalPage";
	public static final String SIZE_TXT_ID = "size";
	public static final String SUPPLIER_TXT_ID = "supplier";
	public static final String TYPE_TXT_ID = "typeCover";
	public static final String PRICE_TXT_ID = "price";
	public static final String SALE_TXT_ID = "priceSale";
	public static final String QUANTITY_TXT_ID = "quantity";
	public static final String ADD_BTN_XPATH = "//button[contains(text(),'Thêm mới')]";
	
	public static final String MSG_XPATH = "//div[@class='swal-modal']//div[@class='swal-text']";
	
	public static final String SUCCESS_MSG = "Thêm sản phẩm thành công";
	public static final String NAME_EMPTY_MSG = "Tên sản phẩm là bắt buộc";
	public static final String CODE_EMPTY_MSG = "Mã sản phẩm là bắt buộc";
	public static final String CODE_EXIST_MSG = "Mã sản phẩm đã tồn tại";
	public static final String IMAGE_EMPTY_MSG = "Hình ảnh là bắt buộc";
	public static final String DESCRIPTION_EMPTY_MSG = "Mô tả là bắt buộc";
	public static final String PRICE_EMPTY_MSG = "Giá gốc là bắt buộc";
	public static final String PRICE_INVALID_MSG = "Giá gốc không hợp lệ";
	public static final String SALE_EMPTY_MSG = "Giá bán là bắt buộc";
	public static final String SALE_INVALID_MSG = "Giá bán không hợp lệ";
	public static final String QUANTITY_EMPTY_MSG = "Số lượng là bắt buộc";
	
	public static WebDriver openScreen(String browser) {
		WebDriver driver = null;
		if (!browser.isEmpty()) {
			driver = Utilities.getDriver(browser);
			driver.get(Constant.BASE_URL_ADMIN);
			LoginAdminScreen.login(driver, Constant.BASE_EMAIL_ADMIN, Constant.BASE_PASSWORD_ADMIN);
			Utilities.clickObscuredElement(driver, HomeAdminScreen.PRODUCT_LINK_XPATH, HomeAdminScreen.ADD_PRODUCT_LINK_XPATH, Constant.WAIT_ELEMENT_EXIST);
			Utilities.clickObscuredElement(driver, HomeAdminScreen.ADD_PRODUCT_LINK_XPATH, ADD_BTN_XPATH, Constant.WAIT_ELEMENT_EXIST);
		}
		return driver;
	}
	
	public static void addProduct(WebDriver driver, String id, String name, String code, String image, String description, String price, String sale, 
			String quantity, String expectedMsg) throws Exception {
		Utilities.inputValueAndValidate(driver, By.id(NAME_TXT_ID), name, name);
		Utilities.inputValueAndValidate(driver, By.id(CODE_TXT_ID), code, code);
		if (!image.equals(Constant.EMPTY_STRING)) {
			Utilities.click(driver, By.xpath(UPLOAD_BTN_XPATH));
			Robot rb = new Robot();
			// Copy File path vào Clipboard
			StringSelection str = new StringSelection(System.getProperty("user.dir") + "\\src\\test\\resources\\test_data\\" + image);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
			Thread.sleep(1000);
			// Nhấn Control+V để dán
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			// Xác nhận Control V trên
			rb.keyRelease(KeyEvent.VK_CONTROL);
			rb.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			// Nhấn Enter
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(4000);
		}
		Utilities.inputValueAndValidate(driver, By.id(DESCRIPTION_TXT_ID), description, description);
		Utilities.inputValueAndValidate(driver, By.id(PRICE_TXT_ID), price, price);
		Utilities.inputValueAndValidate(driver, By.id(SALE_TXT_ID), sale, sale);
		Utilities.inputValueAndValidate(driver, By.id(QUANTITY_TXT_ID), quantity, quantity);
		Utilities.clickObscuredElement(driver, ADD_BTN_XPATH, MSG_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.captureScreen(driver, id);
		Utilities.assertTextValue(driver, By.xpath(MSG_XPATH), expectedMsg);
	}
}
