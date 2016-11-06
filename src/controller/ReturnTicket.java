package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Disc;
import model.bo.DiscBO;
import model.bo.RentalDiscBO;

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

		if (request.getParameterValues("discId") != null) {
			RentalDiscBO rentalDiscBO = new RentalDiscBO();
			DiscBO discBO = new DiscBO();
			String[] discIds = request.getParameterValues("discId");
			for (String discId: discIds) {
				rentalDiscBO.returnDisc(Integer.parseInt(discId));
				
				String qualityId = request.getParameter("qualityIdOf" + discId);
				System.out.println(qualityId);
				Disc disc = discBO.getDisc(Integer.parseInt(discId));
				disc.setQualityId(Byte.valueOf(qualityId));
				discBO.updateDisc(disc);
			}
		}
		
		int ticketId = Integer.parseInt(request.getParameter("ticketId"));
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
