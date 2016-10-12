package model.bo;

import model.dao.TicketStatusDAO;

public class TicketStatusBO {
	
	private TicketStatusDAO ticketstatusDAO;
	
	public TicketStatusBO () {
		ticketstatusDAO = new TicketStatusDAO();
	}
	
	public String getStatusName(byte statusId) {
		return ticketstatusDAO.getStatusName(statusId);
	}
}
