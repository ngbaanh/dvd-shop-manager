/**
 * Lưu ý, phải kiểm tra ràng buộc, các điều kiện ở đây thật kĩ trước khi đẩy qua DAO.
 * ở DAO sẽ không xử lí gì cái này nữa mà sẽ chạy luôn.
 */
package model.bo;

import java.util.ArrayList;

import model.bean.Category;
import model.dao.CategoryDAO;
import util.ICategory;

/**
 * @author NguyenBaAnh
 *
 */
public class CategoryBO implements ICategory {
	private CategoryDAO categoryDAO;

	public CategoryBO() {
		categoryDAO = new CategoryDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#getCategory(int)
	 */
	@Override
	public Category getCategory(int catId) {
		if (catId < 0) {
			return null;
		} else {
			return categoryDAO.getCategory(catId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#getListCategories(int)
	 */
	@Override
	public ArrayList<Category> getListCategories() {
		return categoryDAO.getListCategories();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#addNewCategory(model.bean.Category)
	 */
	@Override
	public boolean addNewCategory(Category cat) {
		if (cat == null) {
			return false;
		} else {
			return categoryDAO.addNewCategory(cat);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#removeCategory(int)
	 */
	@Override
	public boolean removeCategory(int catId) {
		if (catId < 0) {
			return false;
		} else if (this.isFreeToDelete(catId)) {
			return categoryDAO.removeCategory(catId);
		} else {
			return false;
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#updateCategory(model.bean.Category)
	 */
	@Override
	public boolean updateCategory(Category cat) {
		if (cat == null) {
			return false;
		} else {
			return categoryDAO.updateCategory(cat);
		}
	}

	@Override
	public boolean isExist(String categoryName) {
		return categoryDAO.isExist(categoryName);
	}
	
	//=============================
	private boolean isFreeToDelete(int catId) {
		return categoryDAO.isFreeToDelete(catId);
	}

}
