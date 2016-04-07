package controller;

import java.io.IOException;
import java.util.Locale.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DiscSeries;

/**
 * Servlet implementation class AddNewDiscSeries
 */
@WebServlet("/AddNewDiscSeries")
public class AddNewDiscSeries extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddNewDiscSeries() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").forward(request, response);
		
		String tenBoDia = request.getParameter("tenDia");
		String moTa = request.getParameter("moTa");
		String theLoai = request.getParameter("theLoai");
		String soLuong = request.getParameter("soLuong");
		String viTri = request.getParameter("viTri");
		
		//check validate
		if(tenBoDia==null||moTa==null||theLoai==null||soLuong==null||viTri==null){
			String message = "Lỗi;Thieu thong tin;index.jsp;Quay về Trang Chủ"; // Tiêu-đề;Nội-dung;URL;Tên-URL
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response); // show messages
			request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").include(request, response); // load JSP
		}else{
			try {
				DiscSeries discSeries = new DiscSeries();
				discSeries.setDiscSeriesName(tenBoDia);
				discSeries.setDescription(moTa);
				model.bean.Category category = new model.bean.Category();
				category.setCategoryId(Integer.parseInt(theLoai));
				discSeries.setCategory(category);
				discSeries.setTotalDisc(Integer.parseInt(soLuong));
				discSeries.setRemainingDisc(Integer.parseInt(viTri));
			} catch (Exception e) {
				String message = "Lỗi;format data error;index.jsp;Quay về Trang Chủ"; // Tiêu-đề;Nội-dung;URL;Tên-URL
				request.setAttribute("message", message);
				request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response); // show messages
				request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").include(request, response); // load JSP
			}
			response.sendRedirect("ManageDiscSeries.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
