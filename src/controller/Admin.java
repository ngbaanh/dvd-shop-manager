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
			if (request.getParameter("doSave") != null) {
				int itemsPerPage = Integer.parseInt(request.getParameter("items_per_page"));
				int maxItem = Integer.parseInt(request.getParameter("max_item"));
				boolean use_bootstrap_online = Boolean.parseBoolean(request.getParameter("use_bootstrap_online"));
				boolean enable_loading_screen = Boolean.parseBoolean(request.getParameter("enable_loading_screen"));
				if (itemsPerPage > 0 && maxItem > 0) {
					Const.ITEMS_PER_PAGE = itemsPerPage;
					Const.MAX_ITEM = maxItem;
					Const.USE_BOOTSTRAP_ONLINE = use_bootstrap_online;
					Const.ENABLE_LOADING_SCREEN = enable_loading_screen;
				}
			}
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
