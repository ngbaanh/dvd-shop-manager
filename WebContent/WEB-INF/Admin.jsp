<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int itemsPerPage = (int) request.getAttribute("items_per_page");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
html {
	background: repeating-linear-gradient(
  -45deg,
  #222,
  #222 10px,
  #333 10px,
  #333 20px
);
}
</style>
</head>
<body style="width: 800px; margin: 50px auto; border-radius: 3px;">
	<div class="container-fluid">
		<h3>Trang quản lí</h3>
		<form action="Admin" method="post" class="form-horizontal">
			<div class="form-group">
				<label class="col-xs-6 control-label">Số items / trang</label>
				<div class="col-xs-6">
					<input class="form-control" type="number" name="items_per_page"
						value="<%=itemsPerPage%>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-2 col-xs-offset-6">
					<input class="btn btn-primary btn-block" type="submit" value="Lưu">
				</div>
				<div class="col-xs-3 col-xs-offset-1">
					<a class="btn btn-primary btn-danger btn-block" href="index.jsp">Quay
						về trang chủ</a>
				</div>
			</div>

		</form>
	</div>
</body>
</html>
