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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		discSeriesBO = new DiscSeriesBO();
		int catId = 1;
		int page = 2;
		String searchQuery = null;
		ArrayList<DiscSeries> discSeriesList = discSeriesBO.getDiscSeriesList(searchQuery, catId, page);
		request.setAttribute("discSeriesList", discSeriesList);
		categoryBO = new CategoryBO();
		ArrayList<Category> categoryList = categoryBO.getListCategories();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/WEB-INF/ManageDiscSeriesList.jsp").forward(request, response);
	}
           
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
