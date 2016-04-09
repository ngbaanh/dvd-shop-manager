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
 * Servlet implementation class UpdateCategory
 * 
 * @author NguyenBaAnh
 */
@WebServlet("/UpdateCategory")
public class UpdateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryBO categoryBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCategory() {
		super();
		categoryBO = new CategoryBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> UpdateCategory >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int categoryId = Integer.parseInt(request.getParameter("CategoryId"));
		if (request.getParameter("doUpdate") == null) {
			Category category = categoryBO.getCategory(categoryId);
			request.setAttribute("category", category);
			request.getRequestDispatcher("WEB-INF/UpdateCategory.jsp").forward(request, response);
		} else {
			String categoryName = request.getParameter("CategoryName");
			Category category = new Category();
			category.setCategoryId(categoryId);
			category.setCategoryName(categoryName);
			if (categoryBO.updateCategory(category)) {
				response.sendRedirect("UpdateCategory?CategoryId=" + categoryId);
			} else {
				String message = "Lỗi;Cập nhật thể loại thất bại;UpdateCategory?CategoryId=" + categoryId
						+ ";Quay về trang sửa thể loại";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
