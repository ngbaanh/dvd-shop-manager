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
	public RentalDisc() {
		super();
	}
	public RentalDisc(int ticketId, int discId, byte rentingWeeks, Timestamp finalTime) {
		super();
		this.ticketId = ticketId;
		this.discId = discId;
		this.rentingWeeks = rentingWeeks;
		this.finalTime = finalTime;
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
