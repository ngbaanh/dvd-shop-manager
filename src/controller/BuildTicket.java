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
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;
import model.bo.RentalDiscBO;
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
		request.setCharacterEncoding("UTF-8");
		
		@SuppressWarnings("unchecked")
		ArrayList<PendingDisc> list = (ArrayList<PendingDisc>) request.getSession().getAttribute("listPendingDisc");
		if (list == null || list.isEmpty()) {
			response.sendRedirect("ViewDiscSeriesList");
		} else {
			if (request.getParameter("do") != null) {				
				String customerName = request.getParameter("CustomerName");
				String customerPhone = request.getParameter("CustomerPhone");
				String customerAddress = request.getParameter("CustomerAddress");
				Timestamp startTime = new Timestamp(System.currentTimeMillis());
				ArrayList<RentalDisc> rentalDiscList = new ArrayList<RentalDisc>();
				@SuppressWarnings("unchecked") ArrayList<PendingDisc> listPendingDiscs
				= (ArrayList<PendingDisc>) request.getSession().getAttribute("listPendingDisc");
				// Adapt from Quang's product to Anh's product
				int ticketPrice = 0;
				DiscBO discBO = new DiscBO();
				for (PendingDisc pd : listPendingDiscs) {
					RentalDisc rd = new RentalDisc();
					rd.setDiscSeriesId(discBO.getDisc(pd.getDiscId()).getDiscSeriesId());
					rd.setDiscId(pd.getDiscId());
					rd.setTicketId(-1); // waiting to add later after the ticket built
					Timestamp finalTime = new Timestamp(startTime.getTime() + pd.getRentingWeeks() * TimeUnit.DAYS.toMillis(7)); // hiện tại + số tuần
					rd.setFinalTime(finalTime);
					rd.setRentingWeeks((byte)pd.getRentingWeeks());
					rentalDiscList.add(rd);		
					ticketPrice += pd.getRentingWeeks() * discBO.getDisc(pd.getDiscId()).getPrice();
				}
				
				Ticket ticket = new Ticket();
				ticket.setCustomerName(customerName);
				ticket.setCustomerPhone(customerPhone);
				ticket.setCustomerAddress(customerAddress);
				ticket.setStaffName("none");
				ticket.setStatusId((byte)0); //0-đã đặt, 1-đang thuê, 2- đã trả hết, 3-quá hạn
				ticket.setStatusName("Đã đặt"); // cái này làm cho vui
				ticket.setStartTime(startTime);
				ticket.setTicketPrice(ticketPrice);
				ticket.setDeposit("Chưa đặt, chờ khách đến cửa hàng nhập sau");
				ticket.setListDisc(rentalDiscList);
				
				TicketBO ticketBO = new TicketBO();
				
				RentalDiscBO rdBO = new RentalDiscBO();
				ArrayList<RentalDisc> conflictDiscList = rdBO.getConflictDiscList(rentalDiscList);
				if (!conflictDiscList.isEmpty()) {
					request.getRequestDispatcher("/WEB-INF/_bootstrap.jsp").include(request, response);
					response.getWriter().append(this.getListAsHTML(conflictDiscList));
					return;// stop at this.
				}
				//ELSE
				int ticketId = ticketBO.createTicket(ticket);
				if (ticketId > 0) {
					request.getSession().removeAttribute("listPendingDisc");
					request.getRequestDispatcher("/WEB-INF/_bootstrap.jsp").include(request, response);
					response.getWriter().append(this.getSuccessMessage(ticketId, new java.util.Date(ticket.getStartTime().getTime())));
					return; // stop at this.
				} else {
					String message = "Thông báo; Đặt phiếu không thành công! ;#; ";
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
	
	
	private String getListAsHTML(ArrayList<RentalDisc> rdList) {
		DiscSeriesBO dsBO = new DiscSeriesBO();
		StringBuilder html = new StringBuilder();
		html.append("<body><div class=\"container\">");
		html.append("<h2>Thông báo</h2>");
		html.append("<p class=\"text text-danger\"><em>Đặt phiếu không thành công!</em></p><p>Có đĩa trong danh sách đã bị mượn từ trước</p>");
		html.append("<table class=\"table table-bordered\"><caption>Danh sách</caption>");
		html.append("<tr><th>Mã đĩa</th><th>Tên đĩa</th></tr>");
		for (RentalDisc rd : rdList) {
			html.append("<tr><td>" +rd.getDiscId()+ "</td><td>" +dsBO.getDiscSeries(rd.getDiscSeriesId()).getDiscSeriesName()+ "</td></tr>");
		}
		html.append("</table>");
		html.append("<p><a href=\"ViewDiscSeriesList\" class=\"btn btn-info\">Xem lại</a>");
		html.append("&nbsp;<a href=\"InvalidateTicket\" class=\"btn btn-danger\">Hủy phiếu</a></p>");
		html.append("</div></body>");
		return html.toString();
	}
	
	private String getSuccessMessage(int ticketId, java.util.Date d) {
		StringBuilder html = new StringBuilder();
		html.append("<body><div class=\"container\">");
		html.append("<h2>Thông báo</h2>");
		html.append("<p class=\"text text-sucess\"><em>Đặt phiếu thành công!</em></p>");
		html.append("<p>Mã số phiếu: <strong>" + ticketId + "</strong></p>"); 
		html.append("<p>Thời gian đặt phiếu: <strong>" + d.toString() + "</strong></p>");
		html.append("<p class=\"text text-info\"><i>Phiếu này có hiệu lực trong vòng 24 giờ. Vui lòng đến gặp nhân viên của cửa hàng để hoàn thành các thủ tục thuê đĩa <br> Cảm ơn bạn đã sử dụng dịch vụ.</i></p>");
		html.append("<p><a href=\"ViewDiscSeriesList\" class=\"btn btn-warning\">Đóng</a></p>");
		return html.toString();
	}

}
