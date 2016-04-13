package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bo.CategoryBO;

/**
 * Servlet implementation class AddNewCategory
 * 
 * @author NguyenBaAnh
 */
@WebServlet("/AddNewCategory")
public class AddNewCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryBO categoryBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewCategory() {
		super();
		categoryBO = new CategoryBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> AddNewCategory >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String CategoryName = request.getParameter("CategoryName");
		Category category = new Category();
		category.setCategoryName(CategoryName.trim());
		if (categoryBO.isExist(CategoryName)) { // trùng tên
			String message = "Lỗi;Thể loại <strong>" + CategoryName
					+ "</strong> đã tồn tại trong hệ thống;ManageDiscCategory;Quay về trang quản lí thể loại";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		} else if (categoryBO.addNewCategory(category)) {
			response.sendRedirect("ManageDiscCategory");
		} else {
			String message = "Lỗi;Thêm thể loại thất bại;ManageDiscCategory;Quay về trang quản lí thể loại";
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
