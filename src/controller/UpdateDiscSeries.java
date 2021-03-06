package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.bean.DiscSeries;
import model.bean.Staff;
import model.bo.CategoryBO;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class UpdateDiscSeries
 */
@WebServlet("/UpdateDiscSeries")
public class UpdateDiscSeries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscSeriesBO discSeriesBO;
	CategoryBO categoryBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateDiscSeries() {
		super();
		discSeriesBO = new DiscSeriesBO();
		categoryBO = new CategoryBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> UpdateDiscSeries >>>>>>>>>");
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
			String functionName = "Cập nhật thể loại đĩa";
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
		ArrayList<Category> listCategories = categoryBO.getListCategories();
		int discSeriesId = Integer.parseInt(request.getParameter("DiscSeriesId"));

		if (request.getParameter("doUpdate") == null) {
			DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
			request.setAttribute("DiscSeries", discSeries);
			request.setAttribute("AllCategories", listCategories);
			request.getRequestDispatcher("/WEB-INF/UpdateDiscSeries.jsp").forward(request, response);
		} else {
			String discSeriesName = request.getParameter("DiscSeriesName").trim();
			String description = request.getParameter("Description").trim();
			int categoryId = Integer.parseInt(request.getParameter("CategoryId"));
			DiscSeries discSeries = new DiscSeries();
			discSeries.setDiscSeriesId(discSeriesId);
			discSeries.setDiscSeriesName(discSeriesName);
			discSeries.setDescription(description);
			discSeries.setCategory(categoryBO.getCategory(categoryId));
			if (discSeriesBO.hasExistence(discSeries)) { // trùng tên
				String message = "Lỗi khi cập nhật;Bộ đĩa <strong>" + discSeriesName
						+ "</strong> đã tồn tại trong hệ thống;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			} else if (discSeriesBO.updateDiscSeries(discSeries)) {
				// response.sendRedirect("UpdateDiscSeries?DiscSeriesId=" +
				// discSeriesId);
				String message = "Thông báo;Dữ liệu đã cập nhật thành công!;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			} else {
				String message = "Lỗi khi cập nhật;Dữ liệu nhập vào không hợp lệ, xin nhập lại!;#; ";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			}
			request.setAttribute("DiscSeries", discSeries);
			request.setAttribute("AllCategories", listCategories);
			request.getRequestDispatcher("/WEB-INF/UpdateDiscSeries.jsp").include(request, response);
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
