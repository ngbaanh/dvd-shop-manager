<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@page import="model.bo.DiscBO"%>
<%@page import="model.bean.Disc"%>
<%@page import="business.session.PendingDisc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Const"%>

<%
@SuppressWarnings("unchecked") ArrayList<PendingDisc> listPendingDiscs
	= (ArrayList<PendingDisc>) session.getAttribute("listPendingDisc");
	if (listPendingDiscs==null || listPendingDiscs.isEmpty()) { // Chưa chọn đĩa nào thì ko thực hiện đặt phiếu
		response.sendRedirect("ViewDiscSeriesList");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đặt phiếu thuê</title>
<jsp:include page="_bootstrap.jsp" />
<jsp:include page="_header.jsp" />
</head>
<body>
	<jsp:include page="_top.jsp" />

	<div class="container-fluid" style="margin-top:20px;">
		<div class="row">
			<div class="col-sm-10">
			<ol class="breadcrumb">
				<li><a href="HomePage">Trang chủ</a></li>
				<li><a href="ViewDiscSeriesList">Xem danh sách</a></li>
				<li class="active">Lập phiếu thuê đĩa</li>
			</ol>
			</div>
			<div class="col-sm-2">
				<a href="ViewDiscSeriesList" class="btn btn-default btn-block">Đóng</a>
			</div>
		</div>
		
		<i>Thao tác sau sẽ thực hiện việc đặt phiếu đến hệ thống, phiếu chỉ có hiệu lực sau khi gửi yêu cầu thành công</i>
		
		<form name="BuildTicketForm" id="BuildTicketForm" class="form form-horizontal" action="BuildTicket" method="post" onsubmit="return validate()">
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
							<table class="table" id="DiscListTable">
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
								
								<%
								DiscBO discBO = new DiscBO();
								%>
								
								<tbody>
									<% 
										if (listPendingDiscs != null && !listPendingDiscs.isEmpty()) for(int j = 0; j < listPendingDiscs.size(); j++) { 
											PendingDisc disc = listPendingDiscs.get(j);
									%>
									<tr id="DiscItem">
										<td><%=j+1 %></td>
										<td><%=disc.getDiscId() %></td>
										<td><%=disc.getDiscSeriesName() %></td>
										<td id="price"><%=discBO.getDisc(disc.getDiscId()).getPrice() %></td>
										<td>
											<select name="RentingWeeks" id="RentingWeeks" class="form-control" readonly>
												<%
												for (int i = 1;  i <= Const.MAX_RENTING_WEEKS; i++) {
													boolean selected = false;
													if (i == disc.getRentingWeeks()) {
														selected = true;
													}
													out.print("<option " + (selected ? "selected" : "") + " value=\""+i+"\">"+i+"</option>");
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
										<td><span id="totalAmount"></span></td>
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
							<input type="hidden" name="do" >
							<input class="btn btn-success btn-block" type="submit" value="Đặt phiếu">
							<a href="InvalidateTicket" class="btn btn-danger btn-block" onclick="return confirm('Bạn chắc chắn muốn hủy phiếu tạm thời này và loại bỏ danh sách đĩa mà bạn đã chọn?')">Hủy phiếu</a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var total = 0;
			$("#DiscListTable tbody tr").each(function() { // dò từng dòng
				if ($(this).is($('#DiscListTable tbody').find('tr:last'))) { // gặp dòng cuối
					$('#totalAmount').html(total);
					return;
				} // ko gặp thì tính toán số liệu
				price = $(this).find('#price').html();
				rentingWeeks = $(this).find(':selected').text();
				var cash = parseInt(price * rentingWeeks);
				$(this).find('#amount').html(cash);
				total += cash;
			});
		});
		
		function validate() {
			var x1 = document.forms["BuildTicketForm"]["CustomerName"].value;
			var x2 = document.forms["BuildTicketForm"]["CustomerPhone"].value;
			var x3 = document.forms["BuildTicketForm"]["CustomerAddress"].value;
			var validated = true;
			if (x1 == null || x1.trim() == "" || x2 == null || x2.trim() == "" || x3 == null || x3.trim() == "") {
				validated = false;
			} else if (x1.length > <%=Const.MAXLENGTH_NAME%> 
				|| x2.length > <%=Const.MAXLENGTH_STAFFID%>
				|| x3.length > <%=Const.MAXLENGTH_DESCRIPTION%> ) {
				validated = false;
			} 
			if (validated) {
				return true;
			} else {
				alert("<%=Const.INVALID_FORM%>");
				return false;
			}
		}
		
	</script>
</body>
</html>