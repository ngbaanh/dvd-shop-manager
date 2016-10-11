/**
 * 
 */
package model.dao;

import java.util.ArrayList;

import model.bean.Ticket;
import util.ITicket;

/**
 * @author NguyenBaAnh
 *
 */
public class TicketDAO extends DatabaseFactory implements ITicket {

	public TicketDAO() {
		super();
	}

	@Override
	public Ticket getTicket(int ticketId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ticket> getTicketList(String searchQuery, byte statusId, int page) {
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
	public boolean createTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Integer> getSalesByYear(int year) {
		// TODO Auto-generated method stub
		return null;
	}

}
