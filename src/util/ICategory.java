/**
 * 
 */
package util;

import java.util.ArrayList;

import model.bean.Category;

/**
 * @author NguyenBaAnh
 *
 */
public interface ICategory {
	/**
	 * Lấy một thể loại theo mã của nó
	 * @param catId mã thể loại
	 * @return thể loại, hoặc null nếu không tìm thấy.
	 */
	public Category getCategory(int catId);
	
	/**
	 * Lấy danh sách thể loại của một bộ đĩa
	 * @param discSeriesId mã bộ đĩa, nếu mã = 0 thì lấy tất cả thể loại đang có trong hệ thống
	 * @return
	 */
	public ArrayList<Category> getListCategories(int discSeriesId);
	
	/**
	 * Thêm một thể loại mới
	 * @param cat Thể loại
	 * @return true nếu thành công, false nếu thất bại
	 */
	public boolean addNewCategory(Category cat);
	
	/**
	 * Xóa một thể loại
	 * @param catId mã thể loại
	 * @return true nếu xóa thành công, false nếu xóa thất bại
	 */
	public boolean removeCategory(int catId);
	
	/**
	 * Cập nhật 1 thể loại
	 * @param cat thể loại với nội dung đã được sửa
	 * @return true nếu thành công, false nếu thất bại.
	 */
	public boolean updateCategory(Category cat);
}
