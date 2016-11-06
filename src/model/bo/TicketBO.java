package model.bo;

import java.util.ArrayList;

import model.bean.RentalDisc;
import model.bean.Ticket;
import model.dao.TicketDAO;
import util.ITicket;

public class TicketBO implements ITicket{
	private TicketDAO ticketDAO;
	
	public TicketBO() {
		 ticketDAO = new TicketDAO();
	}
	@Override
	public Ticket getTicket(int ticketId) {
		return ticketDAO.getTicket(ticketId);
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
		return ticketDAO.destroyTicket(ticketId);
	}

	@Override
	public int createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketDAO.createTicket(ticket);
	}

	@Override
	public int getSaleByYear(int year) {
		return ticketDAO.getSaleByYear(year);
	}

	@Override
	public ArrayList<Ticket> getTicketList(String searchQuery, int statusId, int page) {
		return ticketDAO.getTicketList(searchQuery, statusId, page);
	}

	public int getMaxPage(int statusId) {
		return ticketDAO.getMaxPage(statusId);
	}
	
	public boolean updateTicket(Ticket ticket){
		return ticketDAO.updateTicket (ticket);
	}
	
	public int getFirstYear() {
		return ticketDAO.getFirstYear();
	}
	public int getFirstMonth() {
		return ticketDAO.getFirstMonth();
	}
	public int getSaleByMonth(int year, int month) {
		return ticketDAO.getSaleByMonth(year, month);
	}

}
