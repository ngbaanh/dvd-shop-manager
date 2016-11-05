package controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bo.TicketBO;

/**
 * Servlet implementation class GetSaleByMonthJSON
 */
@WebServlet("/GetSaleByMonthJSON")
public class GetSaleByMonthJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSaleByMonthJSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int targetYear = Integer.parseInt(request.getParameter("year"));
		
		int firstMonth = 1;
		int lastMonth = 12;
		if (targetYear == currentYear) {
			lastMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		
		TicketBO ticketBO = new TicketBO();
		String strListSaleByMonth = "[";
		for (int month = firstMonth; month < lastMonth; month++) {
			int saleMonth = ticketBO.getSaleByMonth(targetYear, month);
			strListSaleByMonth += "['" + month + "', " + saleMonth + "],";
		}
		strListSaleByMonth += "['" + lastMonth + "', " + ticketBO.getSaleByMonth(targetYear, lastMonth) + "]";
		strListSaleByMonth += "]";
		
		strListSaleByMonth = strListSaleByMonth.replace("\'", "\"");
		System.out.println(strListSaleByMonth);
		response.getWriter().print(strListSaleByMonth);
	}

}
