<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Manager disc series list">



<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="AddNewDisc" method="post">
		<div class="container">

			<div class="row">
				<div class="col-md-5">
					<h3>Thêm đĩa mới</h3>
				</div>
				<!--col-md5-->
				<div class="col-md-2">
					<button type="button" class="btn btn-sm btn-info">Đóng</button>
				</div>
				<!--col-md2-->
			</div>
			<!--row-->

			<h4>
				Bộ đĩa: Avata 1</em>
			</h4>

			<div class="row">
				<div class="col-md-2">
					<p>Vị trí*</p>
				</div>
				<!--col-md2-->
				<div class="col-md-3">
					<input class="form-control" name="viTri">
				</div>
				<!--col-md3-->
			</div>
			<!--row-->

			<div class="row">
				<div class="col-md-2">
					<p>Số lượng đĩa</p>
				</div>
				<!--col-md2-->
				<div class="col-md-3">
					<input type="Text" class="form-control" name="soLuongDia"><br>
					<p>Tất cả đĩa mới có chất lượng mặc định 3*</p>
					<button type="submit" class="btn btn-sm btn-info">Lưu</button>
				</div>
				<!--col-md3-->
			</div>
			<!--row-->

		</div>
		<!--container-->
	</form>
</body>
</html>