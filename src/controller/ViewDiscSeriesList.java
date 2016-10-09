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
 * Servlet implementation class ViewDiscSeriesList
 */
@WebServlet("/ViewDiscSeriesList")
public class ViewDiscSeriesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDiscSeriesList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		DiscSeriesBO discSeriesBO = new DiscSeriesBO();
		
		int destPage = 1;
		
		if (request.getParameter("destPage") != null) {
			destPage = Integer.parseInt(request.getParameter("destPage"));
		}
		
		int cateId = 0;
		
		request.setAttribute("destPage", destPage);
		
		if (request.getParameter("cateId") != null) {
			cateId = Integer.parseInt(request.getParameter("cateId"));
		}
		
		request.setAttribute("cateId", cateId);
		
		ArrayList<DiscSeries> listDiscSeries = discSeriesBO.getDiscSeriesList("", cateId, destPage);
		
		request.setAttribute("listDiscSeries", listDiscSeries);
		
		int maxPage = discSeriesBO.getMaxPage(cateId);
		request.setAttribute("maxPage", maxPage);
		
		CategoryBO categoryBO = new CategoryBO();
		
		ArrayList<Category> listCategories = categoryBO.getListCategories();
		
		request.setAttribute("listCategories", listCategories);
		
		request.getRequestDispatcher("/WEB-INF/ViewDiscSeriesList.jsp").forward(request, response);
	}

}
