package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Ticket;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		TicketBO ticketBO = new TicketBO();
		ArrayList<Ticket> listTickets = ticketBO.getTicketList("", 0, 0);
		request.setAttribute("listTickets",listTickets);
		
		TicketStatusBO ticketStatusBO = new TicketStatusBO();
		request.setAttribute("ticketStatusBO", ticketStatusBO);
		
		request.getRequestDispatcher("WEB-INF/ViewTicketList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
