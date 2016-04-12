<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.Staff"%>

<%
	String staffName = "";
	Staff staff = new Staff();
	staff = (Staff) session.getAttribute("staff");
	if (staff == null) {
		// Nếu chưa login thì chuyển về giao diện chưa đăng nhập
		response.sendRedirect("default.jsp");
	} else {
		boolean isManager = staff.isManager() ? true : false;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống quản lí cửa hàng cho thuê đĩa DVD</title>
<jsp:include page="WEB-INF/_bootstrap.jsp" />
</head>
<body>
	<div class="container-fluid">
		<!-- TOP -->
		<div class="row">
			<div class="col-md-6">
				<h3>
					<a href="index.jsp"><span
						class="glyphicon glyphicon-align-justify"></span></a> Cửa hàng thuê đĩa <strong>SE23</strong>
				</h3>
			</div>
			<div class="col-md-2 col-md-offset-3" style="padding-top: 15px;">
				Chào <span class="text text-lg text-danger"><b><%=staff.getStaffName()%></b></span>
			</div>
			<div class="col-md-1" style="padding-top: 10px;">
				<a class="btn btn-warning btn-block" href="Logout">Thoát</a>
			</div>
		</div>
		<!-- /TOP -->
		<!-- BOTTOM -->
		<div class="row">
			<!-- /LEFT -->
			<div class="col-md-2">
				<!-- Groups -->
				<div class="panel-group">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-home"></span> <a
									href="HomePage" target="_main">Trang chủ</a>
							</h4>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-list"></span> <a
									href="ViewDiscSeriesList" target="_main">Xem Danh Sách</a>
							</h4>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-cd"></span> <a
									data-toggle="collapse" href="#DiscManagement">Quản lí đĩa</a>
							</h4>
						</div>
						<div id="DiscManagement" class="panel-collapse collapse">
							<ul class="list-group">
								<li class="list-group-item"><a href="ManageDiscSeriesList"
									target="_main">Quản lí các bộ đĩa</a></li>
								<li class="list-group-item"><a href="ManageDiscCategory"
									target="_main">Quản lí các thể loại</a></li>
								<%
									if (isManager) {
								%>
								<li class="list-group-item"><a href="ManageDiscPrice"
									target="_main">Quản lí giá thuê đĩa</a></li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-list-alt"></span> <a
									href="ViewTicketList" target="_main">Quản lí phiếu thuê</a>
							</h4>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-user"></span> <a
									data-toggle="collapse" href="#StaffManagement">Quản lí nhân
									viên</a>
							</h4>
						</div>
						<div id="StaffManagement" class="panel-collapse collapse">
							<ul class="list-group">
								<%
									if (isManager) {
								%>
								<li class="list-group-item"><a href="ViewStaffList"
									target="_main">DS nhân viên</a></li>
								<li class="list-group-item"><a href="AddNewStaff"
									target="_main">Thêm nhân viên</a></li>
								<%
									}
								%>
								<li class="list-group-item"><a href="ChangePassword"
									target="_main">Đổi mật khẩu</a></li>
							</ul>
						</div>
					</div>
					<%
						if (isManager) {
					%>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-stats"></span> <a
									href="ViewStatistics" target="_main">Báo cáo thống kê</a>
							</h4>
						</div>
					</div>
					<%
						}
					%>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-info-sign"></span> <a
									href="About" target="_main">Thông tin</a>
							</h4>
						</div>
					</div>
				</div>
				<!-- /Groups -->
			</div>
			<!-- /LEFT -->
			<!-- RIGHT -->
			<div class="col-md-10">
				<div class="well">
						<iframe src="HomePage" name="_main" id="_main"
						style="border: none; width: 100%; height: 585px;"> </iframe>
				</div>
			</div>
			<!-- /RIGHT -->
		</div>
		<!-- /BOTTOM -->
	</div>
	<div style="display:none"><a href="Admin" target="_blank" accesskey="a">Admin</a></div>
</body>
</html>
<%
	}
%>