package controller;

import java.io.IOException;
import java.rmi.server.SocketSecurityException;

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
		String error = "";
		// get parameters
		String password = request.getParameter("password");
		String new_password = request.getParameter("new_password");
		String confirmed_password = request.getParameter("confirmed_password");
		
		/*
		 *  check validation
		 */
		// check blank
	
		if("".equals(password)){
			error = null;
			//error = "Password can not blank!";
			request.setAttribute("error", error);
			System.out.println(error);
		}
		if("".equals(new_password)){
			error = "New password can not blank!";
			System.out.println(error);
		}
		if("".equals(confirmed_password)){
			error = "Confirmed password can not blank!";
			System.out.println(error);
		}
		// check max length
		if(password.length()>30){
			error = "Password can not more than 30 characters!";
		}
		if(new_password.length()>30){
			error = "New password can not more than 30 characters!";
		}
		if(confirmed_password.length()>30){
			error = "Confirmed password can not more than 30 characters!";
		}
		//check matching password
		if(!new_password.equals(confirmed_password)){
			error= "Confirmed password must match with new password!";
			
		}
		request.setAttribute("error", error);
		
		if(error==null){
			HttpSession session = request.getSession();
			Staff staff = (Staff)session.getAttribute("staff");
			
			if(staff.getPassword().equals(password)){ // check matching old password
				staff.setPassword(new_password); // set new password for staff
				if (staffBO.changePassword(staff)) {
					session.setAttribute("staff", staff);
				//	System.out.println("Thành chập");
					//response.sendRedirect(request.getContextPath()+"ChangePassword?msg=1");
				} else {
					error = "Error in processing change password";
				}
			} else {
				error = "Wrong password";
			}
		}
		request.getRequestDispatcher("/WEB-INF/ChangePassword.jsp").forward(request, response);
		
	}

}
