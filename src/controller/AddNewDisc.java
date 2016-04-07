package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Disc;
import model.bean.DiscSeries;

/**
 * Servlet implementation class AddNewDisc
 */
@WebServlet("/AddNewDisc")
public class AddNewDisc extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AddNewDisc() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/ThemDiaMoiVaoMotBoDia.jsp").forward(request, response);
		
		String soLuong = request.getParameter("soLuong");
		String viTri = request.getParameter("viTri");
		
		//check validate
		if(soLuong==null||viTri==null){
			String message = "Lỗi;Thieu thong tin;index.jsp;Quay về Trang Chủ"; // Tiêu-đề;Nội-dung;URL;Tên-URL
			request.setAttribute("message", message);
			request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response); // show messages
			request.getRequestDispatcher("/WEB-INF/ThemDiaMoiVaoMotBoDia.jsp").include(request, response); // load JSP
		}else{
			try {
				Disc disc = new Disc();
				disc.setPlace(viTri);
				
				DiscSeries.this.setTotalDisc(DiscSeries.this.getTotalDisc()+Integer.parseInt(soLuong));
				
			} catch (Exception e) {
				String message = "Lỗi;format data error;index.jsp;Quay về Trang Chủ"; // Tiêu-đề;Nội-dung;URL;Tên-URL
				request.setAttribute("message", message);
				request.getRequestDispatcher("/WEB-INF/Message.jsp").include(request, response); // show messages
				request.getRequestDispatcher("/WEB-INF/ThemMoiMotBoDia.jsp").include(request, response); // load JSP
			}
			response.sendRedirect("ViewListDisc.jsp");
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
