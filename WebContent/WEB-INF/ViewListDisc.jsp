<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Disc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String staffName = "";
	Staff staff = new Staff();
	staff = (Staff) session.getAttribute("staff");
	if (staff == null) {
		// Nếu chưa login thì chuyển về giao diện chưa đăng nhập
		response.sendRedirect("default.jsp");
	} else {
		boolean isManager = staff.isManager() ? true : false;
	}
	DiscSeries discSeries = (DiscSeries) request.getAttribute("DiscSeries");
	ArrayList<Disc> listDisc = discSeries.getListDisc();
	String available = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function redirect(){
			<% response.sendRedirect("AddNewDisc");%>
		}
	</script>
</head>
</head>
<body>
	<div class="container-fluid" style="margin-top: 20px">
		<ol class="breadcrumb">
			<li><a href="#">Quản lý đĩa thuê</a></li>
			<li class="active">Quản lý các bộ đĩa</li>
			<li class="active">Danh sách đĩa</li>
		</ol>
		<div class="row">
			<label class="col-sm-5 control-label">Quản lý các bộ đĩa </label>
			<div class="col-md-1 col-md-offset-6">
				<button class="btn btn-md btn-default btn-block" type="submit"
					btn-sm>Đóng</button>
			</div>
			<div class="col-md-12">
				<label class="col-md-12 control-label">Bộ đĩa: <%=discSeries.getDiscSeriesName()%></label>
			</div>
		</div>
		<!-- /.row -->
		<!-- TABLE -->
		<div class="table-responsive"
			style="margin: 10px 0px 10px 0px; height: 223px; overflow: auto">
			<table class="table table-bordered">
				<tr class="active">
					<th>STT</th>
					<th>Mã đĩa</th>
					<th>Chất lượng</th>
					<th>Tình trạng</th>
					<th>Giá/DVD/Tuần</th>
					<th>Thao tác</th>
				</tr>
				<%
					for (int i = 0; i < listDisc.size(); i++) {
						Disc disc = listDisc.get(i);
						if (disc.isAvailable()) {
							available = "Sẵn sàng";
						} else {
							available = "Đang được thuê";
						}
				%>
				<tr>
					<td><%=i + 1%></td>
					<td><%=disc.getDiscId()%></td>
					<td><%=disc.getQualityId()%></td>
					<td><%=available%></td>
					<td><%=disc.getPrice()%></td>
					<td><a href="UpdateDisc" style="text-decoration: underline;">Sửa</a>
						<a href="RemoveDisc" style="text-decoration: underline;">Xóa</a></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<!-- /TABLE -->
		<div class="row">
			<div class="col-md-3">
				<button class="btn btn-xs btn-default btn-block" type="submit"
					btn-sm  onclick="redirect()">
					<h4>Thêm đĩa mới vào bộ</h4>
				</button>
			</div>
		</div>
		<!-- /.row -->
	</div>
</body>
</html>