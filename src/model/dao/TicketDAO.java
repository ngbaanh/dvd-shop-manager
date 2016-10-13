package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Ticket;
import util.ITicket;

public class TicketDAO extends DatabaseFactory implements ITicket{

	@Override
	public Ticket getTicket(int ticketId) {
		String getQuery = "select * from tiket where TicketId=?";
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
				ticket.setTicketPrice(resultSet.getFloat(9));
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
		String getQuery = "select * from ticket";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			// FIXME - console
			System.out.println("TicketDAO: " + preparedStatement.toString());
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
				ticket.setTicketPrice(resultSet.getFloat(9));
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
	public int createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Integer> getScaleByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Ticket> getTicketList(String searchQuery, byte statusId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Integer> getSalesByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

}
