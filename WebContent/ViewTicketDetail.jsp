<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="Tran Thanh Sang">
<title>ViewTicketDetail</title>
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
				<li><a href="/ViewTicketList">Quản lí phiếu thuê</a></li>
				<li><a href="/ViewTicketList">Xem danh sách phiếu</a></li>
				<li class="active">Xem chi tiết phiếu</li>
			</ol>
		</div>
		<p>Thông tin phiếu.</p>
		<div
			style="border-style: solid; border-width: medium; padding: 20px 0px 20px 0px">
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
					Mã phiếu<span style="color: red">*</span>
				</div>
				<div class="col-md-2">
					<input type="text" id="disabledTextInput" class="form-control"
						value="123" readonly>
				</div>
				<div class="col-md-2 col-md-offset-2">
					Ngày lập phiếu<span style="color: red">*</span>
				</div>
				<div class="col-md-2">
					<input type="text" id="disabledTextInput" class="form-control"
						value="25/01/2016" readonly>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
					Tên khách hàng<span style="color: red">*</span>
				</div>
				<div class="col-md-8">
					<input type="text" class="form-control" value="Nguyễn Anh">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
					Số điện thoại<span style="color: red">*</span>
				</div>
				<div class="col-md-8">
					<input type="text" class="form-control" value="0123456789">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
					Địa chỉ<span style="color: red">*</span>
				</div>
				<div class="col-md-8">
					<input type="text" class="form-control"
						value="Hòa Khánh, Liên Chiểu, Đà Nẵng">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2 col-md-offset-1">Tình trạng phiếu</div>
				<div class="col-md-2 col-md-offset-0">
					<label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio1" value="option1"
						checked="checked" disabled="disabled"> Đã đặt 
				</div>
				<div class="col-md-2 col-md-offset-0">
					</label> <label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio2" value="option2"
						disabled="disabled"> Đang thuê 
				</div>
				<div class="col-md-2 col-md-offset-0">
					</label> <label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio3" value="option3"
						disabled="disabled"> Đã trả hết
					</label>
				</div>
				<div class="col-md-2 col-md-offset-0">
					</label> <label class="radio-inline"> <input type="radio"
						name="inlineRadioOptions" id="inlineRadio3" value="option3"
						disabled="disabled"> Quá hạn
					</label>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-2 col-md-offset-1">
					Tài sản đặt cọc<span style="color: red">*</span>
				</div>
				<div class="col-md-8">
					<input type="text" class="form-control"
						value="1 CMND 'Nguyễn Anh', mã số 123456789">
				</div>
			</div>

		</div>
		<br>
		<div class="row">
			<div class="col-md-9 ">
				<p>Danh sách đĩa thuê</p>
				<div
					style="border-style: solid; border-width: medium; padding: 20px 20px 0px 20px">
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
							<td><div class="btn-group" style="float: right">
									<select>
										<option value="1">1</option>
										<option value="2" selected="selected">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>
								</div></td>
							<td>60.000</td>
						</tr>
						<tr>
							<td>2</td>
							<td>115.</td>
							<td>Mario 3</td>
							<td>25.000</td>
							<td><div class="btn-group" style="float: right">
									<select>
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>
								</div></td>
							<td>25.000</td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>Tổng</td>
							<td>85.000</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-3 col-md-offset-0">
				<p>Thao tác</p>
				<div
					style="border-style: solid; border-width: medium; padding: 20px 20px 20px 20px">
					<button type="button" class="btn btn-primary btn-md btn-block">Cấp
						phiếu</button>
					<button type="button" class="btn btn-default btn-md btn-block"
						disabled="disabled">Gia hạn</button>
					<button type="button" class="btn btn-default btn-md btn-block"
						disabled="disabled">Trả đĩa</button>
					<button type="button" class="btn btn-default btn-md btn-block"">Hủy
						phiếu</button>
				</div>
			</div>
		</div>
	</div>
</body>
</body>
</html>