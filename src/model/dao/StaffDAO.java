/**
 * 
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see util.IStaff#validateSaff(model.bean.Staff)
	 */
	@Override
	public boolean validateStaff(Staff staff) {
		String validQuery = "select StaffId, Password from STAFF where StaffId=? and Password=?";
		try {
			preparedStatement = connection.prepareStatement(validQuery);
			preparedStatement.setString(1, staff.getStaffId());
			preparedStatement.setString(2, staff.getPassword());
			// FIXME - console
			System.out.println("StaffDAO: " + preparedStatement.toString());
			boolean actionResult = preparedStatement.executeQuery().next();
			preparedStatement.close();
			return actionResult;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Staff getStaff(String staffId) {
		String getQuery = "select * from STAFF where StaffId=?";
		try {
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, staffId);
			// FIXME - console
			System.out.println("StaffDAO: " + preparedStatement.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Staff staff = new Staff();
				staff.setStaffId(resultSet.getString("StaffId"));
				staff.setPassword(resultSet.getString("Password"));
				staff.setStaffName(resultSet.getString("StaffName"));
				staff.setStaffAddress(resultSet.getString("StaffAddress"));
				staff.setStaffDOB(Timestamp.valueOf(resultSet.getString("StaffDOB")));
				staff.setStaffPhone(resultSet.getString("StaffPhone"));
				staff.setManager(resultSet.getBoolean("Manager"));
				preparedStatement.close();
				return staff;
			} else {
				preparedStatement.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<Staff> getListStaffs() {
		// TODO Auto-generated method stub
		// Không nằm trong phạm vi cần triển khai
		return null;
	}

	@Override
	public boolean addNewStaff(Staff staff) {
		// TODO Auto-generated method stub
		// Không nằm trong phạm vi cần triển khai
		return false;
	}

	@Override
	public boolean updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		// Không nằm trong phạm vi cần triển khai
		return false;
	}

	@Override
	public boolean removeStaff(String staffId) {
		// TODO Auto-generated method stub
		// Không nằm trong phạm vi cần triển khai
		return false;
	}

	@Override
	public boolean changePassword(String newPass) {
		// TODO Auto-generated method stub
		// Không nằm trong phạm vi cần triển khai
		return false;
	}

}
