package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Xin lỗi, bạn đã đăng nhập với một giao thức không chính xác!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String loginAct = request.getParameter("loginAct");
		if ("yes".equals(loginAct)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			//boolean loginSucess = new LoginBO().validate(username, password);
			// TODO
			if (username.equals("admin") && password.equals("admin")) { // test
				response.sendRedirect("index.jsp");
			} else {
				response.getWriter().append("Đăng nhập thất bại <hr>");
				request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").include(request, response);
		}
		

	}

}
