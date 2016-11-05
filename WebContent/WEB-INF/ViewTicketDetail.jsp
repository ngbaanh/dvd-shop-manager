<%@page import="util.Const"%>
<%@page import="model.bean.Disc"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.RentalDisc"%>
<%@page import="model.bean.TicketStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Ticket"%>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="Tran Thanh Sang">
<title>ViewTicketDetail</title>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_bootstrap.jsp" />
</head>
<body>
	<jsp:include page="_top.jsp" />
	<div class="container-fluid">
		<div style="margin-top: 15px">
			<ol class="breadcrumb">
				<li><a href="ViewTicketList">Quản lí phiếu thuê</a></li>
				<li><a href="ViewTicketList">Xem danh sách phiếu</a></li>
				<li class="active">Xem chi tiết phiếu</li>
			</ol>
		</div>
		<%
		Ticket ticket = (Ticket) request.getAttribute("ticket");
		ArrayList<TicketStatus> listTicketStatus = (ArrayList<TicketStatus>) request
				.getAttribute("listTicketStatus");
		%>
		<form action="/SE23/HandleTicket" method="post" class="form form-horizontal">
			<div class="panel panel-primary">
				<div class="panel-heading"><h4>Thông tin phiếu.</h4></div>
				<div class="panel-body">
					<div class="form-group">
						<div class="col-md-2">
							<label class="control-label">Mã phiếu<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-2">
							<input type="text" name="ticketId" id="disabledTextInput" class="form-control"
								value="<%=ticket.getTicketId()%>" readonly>
						</div>
						<div class="col-md-2 col-md-offset-3">
							<label class="control-label">Ngày lập phiếu<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-3">
							<input type="text" id="disabledTextInput" class="form-control"
								value="<%=new SimpleDateFormat("dd/MM/yyyy").format(ticket.getStartTime()) %>"
								readonly>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-2">
							<label class="control-label">Tên khách hàng<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-4">
							<input type="text" name="customerName"class="form-control"
								value="<%=ticket.getCustomerName()%>" <%=ticket.getStatusId() != 0 ? "readonly" : "" %>>
						</div>
						<div class="col-md-2 col-md-offset-1">
							<label class="control-label">Số điện thoại<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-3">
							<input type="text" name="customerPhone" class="form-control"
								value="<%=ticket.getCustomerPhone()%>" <%=ticket.getStatusId() != 0 ? "readonly" : "" %>>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-2">
							<label class="control-label">Địa chỉ<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-10">
							<input type="text" name="customerAddress" class="form-control"
								value="<%=ticket.getCustomerAddress()%>"
								<%if(ticket.getStatusId() != 0){ %>readonly<%} %>>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-2">
							<label class="control-label">Tình trạng phiếu<span class="text-danger">*</span></label>
						</div>
						<%
						for (TicketStatus tiketStatus : listTicketStatus) {
							if (ticket.getStatusId() == tiketStatus.getStatusId()) {
								%>
								<div class="col-md-2">
									<label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="<%=tiketStatus.getStatusId()%>"
										value="option<%=tiketStatus.getStatusId()%>" checked="checked"
										disabled="disabled"> <%=tiketStatus.getStatusName()%>
										</label>
								</div>
								<%
							} else {
								%>
								<div class="col-md-2">
									<label class="radio-inline"> <input type="radio"
										name="inlineRadioOptions" id="<%=tiketStatus.getStatusId()%>"
										value="option<%=tiketStatus.getStatusId()%>" disabled="disabled">
										<%=tiketStatus.getStatusName()%>
										</label>
								</div>
								<%
							}
						}
						%>
					</div>
					
					<div class="form-group">
						<div class="col-md-2">
							<label class="control-label">Tài sản đặt cọc<span class="text-danger">*</span></label>
						</div>
						<div class="col-md-10">
							<input type="text" name="deposit" class="form-control"
								value="<%=ticket.getDeposit()%>"
								<%if(ticket.getStatusId() != 0){ %>readonly<%} %>>
						</div>
					</div>
				</div> <!-- /.panel-body -->
			</div> <!-- /.panel -->
		
			<div class="row">
				<div class="col-md-9">
					<div class="panel panel-info">
						<div class="panel-heading"><h4>Danh sách đĩa thuê</h4></div>
						<table class="table table-striped table-bordered">
							<tr>
								<th>STT</th>
								<th>Mã đĩa</th>
								<th>Tên đĩa</th>
								<th>Giá/DVD/tuần</th>
								<th>Số tuần thuê</th>
								<th>Thành tiền(VNĐ)</th>
							</tr>
	
							<script type="text/javascript">
								function loadPrice(elementSelectWeeks, pricePerDisc) {
									var elementPriceOfDiscs = elementSelectWeeks.parentElement.parentElement.nextElementSibling;
									var elementPriceOfTicket = document.getElementById("priceOfTicket");
									var oldPriceOfDiscs = parseInt(elementPriceOfDiscs.innerHTML);
									var oldPriceOfTicket = parseInt(elementPriceOfTicket.innerHTML);
									var selectWeeks = parseInt(elementSelectWeeks.value);
									var newPriceOfDiscs = selectWeeks * pricePerDisc;
									var newPriceOfTicket = oldPriceOfTicket - oldPriceOfDiscs + newPriceOfDiscs;
									elementPriceOfDiscs.innerHTML = newPriceOfDiscs;
									elementPriceOfTicket.innerHTML = newPriceOfTicket;
								}
							</script>
							
							<%
							ArrayList<RentalDisc> listRentalDisc 
								= (ArrayList<RentalDisc>) request.getAttribute("listDiscOfTicket");
							ArrayList<DiscSeries> listDiscSeries 
								= (ArrayList<DiscSeries>) request.getAttribute("listDiscSeriesOfTicket");
							ArrayList<Disc> listDisc = (ArrayList<Disc>) request.getAttribute("listDisc");
							int priceOfTicket = 0;
							int firstPriceOfTicket = ticket.getTicketPrice();
							for (int i = 0; i < listRentalDisc.size(); i++) {
							%>
								<tr>
									<td><%=i + 1%></td>
									<td><%=listRentalDisc.get(i).getDiscId()%></td>
									<td><%=listDiscSeries.get(i).getDiscSeriesName()%></td>
									<td><%=listDisc.get(i).getPrice()%></td>
									<td>
										<div class="btn-group" class="text text-center">	
										<%
											int price = listDisc.get(i).getPrice() * listRentalDisc.get(i).getRentingWeeks();
											priceOfTicket += price;
										%>
											<select class="form-control" name="rentingWeek[<%=i%>]" onchange="loadPrice(this, <%=listDisc.get(i).getPrice()%>)" <%if (ticket.getStatusId() != 0) {%>disabled = "disabled"<%}%>>
										<%
											for (int j = 1; j <= Const.MAX_RENTING_WEEKS; j++) {
										%>
												<option value="<%=j%>" 
													<%=listRentalDisc.get(i).getRentingWeeks() == j ? "selected" : "" %>>
													<%=j%>
												</option>
										<% } %>
											</select>
										</div>
									</td>
									<td><%=price%></td>
								</tr>
							<%	}%>
	
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td><b>Tổng</b></td>
								<td id="priceOfTicket"><b><%=priceOfTicket%></b></td>
							</tr>
						</table>
						<textarea name="ticketPrice" hidden><%=priceOfTicket %></textarea>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-success">
					<div class="panel-heading"><h4>Thao tác</h4></div>
					<div class="panel-body">				
					<%
						String attribute[] = {"btn-primary", "btn-warning", "btn-info", "btn-danger"};
						byte statusId = ticket.getStatusId();
					%>			
						<button type="submit" name="CapPhieu"
							class="btn btn-block <%=statusId == 0 ? attribute[statusId] : attribute[statusId] + "\" disabled=\"disabled"%>">
							Cấp phiếu</button>
						
						<button type="submit" name="GiaHan" 
							class="btn btn-block <%=statusId == 1 || statusId == 3 ? attribute[statusId] : attribute[statusId] + "\" disabled=\"disabled"%>">
							Gia hạn</button>
						
						<button type="submit" name="TraDia"
							class="btn btn-block <%=statusId == 1 ? attribute[statusId] : attribute[statusId] + "\" disabled=\"disabled"%>">
							Trả đĩa</button>
						
						<button type="submit" name="HuyPhieu" class="btn btn-block <%=attribute[3]%>">Hủy phiếu</button>
					</div>
				</div>
			</div>
		</form>
		
	</div>
</body>
</html>