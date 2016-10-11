/**
 * 
 */
package model.bean;

import java.sql.Timestamp;

/**
 * @author NguyenBaAnh
 *
 */
public class RentalDisc extends Disc{
	int ticketId;
	byte rentingWeeks;
	Timestamp finalTime;
	public RentalDisc() {
		super();
	}
	/**
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}
	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * @return the rentingWeeks
	 */
	public byte getRentingWeeks() {
		return rentingWeeks;
	}
	/**
	 * @param rentingWeeks the rentingWeeks to set
	 */
	public void setRentingWeeks(byte rentingWeeks) {
		this.rentingWeeks = rentingWeeks;
	}
	/**
	 * @return the finalTime
	 */
	public Timestamp getFinalTime() {
		return finalTime;
	}
	/**
	 * @param finalTime the finalTime to set
	 */
	public void setFinalTime(Timestamp finalTime) {
		this.finalTime = finalTime;
	}
	
	
}
