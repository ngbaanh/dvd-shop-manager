package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Staff;
import model.bo.StaffBO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StaffBO staffBO;
	HttpSession session;
	
    public Login() {
        super();
        staffBO = new StaffBO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Xin lỗi, bạn đã đăng nhập với một giao thức không chính xác!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String loginAct = request.getParameter("loginAct");
		if ("yes".equals(loginAct)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Staff staff = new Staff();
			staff.setStaffId(username);
			staff.setPassword(password);
			if (staffBO.validateSaff(staff)) {
				//staff.setStaffName("Anh Chủ Tiệm");
				//staff.setManager(true);
				staff.setStaffName("Anh Nhân Viên");
				staff.setManager(false);
				session = request.getSession();
				session.setAttribute("staff", staff);
				response.sendRedirect("index.jsp");
			} else {
				response.getWriter().append("Đăng nhập thất bại <hr>");
				request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
		}
		

	}

}
