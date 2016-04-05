<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hệ thống quản lí cửa hàng cho thuê đĩa DVD</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<!-- TOP -->
		<div class="row">
			<div class="col-md-6">
				<h3>
					<a href="index.jsp"><span class="glyphicon glyphicon-align-justify"></span></a> <span
						class="text text-success">Cửa hàng thuê đĩa ABC</span>
				</h3>
			</div>
			<div class="col-md-4 col-md-offset-2" style="padding-top: 15px;">
				<form method="post" action="Login">
					<div class="input-group">
						<input type="text" name="username" class="form-control"
							placeholder="Tài khoản"> <span class="input-group-btn"
							style="width: 0px;"><input type="hidden" name="loginAct" value="yes"></span> <input type="password"
							name="password" class="form-control" placeholder="Mật khẩu">
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
								<span class="glyphicon glyphicon-home"></span>
								<a href="HomePage" target="_main">Trang chủ</a>
							</h4>
						</div>
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-list"></span>
								<a href="ViewDiscSeriesList"
									target="_main">Xem Danh Sách</a>
							</h4>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<span class="glyphicon glyphicon-info-sign"></span>
								<a href="About"
									target="_main">Thông tin</a>
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