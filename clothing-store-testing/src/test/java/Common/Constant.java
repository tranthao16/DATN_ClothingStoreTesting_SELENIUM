package Common;

public final class Constant {
	// Define general information to access test application
	public static final String BASE_URL		 			= "http://139.180.213.183:8010/";
	public static final String URL_REGISTER 			= BASE_URL + "register";
	public static final String URL_LOGIN				= BASE_URL + "login";
	public static final String URL_CHANGE_PWD			= BASE_URL + "change/pasword";

	public static final String BASE_URL_ADMIN			= "http://139.180.213.183:8010/admin/login";
	
	// User name and password to access website
	public static final String BASE_EMAIL_ADMIN			= "admin@gmail.com";
	public static final String BASE_PASSWORD_ADMIN		= "1234567890";
	
	public static final String BASE_EMAIL 				= "clienttest@gmail.com";
	public static final String BASE_PHONE 				= "0987654321";
	public static final String BASE_PASSWORD			= "Abcd1234";
	public static final String BASE_NAME 				= "Client";
	public static final String BASE_ADDRESS 			= "HN";
	public static final String BASE_NOTE	 			= "test";
	
	public static final String NULL_STRING 				= "NULL";
	public static final String EMPTY_STRING 			= "";
	public static final String FULL_SPACE_STRING 		= "        ";
	public static final String HTML_STRING 				= "<p>test</p>";
	public static final String SQL_STRING 				= "1; DROP TABLE Users";
	public static final String JAVASCRIPT_STRING 		= "<script>alert('@#$')</script>";
	public static final String TEST_TEXT				= "test";
	public static final String TEST1_TEXT				= "test1";
	public static final String TEST2_TEXT				= "@#$1";
	public static final String TEST3_TEXT				= "1 2";
	public static final String NAME_TEXT				= "name";
	public static final String INDEX_TEXT				= "INDEX";
	
	public static final String BASE_EMAIL1 				= " clienttest@gmail.com ";
	public static final String BASE_EMAIL2 				= "client test@gmail.com";
	public static final String BASE_EMAIL3 				= "clienttestgmail.com";
	public static final String BASE_EMAIL4 				= "clienttest@gmailcom";
	public static final String BASE_EMAIL5 				= "clienttest@gmail.";
	public static final String BASE_EMAIL6 				= "!@#$%^&*()_+{}:\";'<>,.?/~`";
	public static final String BASE_EMAIL_UPPER			= BASE_EMAIL.toUpperCase();
	public static final String BASE_EMAIL_PWD2 			= "clienttest2@gmail.com";
	public static final String BASE_EMAIL_PWD3			= "clienttest3@gmail.com";
	
	public static final String BASE_NAME1 				= " Kiểm Thử ";
	public static final String BASE_NAME2 				= "Client@#$";
	public static final String BASE_NAME3 				= "Client123";
	
	public static final String BASE_PASSWORD1 			= "1234567";
	public static final String BASE_PASSWORD2 			= "12345678";
	public static final String BASE_PASSWORD3 			= "1234567890123456";
	public static final String BASE_PASSWORD4 			= "12345678901234567";
	public static final String BASE_PASSWORD_UPPER		= BASE_PASSWORD.toUpperCase();
	
	public static final String BASE_ADDRESS1 			= " Số 298 đường Cầu Diễn, quận Bắc Từ Liêm, Hà Nội ";
	public static final String BASE_ADDRESS2 			= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	public static final String BASE_ADDRESS3 			= "test\ntest";

	public static final String BASE_PHONE1 				= " 0987654321 ";
	public static final String BASE_PHONE2 				= "0987 654 321";
	public static final String BASE_PHONE3 				= "098765Abcd";
	public static final String BASE_PHONE4 				= "098765@#$%";
	public static final String BASE_PHONE5 				= "0000000000";
	public static final String BASE_PHONE6 				= "098765432";
	public static final String BASE_PHONE7 				= "0123456789";
	public static final String BASE_PHONE8 				= "1234567890";
	public static final String BASE_PHONE9 				= "09876543210";
	
	public static final String VALID_KEYWORD1 			= "Trong phòng chờ với Bác sĩ Wynn - TẬP 2";
	public static final String VALID_KEYWORD2 			= "Trong phòng chờ với Bác sĩ Wynn";
	public static final String VALID_KEYWORD3 			= " Trong phòng chờ với Bác sĩ Wynn ";
	public static final String VALID_KEYWORD4 			= VALID_KEYWORD1.toUpperCase();
	public static final String INVALID_KEYWORD 			= "Kiểm Thử test";
	
	public static final String BASE_NOTE1 				= " test test ";
	public static final String BASE_NOTE2 				= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	public static final String BASE_NOTE3 				= "test\ntest";
	
	public static final String BASE_TEXT 				= "Kiểm thử";
	public static final String BASE_CODE				= "Abcd1234";
	public static final String BASE_IMAGE				= "valid.png";
	public static final String BASE_DESCRIPTION			= "Kiểm thử";
	public static final String BASE_PRICE				= "20";
	public static final String BASE_SALE				= "24";
	public static final String BASE_QUANTITY			= "2024";
	
	// Define timeout
	public static final double WAIT_INTERVAL 			= 1.5; 
	public static final int WAIT_ELEMENT_EXIST 			= 5;
	public static final int WAIT_ELEMENT_NOT_EXIST 		= 5;
	public static final double WAIT_REFRESH_SCREEN 		= 2.0;

	// Define threshold
	public static final double SIMILITY_THRESHOLD 		= 0.99;
	
	// Define browser
	public static final String IE_BROWSER 		= "IE";
	public static final String EDGE_BROWSER 	= "Edge";
	public static final String CHROME_BROWSER 	= "Chrome";
	public static final String FIREFOX_BROWSER 	= "FireFox";
	public static final String MSEDGE_BROWSER 	= "MSEdge";
}
