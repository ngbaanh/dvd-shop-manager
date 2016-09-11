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
		String getQuery = "select * from category where categoryId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, catId);
			// FIXME - console
			System.out.println("CategoryDAO: " + preparedStatement.toString());
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
		String getQuery = "select * from category";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			// FIXME - console
			System.out.println("CategoryDAO: " + preparedStatement.toString());
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
		String addQuery = "insert into category(CategoryName) values(?)";
		try {
			preparedStatement = connection.prepareStatement(addQuery);
			preparedStatement.setString(1, cat.getCategoryName());
			// FIXME - console
			System.out.println("CategoryDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
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
		String removeQuery = "delete from category where categoryId=?";
		try {
			preparedStatement = connection.prepareStatement(removeQuery);
			preparedStatement.setInt(1, catId);
			// FIXME - console
			System.out.println("CategoryDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
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
		String updateQuery = "update category set CategoryName=? where categoryId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, cat.getCategoryName());
			preparedStatement.setInt(2, cat.getCategoryId());
			// FIXME - console
			System.out.println("CategoryDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isExist(String categoryName) {
		String validQuery = "select CategoryId from category where CategoryName=?";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			preparedStatement.setString(1, categoryName);
			// FIXME - console
			System.out.println("CategoryDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeQuery().next() ? true : false;
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ==============================================
	public boolean isFreeToDelete(int catId) {
		String validQuery = "select DiscSeriesId from disc_series where CategoryId=?";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			preparedStatement.setInt(1, catId);
			boolean actionResult = preparedStatement.executeQuery().next() ? true : false;
			preparedStatement.close();
			return !actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
