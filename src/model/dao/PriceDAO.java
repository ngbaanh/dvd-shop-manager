/**
 * 
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Disc;
import model.bean.DiscSeries;
import model.bean.Price;
import util.IPrice;

/**
 * @author ngbaanh
 *
 */
public class PriceDAO extends DatabaseFactory implements IPrice {

	/**
	 * 
	 */
	public PriceDAO() {
		super();
	}

	@Override
	public Price getPrice(byte qualityId) {
		String getDiscSeriesQuery = "select * from price where QualityId=?";
		try {
			preparedStatement = connection.prepareStatement(getDiscSeriesQuery);
			preparedStatement.setInt(1, qualityId);
			// FIXME - console
			System.out.println("PriceDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Price price = new Price();
				price.setQualityId(resultSet.getByte("QualityId"));
				price.setPrice(resultSet.getInt("Price"));
				preparedStatement.close();
				return price;
			} else {
				preparedStatement.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Price> getListPrices() {
		String getQuery = "select QualityId from price";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Price> list = new ArrayList<Price>();
			while (resultSet.next()) {
				list.add(this.getPrice(resultSet.getByte("QualityId")));
			}
			preparedStatement.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updatePrice(Price price) {
		String updateQuery = "update price set Price=? where QualityId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(1, price.getPrice());
			preparedStatement.setByte(2, price.getQualityId());
			System.out.println(preparedStatement);
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
