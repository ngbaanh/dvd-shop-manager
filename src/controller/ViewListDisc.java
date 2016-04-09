package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DiscSeries;
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
		boolean isValidId = false;
		int discSeriesId = 0;
		try {
			discSeriesId = Integer.parseInt(request.getParameter("DiscSeriesId"));
			isValidId = true;
		} catch (NumberFormatException e) {
			// Bẫy lỗi nhập Id lung tung hoặc không nhập
		}
		if (isValidId && discSeriesId > 0) {
			DiscSeries discSeries = discSeriesBO.getDiscSeries(discSeriesId);
			request.setAttribute("DiscSeries", discSeries);
			request.getRequestDispatcher("/WEB-INF/ViewListDisc.jsp").forward(request, response);
		} else {
			String message = "Lỗi;Nhập sai mã bộ đĩa;ManageDiscSeriesList;Quay về trang quản lí đĩa";
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
