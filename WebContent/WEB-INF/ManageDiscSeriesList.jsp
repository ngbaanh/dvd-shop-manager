<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Disc"%>
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
	currentPage = Integer.parseInt(request.getAttribute("CurrentPage").toString());
	currentSearchQuery = (String) request.getAttribute("CurrentSearchQuery");
	int startIndex = (currentPage - 1) * Const.ITEMS_PER_PAGE + 1;
	int maxPage = Integer.parseInt(request.getAttribute("MaxPage").toString());
	// TODO cần sửa nhiều ở phần lọc thể loại và quay ngược số trang.
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#SearchForm').keydown(function(e) {
			var x = $("#searchQuery").val();
			var key = e.which;
			if (key == 13) {
				if (x == null || x.trim() == "" 
						|| x.length > <%=Const.MAXLENGTH_NAME%>
						|| x.trim().length < 3) {
					//$("#SearchForm").submit(function(e2){
					//    return false;
					//});
					e.preventDefault();
					// alert("<%=Const.INVALID_FORM%>");
				} else {
					$('#SearchForm').submit();
				}
			} else if (x == "") {
				
			}
		});
	});
</script>
<body>
	<jsp:include page="_top.jsp" />
	<div class="container-fluid">
		<div style="margin-top: 15px">
			<ol class="breadcrumb">
				<li><a href="#">Quản lí đĩa</a></li>
				<li class="active">Quản lí các bộ đĩa</li>
			</ol>
		</div>
		<div class="row">
			<div class="col-md-4">
				<form name="SearchForm" id="SearchForm" method="get" action="#">
					<div class="form-group">
						<div class="input-group">
							<span class="input-group-btn">
								<button disabled class="btn btn-success" type="button">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span> <input type="text" class="form-control" <%="".equals(currentSearchQuery)?"":"readonly" %>
								placeholder="Tìm kiếm tên bộ đĩa" id="searchQuery" autocomplete="off"
								name="SearchQuery" value="<%=currentSearchQuery%>" required>
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
					int availableCount = ds.getListDisc().size();
					for (Disc d : ds.getListDisc()) {
						if (!d.isAvailable()) {
							availableCount--;
						}
					}
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
			
			<!-- Điều hướng trang -->
			<%if (!listDiscSeries.isEmpty() && "".equals(currentSearchQuery)) { %>
			<div class="col-md-1 col-md-offset-5">
				<strong class="text text-muted">Trang </strong>
			</div>
			<div class="col-md-2">
				<div class="dropdown">
					<button class="btn btn-default btn-block dropdown-toggle "
						type="button" data-toggle="dropdown">
						<%=currentPage + "/" + maxPage%>&nbsp; <span class="caret"></span>
					</button>
					<div class="dropdown-menu dropdown-menu-right" style="width:200px !important;">
					 	<ul class="pager">
						  <li id="prevPage">&#8610;</li>
						  <li class="active">
						  	<input type="number" id="pageSelection" class="form-control" style="width:70px; display:inline;" 
						  		value="<%=currentPage%>" min="1" max="<%=maxPage%>" required>
						  </li>
						  <li id="nextPage">&#8611;</li>
						</ul>
					 </div>
					 <script type="text/javascript">
					 	$().ready(function(){
					 		var currentPage = <%=currentPage%>;
					 		var maxPage = <%=maxPage%>;
					 		$('#prevPage').attr("class", (currentPage == 1)?"disabled":"");
					 		$('#prevPage').html('<a href="' +( (currentPage == 1)?"#":"?page="+(currentPage-1) )+ '">&#8610;</a>');
					 		$('#nextPage').attr("class", (currentPage == maxPage)?"disabled":"");
					 		$('#nextPage').html('<a href="' +( (currentPage == maxPage)?"#":"?page="+(currentPage+1) )+ '">&#8611;</a>');
					 		$('#pageSelection').keypress(function(e) {
					 			var gotoPage = new Number($('#pageSelection').val());
					 		    if(e.which == 13 && gotoPage > 0 && gotoPage <= maxPage) {
					 		       window.location.replace("?page="+ gotoPage);
					 		    }
					 		});
					 	});
					 </script>
				</div>
			</div>
			<%} %><!-- / Điều hướng trang -->
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
						<div class="col-xs-10">
							<h4 class="modal-title">
								<strong>Sửa thông tin bộ đĩa</strong>
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