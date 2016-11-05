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
 * @author NguyenVanQuang
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
		String getDiscSeriesQuery = "select * from disc_series where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(getDiscSeriesQuery);
			preparedStatement.setInt(1, discSeriesId);
			// FIXME - console
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				DiscSeries discSeries = new DiscSeries();
				discSeries.setDiscSeriesId(discSeriesId);
				discSeries.setDescription(resultSet.getString("Description"));
				discSeries.setDiscSeriesName(resultSet.getString("DiscSeriesName"));
				discSeries.setTotalDisc(resultSet.getInt("TotalDisc"));
				discSeries.setRemainingDisc(resultSet.getInt("RemainingDisc"));
				discSeries.setCategory(categoryDAO.getCategory(resultSet.getInt("CategoryId")));
				discSeries.setListDisc(discDAO.getListDisc(discSeriesId));
				preparedStatement.close();
				return discSeries;
			} else {
				preparedStatement.close();
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
			String getQuery = "select DiscSeriesId from disc_series";
			getQuery += " where DiscSeriesName like ?";
			if (catId > 0) {
				getQuery += " and CategoryId=" + catId;
			}
			getQuery += " order by DiscSeriesId desc limit ?,?";
			
			int startPosition = Const.ITEMS_PER_PAGE * (page - 1);
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, "%" + searchQuery + "%");
			preparedStatement.setInt(2, startPosition);
			preparedStatement.setInt(3, Const.ITEMS_PER_PAGE);
			// FIXME - console
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<DiscSeries> listDiscSeries = new ArrayList<DiscSeries>();
			while (resultSet.next()) {
				int discSeriesId = resultSet.getInt("DiscSeriesId");
				DiscSeries discSeries = this.getDiscSeries(discSeriesId);
				listDiscSeries.add(discSeries);
			}
			preparedStatement.close();
			return listDiscSeries;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @author quang
	 * @param searchQuery
	 * @param catId
	 * @param start
	 * @param length
	 * @param orderDirection 
	 * @param orderColumn 
	 * @return
	 */
	public ArrayList<DiscSeries> getDiscSeriesList(String searchQuery, int catId, int start, int length, int orderColumn, String orderDirection) {
		try {
			String getQuery = "select DiscSeriesId from disc_series, category";
			getQuery += " where disc_series.CategoryId = category.CategoryId";
			getQuery += " and DiscSeriesName like ?";
			if (catId > 0) {
				getQuery += " and category.CategoryId=" + catId;
			}
			String orderColumnName = null;
			switch (orderColumn) {
			case 1:
				orderColumnName = "DiscSeriesName";
				break;
			case 2:
				orderColumnName = "CategoryName";
				break;
			case 3:
				orderColumnName = "RemainingDisc";
				break;
			}
			getQuery += " order by " + orderColumnName + " " + orderDirection;
			if (start >= 0) {
				getQuery += " limit ?,?";
			}
			
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, "%" + searchQuery + "%");
			if (start >= 0) {
				preparedStatement.setInt(2, start);
				preparedStatement.setInt(3, length);
			}
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<DiscSeries> listDiscSeries = new ArrayList<DiscSeries>();
			while (resultSet.next()) {
				int discSeriesId = resultSet.getInt("DiscSeriesId");
				DiscSeries discSeries = this.getDiscSeries(discSeriesId);
				listDiscSeries.add(discSeries);
			}
			preparedStatement.close();
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
		String addDiscSeriesQuery = "insert into disc_series(DiscSeriesName, Description, CategoryId, TotalDisc, RemainingDisc) "
				+ "values(?, ?, ?, 0, 0)";
		try {
			preparedStatement = connection.prepareStatement(addDiscSeriesQuery);
			// Auto increment DiscSeriesId
			preparedStatement.setString(1, discSeries.getDiscSeriesName());
			preparedStatement.setString(2, discSeries.getDescription());
			preparedStatement.setInt(3, discSeries.getCategory().getCategoryId());
			// FIXME - console
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
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
		String updateQuery = "update disc_series set DiscSeriesName=?, Description=?, CategoryId=? where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, discSeries.getDiscSeriesName());
			preparedStatement.setString(2, discSeries.getDescription());
			preparedStatement.setInt(3, discSeries.getCategory().getCategoryId());
			preparedStatement.setInt(4, discSeries.getDiscSeriesId());
			// FIXME - console
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
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
	 * @see util.IDiscSeries#validateDiscSeries(java.lang.String)
	 */
	@Deprecated
	@Override
	public boolean validateDiscSeries(String dsName) {
		String validQuery = "select DiscSeriesId from disc_series where DiscSeriesName=?";
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
		String removeListDiscQuery = "delete from disc where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(removeListDiscQuery);
			preparedStatement.setInt(1, discSeriesId);
			// FIXME - console
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {}
		String removeDiscSeriesListQuery = "delete from disc_series where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(removeDiscSeriesListQuery);
			preparedStatement.setInt(1, discSeriesId);
			// FIXME - console
			System.out.println("DiscSeriesDAO: " + preparedStatement.toString());
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

	// ==========================================================================
	// Các hàm bổ trợ, các thành viên tự thiết kế những hàm có ích cho công việc

	/**
	 * Lấy mã bộ đĩa theo tên
	 * 
	 * @param discSeriesName
	 * @return
	 */
	public int getIdByName(String discSeriesName) {
		String getQuery = "select DiscSeriesId from disc_series where DiscSeriesName=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, discSeriesName);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("DiscSeriesId");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Tính tổng số trang
	 * 
	 * @param catId
	 *            mã thể loại
	 * @return toàn bộ số trang khi catId=0, toàn bộ số trang cho riêng mã thể
	 *         loại catId khi catId>0
	 */
	public int getMaxPage(int catId) {
		String getQuery = "select count(*) from disc_series" + ((catId > 0) ? " where CategoryId=" + catId : "");
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return (int) Math.ceil((double) resultSet.getInt(1) / Const.ITEMS_PER_PAGE);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * Tính tổng số trang
	 * Bổ sung số trang theo nội dung tìm kiếm
	 * 
	 * @param catId
	 *            mã thể loại
	 * @param searchQuery
	 *            nội dung tìm kiếm
	 * @return toàn bộ số trang khi catId=0, toàn bộ số trang cho riêng mã thể
	 *         loại catId khi catId>0
	 */
	public int getMaxPage(int catId, String searchQuery) {
		String getQuery = "select count(*) from disc_series ";
		getQuery += "where DiscSeriesName like ? ";
		getQuery += ((catId > 0) ? "and CategoryId=" + catId : "");
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, "%" + searchQuery + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return (int) Math.ceil((double) resultSet.getInt(1) / Const.ITEMS_PER_PAGE);
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
	}

	public boolean isExist(String discSeriesName) {
		String validQuery = "select DiscSeriesName from disc_series where DiscSeriesName=?";
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

	public int numDiscSeries() {
		String validQuery = "select COUNT(*) from disc_series";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
