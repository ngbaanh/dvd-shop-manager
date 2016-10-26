package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.Staff;
import model.bo.CategoryBO;

/**
 * Servlet implementation class ManageDiscCategory
 * 
 * @author NguyenBaAnh
 */
@WebServlet("/ManageDiscCategory")
public class ManageDiscCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryBO categoryBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageDiscCategory() {
		super();
		categoryBO = new CategoryBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> ManageDiscCategory >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// Kiểm tra quyền hạn.
		HttpSession session = request.getSession();
		Staff loggedInStaff = (Staff) session.getAttribute("staff");
		if (loggedInStaff == null) {
			String functionName = "Quản lí các thể loại đĩa";
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
		ArrayList<Category> allCategories = new ArrayList<Category>();
		allCategories = categoryBO.getListCategories();
		request.setAttribute("AllCategories", allCategories);
		request.getRequestDispatcher("/WEB-INF/ManageDiscCategory.jsp").include(request, response);
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
