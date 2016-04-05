<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
</head>
</head>
<body>
	<div class="container">
		<div class="panel panel-default" style="margin-top: 20px">
			<div class="panel-heading">
				<a href="" style="text-decoration: underline;">Quản lý phiếu
					thuê</a> <span class="glyphicon glyphicon-menu-right"></span> <a
					href="" style="text-decoration: underline;">Xem danh sách phiếu</a>
				<span class="glyphicon glyphicon-menu-right"></span> Xem chi tiết
				phiếu

			</div>
			<!-- /PANEL-HEADING -->

			<div class="panel-body">
				<div class="row">
					<label class="col-sm-5 control-label">Danh sách các đĩa </label>
					<div class="col-md-1 col-md-offset-6">
						<button class="btn btn-md btn-default btn-block" type="submit"
							btn-sm>Đóng</button>
					</div>
					<div class="col-md-12">
					<label class="col-md-12 control-label">Bộ đĩa: </label>
					</div>
				</div>
				<!-- /.row -->
				<!-- TABLE -->
				<div class="table-responsive" style="margin: 10px 0px 10px 0px">
					<table class="table table-bordered">
						<tr class="active">
							<th>STT</th>
							<th>Mã đĩa</th>
							<th>Chất lượng</th>
							<th>Tình trạng</th>
							<th>Giá/DVD/Tuần</th>
							<th>Thao tác</th>
						</tr>
						<tr>
							<th scope="row">1
							</td>
							<td>123</td>
							<td>2*</td>
							<td>Đang được thuê</td>
							<td>30.000</td>
							<td><a href="#">Sửa</a> <a href="#">Xóa</a></td>
						</tr>
						<tr>
							<th scope="row">2
							</td>
							<td>124</td>
							<td>3*</td>
							<td>Sẵn sàng</td>
							<td>30.000</td>
							<td><a href="#">Sửa</a> <a href="#">Xóa</a></td>
						</tr>
						<tr>
							<th scope="row">3
							</td>
							<td>125</td>
							<td>2*</td>
							<td>Đang được thuê</td>
							<td>25.000</td>
							<td><a href="#">Sửa</a> <a href="#">Xóa</a></td>
						</tr>
						<tr>
							<th scope="row">4
							</td>
							<td>123</td>
							<td>3*</td>
							<td>Đang được thuê</td>
							<td>20.000</td>
							<td><a href="#">Sửa</a> <a href="#">Xóa</a></td>
						</tr>
					</table>
				</div>
				<!-- /TABLE -->
				<div class="row">
					<div class="col-md-3">
						<button class="btn btn-xs btn-default btn-block" type="submit"
							btn-sm>
							<h4>Thêm đĩa mới vào bộ</h4>
						</button>
					</div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /PANEL-BODY -->
		</div>
		<!-- /PANEL -->
	</div>
</body>
</html>