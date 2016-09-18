<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="Tran Thanh Sang">
<title>ViewTicketList</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="WEB-INF/_header.jsp" />
<body>
<jsp:include page="WEB-INF/_top.jsp" />
	<div class="container-fluid">
		<div style="margin-top: 15px">
			<ol class="breadcrumb">
				<li><a href="#">Quản lí phiếu thuê</a></li>
				<li class="active">Xem danh sách phiếu</li>
			</ol>
			<p>Danh sách các phiếu thuê và các thông tin cơ bản.</p>
		</div>
		
		<script type="text/javascript">
			$("input#SearchQuery").live(
					"keyup",
					function(e) {
						if (e.which == 13) {
							var searchQuery = document
									.getElementById("SearchQuery").value;
							// kiểm tra valid searchQuery ở đây
							// TODO
							document.location = "#?SearchQuery=" + searchQuery;
						}
					});
		</script>
		<div class="row">
			<div class="col-md-3">
				<form>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-btn">
								<button disabled class="btn btn-success" type="button">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span> <input type="text" class="form-control"
								placeholder="Tìm kiếm" id="SearchQuery"
								name="SearchQuery" value="">
						</div>
						
					</div>
				</form>
			</div>
			<div class="col-md-3">
				nhập Mã phiếu hoặc tên KH để tìm kiếm
			</div>
			
		</div>
		<!-- /.row -->
		

		<table class="table table-bordered">
			
			<tr class="active">
				<th>Thời gian</th>
				<th>Mã phiếu</th>
				<th>Họ tên KH</th>
				<th>Trạng thái</th>
				<th>Thao tác</th>
			</tr>
			
			<tr>
				<td>00:12 25/01/2016</td>
				<td>123</td>
				<td>Nguyễn Anh</td>
				<td>Đã đặt</td>
				<td><a
					href="">Xem chi tiết</a>
			</tr>
			<tr>
				<td>12:20 24/01/2016</td>
				<td>122</td>
				<td>Trần Hiệp</td>
				<td>Đang thuê</td>
				<td><a
					href="">Xem chi tiết</a>
			</tr>
			<tr>
				<td>08:08 21/01/2016</td>
				<td>121</td>
				<td>Lê Minh</td>
				<td>Đang thuê</td>
				<td><a
					href="">Xem chi tiết</a>
			</tr>
			<tr>
				<td>11:35 19/01/2016</td>
				<td>120</td>
				<td>Trần Sang</td>
				<td>Đang thuê</td>
				<td><a
					href="">Xem chi tiết</a>
			</tr>
			<tr>
				<td>09:45 15/01/2016</td>
				<td>119</td>
				<td>Nguyễn Long</td>
				<td>Đã trả</td>
				<td><a
					href="/ViewTicketDetail">Xem chi tiết</a>
			</tr>
			
		</table>
		
		<div class="row">
			<div class="col-md-1 col-md-offset-0">
				<strong class="text text-muted">Trang </strong>
			</div>
			<div class="col-md-1">
				<div class="dropdown">
					<button class="btn btn-default btn-block dropdown-toggle "
						type="button" data-toggle="dropdown">
						1/5
						&nbsp; <span class="caret"></span>
					</button>
					<div class="dropdown-menu dropdown-menu-right"
						style="padding: 5px; width: 600px !important;">
						<%
							String pageLink;
								for (int i = 1; i <= 5; i++) {
									pageLink = "ManageDiscSeriesList";
						%>
						<a
							class="btn <%=(i == 1) ? "btn-link disabled" : "btn-default"%> btn-xs"
							style="float: right; width: 40px; margin: 1px;"
							href="<%=pageLink%>"><%=i%></a>
						<%
							}
						%>
					</div>
				</div>
			</div>
			
			<div class="col-md-2 col-md-offset-4">
				<strong class="text text-muted">Lọc trạng thái </strong>
			</div>
			<div class="col-md-2">
				<div class="dropdown">
					<button class="btn btn-default btn-block dropdown-toggle"
						type="button" data-toggle="dropdown">Trạng thái
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-menu-right">
						
						<li><a href="">Đã đặt</a></li>
						<li><a href="">Đang thuê</a></li>
						<li><a href="">Đã trả</a></li>
					</ul>
				</div>
			</div>
		</div>
		
	</div>
</body>
</body>
</html>