<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Category"%>
<%@page import="util.Const"%>
<%@page import="java.util.ArrayList"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("AllCategories");
	@SuppressWarnings("unchecked")
	ArrayList<DiscSeries> listDiscSeries = (ArrayList<DiscSeries>) request.getAttribute("ListDiscSeries");

	Category currentCategory = (Category) request.getAttribute("CurrentCategory");
	int currentPage = 1;
	int currentCaterogyId = (currentCategory == null) ? 0 : currentCategory.getCategoryId();
	String currentCaterogyName = (currentCategory == null)
			? "Chọn thể loại"
			: currentCategory.getCategoryName();
	String currentSearchQuery = "";
	currentPage = (int) request.getAttribute("CurrentPage");
	currentSearchQuery = (String) request.getAttribute("CurrentSearchQuery");
	int startIndex = (currentPage - 1) * Const.ITEMS_PER_PAGE + 1;
	int maxPage = (int) request.getAttribute("MaxPage");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="author" content="Tran Thanh Sang, Nguyen Ba Anh">
<title>Quản lí các bộ đĩa</title>
<jsp:include page="_bootstrap.jsp" />
</head>
<jsp:include page="_header.jsp" />
<body>
<jsp:include page="_top.jsp" />
	<div class="container-fluid">
		<div style="margin-top: 15px">
			<ol class="breadcrumb">
				<li><a href="#">Quản lí đĩa</a></li>
				<li class="active">Quản lí các bộ đĩa</li>
			</ol>
		</div>
		<script type="text/javascript">
			$("input#SearchQuery").live(
					"keyup",
					function(e) {
						if (e.which == 13) {
							var searchQuery = document
									.getElementById("SearchQuery").value;
							// kiểm tra valid searchQuery ở đây
							// TODO
							document.location = "#?SearchQuery=" + searchQuery;
						}
					});
		</script>
		<div class="row">
			<div class="col-md-4">
				<form>
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-btn">
								<button disabled class="btn btn-success" type="button">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span> <input type="text" class="form-control"
								placeholder="Tìm kiếm tên bộ đĩa" id="SearchQuery"
								name="SearchQuery" value="<%=currentSearchQuery%>">
						</div>
					</div>
				</form>
			</div>
			<%
				if ("".equals(currentSearchQuery)) {
			%>
			<div class="col-md-3 col-md-offset-5">
				<div class="dropdown">
					<button class="btn btn-default btn-block dropdown-toggle"
						type="button" data-toggle="dropdown"><%=currentCaterogyName%>
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-menu-right">
						<%
							if (!listCategories.isEmpty()) {
									String pageLink;
									out.print("<li><a href=\"ManageDiscSeriesList?CategoryId=0&page=" + currentPage
											+ "\"><i>[Không chọn]</i></a></li>");
									for (Category cat : listCategories) {
										pageLink = "ManageDiscSeriesList?CategoryId=" + cat.getCategoryId() + "&page=" + currentPage;
						%>
						<li><a href="<%=pageLink%>"><%=cat.getCategoryName()%></a></li>
						<%
							}
								}
						%>
					</ul>
				</div>
			</div>
			<%
				} else {
			%>
			<div class="col-md-2 col-md-offset-6">
				<a href="javascript:history.go(-1)"
					class="btn btn-default btn-block">Đóng</a>
			</div>
			<%
				}
			%>
		</div>
		<!-- /.row -->
		<%
			if (listDiscSeries.isEmpty()) {
		%>
		<br>
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h4 class="panel-title">Kết quả</h4>
					</div>
					<div class="panel-body"><%=("".equals(currentSearchQuery) ? Const.NOT_FOUND_ON_FILTER : Const.NOT_FOUND_ON_SEARCH)%></div>
				</div>
			</div>
		</div>
		<%
			} else {
		%>
		<script type="text/javascript">
			function setSource(Id) {
				document.getElementById('ModalFrame').src='UpdateDiscSeries?DiscSeriesId='+Id;
			}
			function confirmAct(Name) {
				return confirm('Bạn chắc chắn muốn xóa bộ đĩa: '+Name+"?");
			}
		</script>
		<table class="table table-bordered">
			<caption><%=("".equals(currentSearchQuery)
						? "Danh sách các bộ đĩa (" + Const.ITEMS_PER_PAGE + " bộ/trang)" : "Kết quả tìm kiếm")%></caption>
			<tr class="active">
				<th>STT</th>
				<th>Tên bộ đĩa</th>
				<th>Thể loại</th>
				<th>SL</th>
				<th>Thao tác</th>
			</tr>
			<%
				for (DiscSeries ds : listDiscSeries) {
			%>
			<tr>
				<td><%=(startIndex++)%></td>
				<td><%=ds.getDiscSeriesName()%></td>
				<td><%=ds.getCategory().getCategoryName()%></td>
				<td><%=ds.getRemainingDisc()%> / <%=ds.getTotalDisc()%></td>
				<td><a
					href="ViewListDisc?DiscSeriesId=<%=ds.getDiscSeriesId()%>">Xem</a>
					<a data-toggle="modal" data-target="#UpdateDiscSeries"
					onClick="setSource(<%=ds.getDiscSeriesId()%>)">Sửa</a> <a
					href="RemoveDiscSeries?DiscSeriesId=<%=ds.getDiscSeriesId()%>"
					onClick="return confirmAct('<%=ds.getDiscSeriesName()%>')">Xóa</a></td>
			</tr>
			<%
				}
			%>
		</table>
		<%
			} // end ----------- listDiscSeries
			if ("".equals(currentSearchQuery)) {
		%>
		<div class="row">
			<div class="col-md-4">
				<a type="button" href="CreateNewDiscSeries"
					class="btn btn-primary btn-block">Thêm mới bộ đĩa</a>
			</div>
			<div class="col-md-1 col-md-offset-5">
				<strong class="text text-muted">Trang </strong>
			</div>
			<div class="col-md-2">
				<div class="dropdown">
					<button class="btn btn-default btn-block dropdown-toggle "
						type="button" data-toggle="dropdown">
						<%=currentPage%>
						/
						<%=maxPage%>
						&nbsp; <span class="caret"></span>
					</button>
					<div class="dropdown-menu dropdown-menu-right"
						style="padding: 5px; width: 600px !important;">
						<%
							String pageLink;
								for (int i = 1; i <= maxPage; i++) {
									pageLink = "ManageDiscSeriesList?page=" + i
											+ (currentCaterogyId > 0 ? "&CategoryId=" + currentCaterogyId : "");
						%>
						<a
							class="btn <%=(i == currentPage) ? "btn-link disabled" : "btn-default"%> btn-xs"
							style="float: right; width: 40px; margin: 1px;"
							href="<%=pageLink%>"><%=i%></a>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<%
			}
		%>
		<!-- -----------UpdateDiscSeries Modal------------ -->
		<div id="UpdateDiscSeries" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg"
				style="width: 750px; height: 500px;">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<div class="col-md-11">
							<h4 class="modal-title">
								<strong>Sửa thông tin bộ đĩa</strong>
							</h4>
						</div>
						<div class="col-md-1">
							<button type="button" class="btn btn-sm btn-default"
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