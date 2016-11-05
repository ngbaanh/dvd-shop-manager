/**
 * 
 */
package util;

/**
 * @author NguyenBaAnh
 * Chú ý thay đổi các hằng số dưới đây sẽ trực tiếp ảnh hưởng lên toàn bộ hê thống
 */
public final class Const {
	public static int ITEMS_PER_PAGE = 5;
	public static int MAXLENGTH_STAFFID = 30;
	public static int MAXLENGTH_PASSWORD = 30;
	public static int MAXLENGTH_NAME = 100;
	public static int MAXLENGTH_DESCRIPTION = 255;
	public static int MAX_ITEM = 100; // số đĩa mỗi bộ
	public static boolean USE_BOOTSTRAP_ONLINE = false; // CDN bootstrap
	public static boolean ENABLE_LOADING_SCREEN = true; // loading screen
	public static boolean USE_DATATABLES_ONLINE = true; // CDN DATATABLES
	
	// String
	public static final String INVALID_FORM = "Dữ liệu nhập vào không hợp lệ, xin nhập lại!";
	public static final String NOT_FOUND_ON_SEARCH = "Không tìm thấy";
	public static final String NOT_FOUND_ON_FILTER = "Không có kết quả";
	public static final String INPUT_POSITIVE_NUMBER = "Số lượng phải là số dương";
	
	public static int MAX_RENTING_WEEKS = 12; // số tuần tối đa có thể thuê
	public static int DEFAULT_RENTING_WEEKS = 1; // số tuần mặc định lúc chọn đĩa
	
	/* Special character */
	public static final String CURRENCY_VND = "₫";
	public static final String CURRENCY_USD = "$";
	public static final String CURRENCY_POUND = "£";
}
