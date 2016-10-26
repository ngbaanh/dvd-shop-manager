package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.session.PendingDisc;

/**
 * Servlet implementation class ChangeRentingWeeks
 */
@WebServlet("/ChangeRentingWeeks")
public class ChangeRentingWeeks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeRentingWeeks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("listPendingDisc") != null) {
			ArrayList<PendingDisc> listPendingDisc
				= (ArrayList<PendingDisc>) session.getAttribute("listPendingDisc");
			
			int discId = Integer.parseInt(request.getParameter("discId"));
			int rentingWeeks = Integer.parseInt(request.getParameter("rentingWeeks"));
			
			for (int i = 0; i < listPendingDisc.size(); i++) {
				if (listPendingDisc.get(i).getDiscId() == discId) {
					listPendingDisc.get(i).setRentingWeeks(rentingWeeks);
					break;
				}
			}
			
			session.setAttribute("listPendingDisc", listPendingDisc);
		}
		response.sendRedirect("/SE23/GetPendingDiscAjax");
	}

}
