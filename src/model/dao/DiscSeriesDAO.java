/**
 * 
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Disc;
import model.bean.DiscSeries;
import util.Const;
import util.IDiscSeries;

/**
 * @author NguyenBaAnh
 *
 */
public class DiscSeriesDAO extends DatabaseFactory implements IDiscSeries {
	DiscDAO discDAO;
	CategoryDAO categoryDAO;

	public DiscSeriesDAO() {
		super();
		discDAO = new DiscDAO();
		categoryDAO = new CategoryDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#getDiscSeries(int)
	 */
	@Override
	public DiscSeries getDiscSeries(int discSeriesId) {
		String getDiscSeriesQuery = "select * from DISC_SERIES where DicSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(getDiscSeriesQuery);
			preparedStatement.setInt(1, discSeriesId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				DiscSeries discSeries = new DiscSeries();
				discSeries.setDiscSeriesId(discSeriesId);
				discSeries.setDescription(resultSet.getString("Description"));
				discSeries.setDiscSeriesName(resultSet.getString("DiscSeriesName"));
				discSeries.setTotalDisc(resultSet.getInt("TotalDisc"));
				discSeries.setRemainingDisc(resultSet.getInt("RemaningDisc"));
				discSeries.setCategory(categoryDAO.getCategory(resultSet.getInt("CategoryId")));
				discSeries.setListDisc(discDAO.getListDisc(discSeriesId));
				preparedStatement.close();
				return discSeries;
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
	 * @see util.IDiscSeries#getDiscSeriesList(java.lang.String, int, int)
	 */
	@Override
	public ArrayList<DiscSeries> getDiscSeriesList(String searchQuery, int catId, int page) {
		try {
			String getQuery = "select DiscSeriesId from DISC_SERIES ";
			// Xác định ưu tiên: Khi tìm kiếm thì khóa catId và page
			if (!"".equals(searchQuery)) { // Khóa
				getQuery += "where DiscSeriesName=? order by DiscSeriesId desc";
				preparedStatement = connection.prepareStatement(getQuery);
				preparedStatement.setString(1, searchQuery);
			} else {
				int startPosition = Const.ITEMS_PER_PAGE * page;
				int endPosition = startPosition + Const.ITEMS_PER_PAGE;
				if (catId > 0) {
					getQuery += "where CategoryId=" + catId + " order by DiscSeriesId desc";
				}
				getQuery += " limit ?,?";
				preparedStatement = connection.prepareStatement(getQuery);
				preparedStatement.setInt(1, startPosition);
				preparedStatement.setInt(2, endPosition);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<DiscSeries> listDiscSeries = new ArrayList<DiscSeries>();
			while (resultSet.next()) {
				int discSeriesId = resultSet.getInt("DiscSeriesId");
				DiscSeries discSeries = this.getDiscSeries(discSeriesId);
				listDiscSeries.add(discSeries);
			}
			return listDiscSeries;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#addNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean addNewDiscSeries(DiscSeries discSeries) {
		String addDiscSeriesQuery = "insert into DISC_SERIES(DiscSeriesName, Description, TotalDisc, RemainingDisc, CategoryId) "
				+ "values(?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(addDiscSeriesQuery);
			// Auto increment DiscSeriesId
			preparedStatement.setString(1, discSeries.getDiscSeriesName());
			preparedStatement.setString(2, discSeries.getDescription());
			preparedStatement.setInt(3, discSeries.getTotalDisc());
			preparedStatement.setInt(4, discSeries.getTotalDisc()); // Remain = Total
			preparedStatement.setInt(5, discSeries.getCategory().getCategoryId());
			boolean actionResult = preparedStatement.execute();
			if (actionResult == true) {
				int discSeriesId = this.getIdByName(discSeries.getDiscSeriesName());
				Disc disc = discSeries.getListDisc().get(0);
				disc.setDiscSeriesId(discSeriesId);
				for (int i = 0; i < discSeries.getTotalDisc(); i++) {
					discDAO.addNewDisc(disc);
				}
			}
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
	 * @see util.IDiscSeries#updateNewDiscSeries(model.bean.DiscSeries)
	 */
	@Override
	public boolean updateDiscSeries(DiscSeries discSeries) {
		String updateQuery = "update DISC_SERIES set DiscSeriesName=?, Description=?, CategoryId=? where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, discSeries.getDiscSeriesName());
			preparedStatement.setString(2, discSeries.getDescription());
			preparedStatement.setInt(3, discSeries.getCategory().getCategoryId());
			preparedStatement.setInt(4, discSeries.getDiscSeriesId());
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
	 * @see util.IDiscSeries#validateDiscSeries(java.lang.String)
	 */
	@Override
	public boolean validateDiscSeries(String dsName) {
		String validQuery = "select DiscSeriesId from DISC_SERIES where DiscSeriesName=?";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			preparedStatement.setString(1, dsName);
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
	 * @see util.IDiscSeries#removeDiscSeries(java.lang.String)
	 */
	@Override
	public boolean removeDiscSeries(int discSeriesId) {
		String removeQuery = "delete * from DISC_SERIES where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(removeQuery);
			preparedStatement.setInt(1, discSeriesId);
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
	 * @see util.IDiscSeries#getOverallDiscNumber()
	 */
	@Override
	public int getOverallDiscNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDiscSeries#getOverallDiscSeriesNumber()
	 */
	@Override
	public int getOverallDiscSeriesNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//==========================================================================
	// Các hàm bổ trợ, các thành viên tự thiết kế những hàm có ích cho công việc
	
	/**
	 * Lấy mã bộ đĩa theo tên
	 * @param discSeriesName
	 * @return
	 */
	public int getIdByName(String discSeriesName) {
		String getQuery = "select DicSeriesId from DISC_SERIES where DicSeriesName=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, discSeriesName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("DicSeriesId");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public boolean isExist(String discSeriesName) {
		String validQuery = "select DiscSeriesName from DISC_SERIES where DiscSeriesName=?";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			preparedStatement.setString(1, discSeriesName);
			boolean actionResult = preparedStatement.executeQuery().next() ? true : false;
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
