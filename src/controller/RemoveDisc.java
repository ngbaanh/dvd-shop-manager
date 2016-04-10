package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Disc;
import model.bo.DiscBO;

/**
 * Servlet implementation class RemoveDisc
 */
@WebServlet("/RemoveDisc")
public class RemoveDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscBO discBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveDisc() {
		super();
		discBO = new DiscBO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		int discId;
		try {
			discId = Integer.parseInt(request.getParameter("DiscId"));
		} catch (NumberFormatException e) {
			discId = 0;
		}
		Disc disc = discBO.getDisc(discId);
		if (disc == null) {
			String message = "Lỗi khi lấy thông tin đĩa; Không thể tìm thấy đĩa tương ứng mã số <strong>" + discId
					+ "</strong> trong hệ thống;javascript:history.go(-1);Quay lại";
			request.setAttribute("message", message);
			request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
		} else if (disc.isAvailable()) {
			if (discBO.removeDisc(discId)) {
				String message = "Thao tác thành công; Đĩa có mã số <strong>" + disc.getDiscId()
						+ "</strong> đã xóa thành công khỏi hệ thống.;ViewListDisc?DiscSeriesId="
						+ disc.getDiscSeriesId() + ";Quay lại";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			} else {
				String message = "Thao tác thất bại; Xóa đĩa có mã số<strong>" + disc.getDiscId()
						+ "</strong> thất bại.;javascript:history.go(-1);Quay lại";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			}
		} else {
			String message = "Lỗi khi kiểm tra khả năng xóa; Đĩa có mã số <strong>" + disc.getDiscId()
					+ "</strong> hiện không thể xóa do có người đang mượn.;javascript:history.go(-1);Quay lại";
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
