package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.TicketStatus;

public class TicketStatusDAO extends DatabaseFactory {

	public String getStatusName(byte statusId) {
		String getQuery = "select * from ticket_status where statusId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setByte(1, statusId);
			
			String statusName = null;
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				statusName = resultSet.getString("StatusName");
			}
			preparedStatement.close();
			return statusName;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<TicketStatus> getTicketStatusList() {
		String getQuery = "select * from ticket_status";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			
			ArrayList<TicketStatus> listTicketStatus = new ArrayList<TicketStatus>();
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				TicketStatus ticketStatus = new TicketStatus();
				ticketStatus.setStatusId(resultSet.getInt(1));
				ticketStatus.setStatusName(resultSet.getString(2));
				listTicketStatus.add(ticketStatus);
			}
			preparedStatement.close();
			return listTicketStatus;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
