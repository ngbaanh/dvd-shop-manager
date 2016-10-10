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
 * Servlet implementation class DiscardDisc
 */
@WebServlet("/DiscardDisc")
public class DiscardDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DiscardDisc() {
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
		ArrayList<PendingDisc> listPendingDisc = null;
		
		if (session.getAttribute("listPendingDisc") != null) {
			listPendingDisc = (ArrayList<PendingDisc>) session.getAttribute("listPendingDisc");
		} else {
			listPendingDisc = new ArrayList<PendingDisc>();
		}
		
		int discId = Integer.parseInt(request.getParameter("discId"));
		
		for (int i = 0; i < listPendingDisc.size(); i++) {
			if (discId == listPendingDisc.get(i).getDiscId()) {
				listPendingDisc.remove(i);
				break;
			}
		}
		
		session.setAttribute("listPendingDisc", listPendingDisc);
		
		response.sendRedirect("/SE23/ViewDiscSeriesList");
	}

}
