package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Disc;
import model.bean.Staff;
import model.bo.DiscBO;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class UpdateDisc
 */
@WebServlet("/UpdateDisc")
public class UpdateDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscBO discBO;
	DiscSeriesBO discSeriesBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDisc() {
		super();
		discBO = new DiscBO();
		discSeriesBO = new DiscSeriesBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> UpdateDisc >>>>>>>>>");
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
			String functionName = "Cập nhật đĩa";
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
		int discId = 0;
		Disc disc = new Disc();
		try {
			discId = Integer.parseInt(request.getParameter("DiscId"));
		} catch (NumberFormatException e) {
		}
		disc = discBO.getDisc(discId);
		if (disc == null) {
			String message = "Lỗi;Không thể lấy thông tin đĩa;#; ";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/Message").forward(request, response);
		}
		if (request.getParameter("doUpdate") != null) {
			String place = request.getParameter("Place");
			byte qualityId = Byte.parseByte(request.getParameter("QualityId"));
			disc.setQualityId(qualityId);
			disc.setPlace(place.trim());
			if (!discBO.isFreeToChange(discId)) {
				String message = "Lỗi khi cập nhật;Đĩa này hiện không thể thay đổi thông tin do có người đang mượn;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response);
			} else if (discBO.updateDisc(disc)) {
				String message = "Thông báo;Cập nhật thông tin đĩa thành công;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response);
			} else {
				String message = "Lỗi;Cập nhật thông tin đĩa thất bại;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response);
			}
		}
		String discSeriesName = discSeriesBO.getDiscSeries(disc.getDiscSeriesId()).getDiscSeriesName();
		request.setAttribute("Disc", disc);
		request.setAttribute("DiscSeriesName", discSeriesName);
		request.getRequestDispatcher("/WEB-INF/UpdateDisc.jsp").include(request, response);
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
