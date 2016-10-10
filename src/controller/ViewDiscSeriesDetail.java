package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Disc;
import model.bean.DiscSeries;
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class ViewDiscSeriesDetail
 */
@WebServlet("/ViewDiscSeriesDetail")
public class ViewDiscSeriesDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDiscSeriesDetail() {
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
		if (request.getParameter("discSeriesId") != null) {
			int discSeriesId = Integer.parseInt(request.getParameter("discSeriesId"));
			DiscBO discBO = new DiscBO();
			ArrayList<Disc> listDiscs = discBO.getListDisc(discSeriesId);
			request.setAttribute("listDiscs", listDiscs);
			
			DiscSeriesBO discSeriesBO = new DiscSeriesBO();
			DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
			String discSeriesName = discSeries.getDiscSeriesName();
			request.setAttribute("discSeriesName", discSeriesName);
			int remainingDisc = discSeries.getRemainingDisc();
			request.setAttribute("remainingDisc", remainingDisc);
			
			request.getRequestDispatcher("/WEB-INF/ViewDiscSeriesDetail.jsp").forward(request, response);
		}
	}

}
