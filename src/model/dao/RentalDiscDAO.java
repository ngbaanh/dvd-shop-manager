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
}
