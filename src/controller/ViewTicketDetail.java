package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.bean.Disc;
import model.bean.DiscSeries;
import model.bean.Price;
import model.bean.RentalDisc;
import model.bean.Ticket;
import model.bean.TicketStatus;
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;
import model.bo.RentalDiscBO;
import model.bo.TicketBO;
import model.bo.TicketStatusBO;

/**
 * Servlet implementation class ViewTicketDetail
 */
@WebServlet(description = "Xem chi tiết phiếu thuê", urlPatterns = { "/ViewTicketDetail" })
public class ViewTicketDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewTicketDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		TicketBO ticketBO = new TicketBO();
		Ticket ticket = ticketBO.getTicket(ticketId);
		
		TicketStatusBO ticketStatusBO = new TicketStatusBO();
		ArrayList<TicketStatus> listTicketStatus = ticketStatusBO.getTicketStatusList();
		
		RentalDiscBO rentalDiscBO = new RentalDiscBO();
		ArrayList<RentalDisc> listDiscOfTicket = rentalDiscBO.getListDiscOfTicket(ticketId);
		ArrayList<DiscSeries> listDiscSeriesOfTicket = new ArrayList<DiscSeries>();
		DiscBO discBO = new DiscBO();
		DiscSeriesBO discSeriesBO = new DiscSeriesBO();
		ArrayList<Disc> listDisc = new ArrayList<Disc>();
		for(RentalDisc rentalDisc:listDiscOfTicket){
			Disc disc = discBO.getDisc(rentalDisc.getDiscId());
			DiscSeries discSeries = discSeriesBO.getDiscSeries(disc.getDiscSeriesId());
			listDiscSeriesOfTicket.add(discSeries);
			listDisc.add(disc);
		}
		request.setAttribute("ticket", ticket);
		request.setAttribute("listTicketStatus", listTicketStatus);
		request.setAttribute("listDiscOfTicket", listDiscOfTicket);
		request.setAttribute("listDiscSeriesOfTicket", listDiscSeriesOfTicket);
		request.setAttribute("listDisc", listDisc);
		request.getRequestDispatcher("/WEB-INF/ViewTicketDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
