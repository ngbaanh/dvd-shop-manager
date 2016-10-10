<%@page import="model.bean.Disc"%>
<%@page import="java.util.ArrayList"%>
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
	<div class="container-fluid">
		<%
		String discSeriesName = (String) request.getAttribute("discSeriesName");
		int remainingDisc = (int) request.getAttribute("remainingDisc");
		ArrayList<Disc> listDiscs = (ArrayList<Disc>) request.getAttribute("listDiscs");
		%>
		<div class="row">
			<div class="col-md-10">
				<h4 class="modal-title">Xem chi tiết bộ đĩa: <%=discSeriesName %></h4>
				<h5 class="modal-title">Số lượng đĩa có thể thuê: <%=remainingDisc %></h5>
			</div>
			<div class="col-md-2">
				<a target="_parent" href="/SE23/ViewDiscSeriesList" class="btn btn-default pull-right">Close</a>
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
			<%
			for (int i = 0; i < listDiscs.size(); i++) {
				Disc disc = listDiscs.get(i);
				%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=disc.getDiscId() %></td>
					<td><%=disc.getQualityId() %>*</td>
					<% if (disc.isAvailable()) { %>
					<td>Sẵn sàng</td>
					<%} else { %>
					<td>Đã cho thuê</td>
					<%} %>
					<td><%=disc.getPrice() %></td>
					<td><a href="#">Chọn</a></td>
				</tr>
				<%
			}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>