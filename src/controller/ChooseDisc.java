package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Const;
import business.session.PendingDisc;

/**
 * Servlet implementation class ChooseDisc
 */
@WebServlet("/ChooseDisc")
public class ChooseDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseDisc() {
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
		String discSeriesName = request.getParameter("discSeriesName");
		int discId = Integer.parseInt(request.getParameter("discId"));
		int rentingWeeks = Const.DEFAULT_RENTING_WEEKS;
		PendingDisc pendingDisc = new PendingDisc(discSeriesName, discId, rentingWeeks);
		listPendingDisc.add(pendingDisc);
		
		session.setAttribute("listPendingDisc", listPendingDisc);
		
		response.sendRedirect("/SE23/GetPendingDiscAjax");
	}

}
