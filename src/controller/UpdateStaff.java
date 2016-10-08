package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Staff;
import model.bo.StaffBO;

/**
 * Servlet implementation class UpdateStaff
 */
@WebServlet("/UpdateStaff")
public class UpdateStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffBO staffBO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStaff() {
        super();
        staffBO = new StaffBO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Kiểm tra quyền hạn.
		HttpSession session = request.getSession();
		Staff loggedInStaff = (Staff) session.getAttribute("staff");
		if (loggedInStaff == null) {
			String functionName = "Quản lí các bộ đĩa";
			String message = "Chưa đăng nhập hoặc phiên sử dụng đã kết thúc; Chức năng <b>" + functionName
					+ "</b> cần phải đăng nhập trước khi sử dụng, vui lòng đăng nhập để tiếp tục.;#; ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response);
			String queryString = request.getQueryString();
			request.getRequestDispatcher(
					"Login?FeedBack=" + request.getRequestURI() + (queryString == null ? "" : "?" + queryString))
					.include(request, response);
			return;
		} // END

		// Lấy params từ dưới giao diện lên
		String staffId, dateOB, staffName, staffPhone, staffAddress;
		Timestamp staffDOB;
		staffId = request.getParameter("staffId");
		staffName = request.getParameter("staffName");
		staffPhone = request.getParameter("staffPhone");
		staffAddress = request.getParameter("staffAddress");
		dateOB = request.getParameter("staffDOB");
		if(staffId != null && staffName!=null && staffPhone!=null && staffAddress!=null && dateOB != null){
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(dateOB);
		    staffDOB = new java.sql.Timestamp(parsedDate.getTime());
		    Staff staff = new Staff();
			staff.setStaffId(staffId);
			staff.setStaffName(staffName);
			staff.setStaffPhone(staffPhone);
			staff.setStaffDOB(staffDOB);
			staff.setStaffAddress(staffAddress);
		    if(staffBO.updateStaff(staff)){
				request.setAttribute("message", "Update staff '" + staffId + "' successfully!");
			}
		    else{
		    	request.setAttribute("error", "Update staff '" + staffId + "'  failed!");
		    }
		}catch(Exception e){
			request.setAttribute("error", "Update staff '" + staffId + "' invalid!");
		}
		}
		// Trả lại các thông số mà người dùng đã nhập
		ArrayList<Staff> staffList = staffBO.getListStaffs();
		request.setAttribute("StaffList", staffList);
		request.getRequestDispatcher("/WEB-INF/ViewStaffList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
