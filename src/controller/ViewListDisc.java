package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.DiscSeries;
import model.bean.Staff;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class ViewListDisc
 */
@WebServlet("/ViewListDisc")
public class ViewListDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscSeriesBO discSeriesBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewListDisc() {
		super();
		discSeriesBO = new DiscSeriesBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> ViewListDisc >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// Kiểm tra quyền hạn.
		HttpSession session = request.getSession();
		Staff loggedInStaff = (Staff) session.getAttribute("staff");
		if (loggedInStaff == null) {
			String functionName = "Xem danh sách đĩa của một bộ đĩa";
			String message = "Chưa đăng nhập hoặc phiên sử dụng đã kết thúc; Chức năng <b>" + functionName
					+ "</b> cần phải đăng nhập trước khi sử dụng, vui lòng đăng nhập để tiếp tục.;#; ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response);
			String queryString = request.getQueryString();
			request.getRequestDispatcher(
					"Login?FeedBack=" + request.getRequestURI() + (queryString == null ? "" : "?" + queryString))
					.include(request, response);
			return;
		} // END
		boolean isValidId = false;
		int discSeriesId = 0;
		try {
			discSeriesId = Integer.parseInt(request.getParameter("DiscSeriesId"));
			isValidId = true;
		} catch (NumberFormatException e) {
			// Bẫy lỗi nhập Id lung tung hoặc không nhập
		}
		if (isValidId && discSeriesBO.getDiscSeries(discSeriesId) != null) {
			DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
			request.setAttribute("DiscSeries", discSeries);
			request.getRequestDispatcher("/WEB-INF/ViewListDisc.jsp").forward(request, response);
		} else {
			String message = "Lỗi;Không thể tìm thấy bộ đĩa tương ứng mã số <strong>" + discSeriesId
					+ "</strong>;ManageDiscSeriesList;Quay về trang quản lí đĩa";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
