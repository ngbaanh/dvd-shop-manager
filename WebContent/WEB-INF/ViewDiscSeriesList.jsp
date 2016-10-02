<%@page import="model.bean.DiscSeries"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Const"%>
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
	<div class="container" style="margin-top: 20px">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="HomePage">Trang chủ</a></li>
				<li class="active">Xem danh sách</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-md-4">
				<div class="input-group">
					<span class="input-group-addon glyphicon glyphicon-search"></span>
					<input type="text" class="form-control" placeholder="tìm kiếm">
				</div>
			</div>
			<div class="col-md-3 col-md-offset-1">
				<div class="input-group">
					<select name="PickedType" class="form-control">
						<option>Chọn thể loại</option>
					</select>
				</div>
			</div>
			<div class="col-md-2">
				<label class="pull-right">Trang:</label>
			</div>
			<div class="col-md-2">
				<select class="form-control">
					<option>1/2</option>
				</select>
			</div>
		</div>
		<br>
		<div class="row">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>STT</th>
						<th>Tên bộ đĩa</th>
						<th>Thể loại</th>
						<th>SL</th>
						<th>Thao tác</th>
					</tr>
				</thead>
				<tbody>
				<%
				ArrayList<DiscSeries> listDiscSeries = (ArrayList<DiscSeries>) request.getAttribute("listDiscSeries");
				if (listDiscSeries != null) {
					for (int i = 0; i < listDiscSeries.size(); i++) {
						DiscSeries discSeries = listDiscSeries.get(i);
						%>
						<tr>
							<td><%=i+1 %></td>
							<td><%=discSeries.getDiscSeriesName() %></td>
							<td><%=discSeries.getCategory().getCategoryName() %></td>
							<td><%=discSeries.getRemainingDisc() %>/<%=discSeries.getTotalDisc() %></td>
							<!-- Trigger the modal with a link inside table -->
							<td><a href="#" data-toggle="modal" data-target="#bo_dia_02">Xem</a></td>
						</tr>
						<%
					}
				}
				%>
				</tbody>
			</table>

			<!-- Modal: List all disks of the diskset -->
			<div id="bo_dia_02" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-body">
							<div class="row">
								<div class="col-md-10">
									<h4 class="modal-title">Xem chi tiết bộ đĩa: Avatar 1</h4>
									<h5 class="modal-title">Số lượng đĩa có thể thuê: 2</h5>
								</div>
								<div class="col-md-2">
									<button type="button" class="btn btn-default pull-right"
										data-dismiss="modal">Close</button>
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
					</div>

				</div>
			</div>
			<!-- End Modal -->
		</div>

		<div class="row" id="panel_list_choice">
			<fieldset class="list_choice">
				<legend class="list_choice">Danh sách chọn</legend>

				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th>STT</th>
							<th>Mã đĩa</th>
							<th>Tên</th>
							<th>Giá/DVD/Tuần</th>
							<th>Số tuần thuê</th>
							<th>Thành tiền (VNĐ)</th>
							<th>Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>123</td>
							<td>Avatar 1</td>
							<td>30000</td>
							<td><select><option>2</option></select></td>
							<td>60000</td>
							<td><a href="#">Xóa</a></td>
						</tr>
						<tr>
							<td>2</td>
							<td>115</td>
							<td>Mario 3</td>
							<td>25000</td>
							<td><select><option>1</option></select></td>
							<td>25000</td>
							<td><a href="#">Xóa</a></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>Tổng</td>
							<td>85000</td>
							<td></td>
						</tr>
					</tbody>
				</table>

				<div class="row-fluid text-center">
					<button type="button" class="btn btn-default text-center">Đặt
						thuê</button>
				</div>
			</fieldset>
		</div>
		<style>
fieldset.list_choice {
	border: 1px groove #ddd;
	padding: 0 10px 10px 10px;
	margin: auto;
}

legend.list_choice {
	width: auto;
	padding: 0 10px;
	border-bottom: none;
}

#panel_list_choice {
	box-shadow: 0 -2px 2px 0 rgba(0, 0, 0, 0.2), 0 6px 5px 0
		rgba(0, 0, 0, 0.3);
	padding: 10px;
	margin: 0 0 20px 0;
}
</style>

	</div>
</body>
</html>