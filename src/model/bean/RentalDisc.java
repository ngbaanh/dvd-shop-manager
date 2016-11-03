package model.bean;

import java.sql.Timestamp;

/**
 * @author thanhsang
 *
 */
public class RentalDisc extends Disc {
	int ticketId;
	byte rentingWeeks;
	Timestamp finalTime;
	boolean returned;
	
	public RentalDisc() {
		super();
	}
	public RentalDisc(int ticketId, int discId, byte rentingWeeks, Timestamp finalTime, boolean returned) {
		super();
		this.ticketId = ticketId;
		this.discId = discId;
		this.rentingWeeks = rentingWeeks;
		this.finalTime = finalTime;
		this.returned = returned;
	}
	
	
	public boolean isReturned() {
		return returned;
	}
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
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
}
