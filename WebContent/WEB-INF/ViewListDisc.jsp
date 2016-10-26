<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Disc"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	DiscSeries discSeries = (DiscSeries) request.getAttribute("DiscSeries");
	if (discSeries == null) {
		response.sendRedirect("ManageDiscSeriesList");
	} else {
		ArrayList<Disc> listDisc = discSeries.getListDisc();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem danh sách đĩa của một bộ đia</title>
<jsp:include page="_bootstrap.jsp" />
</head>
<jsp:include page="_header.jsp" />
<body>
<jsp:include page="_top.jsp" />
	<div class="container" style="margin-top: 20px">
		<div class="row">
			<div class="col-md-11">
				<ol class="breadcrumb">
					<li>Quản lý đĩa</li>
					<li><a href="ManageDiscSeriesList">Quản lý các bộ đĩa</a></li>
					<li class="active">Danh sách đĩa</li>
				</ol>
			</div>
			<div class="col-md-1">
				<a href="ManageDiscSeriesList" class="btn btn-default">Đóng</a>
			</div>
		</div>
		<div class="row">
			<h4 class="col-md-6 col-md-offset-1">Danh sách các đĩa</h4>
			<h5 class="col-md-6 col-md-offset-1">
				Bộ đĩa: <strong><%=discSeries.getDiscSeriesName()%></strong> <small>(có
					<%=discSeries.getRemainingDisc()%>/<%=discSeries.getTotalDisc()%>
					đĩa có thể cho thuê)
				</small>
			</h5>
		</div>
		<br>
		<%
			if (listDisc.isEmpty()) {
					out.print("Danh sách rỗng không có đĩa nào!");
				} else {
		%>
		<script type="text/javascript">
			function setSource(Id, Name) {
				document.getElementById('ModalFrame').src=(Name==0?'UpdateDisc?DiscId='+Id:'AddNewDisc?DiscSeriesId='+Id);
				document.getElementById('ModalName').innerHTML = (Name==0?'Sửa chi tiết thông tin đĩa':'Thêm đĩa mới');
			}
			function confirmAct(Name) {
				return confirm('Bạn chắc chắn muốn đĩa có mã số: '+Name+"?");
			}
		</script>
		<table class="table table-striped">
			<tr class="active">
				<th>STT</th>
				<th>Mã đĩa</th>
				<th>Chất lượng</th>
				<th>Tình trạng</th>
				<th>Giá/DVD/Tuần</th>
				<th>Thao tác</th>
			</tr>
			<%
				int i = 1;
						for (Disc disc : listDisc) {
			%>
			<tr>
				<td><%=i++%></td>
				<td><%=disc.getDiscId()%></td>
				<td><%=disc.getQualityId()%>*</td>
				<td><%=disc.isAvailable() ? "Sẵn sàng" : "Đang được thuê"%></td>
				<td><%=disc.getPrice()%> (VNĐ)</td>
				<td><a data-toggle="modal" data-target="#DiscModal"
					onClick="setSource(<%=disc.getDiscId()%>,0)">Sửa</a> <a
					href="RemoveDisc?DiscId=<%=disc.getDiscId()%>"
					onClick="return confirmAct('<%=disc.getDiscId()%>')">Xóa</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			}
		%>
		<div class="row">
			<div class="col-md-3 col-md-offset-1">
				<a class="btn btn-primary btn-block <%=(Const.MAX_ITEM <= discSeries.getTotalDisc())?"disabled":"" %>" data-toggle="modal"
					data-target="#DiscModal"
					onClick="setSource(<%=discSeries.getDiscSeriesId()%>,1)">Thêm
					đĩa mới vào bộ</a>
			</div>
		</div>

		<!-- -----------Disc Modal------------ -->
		<div id="DiscModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg"
				style="margin-top: 50px; width: 750px; height: 400px;">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<div class="col-xs-10">
							<h4 class="modal-title">
								<strong id="ModalName">Sửa chi tiết thông tin đĩa</strong>
							</h4>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-sm btn-block btn-default"
								data-dismiss="modal" onFocus="location.reload();">Đóng</button>
						</div>

					</div>
					<div class="modal-body">
						<iframe id="ModalFrame" src=""
							style="border: none; width: 100%; height: 400px;"></iframe>
					</div>
				</div>

			</div>
		</div>
		<!-- -------------------------------------- -->
	</div>
</body>
</html>
<%
	}
%>