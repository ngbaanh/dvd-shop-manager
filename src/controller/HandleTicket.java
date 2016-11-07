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
 * Servlet implementation class HandleTicket
 */
@WebServlet("/HandleTicket")
public class HandleTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try{
			HttpSession session = request.getSession();
			Staff staff = (Staff) session.getAttribute("staff");
			String staffName = staff.getStaffName();
			
			int ticketId = Integer.parseInt(request.getParameter("ticketId"));
			String customerName = new String(request.getParameter("customerName").getBytes("ISO-8859-1"), "UTF-8") ;
			String customerPhone = new String(request.getParameter("customerPhone").getBytes("ISO-8859-1"), "UTF-8") ;
			String customerAddress = new String(request.getParameter("customerAddress").getBytes("ISO-8859-1"), "UTF-8") ;
			String deposit = new String(request.getParameter("deposit").getBytes("ISO-8859-1"), "UTF-8") ;
			int ticketPrice = Integer.parseInt(request.getParameter("ticketPrice"));
			
			TicketBO ticketBO = new TicketBO();
			Ticket ticket = ticketBO.getTicket(ticketId);
			
			RentalDiscBO rentalDiscBO = new RentalDiscBO();
			ArrayList<RentalDisc> listDiscOfTicket = rentalDiscBO.getListDiscOfTicket(ticketId);
			int numRentalDisc = listDiscOfTicket.size();
			
			if(numRentalDisc>0){
				if(request.getParameter("CapPhieu")!=null){
					ticket.setStatusId((byte)1);
					ticket.setCustomerName(customerName);
					ticket.setCustomerPhone(customerPhone);
					ticket.setCustomerAddress(customerAddress);
					ticket.setStaffName(staffName);
					ticket.setDeposit(deposit);
					ticket.setTicketPrice(ticketPrice);
					
					if(ticketBO.updateTicket(ticket)==true){
						System.out.println("Cấp phiếu thành công");
					} else {
						System.out.println("Có lỗi xãy ra");
					}
					for(int i=0;i<numRentalDisc;i++){
						RentalDisc rentalDisc = listDiscOfTicket.get(i);
						int rentingWeeks = Integer.parseInt(request.getParameter("rentingWeek["+i+"]"));
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
				} else if (request.getParameter("GiaHan")!=null) {
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
					request.setAttribute("listDiscOfTicket", listDiscOfTicket);
					request.setAttribute("listDiscSeriesOfTicket", listDiscSeriesOfTicket);
					request.setAttribute("listDisc", listDisc);
					request.getRequestDispatcher("/WEB-INF/RenewTicket.jsp").forward(request, response);
				
				} else if (request.getParameter("TraDia")!=null) {
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
					request.setAttribute("listDiscOfTicket", listDiscOfTicket);
					request.setAttribute("listDiscSeriesOfTicket", listDiscSeriesOfTicket);
					request.setAttribute("listDisc", listDisc);
					request.getRequestDispatcher("/WEB-INF/ReturnTicket.jsp").forward(request, response);
					
				} else if (request.getParameter("HuyPhieu")!=null){
					ticketBO.destroyTicket(ticketId);
				}
			
			}
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
