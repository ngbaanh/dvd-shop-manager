<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="description" content="Manager disc series list">
<meta name="author" content="Tran Thanh Sang">

<title>ManageDiscSeriesList</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="panel panel-default" style="margin-top: 20px">
			<div class="panel-body">
				<ol class="breadcrumb">
					<li><a href="#">Quản lý đĩa</a></li>
					<li class="active">Quản lý các bộ đĩa</li>
				</ol>
				<div class="row">
					<div class="col-md-3 col-md-offset-0">
						<div class="input-group input-group-sm">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span></span> <input type="text"
								class="form-control" placeholder="Tìm kiếm tên bộ đĩa">
						</div>
					</div>
					<div class="col-md-2 col-md-offset-3">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Chọn thể loại">
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu dropdown-menu-right">
									<li><a href="#">Phim điện ảnh</a></li>
									<li class="divider"></li>
									<li><a href="#">Phim hoạt hình</a></li>
									<li class="divider"></li>
									<li><a href="#">Ca nhạc</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-6 -->
				</div>
				<!-- /.row -->
				<div class="table-responsive" style="margin: 10px 0px 10px 0px">
					<table class="table table-bordered">
						<tr class="active">
							<th>STT</th>
							<th>Tên bộ đĩa</th>
							<th>Thể loại</th>
							<th>SL</th>
							<th>Thao tác</th>
						</tr>
						<tr>
							<td>1</td>
							<td>Fast And Furious 1</td>
							<td>Phim điện ảnh</td>
							<td>4/10</td>
							<td><a href="#">Xem</a> <a href="#">Sửa</a> <a href="#">Xóa</a>
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>Avatar 1</td>
							<td>Phim điện ảnh</td>
							<td>2/5</td>
							<td><a href="#">Xem</a> <a href="#">Sửa</a> <a href="#">Xóa</a>
							</td>
						</tr>
						<tr>
							<td>3</td>
							<td>Naruto: Boruto</td>
							<td>Phim hoạt hình</td>
							<td>5/5</td>
							<td><a href="#">Xem</a> <a href="#">Sửa</a> <a href="#">Xóa</a>
							</td>
						</tr>
						<tr>
							<td>4</td>
							<td>HKT 2013</td>
							<td>Nhạc Việt</td>
							<td>4/10</td>
							<td><a href="#">Xem</a> <a href="#">Sửa</a> <a href="#">Xóa</a>
							</td>
						</tr>
						<tr>
							<td>5</td>
							<td>Mario 3</td>
							<td>Phim hoạt hình</td>
							<td>4/10</td>
							<td><a href="#">Xem</a> <a href="#">Sửa</a> <a href="#">Xóa</a>
							</td>
						</tr>
					</table>
				</div>
				<div class="row">
					<div class="col-md-2 col-md-offset-0">
						<div class="">
							<button type="button" class="btn btn-primary btn-sm ">Thêm
								mới bộ đĩa</button>
						</div>
					</div>
					<div class="col-md-1 col-md-offset-7">
						<h5>
							Trang
							<h5>
					</div>
					<div class="col-md-2 col-md-offset-0">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="3/10">
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu dropdown-menu-right">
									<li><a href="#">1/10</a></li>
									<li class="divider"></li>
									<li><a href="#">2/10</a></li>
									<li class="divider"></li>
									<li><a href="#">3/10</a></li>
								</ul>
							</div>
							<!-- /btn-group -->
						</div>
						<!-- /input-group -->
					</div>
					<!-- /.col-lg-6 -->
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
</body>
</html>