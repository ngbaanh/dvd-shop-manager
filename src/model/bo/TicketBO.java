package model.bo;

import java.util.ArrayList;

import model.bean.Ticket;
import model.dao.TicketDAO;
import util.ITicket;

public class TicketBO implements ITicket{
	private TicketDAO ticketDAO = new TicketDAO();
	@Override
	public Ticket getTicket(int ticketId) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public ArrayList<Ticket> getTicketList(String searchQuery, int statusId, int page) {
		return ticketDAO.getTicketList(searchQuery, statusId, page);
	}

}
