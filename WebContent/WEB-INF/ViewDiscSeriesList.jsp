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
function filterBySearch() {
  	document.getElementById("pickedType").value = 0;
  	document.getElementById("pickedPage").value = 1;
  	loadDiscSeriesList();
}
function filterByType() {
  	document.getElementById("searchQuery").value = "";
  	document.getElementById("pickedPage").value = 1;
  	loadDiscSeriesList();
}
function filterByPager() {
  	var searchQuery = document.getElementById("searchQuery").value;
  	var categoryId = document.getElementById("pickedType").value;
  	var destPage = document.getElementById("pickedPage").value;
  	loadDiscSeriesList();
}
function loadDiscSeriesList() {
	var xhttp;
  	xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		viewDiscSeriesList(this);
    	}
  	};
  	xhttp.open("POST", '/SE23/GetDiscSeriesListAjax', true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	var searchQuery = document.getElementById("searchQuery").value;
  	var categoryId = document.getElementById("pickedType").value;
  	var destPage = document.getElementById("pickedPage").value;
  	var params = "searchQuery=" + searchQuery + "&categoryId=" + categoryId + "&destPage=" + destPage;
  	xhttp.send(params);
}
function viewDiscSeriesList(xhttp) {
  	var screenDiscSeries = JSON.parse(xhttp.responseText);
  	var startPosition = screenDiscSeries.startPosition;
  	var listDiscSeries = screenDiscSeries.listDiscSeries;
  	var bodyListDiscSeries = "";
  	for (index = 0; index < listDiscSeries.length; index++) {
  		var discSeries = listDiscSeries[index];
  		var STT = startPosition + index + 1;
  		bodyListDiscSeries += "<tr>";
  		bodyListDiscSeries += "<td>" + STT + "</td>"
  		bodyListDiscSeries += "<td>" + discSeries.discSeriesName + "</td>";
  		bodyListDiscSeries += "<td>" + discSeries.categoryName + "</td>";
  		bodyListDiscSeries += "<td>" + discSeries.remainingDisc + "/" + discSeries.totalDisc + "</td>";
  		bodyListDiscSeries += "<td><a onClick='loadDiscSeriesDetail(" + discSeries.discSeriesId + ")' href='#' data-toggle='modal' data-target='#detail_target_disc_series'>Xem</a></td>";
  		bodyListDiscSeries += "</tr>";
  	}
  	document.getElementById("tableBodyListDiscSeries").innerHTML = bodyListDiscSeries;
  	updatePager(screenDiscSeries.destPage, screenDiscSeries.maxPage);
}

function updatePager(destPage, maxPage) {
	var pages = "";
	for (index = 1; index <= maxPage; index++) {
		if (index == destPage) {
			pages += "<option value='" + index + "' selected>" + index + "/" + maxPage + "</option>"
		} else {
			pages += "<option value='" + index + "'>" + index + "/" + maxPage + "</option>"
		}
	}
	if (pages == "") {
		pages = "<option value='1'>0/0</option>";
	}
	document.getElementById("pickedPage").innerHTML = pages;
}


