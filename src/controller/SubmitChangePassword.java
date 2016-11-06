package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Staff;
import model.bo.StaffBO;

/**
 * Servlet implementation class SubmitChangePassword
 */
@WebServlet("/SubmitChangePassword")
public class SubmitChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		StaffBO staffBO = new StaffBO();
		ArrayList<String> errors = new ArrayList<String>();
		// get parameters
		String password = request.getParameter("password");
		String new_password = request.getParameter("new_password");
		String confirmed_password = request.getParameter("confirmed_password");
		
		/*
		 *  check validation
		 */
		// check blank
		if("".equals(password)){
			errors.add("Mật khẩu cũ không được để trống!");
		}
		if("".equals(new_password)){
			errors.add("Mật khẩu mới không được để trống!");
		}
		if("".equals(confirmed_password)){
			errors.add("Xác nhận mật khẩu mới không được để trống!");
		}
		// check max length
		if(password.length() > 30){
			errors.add("Mật khẩu cũ không được quá 30 kí tự!");
		}
		if(new_password.length() > 30){
			errors.add("Mật khẩu mới không được quá 30 kí tự!");
		}
		if(confirmed_password.length() > 30){
			errors.add("Xác nhận mật khẩu mới không được quá 30 kí tự!");
		}
		//check matching password
		if(!new_password.equals(confirmed_password)){
			errors.add("Xác nhận mật khẩu mới không trùng với mật khẩu mới!");
		}
		
		if (errors.size() == 0){
			HttpSession session = request.getSession();
			Staff staff = (Staff)session.getAttribute("staff");
			
			if(staff.getPassword().equals(password)){ // check matching old password
				staff.setPassword(new_password); // set new password for staff
				if (staffBO.changePassword(staff)) {
					session.setAttribute("staff", staff);
					request.setAttribute("success", "Đổi mật khẩu thành công!");
				} else {
					errors.add("Lỗi trong quá trình đổi mật khẩu");
				}
			} else {
				errors.add("Mật khẩu cũ không đúng");
			}
		}
		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
		}
		request.getRequestDispatcher("/WEB-INF/ChangePassword.jsp").forward(request, response);
		
	}

}
