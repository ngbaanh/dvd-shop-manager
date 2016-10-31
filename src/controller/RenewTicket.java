package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.RentalDisc;
import model.bean.Ticket;
import model.bo.RentalDiscBO;
import model.bo.TicketBO;

/**
 * Servlet implementation class RenewTicket
 */
@WebServlet(description = "Gia hạn đĩa thuê", urlPatterns = { "/RenewTicket" })
public class RenewTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RenewTicket() {
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
		int ticketPrice = Integer.parseInt(request.getParameter("ticketPrice"));
		TicketBO ticketBO = new TicketBO();
		Ticket ticket = ticketBO.getTicket(ticketId);
		ticket.setTicketPrice(ticketPrice);
		if(ticketBO.updateTicket(ticket)==true){
			System.out.println("Cập nhật Phiếu thành công");
		} else {
			System.out.println("Có lỗi xãy ra");
		}
		
		RentalDiscBO rentalDiscBO = new RentalDiscBO();
		ArrayList<RentalDisc> listDiscOfTicket = rentalDiscBO.getListDiscOfTicket(ticketId);
		int numRentalDisc = listDiscOfTicket.size();
		for(int i=0;i<numRentalDisc;i++){
			RentalDisc rentalDisc = listDiscOfTicket.get(i);
			int rentingWeeks = Integer.parseInt(request.getParameter("rentingWeek["+i+"]"))+ rentalDisc.getRentingWeeks();
			rentalDisc.setRentingWeeks((byte) rentingWeeks);
			Timestamp finalTime = new Timestamp(ticket.getStartTime().getTime() + rentingWeeks * TimeUnit.DAYS.toMillis(7));
			rentalDisc.setFinalTime(finalTime);
			if(rentalDiscBO.updateRentalDisc(rentalDisc)==true){
				System.out.println("Cập nhật thành công");
			}else {
				System.out.println("Có lỗi đâu đó xãy ra");
			}
		}
		
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
