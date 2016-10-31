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
<title>ReturnTicket</title>
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
				<h3 style="margin-left: 30px">Trả đĩa</h3>
			</div>
			<div class="col-md-1 col-md-offset-0" style="padding-top: 20px">
				<button type="button" class="btn btn-default">
					<a href="/SE23/ViewTicketDetail?ticketId=<%=ticket.getTicketId()%>">Đóng</a>
				</button>
			</div>
		</div>
		
		<form action="/SE23/ReturnTicket?ticketId=<%=ticket.getTicketId()%>" method="post">
			<div class="row">
				<div class="col-md-4 ">
					<p style="margin: 20px 0px 10px 50px">Mã phiếu: <%=ticket.getTicketId() %></p>
				</div>
				<div class="col-md-4 ">
					<p style="margin: 20px 0px 10px 100px">Ngày lập phiếu:
					<%=ticket.getStartTime().getDate()%>/<%=ticket.getStartTime().getMonth() + 1%>/<%=ticket.getStartTime().getYear() + 1900%></p>
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
						<%
						for(int i=0;i<listDiscOfTicket.size();i++){
							%>
							<tr>
								<td><%=i+1%></td>
								<td><%=listDisc.get(i).getDiscId()%></td>
								<td><%=listDiscSeriesOfTicket.get(i).getDiscSeriesName() %></td>
								<td>
									<div class="row">
									<%
									for (int j=1;j<=3;j++) {
										if (listDisc.get(i).getQualityId()== j) {
											%>
											<div class="col-md-2 col-md-offset-0">
												<label class="radio-inline"> <input type="radio"
													name="inlineRadioOptions<%=i%>" id="<%=j%>"
													value="option<%=j%>" checked="checked"> 
													<%=j%>*
											</div>
											<%
										} else {
											%>
											<div class="col-md-2 col-md-offset-0">
												<label class="radio-inline"> <input type="radio"
													name="inlineRadioOptions<%=i%>" id="<%=j%>"
													value="option<%=j%>">
													<%=j%>*
											</div>
											<%
										}
									}
									%>	
									</div>
								</td>
								<td><%=listDisc.get(i).getPlace() %></td>
								<td><label class="checkbox-inline"> <input
										type="checkbox" name="checkbox<%=i%>" id="inlineCheckbox<%=i%>" value="option<%=i%>">
								</label></td>
							</tr>
						<%} %>
	
					</table>
				</div>
			</div>

			<div class="row">
				<div class="col-md-1 col-md-offset-5" style="padding-top: 20px">
					<button type="submit" class="btn btn-primary">Lưu thông tin</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>