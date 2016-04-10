package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Staff;
import util.Const;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		session = request.getSession();
		Staff staff = (Staff) session.getAttribute("staff");
		if (staff != null && "admin".equals(staff.getStaffId())) {
			int itemsPerPage = Const.ITEMS_PER_PAGE;
			if (request.getParameter("doSave") == null) {
				String items_per_page = request.getParameter("items_per_page");
				if (items_per_page == null || "".equals(items_per_page)) {
					itemsPerPage = Const.ITEMS_PER_PAGE;
				} else {
					itemsPerPage = Integer.parseInt(items_per_page);
				}
				if (itemsPerPage > 0) {
					Const.ITEMS_PER_PAGE = itemsPerPage;
				}
			} else {

			}
			request.setAttribute("items_per_page", itemsPerPage);
			request.getRequestDispatcher("/WEB-INF/Admin.jsp").include(request, response);
		} else {
			String message = "Lỗi khi vào trang quản lí;Bạn không đủ quyền hạn thực hiện chức năng này, vui lòng đăng nhập tài khoản <strong>admin</strong>;javascript:history.go(-1);Quay lại";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
