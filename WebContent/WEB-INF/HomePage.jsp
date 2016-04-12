<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.Staff"%>
<%
	Staff staff = (Staff) session.getAttribute("staff");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
<jsp:include page="_bootstrap.jsp" />
<jsp:include page="_header.jsp" />
<body>
<jsp:include page="_top.jsp" />
<img src="images/logo.png" width="100%" />
	<div class="container">
		<div class="text text-lg text-default">
				<%
					if (staff == null) {
				%>
				Chào mừng bạn đến với hệ thống cửa hàng của chúng tôi... Xin chọn
				chức năng ở bên trái.
				<%
					} else {
				%>
				Chào mừng <strong class="text text-primary"><%=staff.getStaffName()%></strong>. Các chức năng
				tương ứng quyền hạn của bạn đã được mở ở bên trái
				<%
					}
				%>
		</div>
	</div>
</body>
</html>