package model.bo;

import java.util.ArrayList;

import model.bean.Disc;
import model.bean.RentalDisc;
import model.dao.RentalDiscDAO;

public class RentalDiscBO {
	private RentalDiscDAO rentalDiscDAO = new RentalDiscDAO();
	
	public RentalDiscBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<RentalDisc> getListDiscOfTicket(int ticketId) {
		return rentalDiscDAO.getListDiscOfTicket(ticketId);
	}
}
