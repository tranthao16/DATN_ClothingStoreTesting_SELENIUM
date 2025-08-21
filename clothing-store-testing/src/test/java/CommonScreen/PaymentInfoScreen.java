package CommonScreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Common.Constant;
import Common.Utilities;

public class PaymentInfoScreen {
	public static final String NAME_TXT_ID = "getName";
	public static final String PHONE_TXT_ID = "getPhone";
	public static final String EMAIL_TXT_ID = "getEmail";
	public static final String ADDRESS_TXT_ID = "getAddress";
	public static final String NOTE_TXT_ID = "getNote";

	public static final String ORDER_BTN_ID = "load";

	public static final String MSG_XPATH = "//*[@class='alert']";

	public static final String SUCCESS_MSG = "Cám ơn bạn. Đơn hàng của bạn đã được tiếp nhận.";
	public static final String EMPTY_NAME_MSG = "Tên khách hàng không được để trống";
	public static final String EMPTY_PHONE_MSG = "Số điện thoại không được để trống";
	public static final String INVALID_PHONE_MSG = "Số điện thoại không hợp lệ";
	public static final String INVALID_EMAIL_MSG = "Email không hợp lệ";
	public static final String EMPTY_ADDRESS_MSG = "Địa chỉ giao hàng không được để trống";

	public static void openScreen(WebDriver driver) {
		Utilities.clickObscuredElement(driver, HomeScreen.HOME_LINK_XPATH,
				HomeScreen.PRODUCT_LINK_XPATH.replace(Constant.INDEX_TEXT, "1"), Constant.WAIT_ELEMENT_EXIST);
		Utilities.mouseHover(driver, HomeScreen.PRODUCT_LINK_XPATH.replace(Constant.INDEX_TEXT, "1"));
		Utilities.clickObscuredElement(driver, HomeScreen.ADD_TO_CART_BTN_XPATH.replace(Constant.INDEX_TEXT, "1"),
				HomeScreen.MSG_XPATH, Constant.WAIT_ELEMENT_EXIST);
		Utilities.clickObscuredElement(driver, By.xpath(HomeScreen.ORDER_NOW_BTN_XPATH),
				By.xpath(CartScreen.CHECKOUT_BTN_XPATH), Constant.WAIT_ELEMENT_EXIST);
		Utilities.clickObscuredElement(driver, By.xpath(CartScreen.CHECKOUT_BTN_XPATH),
				By.id(PaymentInfoScreen.ORDER_BTN_ID), Constant.WAIT_ELEMENT_EXIST);
	}

	public static void orderProductWithoutLogin(WebDriver driver, String id, String name, String phone, String email,
			String address, String note, String expectedMsg) {
		Utilities.inputValueAndValidate(driver, By.id(NAME_TXT_ID), name, name);
		Utilities.inputValueAndValidate(driver, By.id(PHONE_TXT_ID), phone, phone);
		Utilities.inputValueAndValidate(driver, By.id(EMAIL_TXT_ID), email, email);
		Utilities.inputValueAndValidate(driver, By.id(ADDRESS_TXT_ID), address, address);
		Utilities.inputValueAndValidate(driver, By.id(NOTE_TXT_ID), note, note);
		Utilities.clickObscuredElement(driver, By.id(ORDER_BTN_ID), By.xpath(MSG_XPATH), Constant.WAIT_ELEMENT_EXIST);
		Utilities.captureScreen(driver, id);
		Utilities.assertTextValueVisible(driver, By.xpath(MSG_XPATH), expectedMsg);
	}

	public static void orderProductWithLogin(WebDriver driver, String id, String phone, String address, String note,
			String expectedMsg) {
		Utilities.inputValueAndValidate(driver, By.id(PHONE_TXT_ID), phone, phone);
		Utilities.inputValueAndValidate(driver, By.id(ADDRESS_TXT_ID), address, address);
		Utilities.inputValueAndValidate(driver, By.id(NOTE_TXT_ID), note, note);
		Utilities.clickObscuredElement(driver, By.id(ORDER_BTN_ID), By.xpath(MSG_XPATH), Constant.WAIT_ELEMENT_EXIST);
		Utilities.captureScreen(driver, id);
		Utilities.assertTextValueVisible(driver, By.xpath(MSG_XPATH), expectedMsg);
	}
}
