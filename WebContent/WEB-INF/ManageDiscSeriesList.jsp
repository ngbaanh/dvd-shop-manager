<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Category"%>
<%@page import="util.Const"%>
<%@page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("AllCategories");
	@SuppressWarnings("unchecked")
	ArrayList<DiscSeries> listDiscSeries = (ArrayList<DiscSeries>) request.getAttribute("ListDiscSeries");
	int currentPage = 1;
	int currentCaterogyId = 0;
	String currentSearchQuery = "";
	currentPage = (int) request.getAttribute("CurrentPage");
	currentCaterogyId = (int) request.getAttribute("CurrentCaterogyId");
	currentSearchQuery = (String) request.getAttribute("CurrentSearchQuery");
	int startIndex = (currentPage - 1) * Const.ITEMS_PER_PAGE + 1;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="Tran Thanh Sang, Nguyen Ba Anh">
<title>Quản lí các bộ đĩa</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
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
				<%
					if (listDiscSeries.isEmpty()) {
				%>
				<div class="row">
					<div class="col-xs-6 col-xs-offset-3">
						<div class="panel panel-danger">
							<div class="panel-heading">
								<h4 class="panel-title">Kết quả</h4>
							</div>
							<div class="panel-body">Không tìm thấy</div>
						</div>
					</div>
				</div>
				<%
					} else {
				%>
				<div class="table">
					<table class="table table-bordered">
						<tr class="active">
							<th>STT</th>
							<th>Tên bộ đĩa</th>
							<th>Thể loại</th>
							<th>SL</th>
							<th>Thao tác</th>
						</tr>
						<%
							for (DiscSeries ds : listDiscSeries) {
						%>
						<tr>
							<td><%=(startIndex++)%></td>
							<td><%=ds.getDiscSeriesName()%></td>
							<td><%=ds.getCategory().getCategoryName()%></td>
							<td><%=ds.getRemainingDisc()%> / <%=ds.getTotalDisc()%></td>
							<td><a
								href="ViewListDisc?DiscSeriesId=<%=ds.getDiscSeriesId()%>">Xem</a>
								<a href="#">Sửa</a> <a href="#">Xóa</a></td>
						</tr>
						<%
							}
						%>
					</table>
					<%
						} // end ----------- listDiscSeries
					%>
				</div>
				<div class="row">
					<div class="col-md-2 col-md-offset-0">
						<div class="">
							<a type="button" href="CreateNewDiscSeries"
								class="btn btn-primary btn-sm">Thêm mới bộ đĩa</a>
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