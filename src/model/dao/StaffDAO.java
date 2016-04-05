/**
 * 
 */
package model.dao;

import java.util.ArrayList;

import model.bean.Staff;
import util.IStaff;

/**
 * @author NguyenBaAnh
 *
 */
public class StaffDAO implements IStaff {

	/**
	 * 
	 */
	public StaffDAO() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see util.IStaff#validateSaff(model.bean.Staff)
	 */
	@Override
	public boolean validateSaff(Staff staff) {
		// TODO Auto-generated method stub
		return false;
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
