package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Ticket;

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
		Ticket ticket = (Ticket) request.getAttribute("ticket");
		System.out.println(ticket.getTicketId());
		System.out.println(ticket.getStatusId());
		if(!request.getParameter("CapPhieu").isEmpty()){
			ticket.setStatusId((byte)1);
			System.out.println(ticket.getStatusId());
		}
//		} else if (!request.getParameter("GiaHan").isEmpty()) {
//			ticket.setStatusId((byte)1);
//		} else if (!request.getParameter("TraDia").isEmpty()) {
//			ticket.setStatusId((byte)2);
//		} else if (!request.getParameter("HuyPhieu").isEmpty()){
//			
//		}
		request.setAttribute("ticket", ticket);
		request.getRequestDispatcher("/WEB-INF/ViewTicketDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
