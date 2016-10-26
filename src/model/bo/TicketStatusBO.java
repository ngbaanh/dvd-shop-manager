package model.bo;

import java.util.ArrayList;

import model.bean.TicketStatus;
import model.dao.TicketStatusDAO;

public class TicketStatusBO {
	
	private TicketStatusDAO ticketstatusDAO;
	
	public TicketStatusBO () {
		ticketstatusDAO = new TicketStatusDAO();
	}
	
	public String getStatusName(byte statusId) {
		return ticketstatusDAO.getStatusName(statusId);
	}

	public ArrayList<TicketStatus> getTicketStatusList() {
		return ticketstatusDAO.getTicketStatusList();
	}
}
