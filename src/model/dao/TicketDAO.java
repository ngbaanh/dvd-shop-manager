package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.RentalDisc;
import model.bean.Ticket;
import util.Const;
import util.ITicket;

public class TicketDAO extends DatabaseFactory implements ITicket{

	@Override
	public Ticket getTicket(int ticketId) {
		String getQuery = "select * from ticket where TicketId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setInt(1, ticketId);
			// FIXME - console
			System.out.println("TicketDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(resultSet.getInt(1));
				ticket.setStartTime(resultSet.getTimestamp(2));
				ticket.setStatusId(resultSet.getByte(3));
				ticket.setCustomerName(resultSet.getString(4));
				ticket.setCustomerPhone(resultSet.getString(5));
				ticket.setCustomerAddress(resultSet.getString(6));
				ticket.setStaffName(resultSet.getString(7));
				ticket.setDeposit(resultSet.getString(8));
				ticket.setTicketPrice(resultSet.getInt(9));
				preparedStatement.close();
				return ticket;
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
	public ArrayList<Ticket> getTicketList(String searchQuery, int statusId, int page) {
		try {
			String getQuery = "select * from ticket";
			// Xác định ưu tiên: Khi tìm kiếm thì khóa catId và page
			if (!"".equals(searchQuery)) { // Khóa
				getQuery += " where TicketId like ? or CustomerName like ? order by TicketId desc";
				preparedStatement = connection.prepareStatement(getQuery);
				preparedStatement.setString(1, "%" + searchQuery + "%");
				preparedStatement.setString(2, "%" + searchQuery + "%");
			} else {
				int startPosition = Const.ITEMS_PER_PAGE * (page - 1);
				if (statusId >= 0) {
					getQuery += " where StatusId=" + statusId;
				}
				getQuery += " order by TicketId desc limit ?,?";
				preparedStatement = connection.prepareStatement(getQuery);
				preparedStatement.setInt(1, startPosition);
				preparedStatement.setInt(2, Const.ITEMS_PER_PAGE);
			}
			// FIXME - console
			System.out.println("TicketDAO:" + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<Ticket> alTicket = new ArrayList<Ticket>();
			while (resultSet.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(resultSet.getInt("TicketId"));
				ticket.setStartTime(resultSet.getTimestamp("StartTime"));
				ticket.setStatusId(resultSet.getByte(3));
				ticket.setCustomerName(resultSet.getString(4));
				ticket.setCustomerPhone(resultSet.getString(5));
				ticket.setCustomerAddress(resultSet.getString(6));
				ticket.setStaffName(resultSet.getString(7));
				ticket.setDeposit(resultSet.getString(8));
				ticket.setTicketPrice(resultSet.getInt(9));
				alTicket.add(ticket);
			}
			preparedStatement.close();
			return alTicket;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public boolean renewTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean returnTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean destroyTicket(int ticketId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * return TicketId on success, 0 on failure
	 */
	public int createTicket(Ticket ticket) {
		String createTicketQuery = "insert into ticket(StartTime, StatusId, CustomerName, CustomerPhone, CustomerAddress, StaffName, Deposit, TicketPrice)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			preparedStatement = connection.prepareStatement(createTicketQuery);
			preparedStatement.setTimestamp(1, ticket.getStartTime());
			preparedStatement.setByte(2, ticket.getStatusId());
			preparedStatement.setString(3, ticket.getCustomerName());
			preparedStatement.setString(4, ticket.getCustomerPhone());
			preparedStatement.setString(5, ticket.getCustomerAddress());
			preparedStatement.setString(6, ticket.getStaffName());
			preparedStatement.setString(7, ticket.getStaffName());
			preparedStatement.setInt(8, ticket.getTicketPrice());
			// FIXME - console
			System.out.println("TicketDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeUpdate() > 0 ? true : false;
			preparedStatement.close();
			if (actionResult) {
				preparedStatement.close();
				String getTicketIdQuery = "select TicketId from ticket where StartTime = ?";
				PreparedStatement ps2 = connection.prepareStatement(getTicketIdQuery);
				ps2.setTimestamp(1, ticket.getStartTime());
				int ticketId = 0;
				if (ps2.execute()) {
					ResultSet rs = ps2.getResultSet();
					if (rs.next()) {
						ticketId = rs.getInt(1);
						String createRentalDiscQuery = "insert into rental_disc (TicketId, DiscId, RentingWeeks, FinalTime) "
								+ " values(?, ?, ?, ?)";
						String updateDiscStatusQuery = "update disc set Available=? where DiscId=?";
						// TODO
						ArrayList<RentalDisc> listDisc = ticket.getListDisc();
						for (RentalDisc rd : listDisc) {
							PreparedStatement ps3 = connection.prepareStatement(createRentalDiscQuery);
							ps3.setInt(1, ticketId);
							ps3.setInt(2, rd.getDiscId());
							ps3.setInt(3, rd.getRentingWeeks());
							ps3.setTimestamp(4, rd.getFinalTime());
							System.out.println("TicketDAO > Add RentalDisc: " + ps3.toString());
							ps3.execute();
							
							PreparedStatement ps4 = connection.prepareStatement(updateDiscStatusQuery);
							ps4.setBoolean(1, new Boolean(false));
							ps4.setInt(2, rd.getDiscId());
							System.out.println("TicketDAO > Change Disc Availability: " + ps4.toString());
							ps4.execute();
							
							ps3.close();
							ps4.close();
						}
						ps2.close();
						return ticketId > 0 ? ticketId : 0;
					}
					return 0;
				}
				return 0;
			} else {
				preparedStatement.close();
				return 0;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<Integer> getScaleByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMaxPage(int statusId) {
		String getQuery = "select count(*) from ticket" + ((statusId >= 0) ? " where StatusId=" + statusId : "");
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
	public ArrayList<Ticket> getTicketList(String searchQuery, byte statusId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Integer> getSalesByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateTicket(Ticket ticket) {
		String updateQuery = "update ticket set StartTime=?, StatusId=?, CustomerName=?,"
				+ "CustomerPhone=?, CustomerAddress=?, StaffName=?, Deposit=?, TicketPrice=? where TicketId=?";
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setTimestamp(1, ticket.getStartTime());
			preparedStatement.setByte(2, ticket.getStatusId());
			preparedStatement.setString(3, ticket.getCustomerName());
			preparedStatement.setString(4, ticket.getCustomerPhone());
			preparedStatement.setString(5,ticket.getCustomerAddress());
			preparedStatement.setString(6, ticket.getStaffName());
			preparedStatement.setString(7, ticket.getDeposit());
			preparedStatement.setInt(8, ticket.getTicketPrice());
			preparedStatement.setInt(9, ticket.getTicketId());
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
