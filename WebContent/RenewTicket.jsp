<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="Tran Thanh Sang">
<title>RenewTicket</title>
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
		<div class="row">
			<div class="col-md-11 ">
				<h3>Gia hạn đĩa thuê</h3>
			</div>
			<div class="col-md-1 col-md-offset-0" style="padding-top: 20px">
				<button type="button" class="btn btn-default">Đóng</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 ">
				<p style="margin: 20px 0px 10px 100px">Mã phiếu: 123</p>
				<div style="margin: 0px 30px 10px 50px">
					<table class="table table-striped table-bordered">
						<tr>
							<th class="info">STT</th>
							<th class="info">Mã đĩa</th>
							<th class="info">Tên đĩa</th>
							<th class="info">Giá/DVD/tuần</th>
							<th class="info">Số tuần thuê</th>
							<th class="info">Thành tiền(VNĐ)</th>
						</tr>
						<tr>
							<td>1</td>
							<td>123</td>
							<td>Avatar 1</td>
							<td>30.000</td>
							<td>2 +
								<div class="btn-group" style="float: right">
									<select>
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>
								</div>
							</td>
							<td>90.000</td>
						</tr>
						<tr>
							<td>2</td>
							<td>115</td>
							<td>Mario 3</td>
							<td>25.000</td>
							<td>1 +
								<div class="btn-group" style="float: right">
									<select>
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>
								</div>
							</td>
							<td>50.000</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>Tổng mới</td>
							<td>140.000</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>Tổng củ</td>
							<td>85.000</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>Phát sinh</td>
							<td>55.000</td>
						</tr>
					</table>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-md-1 col-md-offset-5" style="padding-top: 20px">
				<button type="button" class="btn btn-default">Cập nhật phiếu</button>
			</div>
		</div>
	</div>
</body>
</body>
</html>