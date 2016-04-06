package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.CategoryBO;

/**
 * Servlet implementation class RemoveCategory
 */
@WebServlet("/RemoveCategory")
public class RemoveCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CategoryBO categoryBO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCategory() {
        super();
        categoryBO = new CategoryBO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int CategoryId = Integer.parseInt(request.getParameter("CategoryId"));
		if (categoryBO.removeCategory(CategoryId)) {
			response.sendRedirect("ManageDiscCategory");
		} else {
			String message = "Lỗi;Không thể xóa thể loại này vì đang được sử  dụng cho bộ đĩa trong hệ thống;ManageDiscCategory;Quay về trang quản lí thể loại";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
