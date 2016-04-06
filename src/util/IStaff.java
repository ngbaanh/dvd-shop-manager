/**
 * 
 */
package util;

import java.util.ArrayList;

import model.bean.Staff;

/**
 * @author NguyenBaAnh
 *
 */
public interface IStaff {
	public Staff getStaff(String staffId);
	public ArrayList<Staff> getListStaffs();
	public boolean addNewStaff(Staff staff);
	public boolean updateStaff(Staff staff);
	public boolean removeStaff(String staffId);
	public boolean validateStaff(Staff staff);
	public boolean changePassword(String newPass);
}
