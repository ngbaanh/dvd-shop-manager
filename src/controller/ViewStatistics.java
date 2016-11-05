package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.DiscBO;
import model.bo.DiscSeriesBO;
import model.bo.TicketBO;

/**
 * Servlet implementation class ViewStatistics
 */
@WebServlet("/ViewStatistics")
public class ViewStatistics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewStatistics() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		DiscSeriesBO discSeriesBO = new DiscSeriesBO();
		int numDiscSeries = discSeriesBO.numDiscSeries();
		request.setAttribute("numDiscSeries", numDiscSeries);
		
		DiscBO discBO = new DiscBO();
		int numDiscs = discBO.numDiscs();
		request.setAttribute("numDiscs", numDiscs);
		int numDiscAvailable = discBO.numDiscAvailable();
		request.setAttribute("numDiscAvailable", numDiscAvailable);
		int numDiscNonAvailable = discBO.numDiscNonAvailable();
		request.setAttribute("numDiscNonAvailable", numDiscNonAvailable);
		
		//bieu do theo nam
		TicketBO ticketBO = new TicketBO();
		int firstYear = ticketBO.getFirstYear();
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String strListSaleByYear = "[";
		for (int year = firstYear; year < currentYear; year++) {
			int sale = ticketBO.getSaleByYear(year);
			strListSaleByYear += "['" + year + "', " + sale + "],";
		}
		strListSaleByYear += "['" + currentYear + "', " + ticketBO.getSaleByYear(currentYear) + "]";
		strListSaleByYear += "]";
		System.out.println(strListSaleByYear);
		request.setAttribute("strListSaleByYear", strListSaleByYear);
		
		request.getRequestDispatcher("/WEB-INF/ViewStatistics.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
