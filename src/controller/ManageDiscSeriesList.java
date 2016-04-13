package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.DiscSeries;
import model.bo.CategoryBO;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class ManageDiscSeriesList
 */
@WebServlet("/ManageDiscSeriesList")
public class ManageDiscSeriesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscSeriesBO discSeriesBO;
	CategoryBO categoryBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageDiscSeriesList() {
		super();
		discSeriesBO = new DiscSeriesBO();
		categoryBO = new CategoryBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> ManageDiscSeriesList >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// Lấy params từ dưới giao diện lên
		String searchQuery = request.getParameter("SearchQuery");
		if (searchQuery == null || "".equals(searchQuery.trim())) {
			searchQuery = "";
		}
		searchQuery = searchQuery.trim();
		int catId = 0;
		int page = 1;
		try {
			catId = Integer.parseInt(request.getParameter("CategoryId"));
		} catch (NumberFormatException e) {
		}
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		int maxPage = discSeriesBO.getMaxPage(catId);
		System.out.println("ManageDiscSeriesList: SearchQuery = '" + searchQuery + "'; categoryId = " + catId
				+ "; page = " + page);
		// Lấy danh sách toàn bộ thể loại
		ArrayList<Category> allCategories = categoryBO.getListCategories();
		Category currentCategory = categoryBO.getCategory(catId);
		request.setAttribute("AllCategories", allCategories);
		// Lấy danh sách bộ đĩa dựa vào các thông số
		ArrayList<DiscSeries> listDiscSeries = discSeriesBO.getDiscSeriesList(searchQuery, catId, page);
		// Trả lại các thông số mà người dùng đã nhập
		request.setAttribute("ListDiscSeries", listDiscSeries);
		request.setAttribute("CurrentCategory", currentCategory);
		request.setAttribute("CurrentSearchQuery", searchQuery);
		request.setAttribute("CurrentPage", (listDiscSeries == null || listDiscSeries.isEmpty()) ? 1 : page);
		request.setAttribute("MaxPage", maxPage);
		request.getRequestDispatcher("/WEB-INF/ManageDiscSeriesList.jsp").forward(request, response);
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
