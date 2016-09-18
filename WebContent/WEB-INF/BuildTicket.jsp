<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đặt phiếu thuê</title>
<jsp:include page="_bootstrap.jsp" />
<jsp:include page="_header.jsp" />
<script type="text/javascript">
	$(document).ready(function() {
		$('#RentingWeeks').change(function(){
			//alert($(this).parent().parent().html());
			var $tableRow = $(this).parent().parent();
			var $tableData = $tableRow.find("td");
			var $price = parseInt($tableData.eq(3).text());
			//alert($price);
			var $numberOfWeeks = parseInt($tableData.eq(4).("option:selected").val());
			alert($numberOfWeeks);
			//alert($price * $numberOfWeeks);
			$tableData.eq(5).html = "" + $price * $numberOfWeeks;
		});
	});
</script>
</head>
<body>
	<jsp:include page="_top.jsp" />

	<div class="container-fluid">
		<ol class="breadcrumb">
			<li><a href="HomePage">Trang chủ</a></li>
			<li><a href="ViewDiscSeriesList">Xem danh sách</a></li>
			<li class="active">Lập phiếu thuê đĩa</li>
		</ol>
		<i>Thao tác sau sẽ thực hiện việc đặt phiếu đến hệ thống, phiếu chỉ có hiệu lực sau khi gửi yêu cầu thành công</i>
		
		<form name="BuildTicketForm" id="BuildTicketForm" class="form form-horizontal" action="BuildTicket" method="post">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>Thông tin khách hàng</h4>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-3">
							<label for="CustomerName" class="control-label">Tên khách hàng <span style="color:#ff0000">*</span></label>
						</div>
						<div class="col-md-9">
							<input name="CustomerName" value="" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-3">
							<label for="CustomerPhone" class="control-label">Số điện thoại <span style="color:#ff0000">*</span></label>
						</div>
						<div class="col-md-9">
							<input name="CustomerPhone" value="" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-3">
							<label for="CustomerAddress" class="control-label">Địa chỉ <span style="color:#ff0000">*</span></label>
						</div>
						<div class="col-md-9">
							<input name="CustomerAddress" value="" class="form-control">
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-10">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Danh sách đĩa thuê</h4>
						</div>
						<div class="panel-body">
							<table class="table">
								<thead>
									<tr>
										<th>STT</th>
										<th>Mã đĩa</th>
										<th>Tên đĩa</th>
										<th>Giá/DVD/Tuần</th>
										<th>Số tuần thuê</th>
										<th>Thành tiền</th>
									</tr>
								</thead>
								<tbody>
									<% for(int j=1; j<=3; j++) { %>
									<tr id="DiscItem">
										<td><%=j %></td>
										<td>123</td>
										<td>123</td>
										<td id="price">30000</td>
										<td>
											<select name="RentingWeeks" id="RentingWeeks" class="form-control">
												<% for (int i=1;  i<=10; i++) {
													out.print("<option value=\""+i+"\">"+i+"</option>");
												}%>
											</select>
										</td>
										<td id="amount"></td>
									</tr>
									<%} %>
									<tr>
										<td colspan="5">
											<p class="text text-right"><strong>Tổng</strong></p>
										</td>
										<td>..</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				
				<div class="col-md-2">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>Thao tác</h4>
						</div>
						<div class="panel-body">
							<input class="btn btn-success btn-block" type="submit" value="Đặt phiếu">
							<a href="InvalidateTicket" class="btn btn-danger btn-block">Hủy phiếu</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>