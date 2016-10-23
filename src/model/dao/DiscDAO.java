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
		String getQuery = "select DISC.DiscSeriesId, DISC.Available, DISC.Place, PRICE.Price, DISC.QualityId "
				+ " from DISC, PRICE where DiscId=? and DISC.QualityId=PRICE.QualityId";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, discId);
			// FIXME - console
			System.out.println("DiscDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Disc disc = new Disc();
				disc.setDiscId(discId);
				disc.setDiscSeriesId(resultSet.getInt("DiscSeriesId"));
				disc.setQualityId(resultSet.getByte("QualityId"));
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
			// FIXME - console
			System.out.println("DiscDAO: " + preparedStatement.toString());
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
		// FIXME
		String addQuery = "insert into DISC(DiscSeriesId, QualityId, Place) " + "values(?, ?, ?)";
		String incNumber = "update DISC_SERIES set TotalDisc = (TotalDisc+1), RemainingDisc = (RemainingDisc+1) where DiscSeriesId=?";
		try {
			preparedStatement = connection.prepareStatement(addQuery);
			preparedStatement.setInt(1, disc.getDiscSeriesId());
			preparedStatement.setInt(2, disc.getQualityId());
			preparedStatement.setString(3, disc.getPlace());
			// FIXME - console
			System.out.println("DiscDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
			if (actionResult == true) {
				preparedStatement = connection.prepareStatement(incNumber);
				preparedStatement.setInt(1, disc.getDiscSeriesId());
				// FIXME - console
				System.out.println("DiscDAO: " + preparedStatement.toString());
				preparedStatement.executeUpdate();
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
			// FIXME - console
			System.out.println("DiscDAO: " + preparedStatement.toString());
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
	 * @see util.IDisc#removeDisc(int)
	 */
	@Override
	public boolean removeDisc(int discId) {
		String removeQuery = "delete from DISC where DiscId=?";
		String decNumber = "update DISC_SERIES set TotalDisc = (TotalDisc-1), RemainingDisc = (RemainingDisc-1) where DiscSeriesId="
				+ this.getDisc(discId).getDiscSeriesId();
		try {
			preparedStatement = connection.prepareStatement(removeQuery);
			preparedStatement.setInt(1, discId);
			// FIXME - console
			System.out.println("DiscDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
			if (actionResult == true) {
				preparedStatement = connection.prepareStatement(decNumber);
				// FIXME - console
				System.out.println("DiscDAO: " + preparedStatement.toString());
				preparedStatement.executeUpdate();
			}
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
