package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.RentalDisc;

public class RentalDiscDAO extends DatabaseFactory {

	public ArrayList<RentalDisc> getListDiscOfTicket(int ticketId) {
		String getQuery = "select * from rental_disc where TicketId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, ticketId);
			// FIXME - console
			System.out.println("RentalDiscDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<RentalDisc> listRentalDisc = new ArrayList<RentalDisc>();
			while (resultSet.next()) {
				RentalDisc rentalDisc = new RentalDisc();
				rentalDisc.setTicketId(resultSet.getInt("TicketId"));
				rentalDisc.setDiscId(resultSet.getInt("DiscId"));
				rentalDisc.setRentingWeeks(resultSet.getByte("RentingWeeks"));
				rentalDisc.setFinalTime(resultSet.getTimestamp("FinalTime"));
				rentalDisc.setReturned(resultSet.getBoolean("Returned"));
				listRentalDisc.add(rentalDisc);
			}
			preparedStatement.close();
			return listRentalDisc;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateRentalDisc(RentalDisc rentalDisc) {
		// TODO Auto-generated method stub
		String updateQuery = "update rental_disc set RentingWeeks=?, FinalTime=? where TicketId=? and DiscId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setByte(1, rentalDisc.getRentingWeeks());
			preparedStatement.setTimestamp(2, rentalDisc.getFinalTime());
			preparedStatement.setInt(3, rentalDisc.getTicketId());
			preparedStatement.setInt(4, rentalDisc.getDiscId());
			System.out.println("DiscDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<RentalDisc> getConflictDiscList(ArrayList<RentalDisc> rentalDiscList) {
		ArrayList<RentalDisc> conflictList = new ArrayList<RentalDisc>();
		for (RentalDisc rd : rentalDiscList) {
			if (this.isConflict(rd.getDiscId())) {
				conflictList.add(rd);
			}
		}
		return conflictList;
	}
	
	private boolean isConflict(int discId) {
		//String checkSQL = "select DiscId from rental_disc where Returned = ? and DiscId = ?";
		String checkSQL = "select DiscId from disc where Available = ? and DiscId = ?";
		try {
			preparedStatement = connection.prepareStatement(checkSQL);
			preparedStatement.setBoolean(1, new Boolean(false));
			preparedStatement.setInt(2, discId);
			// FIXME - console
			System.out.println("RentalDiscDAO: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				preparedStatement.close();
				return true;
			}
			preparedStatement.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean returnDisc(int discId) {
		String updateQuery = "UPDATE rental_disc SET Returned = true where DiscId = ?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setInt(1, discId);
			System.out.println("RentalDiscDAO: " + preparedStatement.toString());
			if (preparedStatement.executeUpdate() > 0) {
				preparedStatement.close();
				return true;
			}
			preparedStatement.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
