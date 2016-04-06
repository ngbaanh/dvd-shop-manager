/**
 * 
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
		} else {
			return categoryDAO.removeCategory(catId);
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

}
