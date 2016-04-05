/**
 * 
 */
package model.bo;

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

}
