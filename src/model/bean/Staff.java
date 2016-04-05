/**
 * 
 */
package model.bean;

import java.sql.Timestamp;

/**
 * @author NguyenBaAnh
 *
 */
public class Staff {
	private String staffId, password, staffName, staffPhone, staffAddress;
	private Timestamp staffDOB;
	private boolean manager;

	public Staff() {
		manager = false;
	}

	/**
	 * @return the staffId
	 */
	public String getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the staffPhone
	 */
	public String getStaffPhone() {
		return staffPhone;
	}

	/**
	 * @param staffPhone the staffPhone to set
	 */
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	/**
	 * @return the staffAddress
	 */
	public String getStaffAddress() {
		return staffAddress;
	}

	/**
	 * @param staffAddress the staffAddress to set
	 */
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	/**
	 * @return the staffDOB
	 */
	public Timestamp getStaffDOB() {
		return staffDOB;
	}

	/**
	 * @param staffDOB the staffDOB to set
	 */
	public void setStaffDOB(Timestamp staffDOB) {
		this.staffDOB = staffDOB;
	}

	/**
	 * @return the manager
	 */
	public boolean isManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	

}
