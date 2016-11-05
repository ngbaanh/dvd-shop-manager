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
import model.bean.Disc;
import model.bean.DiscSeries;
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class GetDiscSeriesDetailAjax
 */
@WebServlet("/GetDiscSeriesDetailJSON")
public class GetDiscSeriesDetailJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDiscSeriesDetailJSON() {
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
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		ArrayList<PendingDisc> listPendingDisc = null;
		
		if (session.getAttribute("listPendingDisc") != null) {
			listPendingDisc = (ArrayList<PendingDisc>) session.getAttribute("listPendingDisc");
		} else {
			listPendingDisc = new ArrayList<PendingDisc>();
		}
		
		if (request.getParameter("discSeriesId") != null) {
			int discSeriesId = Integer.parseInt(request.getParameter("discSeriesId"));
			DiscBO discBO = new DiscBO();
			ArrayList<Disc> listDiscs = discBO.getListDisc(discSeriesId);
			
			DiscSeriesBO discSeriesBO = new DiscSeriesBO();
			DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
			String discSeriesName = discSeries.getDiscSeriesName();
			int remainingDisc = discSeries.getRemainingDisc();
			
			String strListDiscs = "{ 'discSeriesName': '" + discSeriesName + "'";
			strListDiscs += ", 'remainingDisc': " + remainingDisc;
			strListDiscs += ", 'listDiscs': [";
			for (int index = 0; index < listDiscs.size(); index++) {
				Disc disc = listDiscs.get(index);
				if (index == 0) {
					strListDiscs += "{";
				} else {
					strListDiscs += ", {";
				}
				strListDiscs += "'discId': " + disc.getDiscId();
				strListDiscs += ",'qualityId': " + disc.getQualityId();
				strListDiscs += ",'isAvailable': " + disc.isAvailable();
				strListDiscs += ",'price': " + disc.getPrice();
				boolean isPicked = false;
				for (int indexPendingDisc = 0; indexPendingDisc < listPendingDisc.size(); indexPendingDisc++) {
					if (listPendingDisc.get(indexPendingDisc).getDiscId() == disc.getDiscId()) {
						isPicked = true;
						break;
					}
				}
				strListDiscs += ",'isPicked': " + isPicked;
				strListDiscs += "}";
			}
			strListDiscs += "] }";
			strListDiscs = strListDiscs.replace("\'", "\"");
			response.getWriter().print(strListDiscs);
		} else {
			response.getWriter().print("");
		}
	}

}
