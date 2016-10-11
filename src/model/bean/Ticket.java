/**
 * 
 */
package model.bean;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author NguyenBaAnh
 *
 */
public class Ticket {
	int ticketId, ticketPrice;
	Timestamp startTime;
	byte statusId;
	String statusName, customerName, customerPhone, customerAddress, deposit;
	ArrayList<RentalDisc> listDisc;
	
	public Ticket() {
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
	 * @return the ticketPrice
	 */
	public int getTicketPrice() {
		return ticketPrice;
	}

	/**
	 * @param ticketPrice the ticketPrice to set
	 */
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	/**
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the statusId
	 */
	public byte getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(byte statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerPhone
	 */
	public String getCustomerPhone() {
		return customerPhone;
	}

	/**
	 * @param customerPhone the customerPhone to set
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the deposit
	 */
	public String getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	/**
	 * @return the listDisc
	 */
	public ArrayList<RentalDisc> getListDisc() {
		return listDisc;
	}

	/**
	 * @param listDisc the listDisc to set
	 */
	public void setListDisc(ArrayList<RentalDisc> listDisc) {
		this.listDisc = listDisc;
	}
	
	
}
