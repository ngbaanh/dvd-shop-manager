package model.bean;

import java.sql.Timestamp;

public class RentalDisc {
	private int ticketId;
	private int discId;
	private byte rentingWeeks;
	private Timestamp finalTime;
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getDiscId() {
		return discId;
	}
	public void setDiscId(int discId) {
		this.discId = discId;
	}
	public byte getRentingWeeks() {
		return rentingWeeks;
	}
	public void setRentingWeeks(byte rentingWeeks) {
		this.rentingWeeks = rentingWeeks;
	}
	public Timestamp getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(Timestamp finalTime) {
		this.finalTime = finalTime;
	}
	public RentalDisc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RentalDisc(int ticketId, int discId, byte rentingWeeks, Timestamp finalTime) {
		super();
		this.ticketId = ticketId;
		this.discId = discId;
		this.rentingWeeks = rentingWeeks;
		this.finalTime = finalTime;
	}
	
}
