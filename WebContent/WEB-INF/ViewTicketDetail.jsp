<%@page import="util.Const"%>
<%@page import="model.bean.Disc"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.RentalDisc"%>
<%@page import="model.bean.TicketStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Ticket"%>
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
<jsp:include page="_header.jsp" />
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
		<form action="/SE23/HandleTicket" method="post">
			<p>Thông tin phiếu.</p>
			<div
				style="border-style: solid; border-width: medium; padding: 20px 0px 20px 0px">
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						Mã phiếu<span style="color: red">*</span>
					</div>
					<div class="col-md-2">
						<input type="text" name="ticketId" id="disabledTextInput" class="form-control"
							value="<%=ticket.getTicketId()%>" readonly>
					</div>
					<div class="col-md-2 col-md-offset-2">
						Ngày lập phiếu<span style="color: red">*</span>
					</div>
					<div class="col-md-2">
						<input type="text" id="disabledTextInput" class="form-control"
							value="<%=ticket.getStartTime().getDate()%>/<%=ticket.getStartTime().getMonth() + 1%>/<%=ticket.getStartTime().getYear() + 1900%>"
							readonly>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						Tên khách hàng<span style="color: red">*</span>
					</div>
					<div class="col-md-8">
						<input type="text" name="customerName"class="form-control"
							value="<%=ticket.getCustomerName()%>" 
							<%if(ticket.getStatusId() != 0){ %>readonly<%} %>>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						Số điện thoại<span style="color: red">*</span>
					</div>
					<div class="col-md-8">
						<input type="text" name="customerPhone" class="form-control"
							value="<%=ticket.getCustomerPhone()%>"
							<%if(ticket.getStatusId() != 0){ %>readonly<%} %>>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						Địa chỉ<span style="color: red">*</span>
					</div>
					<div class="col-md-8">
						<input type="text" name="customerAddress" class="form-control"
							value="<%=ticket.getCustomerAddress()%>"
							<%if(ticket.getStatusId() != 0){ %>readonly<%} %>>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-md-2 col-md-offset-1">Tình trạng phiếu</div>
					<%
					for (TicketStatus tiketStatus : listTicketStatus) {
						if (ticket.getStatusId() == tiketStatus.getStatusId()) {
							%>
							<div class="col-md-2 col-md-offset-0">
								<label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="<%=tiketStatus.getStatusId()%>"
									value="option<%=tiketStatus.getStatusId()%>" checked="checked"
									disabled="disabled"> <%=tiketStatus.getStatusName()%>
							</div>
							<%
						} else {
							%>
							<div class="col-md-2 col-md-offset-0">
								<label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" id="<%=tiketStatus.getStatusId()%>"
									value="option<%=tiketStatus.getStatusId()%>" disabled="disabled">
									<%=tiketStatus.getStatusName()%>
							</div>
							<%
						}
					}
					%>
	
				</div>
				<br>
				<div class="row">
					<div class="col-md-2 col-md-offset-1">
						Tài sản đặt cọc<span style="color: red">*</span>
					</div>
					<div class="col-md-8">
						<input type="text" name="deposit" class="form-control"
							value="<%=ticket.getDeposit()%>"
							<%if(ticket.getStatusId() != 0){ %>readonly<%} %>>
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
	
							<script>
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
							ArrayList<RentalDisc> listRentalDisc = (ArrayList<RentalDisc>) request.getAttribute("listDiscOfTicket");
							ArrayList<DiscSeries> listDiscSeries = (ArrayList<DiscSeries>) request
									.getAttribute("listDiscSeriesOfTicket");
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
										<div class="btn-group" style="float: right">	
											<%
											int price = listDisc.get(i).getPrice() * listRentalDisc.get(i).getRentingWeeks();
											priceOfTicket += price;
											%>
											<select name="rentingWeek[<%=i%>]" onchange="loadPrice(this, <%=listDisc.get(i).getPrice()%>)" <%if (ticket.getStatusId() != 0) {%>disabled = "disabled"<%}%>>
											<%
											for (int j = 1; j <= Const.MAX_RENTING_WEEKS; j++) {
												%>
												<option
													value="<%=j%>"
													<%
													if (listRentalDisc.get(i).getRentingWeeks() == j) {
														%>
														selected="selected"
														<%
													}
													%>
												>
													<%=j%>
												</option>
												<%
											}
											%>
											</select>
										</div>
									</td>
									<td><%=price%></td>
								</tr>
								<%
							}
							%>
	
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Tổng</td>
								<td id="priceOfTicket"><%=priceOfTicket%></td>
							</tr>
						</table>
						<textarea name="ticketPrice" hidden><%=priceOfTicket %></textarea>
					</div>
				</div>
				<div class="col-md-3 col-md-offset-0">
	
					<p>Thao tác</p>
					<div
						style="border-style: solid; border-width: medium; padding: 20px 20px 20px 20px">
							
						<button type="submit" name="CapPhieu"
							<%if(ticket.getStatusId() == 0){ %>class="btn btn-primary btn-md btn-block"<%} 
							else{%> class="btn btn-default btn-md btn-block" disabled="disabled"<%} %>>Cấp phiếu
						</button>


						<button type="submit" name="GiaHan" 
							<%if(ticket.getStatusId() == 1 || ticket.getStatusId() == 3){ %>class="btn btn-primary btn-md btn-block"<%} 
							else{%> class="btn btn-default btn-md btn-block" disabled="disabled"<%} %>>Gia hạn
						</button>
						
						<button type="submit" name="TraDia"
							<%if(ticket.getStatusId() == 1){ %>class="btn btn-primary btn-md btn-block"<%} 
							else{%> class="btn btn-default btn-md btn-block" disabled="disabled"<%} %>>Trả đĩa
						</button>
						
						<button type="submit" name="HuyPhieu"
							class="btn btn-primary btn-md btn-block"">Hủy phiếu
						</button>
					</div>
				</div>
			</div>
		</form>
		
	</div>
</body>
</html>