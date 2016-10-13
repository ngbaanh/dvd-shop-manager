package model.bean;

import java.sql.Timestamp;

public class Ticket {
	private int ticketId;
	private Timestamp startTime;
	private byte statusId;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	private String staffName;
	private String deposit;
	private float ticketPrice;
	
	public Ticket(int ticketId, Timestamp startTime, byte
			statusId, String customerName, String customerPhone,
			String customerAddress, String staffName, String deposit, float ticketPrice) {
		super();
		this.ticketId = ticketId;
		this.startTime = startTime;
		this.statusId = statusId;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.customerAddress = customerAddress;
		this.staffName = staffName;
		this.deposit = deposit;
		this.ticketPrice = ticketPrice;
	}
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public byte getStatusId() {
		return statusId;
	}

	public void setStatusId(byte statusId) {
		this.statusId = statusId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
}
