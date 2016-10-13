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

import business.session.PendingDisc;
import model.bean.RentalDisc;
import model.bean.Ticket;
import model.bo.TicketBO;
/**
 * Servlet implementation class BuildTicket
 */
@WebServlet("/BuildTicket")
public class BuildTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuildTicket() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		ArrayList<PendingDisc> list = (ArrayList<PendingDisc>) request.getSession().getAttribute("listPendingDisc");
		if (list == null || list.isEmpty()) {
			response.sendRedirect("ViewDiscSeriesList");
		} else {
			if (request.getParameter("do") != null) {
				// TODO - xu li dat phieu 
				response.getWriter().append("OK DANG XU LI.///");
				
				String customerName = request.getParameter("CustomerName");
				String customerPhone = request.getParameter("CustomerPhone");
				String customerAddress = request.getParameter("CustomerAddress");
				Timestamp startTime = new Timestamp(System.currentTimeMillis());
				ArrayList<RentalDisc> rentalDiscList = new ArrayList<>();
				@SuppressWarnings("unchecked") ArrayList<PendingDisc> listPendingDiscs
				= (ArrayList<PendingDisc>) request.getSession().getAttribute("listPendingDisc");
				// Adapt from Quang's product to Anh's product
				int ticketPrice = 0; // FIXME
				for (PendingDisc pd : listPendingDiscs) {
					RentalDisc rd = new RentalDisc();
					rd.setDiscId(pd.getDiscId());
					rd.setTicketId(-1); // waiting to add later after the ticket built
					Timestamp finalTime = new Timestamp(startTime.getTime() + pd.getRentingWeeks() * TimeUnit.DAYS.toMillis(7)); // hiện tại + số tuần
					rd.setFinalTime(finalTime);
					rd.setRentingWeeks((byte)pd.getRentingWeeks());
					rentalDiscList.add(rd);					
				}
				
				Ticket ticket = new Ticket();
				ticket.setCustomerName(customerName);
				ticket.setCustomerPhone(customerPhone);
				ticket.setCustomerAddress(customerAddress);
				ticket.setStatusId((byte)0); //0-đã đặt, 1-đang thuê, 2- đã trả hết, 3-quá hạn
				ticket.setStatusName("Đã đặt"); // cái này làm cho vui
				ticket.setStartTime(startTime);
				ticket.setTicketPrice(ticketPrice);
				ticket.setDeposit("Chưa đặt, chờ khách đến cửa hàng nhập sau");
				ticket.setListDisc(rentalDiscList);
				
				TicketBO ticketBO = new TicketBO();
				if (ticketBO.createTicket(ticket) > 0) {
					String message = "Thông báo; OK ;#; "; // FIXME sua lai thong bao cho khop SRS
					request.setAttribute("message", message);
					request.getRequestDispatcher("WEB-INF/Message.jsp").forward(request, response);
				} else {
					String message = "Thông báo; DAT VE KHONG THANH CONG ;#; "; // FIXME sua lai thong bao cho khop SRS
					request.setAttribute("message", message);
					request.getRequestDispatcher("WEB-INF/Message.jsp").forward(request, response);
				}				
			} else {
				request.getRequestDispatcher("/WEB-INF/BuildTicket.jsp").include(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