function loadDiscSeriesDetail(discSeriesId) {
	var xhttp;
  	xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		viewDiscSeriesDetail(this);
    	}
  	};
  	xhttp.open("POST", '/SE23/GetDiscSeriesDetailAjax', true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xhttp.send("discSeriesId=" + discSeriesId);
}
function viewDiscSeriesDetail(xhttp) {
  	var screenDiscSeriesDetail = JSON.parse(xhttp.responseText);
  	var discSeriesName = screenDiscSeriesDetail.discSeriesName;
  	var remainingDisc = screenDiscSeriesDetail.remainingDisc;
  	document.getElementById("displayDiscSeriesName").innerHTML = "Xem chi tiết bộ đĩa: " + discSeriesName;
  	document.getElementById("displayRemainingDisc").innerHTML = "Số lượng đĩa có thể thuê: " + remainingDisc;
  	
  	var listDiscs = screenDiscSeriesDetail.listDiscs;
  	var bodyListDiscs = "";
  	for (index = 0; index < listDiscs.length; index++) {
  		var disc = listDiscs[index];
  		var STT = index + 1;
  		bodyListDiscs += "<tr>";
  		bodyListDiscs += "<td>" + STT + "</td>"
  		bodyListDiscs += "<td>" + disc.discId + "</td>";
  		bodyListDiscs += "<td>" + disc.qualityId+ "</td>";
  		if (disc.isAvailable) {
  			bodyListDiscs += "<td>Sẵn sàng</td>";
  		} else {
  			bodyListDiscs += "<td>Đã cho thuê</td>";
  		}
  		bodyListDiscs += "<td>" + disc.price + "</td>";
  		if (disc.isPicked) {
  			bodyListDiscs += "<td>Đã chọn</td>";
  		} else {
  			bodyListDiscs += "<td><a href='#' onClick='chooseDisc(\"" + discSeriesName + "\"," + disc.discId + ")' data-dismiss='modal'>Chọn</a></td>";
  		}
  		bodyListDiscs += "</tr>";
  	}
  	document.getElementById("tableBodyDiscSeriesDetail").innerHTML = bodyListDiscs;
}

function chooseDisc(discSeriesName, discId) {
	var xhttp;
  	xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		viewListPendingDisc(this);
    	}
  	};
  	xhttp.open("POST", '/SE23/ChooseDisc', true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xhttp.send("discSeriesName=" + discSeriesName + "&discId=" + discId);
}

function discardDisc(discId) {
	var xhttp;
  	xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		viewListPendingDisc(this);
    	}
  	};
  	xhttp.open("POST", '/SE23/DiscardDisc', true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xhttp.send("discId=" + discId);
}

function loadPendingDisc() {
	var xhttp;
  	xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		viewListPendingDisc(this);
    	}
  	};
  	xhttp.open("POST", '/SE23/GetPendingDiscAjax', true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xhttp.send();
}

function viewListPendingDisc(xhttp) {
	var screenListPendingDisc = JSON.parse(xhttp.responseText);
	var listPendingDisc = screenListPendingDisc.listPendingDisc;
	var maxRentingWeeks = screenListPendingDisc.MAX_RENTING_WEEKS;
	var totalPrices = 0;
	
  	var bodyListPendingDisc = "";
  	for (index = 0; index < listPendingDisc.length; index++) {
  		var pendingDisc = listPendingDisc[index];
  		var STT = index + 1;
  		bodyListPendingDisc += "<tr>";
  		bodyListPendingDisc += "<td>" + STT + "</td>"
		bodyListPendingDisc += "<td>" + pendingDisc.discId + "</td>";
  		if (pendingDisc.deleted) {
  			bodyListPendingDisc += "<td>Vừa bị gỡ xuống</td>";
  			bodyListPendingDisc += "<td>Vừa bị gỡ xuống</td>";
  			bodyListPendingDisc += "<td>Vừa bị gỡ xuống</td>";
  			bodyListPendingDisc += "<td>Vừa bị gỡ xuống</td>";
  		} else {
  	  		bodyListPendingDisc += "<td>" + pendingDisc.discSeriesName + "</td>";
  	  		bodyListPendingDisc += "<td>" + pendingDisc.price + "</td>";
  	  		bodyListPendingDisc += "<td><select class='form-control input-sm' onChange='changeRentingWeeks(" + pendingDisc.discId + ", this)'>";
  			for (indexRentingWeeks = 1; indexRentingWeeks <= maxRentingWeeks; indexRentingWeeks++) {
  				if (indexRentingWeeks == pendingDisc.rentingWeeks) {
  					bodyListPendingDisc += "<option selected>" + indexRentingWeeks + "</option>";
  				} else {
  					bodyListPendingDisc += "<option>" + indexRentingWeeks + "</option>";
  				}
  			}
  			bodyListPendingDisc += "</select></td>";
  			var toMoney = pendingDisc.price * pendingDisc.rentingWeeks;
  			totalPrices += toMoney;
  			bodyListPendingDisc += "<td>" + toMoney + "</td>";
  		}
  		bodyListPendingDisc += "<td><a href='#' onClick='discardDisc(" + pendingDisc.discId + ")'>Xóa</a></td>";
  		bodyListPendingDisc += "</tr>";
  	}
  	bodyListPendingDisc += "<tr><td></td><td></td><td></td><td></td><td>Tổng</td><td>" + totalPrices + "</td><td></td></tr>";
  	document.getElementById("tableBodyListPendingDisc").innerHTML = bodyListPendingDisc;
  	if (totalPrices == 0) {
  		document.getElementById("buttonBuildTicket").setAttribute("disabled", "");
  	} else {
  		document.getElementById("buttonBuildTicket").removeAttribute("disabled");
  	}
}

