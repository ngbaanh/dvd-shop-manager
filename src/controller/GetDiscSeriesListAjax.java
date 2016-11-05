package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DiscSeries;
import model.bo.DiscSeriesBO;
import util.Const;

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
		int start = 0;
		int length = Const.ITEMS_PER_PAGE;
		int orderColumn = 1;
		String orderDirection = "asc";
		
		if (request.getParameter("search[value]") != null) {
			searchQuery = request.getParameter("search[value]");
		}
		if (request.getParameter("categoryId") != null) {
			categoryId = Integer.parseInt(request.getParameter("categoryId"));
			System.out.println(categoryId);
		}
		if (request.getParameter("start") != null) {
			start = Integer.parseInt(request.getParameter("start"));
		}
		if (request.getParameter("length") != null) {
			length = Integer.parseInt(request.getParameter("length"));
		}
		if (request.getParameter("order[0][dir]") != null) {
			orderColumn = Integer.parseInt(request.getParameter("order[0][column]"));
			orderDirection = request.getParameter("order[0][dir]");
		}

		DiscSeriesBO discSeriesBO = new DiscSeriesBO();
		ArrayList<DiscSeries> listDiscSeries = discSeriesBO.getDiscSeriesList(searchQuery, categoryId, -1, length, orderColumn, orderDirection);
		ArrayList<DiscSeries> listDiscSeriesPaged = discSeriesBO.getDiscSeriesList(searchQuery, categoryId, start, length, orderColumn, orderDirection);
		
		String strListDiscSeries = "{ 'recordsTotal': " + listDiscSeries.size();
		strListDiscSeries += ", 'recordsFiltered': " + listDiscSeries.size();
		strListDiscSeries += ", 'data': [";
		for (int index = 0; index < listDiscSeriesPaged.size(); index++) {
			DiscSeries discSeries = listDiscSeriesPaged.get(index);
			if (index == 0) {
				strListDiscSeries += "{";
			} else {
				strListDiscSeries += ", {";
			}
			strListDiscSeries += "'STT': " + (index + start + 1);
			strListDiscSeries += ",'discSeriesId': " + discSeries.getDiscSeriesId();
			strListDiscSeries += ",'discSeriesName': '" + discSeries.getDiscSeriesName() + "'";
			strListDiscSeries += ",'categoryName': '" + discSeries.getCategory().getCategoryName() + "'";
			strListDiscSeries += ",'numberDiscs': {'remainingDisc': " + discSeries.getRemainingDisc();
			strListDiscSeries += ",'totalDisc': " + discSeries.getTotalDisc() + "}";
			strListDiscSeries += "}";
		}
		strListDiscSeries += "] }";
		strListDiscSeries = strListDiscSeries.replace("\'", "\"");
		response.getWriter().print(strListDiscSeries);
	}

}
