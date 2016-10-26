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
		// FIXME - console
		System.out.println("\n>>>>>>>>> Login >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		//response.getWriter().append("Xin lỗi, bạn đã đăng nhập với một giao thức không chính xác!");
		request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if (request.getParameter("doLogin") != null) {
			// Get params
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// Encapsulate
			Staff staff = new Staff();
			staff.setStaffId(username);
			staff.setPassword(password);

			// Send to StaffBO and wait return
			if (staffBO.validateStaff(staff)) { // validated successful
				session = request.getSession();
				staff = staffBO.getStaff(username);
				session.setAttribute("staff", staff);
				String feedBack = (String) request.getParameter("FeedBack");
				session.setAttribute("FeedBack", feedBack);
				// Chọn nơi trả về
				
				response.sendRedirect("index.jsp"); // redirect to landing page.
			} else { // validated fail
				//session.invalidate();
				String message = "Lỗi;Đăng nhập thất bại;index.jsp;Quay về Trang Chủ"; // Tiêu-đề;Nội-dung;URL;Tên-URL
				request.setAttribute("message", message);
				request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response);
				request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
			}
		} else { // request a form view
			request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
		}

	}

}
