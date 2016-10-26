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
import model.bean.Disc;
import model.bo.DiscBO;
import business.session.PendingDisc;

/**
 * Servlet implementation class GetPendingDiscAjax
 */
@WebServlet("/GetPendingDiscAjax")
public class GetPendingDiscAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPendingDiscAjax() {
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
		
		DiscBO discBO = new DiscBO();
		String strListPendingDisc = "{ 'listPendingDisc': [";
		for (int index = 0; index < listPendingDisc.size(); index++) {
			PendingDisc pendingDisc = listPendingDisc.get(index);
			if (index == 0) {
				strListPendingDisc += "{";
			} else {
				strListPendingDisc += ", {";
			}
			Disc disc = discBO.getDisc(pendingDisc.getDiscId());
			if (disc != null) {
				strListPendingDisc += "'deleted': false";
				strListPendingDisc += ",'discId': " + pendingDisc.getDiscId();
				strListPendingDisc += ",'discSeriesName': '" + pendingDisc.getDiscSeriesName() + "'";
				strListPendingDisc += ",'price': " + disc.getPrice();
				strListPendingDisc += ",'rentingWeeks': " + pendingDisc.getRentingWeeks();
			} else {
				strListPendingDisc += "'deleted': true";
				strListPendingDisc += ",'discId': " + pendingDisc.getDiscId();
			}
			strListPendingDisc += "}";
		}
		strListPendingDisc += "], 'MAX_RENTING_WEEKS': " + Const.MAX_RENTING_WEEKS + "}";
		
		strListPendingDisc = strListPendingDisc.replace("\'", "\"");
		response.getWriter().print(strListPendingDisc);
	}

}
