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

<!-- import datatable library -->
<jsp:include page="_dataTables.jsp"></jsp:include>

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
	margin: 20px -15px;
	border-radius: 5px;
}
</style>

<script type="text/javascript">
function loadDiscSeriesDetail(discSeriesId) {
  	$.ajax({
		url: "GetDiscSeriesDetailJSON",
		type: "POST",
		data: {
			'discSeriesId': discSeriesId
		},
		success: function(strScreenDiscSeriesDetail){
			viewDiscSeriesDetail(strScreenDiscSeriesDetail);
    	}
	});
}
function viewDiscSeriesDetail(strScreenDiscSeriesDetail) {
  	var screenDiscSeriesDetail = JSON.parse(strScreenDiscSeriesDetail);
  	var discSeriesName = screenDiscSeriesDetail.discSeriesName;
  	var remainingDisc = screenDiscSeriesDetail.remainingDisc;
  	$("#displayDiscSeriesName").html("Xem chi tiết bộ đĩa: " + discSeriesName);
  	$("#displayRemainingDisc").html("Số lượng đĩa có thể thuê: " + remainingDisc);
  	
  	var listDiscs = screenDiscSeriesDetail.listDiscs;
  	var bodyListDiscs = "";
  	for (index = 0; index < listDiscs.length; index++) {
  		var disc = listDiscs[index];
  		if (!disc.isAvailable) {
  			continue;
  		}
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
  		if (!disc.isAvailable) {
  			bodyListDiscs += "<td>Đã cho thuê</td>";
  		} else if (disc.isPicked) {
  			bodyListDiscs += "<td>Bạn đã chọn</td>";
  		} else {
  			bodyListDiscs += "<td><a href='#' onClick='chooseDisc(\"" + discSeriesName + "\"," + disc.discId + ")' data-dismiss='modal'>Chọn</a></td>";
  		}
  		bodyListDiscs += "</tr>";
  	}
  	$("#tableBodyDiscSeriesDetail").html(bodyListDiscs);
}

function chooseDisc(discSeriesName, discId) {
  	$.ajax({
		url: "ChooseDisc",
		type: "POST",
		data: {
			'discSeriesName': discSeriesName,
			'discId': discId
		},
		success: function(){
			loadPendingDisc();
    	}
	});
}

function discardDisc(discId) {
  	$.ajax({
		url: "DiscardDisc",
		type: "POST",
		data: {
			'discId': discId
		},
		success: function(){
			loadPendingDisc();
    	}
	});
}

function loadPendingDisc() {
  	$.ajax({
		url: "GetPendingDiscJSON",
		type: "POST",
		success: function(strScreenListPendingDisc){
			viewListPendingDisc(strScreenListPendingDisc);
    	}
	});
}

function viewListPendingDisc(strScreenListPendingDisc) {
	var screenListPendingDisc = JSON.parse(strScreenListPendingDisc);
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
  	$("#tableBodyListPendingDisc").html(bodyListPendingDisc);
  	if (totalPrices == 0) {
  		$("#buttonBuildTicket").attr("disabled", "");
  	} else {
  		$("#buttonBuildTicket").removeAttr("disabled");
  	}
}

function changeRentingWeeks(discId, pickedRentingWeeks) {
  	$.ajax({
		url: "ChangeRentingWeeks",
		type: "POST",
		data: {
			'discId': discId,
			'rentingWeeks': pickedRentingWeeks.value
		},
		success: function(){
			loadPendingDisc();
    	}
	});
}

$(function() {
	loadPendingDisc();
	
    var tableListDiscSeries = $('#tableListDiscSeries').DataTable({
    	"pageLength": 5,
        "stateSave": false,
        "pagingType": "full_numbers",
        "lengthMenu": [[5, 10, 25, 50, 100], [5, 10, 25, 50, 100]],
        "language": {
            "lengthMenu": "Hiển thị _MENU_ bộ đĩa mỗi trang",
            "zeroRecords": "Hiện không có bộ đĩa nào - Xin lỗi!",
            "info": "Đang hiển thị từ _START_ đến _END_ trong tổng số _TOTAL_ bộ đĩa",
            "infoEmpty": "Đang hiển thị từ 0 đến 0 trong tổng số 0 bộ đĩa",
            "loadingRecords": "Đang tải dữ liệu...",
            "processing": "Đang xử lý...",
            "search": "Tìm kiếm theo tên:",
            "paginate": {
                "first":      "Đầu",
                "last":       "Cuối",
                "previous":   "Trước",
                "next":       "Sau"
            },
        },
        "order": [[1, "asc"]],
        "processing": true,
        "serverSide": true,
        "ajax": {
        	url: "GetDiscSeriesListJSON",
        	type: 'POST',
        	data: function (d) {
        		d.categoryId = $("#pickedType").val();
        	}
        },
        "columns":
        	[
            {
            	"orderable": false,
            	"data": "STT"
            },
            {
            	"orderable": true,
            	"data": "discSeriesName"
            },
            {
            	"orderable": true,
            	"data": "categoryName"
            },
            {
            	"orderable": true,
            	"data": "numberDiscs",
            	"render": function(data) {
            		return data.remainingDisc + "/" + data.totalDisc;
            	}
            },
            {
            	"orderable": false,
            	"data": "discSeriesId",
            	"render": function(data) {
            		return "<a onClick='loadDiscSeriesDetail(" + data + ")' href='#' data-toggle='modal' data-target='#detail_target_disc_series'>Xem</a>";
            	}
            }
            ]
    });
    
    $("#pickedType").on("change", function() {
    	tableListDiscSeries.draw();
    });
});
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
			<div class="col-sm-3" style="margin: 0 0 5px -15px">
				<select id="pickedType" name="pickedType" class="form-control">
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
		<div class="row">
			<table id="tableListDiscSeries" class="table table-bordered table-striped" cellspacing="0" width="100%">
				<thead>
					<tr>
						<th style="width: 5%">STT</th>
						<th style="width: 45%">Tên bộ đĩa</th>
						<th style="width: 32%">Thể loại</th>
						<th style="width: 8%">SL</th>
						<th style="width: 10%">Thao tác</th>
					</tr>
				</thead>
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

		<br>
		<div class="row">
			<div class="panel panel-info">
				<div class="panel-heading">Danh sách chọn</div>
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
			</div>
			<div class="row text-center">
				<form action="BuildTicket" method="post">
					<input type="submit" id="buttonBuildTicket" value="Đặt thuê" class="btn btn-success">
				</form>
			</div>
		</div>
	</div>
</body>
</html>