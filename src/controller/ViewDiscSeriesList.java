package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bo.CategoryBO;

/**
 * Servlet implementation class ViewDiscSeriesList
 */
@WebServlet("/ViewDiscSeriesList")
public class ViewDiscSeriesList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDiscSeriesList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		CategoryBO categoryBO = new CategoryBO();
		ArrayList<Category> listCategories = categoryBO.getListCategories();
		request.setAttribute("listCategories", listCategories);
		
		request.getRequestDispatcher("/WEB-INF/ViewDiscSeriesList.jsp").forward(request, response);
	}

}
