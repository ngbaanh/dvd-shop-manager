<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.DiscSeries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset = "utf-8">
	<meta name="description" content="Manager disc series list">
    <meta name="author" content="Tran Thanh Sang">

	<title>ManageDiscSeriesList</title>
    
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
			<div class="panel-body">
				<ol class="breadcrumb">
					<li><a href="#">Quản lý đĩa</a></li>
					<li class="active">Quản lý các bộ đĩa</li>
				</ol>
				<div class="row">
					<div class="col-md-3 col-md-offset-0">
						<div class="input-group input-group-sm">
							<span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
							<input type="text" class="form-control" id="textSearch" placeholder="Tìm kiếm tên bộ đĩa" required="required">
							<script type="text/javascript">
							$(".form-control").keypress(function (event) { 
							if (event.which == 13) { 
								var keyword = document.getElementById("textSearch").value;
								if(keyword ==""){
									alert("Gợi ý: Bạn phải nhập từ khóa cần tìm kiếm trước khi nhấn Enter.");
								}
							return false; 
							} 
							});
							</script> 
						</div>
					</div>
					<div class="col-md-2 col-md-offset-3">
						<div class="input-group">
						<%
								ArrayList<Category> alCat=(ArrayList<Category>)request.getAttribute("alCat");
							%>
							<input type="text" class="form-control" placeholder="Thể loại" disabled="disabled">
							<div class="input-group-btn">
							
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
								<ul class="dropdown-menu dropdown-menu-right">
									<%
										if(alCat!=null){
											for(Category objCat:alCat){
												
									%>
									<li><a href="#"><%=objCat.getCategoryName() %></a></li>
									<li class="divider"></li>
									
									<%}} %>
								</ul>
							</div><!-- /btn-group -->
						</div><!-- /input-group -->
					</div><!-- /.col-lg-6 -->
				</div><!-- /.row -->
				<div class="table-responsive" style="margin:10px 0px 10px 0px">
					<table  class="table table-bordered">
						<tr class="active">
							<th>STT</th>
							<th>Tên bộ đĩa</th>
							<th>Thể loại</th>
							<th>SL</th>
							<th>Thao tác</th>
						</tr>
						<%
							ArrayList<DiscSeries> DanhSachBoDia = (ArrayList<DiscSeries>)request.getAttribute("DanhSachBoDia");
						%>
						<%
							if(DanhSachBoDia!=null){
								for (DiscSeries ds :DanhSachBoDia ) {
						%>
						<tr>
						<td><%=ds.getDiscSeriesId() %></td>
						<td><%=ds.getDiscSeriesName()%></td>
						<td><%=ds.getCategory()%></td>
						<td><%=ds.getRemainingDisc()%> / <%=ds.getTotalDisc()%></td>
						<td>
								<a href="#">Xem</a>
								<a href="#">Sửa</a>
								<a href="#">Xóa</a>
							</td>
						</tr>
						<%
							}}
						%>
					</table>
				</div>
				<div class="row">
					<div class="col-md-2 col-md-offset-0">
						<div class="">
							<button type="button" class="btn btn-primary btn-sm ">Thêm mới bộ đĩa</button>
						</div>
					</div>
					<div class="col-md-1 col-md-offset-7">
							<h5>Trang<h5>
					</div>
					<div class="col-md-2 col-md-offset-0">
						<div class="input-group">
							<input type="text" class="form-control" placeholder="3/10" di>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></button>
								<ul class="dropdown-menu dropdown-menu-right">
									<li><a href="#">1/10</a></li>
									<li class="divider"></li>
									<li><a href="#">2/10</a></li>
									<li class="divider"></li>
									<li><a href="#">3/10</a></li>
								</ul>
							</div><!-- /btn-group -->
						</div><!-- /input-group -->
					</div><!-- /.col-lg-6 -->
				</div><!-- /.row -->
			</div>
		</div>
</body>
</html>