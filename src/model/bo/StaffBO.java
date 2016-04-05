/**
 * 
 */
package model.bo;

import java.util.ArrayList;

import model.bean.Staff;
import model.dao.StaffDAO;
import util.IStaff;
import util.Const;

/**
 * @author NguyenBaAnh
 *
 */
public class StaffBO implements IStaff {
	StaffDAO staffDAO;

	public StaffBO() {
		staffDAO = new StaffDAO();
	}

	/**
	 * Kiểm tra xem một tài khoản có tồn tại trong hệ thống hay không?
	 * 
	 * @param: staff
	 *             nhân viên (có tối thiểu 2 thông tin Staff.staffId và
	 *             Staff.password)
	 * @return <b>true</b> nếu tài khoản, mật khẩu nhân viên hợp lệ và có trong
	 *         CSDL. <b>false</b> nếu ngược lại.
	 */
	@Override
	public boolean validateStaff(Staff staff) {
		// return true;
		if (staff == null) {
			return false; // null
		} else {
			if ("".equals(staff.getStaffId().trim()) || "".equals(staff.getPassword().trim())) {
				return false; // để trống hoặc nhập toàn dấu cách
			}
			if (staff.getStaffId().length() > Const.MAXLENGTH_STAFFID
					|| staff.getPassword().length() > Const.MAXLENGTH_PASSWORD) {
				return false; // vượt quá maxlength
			} else { // nếu vượt qua hết bên trên...
				return staffDAO.validateStaff(staff); // ...chờ :StaffDAO return
			}
		}
	}

	/**
	 * Lấy 1 nhân viên dựa vào mã nhân viên
	 * 
	 * @param staffId:
	 *            mã nhân viên
	 * @return null nếu không tồn tại nhân viên, ngược lại lấy đầy đủ thông tin
	 *         của 1 nhân viên gói vào Staff
	 */
	@Override
	public Staff getStaff(String staffId) {
		if (staffId == null || "".equals(staffId.trim())) {
			return null;
		} else {
			return staffDAO.getStaff(staffId);
		}
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
