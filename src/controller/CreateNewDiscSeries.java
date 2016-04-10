package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Disc;
import model.bean.DiscSeries;
import model.bo.CategoryBO;
import model.bo.DiscSeriesBO;

/**
 * Servlet implementation class CreateNewDiscSeries
 * 
 * @author LeMinh, NguyenBaAnh
 */
@WebServlet("/CreateNewDiscSeries")
public class CreateNewDiscSeries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DiscSeriesBO discSeriesBO;
	CategoryBO categoryBO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateNewDiscSeries() {
		super();
		discSeriesBO = new DiscSeriesBO();
		categoryBO = new CategoryBO();
		// FIXME - console
		System.out.println("\n>>>>>>>>> CreateNewDiscSeries >>>>>>>>>");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// Lấy danh sách tất cả thể loại đĩa có trong hệ thống
		ArrayList<Category> listCategories = categoryBO.getListCategories();
		// ==== Code mới của Bá Anh
		// =====================================================
		if (request.getParameter("doCreate") == null) { // Tải giao diện trống
			// Gửi danh sách thể loại về giao diện
			request.setAttribute("AllCategories", listCategories);
			request.getRequestDispatcher("/WEB-INF/CreateNewDiscSeries.jsp").forward(request, response);
		} else { // Khi gửi dữ liệu từ giao diện lên
			// Lấy params
			String discSeriesName = request.getParameter("DiscSeriesName").trim();
			String description = request.getParameter("Description").trim();
			int categoryId = Integer.parseInt(request.getParameter("CategoryId"));
			int totalDisc = Integer.parseInt(request.getParameter("TotalDisc"));
			byte qualityId = 3; // mặc định là 3*
			String place = request.getParameter("Place").trim();
			// đóng gói params vào Bean
			DiscSeries discSeries = new DiscSeries();
			Disc disc = new Disc(); // 1 đĩa đại diện cho totalDisc đĩa
			disc.setQualityId(qualityId);
			disc.setPlace(place);
			ArrayList<Disc> discForList = new ArrayList<Disc>();
			discForList.add(disc);
			// không cần setDiscSeriesId() vì thêm mới thì DBMS sẽ tự cấp phát
			discSeries.setDiscSeriesName(discSeriesName);
			discSeries.setDescription(description);
			discSeries.setCategory(categoryBO.getCategory(categoryId));
			discSeries.setTotalDisc(totalDisc);
			discSeries.setRemainingDisc(totalDisc); // mới remain = total
			discSeries.setListDisc(discForList);
			// Gửi về lại giao diện những thông tin vừa nhập
			request.setAttribute("AllCategories", listCategories);
			request.setAttribute("FailedDiscSeries", discSeries);
			request.getRequestDispatcher("/WEB-INF/CreateNewDiscSeries.jsp").include(request, response);
			// Hết nhiệm vụ, đẩy qua cho BO xử lí và chờ phản hồi
			if (discSeriesBO.isExist(discSeriesName)) { // trùng tên
				String message = "Lỗi khi thêm mới;Bộ đĩa <strong>" + discSeriesName
						+ "</strong> đã tồn tại trong hệ thống;javascript:history.go(-1);Quay lại";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			} else if (discSeriesBO.addNewDiscSeries(discSeries)) {
				int newDiscSeriesId = discSeriesBO.getIdByName(discSeriesName);
				response.sendRedirect("ViewListDisc?DiscSeriesId=" + newDiscSeriesId);
			} else { // thất bại
				// Kèm theo thông báo lỗi
				String message = "Thêm bộ đĩa thất bại;Dữ liệu nhập vào không hợp lệ, xin nhập lại!;ManageDiscSeriesList;Quay về trang quản lí đĩa";
				request.setAttribute("message", message);
				request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
			}
		}
		/*
		 * // Code cũ của Minh
		 * request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").forward(
		 * request, response);
		 * 
		 * String tenBoDia = request.getParameter("tenDia"); String moTa =
		 * request.getParameter("moTa"); String theLoai =
		 * request.getParameter("theLoai"); String soLuong =
		 * request.getParameter("soLuong"); String viTri =
		 * request.getParameter("viTri");
		 * 
		 * //check validate
		 * if(tenBoDia==null||moTa==null||theLoai==null||soLuong==null||viTri==
		 * null){ String message =
		 * "Lỗi;Thieu thong tin;index.jsp;Quay về Trang Chủ"; //
		 * Tiêu-đề;Nội-dung;URL;Tên-URL request.setAttribute("message",
		 * message);
		 * request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request,
		 * response); // show messages
		 * request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").include(
		 * request, response); // load JSP }else{ try { DiscSeries discSeries =
		 * new DiscSeries(); discSeries.setDiscSeriesName(tenBoDia);
		 * discSeries.setDescription(moTa); model.bean.Category category = new
		 * model.bean.Category();
		 * category.setCategoryId(Integer.parseInt(theLoai));
		 * discSeries.setCategory(category);
		 * discSeries.setTotalDisc(Integer.parseInt(soLuong));
		 * discSeries.setRemainingDisc(Integer.parseInt(viTri)); } catch
		 * (Exception e) { String message =
		 * "Lỗi;format data error;index.jsp;Quay về Trang Chủ"; //
		 * Tiêu-đề;Nội-dung;URL;Tên-URL request.setAttribute("message",
		 * message);
		 * request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request,
		 * response); // show messages
		 * request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").include(
		 * request, response); // load JSP }
		 * response.sendRedirect("ManageDiscSeries.jsp"); }
		 */
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
