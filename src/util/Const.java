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
	public static int MAXLENGTH_DESCRIPTION = 1000;
	public static int MAX_ITEM = 30; // số đĩa mỗi bộ
	public static boolean USE_BOOTSTRAP_ONLINE = true; // CDN bootstrap
	public static boolean ENABLE_LOADING_SCREEN = true; // loading screen
	
	// String
	public static final String INVALID_FORM = "Dữ liệu nhập vào không hợp lệ, xin nhập lại!";
	public static final String NOT_FOUND_ON_SEARCH = "Không tìm thấy";
	public static final String NOT_FOUND_ON_FILTER = "Không có kết quả";
	public static final String INPUT_POSITIVE_NUMBER = "Số lượng phải là số dương";
}
