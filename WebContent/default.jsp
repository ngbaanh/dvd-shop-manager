<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
<%@ page import="model.bean.Staff"%>
<%
	Staff loggedInStaff = (Staff) session.getAttribute("staff");
	if (loggedInStaff != null) {
		response.sendRedirect("index.jsp");
	} // END
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống quản lí cửa hàng cho thuê đĩa DVD</title>
<jsp:include page="WEB-INF/_bootstrap.jsp" />
<script type="text/javascript">
	function validateForm() {
		var x1 = document.forms["loginForm"]["username"].value;
		var x2 = document.forms["loginForm"]["password"].value;
		var validated = true;
		if (x1 == null || x1.trim() == "" || x2 == null || x2.trim() == "") {
			validated = false;
		} else if (x1.length > <%=Const.MAXLENGTH_STAFFID%> 
			|| x2.length > <%=Const.MAXLENGTH_PASSWORD%>) {
			validated = false;
		}
		if (validated) {
			return true;
		} else {
			alert("<%=Const.INVALID_FORM%>");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<!-- TOP -->
		<div class="row">
			<div class="col-md-6">
				<h3>
					<a href="index.jsp"><span
						class="glyphicon glyphicon-align-justify"></span></a> Cửa hàng thuê
					đĩa <strong>SE23</strong>
				</h3>
			</div>
			<div class="col-md-4 col-md-offset-2" style="padding-top: 15px;">
				<form method="post" action="Login" name="loginForm"
					onSubmit="return validateForm()">
					<div class="input-group">
						<input type="text" name="username" class="form-control"
							placeholder="Tài khoản"> <span class="input-group-btn"
							style="width: 0px;"><input type="hidden" name="doLogin"></span>
						<input type="password" name="password" class="form-control"
							placeholder="Mật khẩu">
						<div class="input-group-btn">
							<button type="submit" class="btn btn-primary">Đăng nhập</button>
						</div>
					</div>

				</form>
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
								<span class="glyphicon glyphicon-info-sign"></span> <a
									href="About" target="_main">Thông tin</a>
							</h4>
						</div>
					</div>
					<div class="panel panel-success">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-copyright-mark"></span> <i>SE23</i>
							</h4>
						</div>
						<div class="panel-body">Đồ án PTTK HTTT</div>
					</div>
				</div>
				<!-- /Groups -->
			</div>
			<!-- /LEFT -->
			<!-- RIGHT -->
			<div class="col-md-10">
				<div class="well">
					<iframe src="HomePage" name="_main"
						style="border: none; width: 100%; height: 585px;"> </iframe>
				</div>
			</div>
			<!-- /RIGHT -->
		</div>
		<!-- /BOTTOM -->
	</div>
</body>
</html>