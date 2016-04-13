package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Disc;
import model.bean.DiscSeries;
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;
import util.Const;

/**
 * Servlet implementation class AddNewDisc
 */
@WebServlet("/AddNewDisc")
public class AddNewDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscBO discBO;
	DiscSeriesBO discSeriesBO;

	/**
	 * Default constructor.
	 */
	public AddNewDisc() {
		discBO = new DiscBO();
		discSeriesBO = new DiscSeriesBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> AddNewDisc >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		int discSeriesId = 0, discNumber = 1;
		String discSeriesName = request.getParameter("DiscSeriesName");
		String place = request.getParameter("Place");
		if (place != null) {
			place = place.trim();
		}
		try {
			discSeriesId = Integer.parseInt(request.getParameter("DiscSeriesId"));
			discNumber = Integer.parseInt(request.getParameter("DiscNumber"));
		} catch (NumberFormatException e) {
		}
		DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
		if (discSeries == null) {
			String message = "Lỗi;Không thể lấy thông tin bộ đĩa;#; ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/Message").forward(request, response);
		} else if (request.getParameter("doAdd") == null) {
			discSeriesName = discSeries.getDiscSeriesName();
			place = discSeries.getListDisc().get(0).getPlace();
		} else if (discNumber < 1) {
			discSeriesName = discSeries.getDiscSeriesName();
			place = discSeries.getListDisc().get(0).getPlace();
			String message = "Lỗi;" + Const.INPUT_POSITIVE_NUMBER + ";#; ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		} else if (Const.MAX_ITEM < discSeries.getTotalDisc() + discNumber) {
			discSeriesName = discSeries.getDiscSeriesName();
			place = discSeries.getListDisc().get(0).getPlace();
			String message = "Lỗi khi thêm đĩa; Mỗi bộ đĩa chỉ có thể chứa tối đa " + Const.MAX_ITEM
					+ " đĩa.<br>Có thể thêm tối đa <strong>"
					+ ((Const.MAX_ITEM > discSeries.getTotalDisc()) ? Const.MAX_ITEM - discSeries.getTotalDisc() : 0)
					+ "</strong> đĩa nữa;#; ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		} else {
			Disc disc = new Disc();
			disc.setDiscSeriesId(discSeriesId);
			disc.setPlace(place);
			disc.setQualityId((byte) 3);
			boolean added = true;
			int index = 1;
			for (index = 1; index <= discNumber; index++) {
				if (!discBO.addNewDisc(disc)) {
					added = false;
					break;
				}
			}
			if (added) {
				String message = "Thông báo; Thêm " + discNumber + " đĩa thành công;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").forward(request, response);
			} else if (index == 1) {
				String message = "Lỗi khi thêm đĩa; Thêm " + discNumber + " đĩa thất bại;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			} else {
				String message = "Thông báo; Chỉ thêm " + index + "/" + discNumber + " đĩa thành công;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			}
			discSeriesName = discSeries.getDiscSeriesName();
			place = discSeries.getListDisc().get(0).getPlace();
		}
		request.setAttribute("DiscSeriesId", discSeriesId);
		request.setAttribute("DiscSeriesName", discSeriesName);
		request.setAttribute("Place", place);
		request.setAttribute("DiscNumber", discNumber);
		request.getRequestDispatcher("/WEB-INF/AddNewDisc.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
