<%@page import="model.bo.DiscBO"%>
<%@page import="model.bean.Disc"%>
<%@page import="business.session.PendingDisc"%>
<%@page import="model.bean.Category"%>
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

<style>
fieldset.list_choice {
	border: 1px groove #eee;
	padding: 0 10px 10px 10px;
	margin: auto;
	
}

legend.list_choice {
	width: auto;
	padding: 0 10px;
	border-bottom: none;
	border-radius: 5px;
}

#panel_list_choice {
	box-shadow: 0 -2px 2px 0 rgba(0, 0, 0, 0.2), 0 6px 5px 0
		rgba(0, 0, 0, 0.3);
	padding: 10px;
	margin: 0 0 20px 0;
	border-radius: 5px;
}
</style>

<script type="text/javascript">
function filter_by_type(picked_type) {
	document.getElementById("input_picked_type").value = picked_type.value;
	document.getElementById("form_picked_type").submit();
}

function changeSourceOfIframe(discSeriesId) {
	document.getElementById("iframe_modal")
	.setAttribute("src", "/SE23/ViewDiscSeriesDetail?discSeriesId=" + discSeriesId);
}

function changeRentingWeeks(discId, pickedRentingWeeks) {
	document.getElementById("input_discId_of_change_renting_weeks").value = discId;
	document.getElementById("input_value_of_change_renting_weeks").value = pickedRentingWeeks.value;
	document.getElementById("form_change_renting_weeks").submit();
}
</script>

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
				<form action="/SE23/ViewDiscSeriesList" method="post">
					<div class="input-group">
						<%
						String searchQuery = request.getAttribute("searchQuery").toString();
						%>
						<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
						<input type="text" name="searchQuery" value="<%=searchQuery %>" class="form-control" placeholder="tìm kiếm">
					</div>
				</form>
			</div>
			<div class="col-md-3 col-md-offset-1">
				<div class="form-group">
					<form id="form_picked_type" action="/SE23/ViewDiscSeriesList" method="post">
						<input id="input_picked_type" name="cateId" type="text" class="hidden">
					</form>
					<select name="PickedType" onChange="filter_by_type(this)" class="form-control">
						<option value="0">Chọn thể loại</option>
						<%
						//int cateId = (int) request.getAttribute("cateId");
						int cateId = Integer.parseInt(request.getAttribute("cateId").toString());
						ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("listCategories");
						for (int i = 0; i < listCategories.size(); i++) {
							Category category = listCategories.get(i);
							if (cateId == category.getCategoryId()) {
								%>
								<option value="<%=category.getCategoryId()%>" selected>
									<%=category.getCategoryName()%>
								</option>
								<%
							} else {
								%>
								<option value="<%=category.getCategoryId()%>">
									<%=category.getCategoryName()%>
								</option>
								<%
							}
							
						}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-2">
					<label class="control-label pull-right" for="pageDropdown">Trang:</label>
				</div>
				<div class="col-md-2">
					<div class="dropdown" id="pageDropdown">
						<%
						int currentPage = Integer.parseInt(request.getAttribute("destPage").toString());
						int maxPage = Integer.parseInt(request.getAttribute("maxPage").toString());
						%>
				  		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">
				  			<%=currentPage %>/<%=maxPage %>
				  			<span class="caret"></span>
				  		</button>
				  		<ul class="dropdown-menu">
				  		<%
						for (int i = 0; i < maxPage; i++) {
							%>
							<li>
								<form action="/SE23/ViewDiscSeriesList" method="post">
									<input name="searchQuery" value="<%=searchQuery%>" class="hidden">
									<input name="cateId" value="<%=cateId%>" class="hidden">
									<input name="destPage" value="<%=i + 1%>" class="hidden">
									<input type="submit" class="btn btn-link" value="<%=i + 1%>/<%=maxPage%>">
								</form>
							</li>
							<%
						}
						%>
				  		</ul>
					</div>
				</div>
			</div> <!-- /.form-group -->
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
				
				int startPosition = Const.ITEMS_PER_PAGE * (currentPage - 1);
				
				for (int i = 0; i < listDiscSeries.size(); i++) {
					DiscSeries discSeries = listDiscSeries.get(i);
					%>
					<tr>
						<td><%=i + 1 + startPosition%></td>
						<td><%=discSeries.getDiscSeriesName()%></td>
						<td><%=discSeries.getCategory().getCategoryName()%></td>
						<td><%=discSeries.getRemainingDisc()%>/<%=discSeries.getTotalDisc()%></td>
						<!-- Trigger the modal with a link inside table -->
						<td><a onClick="changeSourceOfIframe(<%=discSeries.getDiscSeriesId()%>)" href="#" data-toggle="modal" data-target="#detail_target_disc_series">Xem</a></td>
					</tr>
					<%
				}
				%>
				</tbody>
			</table>

			<!-- Modal: List all disks of the target disk series -->
			<div id="detail_target_disc_series" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-body" style="height: 500px;">
							<iframe id="iframe_modal" src="/SE23/ViewDiscSeriesDetail" 
							style="width: 100%; height: 100%; border: none;">
							</iframe>
						</div>
					</div>

				</div>
			</div>
			<!-- End Modal -->
		</div>

		<div class="row" id="panel_list_choice">
			<fieldset class="list_choice">
				<legend class="list_choice">Danh sách chọn</legend>
				
				<form id="form_change_renting_weeks" action="/SE23/ChangeRentingWeeks" method="post" class="hidden">
					<input id="input_discId_of_change_renting_weeks" type="text" name="discId" value="">
					<input id="input_value_of_change_renting_weeks" type="text" name="rentingWeeks" value="">
				</form>
				
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
					<%
					int totalPrices = 0;
					if (session.getAttribute("listPendingDisc") == null) {
						ArrayList<PendingDisc> listPendingDiscs = new ArrayList<PendingDisc>();
						session.setAttribute("listPendingDiscs", listPendingDiscs);
					} else {
						ArrayList<PendingDisc> listPendingDiscs
							= (ArrayList<PendingDisc>) session.getAttribute("listPendingDisc");
						DiscBO discBO = new DiscBO();
						for (int i = 0; i < listPendingDiscs.size(); i++) {
							PendingDisc pendingDisc = listPendingDiscs.get(i);
							Disc disc = discBO.getDisc(pendingDisc.getDiscId());
							if (disc != null) {
								totalPrices += disc.getPrice() * pendingDisc.getRentingWeeks();
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=disc.getDiscId() %></td>
									<td><%=pendingDisc.getDiscSeriesName() %></td>
									<td><%=disc.getPrice() %></td>
									<td>
										<select class="form-control" onChange="changeRentingWeeks(<%=disc.getDiscId() %>, this)">
										<%
										for (int indexRentingWeeks = 1; indexRentingWeeks <= Const.MAX_RENTING_WEEKS; indexRentingWeeks++) {
											if (indexRentingWeeks == pendingDisc.getRentingWeeks()) {
												%>
												<option selected><%=indexRentingWeeks %></option>
												<%
											} else {
												%>
												<option><%=indexRentingWeeks %></option>
												<%
											}
										}
										%>
										</select>
									</td>
									<td><%=disc.getPrice() * pendingDisc.getRentingWeeks() %></td>
									<td>
										<form target="_parent" action="/SE23/DiscardDisc" method="post">
											<input type="text" name="discId" value="<%=disc.getDiscId() %>" class="hidden">
											<input type="submit" value="Xóa" class="btn btn-link">
										</form>
									</td>
								</tr>
								<%
							} else {
								%>
								<tr>
									<td><%=i+1 %></td>
									<td><%=pendingDisc.getDiscId() %></td>
									<td>Vừa bị xóa</td>
									<td>Vừa bị xóa</td>
									<td>Vừa bị xóa</td>
									<td>Vừa bị xóa</td>
									<td>
										<form target="_parent" action="/SE23/DiscardDisc" method="post">
											<input type="text" name="discId" value="<%=disc.getDiscId() %>" class="hidden">
											<input type="submit" value="Xóa" class="btn btn-link">
										</form>
									</td>
								</tr>
								<%
							}
						}
					}
					%>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td>Tổng</td>
							<td><%=totalPrices %></td>
							<td></td>
						</tr>
					</tbody>
				</table>

				<div class="row-fluid text-center">
					<a href="/SE23/BuildTicket" class="btn btn-success <%=totalPrices > 0 ? "" : "disabled" %>">
						Đặt thuê
					</a>
				</div>
			</fieldset>
		</div>
	</div>
</body>
</html>