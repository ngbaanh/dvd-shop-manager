/**
 * 
 */
package model.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Staff;
import util.IStaff;

/**
 * @author NguyenBaAnh
 *
 */
public class StaffDAO extends DatabaseFactory implements IStaff {
	public StaffDAO() {
		super();
	}

	/* (non-Javadoc)
	 * @see util.IStaff#validateSaff(model.bean.Staff)
	 */
	@Override
	public boolean validateSaff(Staff staff) {
		String validQuery = "select StaffId, Password from STAFF where StaffId=? and Password=?";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			preparedStatement.setString(1, staff.getStaffId());
			preparedStatement.setString(2, staff.getPassword());
			return (preparedStatement.execute());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Staff getStaff(String staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Staff> getListStaffs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewStaff(Staff staff) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeStaff(String staffId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(String newPass) {
		// TODO Auto-generated method stub
		return false;
	}

}
