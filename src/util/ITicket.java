/**
 * 
 */
package util;

import java.util.ArrayList;

import model.bean.Ticket;

/**
 * @author NguyenBaAnh
 *
 */
public interface ITicket {
	public Ticket getTicket(int ticketId);
	public ArrayList<Ticket> getTicketList(String searchQuery, byte statusId, int page);
	public boolean renewTicket(Ticket ticket);
	public boolean returnTicket(Ticket ticket);
	public boolean destroyTicket(int ticketId);
	public boolean createTicket(Ticket ticket);
	public ArrayList<Integer> getSalesByYear(int year); 
}
