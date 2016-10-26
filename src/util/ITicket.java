package util;

import java.util.ArrayList;

import model.bean.Ticket;

public interface ITicket {
	public Ticket getTicket(int ticketId);
	public ArrayList<Ticket> getTicketList(String searchQuery, int statusId, int page);
	public boolean renewTicket(Ticket ticket);
	public boolean returnTicket(Ticket ticket);
	public boolean destroyTicket(int ticketId);
	public int createTicket(Ticket ticket);
	public ArrayList<Integer> getScaleByYear(int year);
}
