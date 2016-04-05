/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.Staff;
import util.IStaff;

/**
 * @author NguyenBaAnh
 *
 */
public class StaffBO implements IStaff {
	// StaffDAO staffDAO;
	/**
	 * 
	 */
	public StaffBO() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public boolean validateSaff(Staff staff) {
		// TODO Auto-generated method stub
		// thử validate với 1 tải giả, staff = [anhchutien,123456]
		//if (staff.getStaffId().equals("anhchutiem") && staff.getPassword().equals("123456")) {
		if (staff.getStaffId().equals("anhnhanvien") && staff.getPassword().equals("123456")) {
			return true;
		}
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
