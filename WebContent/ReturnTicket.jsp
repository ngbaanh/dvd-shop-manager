<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="Tran Thanh Sang">
<title>ReturnTicket</title>
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
				<h3 style="margin-left: 30px">Trả đĩa</h3>
			</div>
			<div class="col-md-1 col-md-offset-0" style="padding-top: 20px">
				<button type="button" class="btn btn-default">Đóng</button>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 ">
				<p style="margin: 20px 0px 10px 50px">Mã phiếu: 123</p>
			</div>
			<div class="col-md-4 ">
				<p style="margin: 20px 0px 10px 100px">Ngày lập phiếu:
					25/01/2016</p>
			</div>
		</div>
		<div class="row">
			<p style="margin: 0px 0px 10px 60px">Vui lòng chọn đĩa cần trả và
				cập nhật các thông tin cần thiết trước khi lưu lại kho</p>
			<div style="margin: 0px 30px 10px 50px">
				<table class="table table-striped table-bordered">
					<tr>
						<th class="info">STT</th>
						<th class="info">Mã đĩa</th>
						<th class="info">Tên đĩa</th>
						<th class="info">Đánh giá chất lượng</th>
						<th class="info">Vị trí lưu kho</th>
						<th class="info">Chọn trả</th>
					</tr>
					<tr>
						<td>1</td>
						<td>123</td>
						<td>Avatar 1</td>
						<td><div class="row">
								<div class="col-md-2 col-md-offset-0">
									<label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio1" value="option1">
										1* 
								</div>
								<div class="col-md-2 col-md-offset-0">
									</label> <label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio2" value="option2">
										2* 
								</div>
								<div class="col-md-2 col-md-offset-0">
									</label> <label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="inlineRadio3" value="option3"
										checked="checked"> 3*
									</label>
								</div>
							</div></td>
						<td>Tủ A tầng 3 ngăn 1</td>
						<td><label class="checkbox-inline"> <input
								type="checkbox" id="inlineCheckbox1" value="option1" checked="checked">
						</label></td>
					</tr>
					<tr>
						<td>2</td>
						<td>115</td>
						<td>Mario 3</td>
						<td><div class="row">
								<div class="col-md-2 col-md-offset-0">
									<label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions1" id="inlineRadio1" value="option1">
										1* 
								</div>
								<div class="col-md-2 col-md-offset-0">
									</label> <label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions1" id="inlineRadio2" value="option2">
										2* 
								</div>
								<div class="col-md-2 col-md-offset-0">
									</label> <label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions1" id="inlineRadio3" value="option3"
										checked="checked"> 3*
									</label>
								</div>
							</div></td>
						<td>Tủ B tầng 2 ngăn 2</td>
						<td><label class="checkbox-inline"> <input
								type="checkbox" id="inlineCheckbox1" value="option1">
						</label></td>
					</tr>

				</table>
			</div>
		</div>

	</div>
	<div class="row">
		<div class="col-md-1 col-md-offset-5" style="padding-top: 20px">
			<button type="button" class="btn btn-default">Lưu thông tin</button>
		</div>
	</div>
</body>
</body>
</html>