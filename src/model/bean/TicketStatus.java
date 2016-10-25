package model.bean;

public class TicketStatus {
	private int statusId;
	private String statusName;
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public TicketStatus(int statusId, String statusName) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
	}
	public TicketStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
