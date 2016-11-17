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
		if (staff == null) {
			return false; // null
		} else {
			if ("".equals(staff.getStaffId().trim()) 
					|| "".equals(staff.getPassword().trim())
					|| staff.getStaffId().length() > Const.MAXLENGTH_STAFFID
					|| staff.getPassword().length() > Const.MAXLENGTH_PASSWORD) {
				return false; // vượt quá maxlength
			}
			// nếu vượt qua hết bên trên...
			return staffDAO.validateStaff(staff); // ...chờ :StaffDAO return
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
		return staffDAO.getListStaffs();
	}

	@Override
	public boolean addNewStaff(Staff staff) {
		if(!isValidateStaff(staff)) return false;
		return staffDAO.addNewStaff(staff);
	}
	private boolean isPhonenumber(String phoneNumber){
		if(phoneNumber == null) return false;
		if(phoneNumber.length()<10 || phoneNumber.length()>11) return false;
		for(int i=0; i<phoneNumber.length(); i++) if(!Character.isDigit(phoneNumber.charAt(i))) return false;
		return true;
	}
	private boolean isValidateStaff(Staff staff) {
		if(staff == null) return false;
		if(staff.getStaffId() == null || "".equals(staff.getStaffId().trim()) || staff.getStaffId().length()>Const.MAXLENGTH_STAFFID) return false;
		if(staff.getStaffName() == null || "".equals(staff.getStaffName().trim()) || staff.getStaffName().length()>Const.MAXLENGTH_NAME) return false;
		if(staff.getPassword() == null || "".equals(staff.getPassword().trim()) || staff.getPassword().length()>Const.MAXLENGTH_PASSWORD) return false;
		if(staff.getStaffDOB() == null) return false;
		if(!isPhonenumber(staff.getStaffPhone())) return false;
		if(staff.getStaffAddress() == null || "".equals(staff.getStaffAddress().trim()) || staff.getStaffAddress().length()>Const.MAXLENGTH_ADDRESS) return false;
		return true;
	}

	@Override
	public boolean updateStaff(Staff staff) {
		if(staff == null) return false;
		if(staff.getStaffId() == null || "".equals(staff.getStaffId().trim()) || staff.getStaffId().length()>Const.MAXLENGTH_STAFFID) return false;
		if(staff.getStaffName() == null || "".equals(staff.getStaffName().trim()) || staff.getStaffName().length()>Const.MAXLENGTH_NAME) return false;
		if(staff.getDateOB() == null) return false;
		if(!isPhonenumber(staff.getStaffPhone())) return false;
		if(staff.getStaffAddress() == null || "".equals(staff.getStaffAddress().trim()) || staff.getStaffAddress().length()>Const.MAXLENGTH_ADDRESS) return false;
		return staffDAO.updateStaff(staff);
	}

	@Override
	public boolean removeStaff(String staffId) {
		if("".equals(staffId) || staffId == null) return false;
		return staffDAO.removeStaff(staffId);
	}

	@Override
	public boolean changePassword(String newPass) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean changePassword(Staff staff) {
		if (this.validateStaff(staff)) {
			return staffDAO.changePassword(staff);
		} else {
			return false;
		}
	}

}
