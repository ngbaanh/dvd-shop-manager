package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
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

		ArrayList<Category> allCategories = new ArrayList<Category>();
		allCategories = categoryBO.getListCategories();
		request.setAttribute("AllCategories", allCategories);
		request.getRequestDispatcher("/WEB-INF/ManageDiscCategory.jsp").forward(request, response);
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
