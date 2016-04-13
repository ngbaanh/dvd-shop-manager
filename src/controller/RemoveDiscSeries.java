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
 * Servlet implementation class RemoveDiscSeries
 */
@WebServlet("/RemoveDiscSeries")
public class RemoveDiscSeries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscSeriesBO discSeriesBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveDiscSeries() {
		super();
		discSeriesBO = new DiscSeriesBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> RemoveDiscSeries >>>>>>>>>");
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
			String functionName = "Xóa một bộ đĩa";
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
		int discSeriesId;
		try {
			discSeriesId = Integer.parseInt(request.getParameter("DiscSeriesId"));
		} catch (NumberFormatException e) {
			discSeriesId = 0;
		}
		DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
		if (discSeries == null) {
			String message = "Lỗi khi lấy thông tin bộ đĩa; Không thể tìm thấy bộ đĩa tương ứng mã số <strong>"
					+ discSeriesId + "</strong> trong hệ thống;ManageDiscSeriesList;Quay về trang quản lí đĩa";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		} else if (discSeriesBO.isFreeToDelete(discSeriesId)) {
			if (discSeriesBO.removeDiscSeries(discSeriesId)) {
				String message = "Thao tác thành công; Xóa bộ đĩa <strong>" + discSeries.getDiscSeriesName()
						+ "</strong> đã xóa thành công khỏi hệ thống.;ManageDiscSeriesList;Quay về trang quản lí đĩa";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			} else {
				String message = "Thao tác thất bại; Xóa bộ đĩa <strong>" + discSeries.getDiscSeriesName()
						+ "</strong> thất bại.;ManageDiscSeriesList;Quay về trang quản lí đĩa";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			}
		} else {
			String message = "Lỗi khi kiểm tra khả năng xóa; Bộ đĩa <strong>" + discSeries.getDiscSeriesName()
					+ "</strong> hiện không thể xóa do có người đang mượn đĩa trong bộ.;ManageDiscSeriesList;Quay về trang quản lí đĩa";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		}
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
