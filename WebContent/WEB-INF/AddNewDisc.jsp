<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String discSeriesName = (String) request.getAttribute("DiscSeriesName");

	if (discSeriesName != null && !"".equals(discSeriesName)) {
		int discSeriesId = (int) request.getAttribute("DiscSeriesId");
		int discNumber = (int) request.getAttribute("DiscNumber");
		String place = (String) request.getAttribute("Place");
		if (place == null) {
			place = "";
		}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Thêm đĩa mới vào bộ</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<h4>
				Bộ đĩa: <strong><%=discSeriesName%></strong>
			</h4>
		</div><br>
		<form class="form-horizontal" action="AddNewDisc" method="get">
			<div class="form-group">
				<label class="col-xs-3">Vị trí *</label>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="Place"
						value="<%=place%>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-3">Số lượng đĩa *</label>
				<div class="col-xs-9">
					<input type="number" class="form-control" name="DiscNumber"
						value="<%=discNumber%>">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-9 col-xs-offset-3">
					<i>Đĩa thêm mới mặc định chất lượng 3*</i>
				</div>
			</div>
			<div class="form-group">
				<input type="hidden"
					name="DiscSeriesId" value="<%=discSeriesId%>">
				<input type="hidden" name="doAdd">
				<div class="col-xs-4 col-xs-offset-4">
					<input type="submit" class="btn btn-block btn-primary" value="Thêm mới">
				</div>
			</div>
		</form>
	</div>
	<!--container-->
</body>
</html>
<%
	}
%>