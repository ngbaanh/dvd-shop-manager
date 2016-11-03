package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Price;
import model.bo.PriceBO;

/**
 * Servlet implementation class ManageDiscPrice
 */
@WebServlet("/ManageDiscPrice")
public class ManageDiscPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PriceBO priceBO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageDiscPrice() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		//TODO
		String action = request.getParameter("action");
		if ("do".equals(action)) {
			try {
				int value1 = Integer.parseInt(request.getParameter("value1"));
				int value2 = Integer.parseInt(request.getParameter("value2"));
				int value3 = Integer.parseInt(request.getParameter("value3"));
				if (value1 < value2 && value2 < value3) {
					priceBO = new PriceBO();
					Price price1 = new Price();
					price1.setQualityId((byte)1);
					price1.setPrice(value1);
					
					Price price2 = new Price();
					price2.setQualityId((byte)2);
					price2.setPrice(value2);
					
					Price price3 = new Price();
					price3.setQualityId((byte)3);
					price3.setPrice(value3);
					
					if (priceBO.updatePrice(price1) && priceBO.updatePrice(price2) && priceBO.updatePrice(price3)) {
						String message = "Thành công;Tất cả thông tin đã được lưu lại;ManageDiscPrice;Quay lại";
						request.setAttribute("message", message);
						request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
					} else {
						String message = "Thất bại;Cập nhật giá không thành công;ManageDiscPrice;Quay lại";
						request.setAttribute("message", message);
						request.getRequestDispatcher("WEB-INF/Message.jsp").include(request, response);
					}
				} else {
					String message = "Thất bại;Kiểm tra lại giá trị;ManageDiscPrice;Quay lại";
					request.setAttribute("message", message);
					request.getRequestDispatcher("Message").include(request, response);
				}
			} catch (NumberFormatException e) {}
		} else {
			priceBO = new PriceBO();
			request.setAttribute("value1", priceBO.getPrice((byte) 1).getPrice());
			request.setAttribute("value2", priceBO.getPrice((byte) 2).getPrice());
			request.setAttribute("value3", priceBO.getPrice((byte) 3).getPrice());
			request.getRequestDispatcher("/WEB-INF/ManageDiscPrice.jsp").include(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
