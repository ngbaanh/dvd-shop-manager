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
import model.bean.Ticket;
import model.bean.TicketStatus;
import model.bo.TicketBO;
import model.bo.TicketStatusBO;

/**
 * Servlet implementation class ViewTicketList
 */
@WebServlet("/ViewTicketList")
public class ViewTicketList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewTicketList() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		TicketBO ticketBO = new TicketBO();
		ArrayList<Ticket> listTickets = new ArrayList<Ticket>();
		
		String searchQuery = null; 
		if (request.getParameter("SearchQuery") == null) {
			searchQuery = "";
		} else {
			searchQuery = new String(request.getParameter("SearchQuery").getBytes("ISO-8859-1"),"UTF-8");
		}
		byte statusId = -1;
		int page = 1;
		try {
			statusId = Byte.parseByte(request.getParameter("StatusId"));
		} catch (NumberFormatException e) {
		}
		System.out.println(statusId);
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
		}
		int maxPage = ticketBO.getMaxPage(statusId);
		
		TicketStatusBO ticketStatusBO = new TicketStatusBO();
		ArrayList<TicketStatus> listTicketStatus = ticketStatusBO.getTicketStatusList();
		String currentTicketStatus = ticketStatusBO.getStatusName(statusId);
		TicketStatus ticketStatus = new TicketStatus(statusId, currentTicketStatus);
		listTickets = ticketBO.getTicketList(searchQuery, statusId, page);
		
		// Trả lại các thông số mà người dùng đã nhập
		request.setAttribute("listTickets", listTickets);
		request.setAttribute("listTicketStatus", listTicketStatus);
		request.setAttribute("ticketStatusBO", ticketStatusBO); //? FIXME
		request.setAttribute("ticketStatus", ticketStatus);
		request.setAttribute("CurrentSearchQuery", searchQuery);
		request.setAttribute("CurrentPage", (listTickets == null || listTickets.isEmpty()) ? 1 : page);
		request.setAttribute("MaxPage", maxPage);
		request.getRequestDispatcher("WEB-INF/ViewTicketList.jsp").forward(request, response);

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
