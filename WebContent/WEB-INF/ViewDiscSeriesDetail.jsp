<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem danh sách các bộ đĩa</title>
<jsp:include page="_bootstrap.jsp" />

</head>
<jsp:include page="_header.jsp" />
<body>
	<jsp:include page="_top.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h4 class="modal-title">Xem chi tiết bộ đĩa: Avatar 1</h4>
				<h5 class="modal-title">Số lượng đĩa có thể thuê: 2</h5>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-default pull-right">Close</button>
			</div>
		</div>
		<br>
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>STT</th>
					<th>Mã đĩa</th>
					<th>Chất lượng</th>
					<th>Tình trạng</th>
					<th>Giá/DVD/tuần</th>
					<th>Thêm vào danh sách chọn?</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>124</td>
					<td>3*</td>
					<td>Sẵn sàng</td>
					<td>30000</td>
					<td><a href="#">Chọn</a></td>
				</tr>
				<tr>
					<td>2</td>
					<td>126</td>
					<td>1*</td>
					<td>Sẵn sàng</td>
					<td>20000</td>
					<td><a href="#">Chọn</a></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>