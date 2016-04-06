/**
 * 
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Disc;
import util.IDisc;

/**
 * @author NguyenBaAnh
 *
 */
public class DiscDAO extends DatabaseFactory implements IDisc {

	public DiscDAO() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#getDisc(int)
	 */
	@Override
	public Disc getDisc(int discId) {
		String getQuery = "select DISC.DiscSeriesId, DISC.Available, DISC.Place, PRICE.Price "
				+ " from DISC, PRICE where DiscId=? and DISC.QualityId=PRICE.QualityId";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, discId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Disc disc = new Disc();
				disc.setDiscId(discId);
				disc.setDiscSeriesId(resultSet.getInt("DiscSeriesId"));
				disc.setPlace(resultSet.getString("Place"));
				disc.setAvailable(resultSet.getBoolean("Available"));
				disc.setPrice(resultSet.getInt("Price"));
				preparedStatement.close();
				return disc;
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
	 * @see util.IDisc#getListDisc(int)
	 */
	@Override
	public ArrayList<Disc> getListDisc(int discSeriesId) {
		String getQuery = "select DiscId from DISC where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, discSeriesId);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Disc> listDisc = new ArrayList<Disc>();
			while (resultSet.next()) {
				Disc disc = this.getDisc(resultSet.getInt("DiscId"));
				listDisc.add(disc);
			}
			preparedStatement.close();
			return listDisc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IDisc#addNewDisc(model.bean.Disc)
	 */
	@Override
	public boolean addNewDisc(Disc disc) {
		String addQuery = "insert into DISC(DiscId, DiscSeriesId, Available, QualityId, Place) "
				+ "values(?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(addQuery);
			preparedStatement.setInt(1, disc.getDiscId());
			preparedStatement.setInt(2, disc.getDiscSeriesId());
			preparedStatement.setBoolean(3, disc.isAvailable());
			preparedStatement.setInt(4, disc.getQualityId());
			preparedStatement.setString(5, disc.getPlace());
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
	 * @see util.IDisc#updateDisc(model.bean.Disc)
	 */
	@Override
	public boolean updateDisc(Disc disc) {
		String updateQuery = "update DISC set QualityId=?, Place=? where DiscId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(1, disc.getQualityId());
			preparedStatement.setString(2, disc.getPlace());
			preparedStatement.setInt(3, disc.getDiscId());
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
	 * @see util.IDisc#removeDisc(int)
	 */
	@Override
	public boolean removeDisc(int discId) {
		String removeQuery = "delete * from DISC where DiscId=?";
		try {
			preparedStatement = connection.prepareStatement(removeQuery);
			preparedStatement.setInt(1, discId);
			boolean actionResult = preparedStatement.execute();
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