function loadDocuments() {
	loadDiscSeriesList();
	loadPendingDisc();
}

function changeRentingWeeks(discId, pickedRentingWeeks) {
	var xhttp;
  	xhttp = new XMLHttpRequest();
  	xhttp.onreadystatechange = function() {
    	if (this.readyState == 4 && this.status == 200) {
    		loadPendingDisc();
    	}
  	};
  	xhttp.open("POST", '/SE23/ChangeRentingWeeks', true);
  	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  	xhttp.send("discId=" + discId + "&rentingWeeks=" + pickedRentingWeeks.value);
}
</script>

</head>
<jsp:include page="_header.jsp" />
<body onload="loadDocuments()">
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
						<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>
						<input id="searchQuery" type="text" onKeyUp="filterBySearch()" name="searchQuery" value="" class="form-control" placeholder="tìm kiếm">
					</div>
				</form>
			</div>
			<div class="col-md-3 col-md-offset-1">
				<div class="form-group">
					<select id="pickedType" name="pickedType" onChange="filterByType()" class="form-control">
						<option value="0">Chọn thể loại</option>
						<%
						ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("listCategories");
						for (int i = 0; i < listCategories.size(); i++) {
							Category category = listCategories.get(i);
							%>
							<option value="<%=category.getCategoryId()%>">
								<%=category.getCategoryName()%>
							</option>
							<%
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
					<div class="form-group">
						<select id="pickedPage" name="pickedPage" onChange="filterByPager()" class="form-control">
							<option value="1"></option>
					  	</select>
					</div>
				</div>
			</div> <!-- /.form-group -->
		</div>
		<br>
		<div class="row">
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th style="width: 4%">STT</th>
						<th style="width: 41%">Tên bộ đĩa</th>
						<th style="width: 41%">Thể loại</th>
						<th style="width: 6%">SL</th>
						<th style="width: 8%">Thao tác</th>
					</tr>
				</thead>
				<tbody id="tableBodyListDiscSeries">
				</tbody>
			</table>

			<!-- Modal: List all disks of the target disk series -->
			<div id="detail_target_disc_series" class="modal fade" role="dialog">
				<div class="modal-dialog modal-lg">

					<!-- Modal content-->
				    <div class="modal-content">
						<div class="modal-header">
					        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
					        <div class="row-fluid">
									<h4 id="displayDiscSeriesName" class="modal-title"></h4>
									<h5 id="displayRemainingDisc" class="modal-title"></h5>
							</div>
					    </div>
					    
					    <div class="modal-body">
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
								<tbody id="tableBodyDiscSeriesDetail">
								</tbody>
							</table>
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
							<th style="width: 5%">STT</th>
							<th style="width: 10%">Mã đĩa</th>
							<th style="width: 36%">Tên</th>
							<th style="width: 12%">Giá/DVD/Tuần</th>
							<th style="width: 12%">Số tuần thuê</th>
							<th style="width: 15%">Thành tiền (VNĐ)</th>
							<th style="width: 10%">Thao tác</th>
						</tr>
					</thead>
					<tbody id="tableBodyListPendingDisc">
					</tbody>
				</table>

				<div class="row-fluid text-center">
					<form action="/SE23/BuildTicket" method="post">
						<input type="submit" id="buttonBuildTicket" value="Đặt thuê" class="btn btn-success">
					</form>
				</div>
			</fieldset>
		</div>
	</div>
</body>
</html>