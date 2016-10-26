package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Const;
import model.bean.DiscSeries;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class GetDiscSeriesListAjax
 */
@WebServlet("/GetDiscSeriesListAjax")
public class GetDiscSeriesListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDiscSeriesListAjax() {
        super();
        // TODO Auto-generated constructor stub
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
		
		String searchQuery = "";
		int categoryId = 0;
		int destPage = 1;
		
		if (request.getParameter("searchQuery") != null) {
			searchQuery = request.getParameter("searchQuery");
		}
		if (request.getParameter("categoryId") != null) {
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
		}
		if (request.getParameter("destPage") != null) {
			destPage = Integer.parseInt(request.getParameter("destPage"));
		}

		int startPosition = Const.ITEMS_PER_PAGE * (destPage - 1);
		
		DiscSeriesBO discSeriesBO = new DiscSeriesBO();
		ArrayList<DiscSeries> listDiscSeries = discSeriesBO.getDiscSeriesList(searchQuery, categoryId, destPage);
		int maxPage = discSeriesBO.getMaxPage(categoryId, searchQuery);
		
		String strListDiscSeries = "{ 'destPage': " + destPage;
		strListDiscSeries += ", 'maxPage': " + maxPage;
		strListDiscSeries += ", 'startPosition': " + startPosition;
		strListDiscSeries += ", 'listDiscSeries': [";
		for (int index = 0; index < listDiscSeries.size(); index++) {
			DiscSeries discSeries = listDiscSeries.get(index);
			if (index == 0) {
				strListDiscSeries += "{";
			} else {
				strListDiscSeries += ", {";
			}
			strListDiscSeries += "'discSeriesId': " + discSeries.getDiscSeriesId();
			strListDiscSeries += ",'discSeriesName': '" + discSeries.getDiscSeriesName() + "'";
			strListDiscSeries += ",'categoryName': '" + discSeries.getCategory().getCategoryName() + "'";
			strListDiscSeries += ",'remainingDisc': " + discSeries.getRemainingDisc();
			strListDiscSeries += ",'totalDisc': " + discSeries.getTotalDisc();
			strListDiscSeries += "}";
		}
		strListDiscSeries += "] }";
		strListDiscSeries = strListDiscSeries.replace("\'", "\"");
		response.getWriter().print(strListDiscSeries);
	}

}
