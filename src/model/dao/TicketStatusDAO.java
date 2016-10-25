package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
