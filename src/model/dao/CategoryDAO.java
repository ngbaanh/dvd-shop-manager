/**
 * 
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Category;
import util.ICategory;

/**
 * @author NguyenBaAnh
 *
 */
public class CategoryDAO extends DatabaseFactory implements ICategory {

	public CategoryDAO() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#getCategory(int)
	 */
	@Override
	public Category getCategory(int catId) {
		String getQuery = "select * from CATEGORY where categoryId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, catId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Category cat = new Category();
				cat.setCategoryId(resultSet.getInt("CategoryId"));
				cat.setCategoryName(resultSet.getString("CategoryName"));
				preparedStatement.close();
				return cat;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#getListCategories(int)
	 */
	@Override
	public ArrayList<Category> getListCategories() {
		String getQuery = "select * from CATEGORY";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Category> listCategories = new ArrayList<Category>();
			while (resultSet.next()) {
				Category cat = new Category();
				cat.setCategoryId(resultSet.getInt("CategoryId"));
				cat.setCategoryName(resultSet.getString("CategoryName"));
				listCategories.add(cat);
			}
			preparedStatement.close();
			return listCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#addNewCategory(model.bean.Category)
	 */
	@Override
	public boolean addNewCategory(Category cat) {
		String addQuery = "insert into CATEGORY(CategoryId, CategoryName) values(?, ?)";
		try {
			preparedStatement = connection.prepareStatement(addQuery);
			preparedStatement.setInt(1, cat.getCategoryId());
			preparedStatement.setString(2, cat.getCategoryName());
			boolean actionResult = preparedStatement.execute();
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.ICategory#removeCategory(int)
	 */
	@Override
	public boolean removeCategory(int catId) {
		String addQuery = "delete * from CATEGORY where categoryId=?";
		try {
			preparedStatement = connection.prepareStatement(addQuery);
			preparedStatement.setInt(1, catId);
			boolean actionResult = preparedStatement.execute();
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
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
		String addQuery = "update CATEGORY set CategoryName=? where categoryId=?";
		try {
			preparedStatement = connection.prepareStatement(addQuery);
			preparedStatement.setString(1, cat.getCategoryName());
			preparedStatement.setInt(2, cat.getCategoryId());
			boolean actionResult = preparedStatement.execute();
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
