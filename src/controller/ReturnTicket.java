package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Disc;
import model.bean.DiscSeries;
import model.bean.RentalDisc;
import model.bean.Staff;
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

		try {
			HttpSession session = request.getSession();
			Staff staff = (Staff) session.getAttribute("staff");
			String staffName = staff.getStaffName();
			int ticketId = Integer.parseInt(request.getParameter("ticketId"));
			if (request.getParameterValues("discId") != null) {
				TicketBO ticketBO = new TicketBO();
				Ticket ticket = ticketBO.getTicket(ticketId);
				ticket.setStaffName(staffName);
				
				RentalDiscBO rentalDiscBO = new RentalDiscBO();
				DiscBO discBO = new DiscBO();
				DiscSeriesBO discSeriesBO = new DiscSeriesBO();
				String[] discIds = request.getParameterValues("discId");
				
				for (String discId: discIds) {
					rentalDiscBO.returnDisc(Integer.parseInt(discId));				
					String qualityId = request.getParameter("qualityIdOf" + discId);
					System.out.println(qualityId);
					Disc disc = discBO.getDisc(Integer.parseInt(discId));
					disc.setQualityId(Byte.valueOf(qualityId));
					disc.setAvailable(true);
					discBO.updateDisc(disc);
					
					DiscSeries discSeries = discSeriesBO.getDiscSeries(disc.getDiscSeriesId());
					discSeries.setRemainingDisc(discSeries.getRemainingDisc()+1);
					discSeriesBO.updateDiscSeries(discSeries);
				}

				ArrayList<RentalDisc> listDiscOfTicket = rentalDiscBO.getListDiscOfTicket(ticketId);
				int numDiscOfTicket = listDiscOfTicket.size();
				int numDiscReturned = 0;
				for(int i=0; i<numDiscOfTicket; i++){
					if(listDiscOfTicket.get(i).isReturned()){
						numDiscReturned += 1;
					}
				}
				if(numDiscOfTicket==numDiscReturned){
					ticket.setStatusId((byte) 2);
				}
				
				if(ticketBO.updateTicket(ticket)==true){
					System.out.println("Cập nhật Phiếu thành công");
				} else {
					System.out.println("Có lỗi xãy ra");
				}
			} 
			request.getRequestDispatcher("/ViewTicketDetail?ticketId="+ticketId).forward(request, response);
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
