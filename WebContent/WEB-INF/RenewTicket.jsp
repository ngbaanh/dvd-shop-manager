<%@page import="util.Const"%>
<%@page import="model.bean.Disc"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.RentalDisc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Ticket"%>
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
	
	<jsp:include page="_header.jsp" />
<body>
	<%
		Ticket ticket = (Ticket) request.getAttribute("ticket");
		ArrayList<RentalDisc> listDiscOfTicket = (ArrayList<RentalDisc>) request.getAttribute("listDiscOfTicket");
		ArrayList<DiscSeries> listDiscSeriesOfTicket = (ArrayList<DiscSeries>) request.getAttribute("listDiscSeriesOfTicket");
		ArrayList<Disc> listDisc = (ArrayList<Disc>) request.getAttribute("listDisc");
	%>
	<jsp:include page="_top.jsp" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-11 ">
				<h3>Gia hạn đĩa thuê</h3>
			</div>
			<div class="col-md-1 col-md-offset-0" style="padding-top: 20px">
				<button type="button" class="btn btn-default">
					<a href="/SE23/ViewTicketDetail?ticketId=<%=ticket.getTicketId()%>">Đóng</a>
				</button>
			</div>
		</div>
		<form action="/SE23/RenewTicket?ticketId=<%=ticket.getTicketId()%>" method="post">
			<div class="row">
				<div class="col-md-12 ">
					<p style="margin: 20px 0px 10px 100px">Mã phiếu: <%=ticket.getTicketId() %></p>
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
							<script>
								function loadPrice(elementSelectWeeks, pricePerDisc, oldPriceOfDisc) {
									var elementPriceOfDiscs = elementSelectWeeks.parentElement.parentElement.nextElementSibling;
									var elementPriceOfTicket = document.getElementById("priceOfTicket");
									var elementOldPriceOfTicket = document.getElementById("oldPriceOfTicket");
									var elementPayments = document.getElementById("payments");
									
									var priceOfDiscs = parseInt(elementPriceOfDiscs.innerHTML);
									var oldPriceOfTicket = parseInt(elementOldPriceOfTicket.innerHTML);
									var priceOfTicket = parseInt(elementPriceOfTicket.innerHTML);
									var selectWeeks = parseInt(elementSelectWeeks.value);
									
									var newPriceOfDiscs = selectWeeks * pricePerDisc + oldPriceOfDisc;						
									var newPriceOfTicket = priceOfTicket + newPriceOfDiscs - priceOfDiscs;
									
									elementPriceOfDiscs.innerHTML = newPriceOfDiscs;
									elementPriceOfTicket.innerHTML = newPriceOfTicket;
									elementPayments.innerHTML = newPriceOfTicket - oldPriceOfTicket;
								}
							</script>
							<%
							int priceOfTicket = 0;
							int oldPriceOfTicket = 0;
							for(int i=0;i<listDiscOfTicket.size();i++){
							%>
							<tr>
								<td><%=i+1%></td>
								<td><%=listDiscOfTicket.get(i).getDiscId()%></td>
								<td><%=listDiscSeriesOfTicket.get(i).getDiscSeriesName()%></td>
								<td><%=listDisc.get(i).getPrice()%></td>
								<td><%=listDiscOfTicket.get(i).getRentingWeeks()%> +
									<div class="btn-group" style="float: right">
										<%
										int oldPrice = listDisc.get(i).getPrice() * listDiscOfTicket.get(i).getRentingWeeks();
										int newPrice = listDisc.get(i).getPrice() * (listDiscOfTicket.get(i).getRentingWeeks()+1);
										if (listDiscOfTicket.get(i).isReturned()) {
											newPrice = oldPrice;
										}
										priceOfTicket += newPrice;
									    oldPriceOfTicket += oldPrice;
									    if (listDiscOfTicket.get(i).isReturned()) {
									    	%>
									    	Đĩa đã trả
											<select name="rentingWeek[<%=i%>]" class="hidden">
												<option value="0">0</option>
											</select>
											<%
									    } else {
											%>
											<select name="rentingWeek[<%=i%>]" onchange="loadPrice(this, <%=listDisc.get(i).getPrice()%>, <%=oldPrice%>)">
											<%
											for (int j = 1; j <= Const.MAX_RENTING_WEEKS; j++) {
												%>
												<option
													value="<%=j%>"
													<%
													if (j==Const.DEFAULT_RENTING_WEEKS) {
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
										<%
										}
										%>
									</div>
								</td>
								<td><%=newPrice %></td>
							</tr>
							<%} %>
							
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Tổng mới</td>
								<td id="priceOfTicket"><%=priceOfTicket %></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Tổng cũ</td>
								<td id="oldPriceOfTicket"><%=oldPriceOfTicket%></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td>Phát sinh</td>
								<td id="payments"><%=priceOfTicket-oldPriceOfTicket %></td>
							</tr>
						</table>
						<textarea name="ticketPrice" hidden><%=priceOfTicket %></textarea>
					</div>
				</div>
	
			</div>
			<div class="row">
				<div class="col-md-1 col-md-offset-5" style="padding-top: 20px">
					<button type="submit" name="CapNhatPhieu" class="btn btn-default">Cập nhật phiếu</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>