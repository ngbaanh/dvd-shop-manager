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
import model.bean.RentalDisc;
import model.bean.Ticket;
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;
import model.bo.RentalDiscBO;
import model.bo.TicketBO;

/**
 * Servlet implementation class ReturnTicket
 */
@WebServlet(description = "Trả đĩa", urlPatterns = { "/ReturnTicket" })
public class ReturnTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
		
//		TicketBO ticketBO = new TicketBO();
//		Ticket ticket = ticketBO.getTicket(ticketId);
//		
//		RentalDiscBO rentalDiscBO = new RentalDiscBO();
//		ArrayList<RentalDisc> listDiscOfTicket = rentalDiscBO.getListDiscOfTicket(ticketId);
//		int numRentalDisc = listDiscOfTicket.size();
//		
//		ArrayList<DiscSeries> listDiscSeriesOfTicket = new ArrayList<DiscSeries>();
//		DiscBO discBO = new DiscBO();
//		DiscSeriesBO discSeriesBO = new DiscSeriesBO();
//		ArrayList<Disc> listDisc = new ArrayList<Disc>();
//		for(int i=0;i<listDiscOfTicket.size();i++){
//			RentalDisc rentalDisc = new RentalDisc();
//			Disc disc = discBO.getDisc(rentalDisc.getDiscId());
//			DiscSeries discSeries = discSeriesBO.getDiscSeries(disc.getDiscSeriesId());
//			listDiscSeriesOfTicket.add(discSeries);
//			listDisc.add(disc);
//		}
		
		request.getRequestDispatcher("/ViewTicketDetail?ticketId="+ticketId).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
