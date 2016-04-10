<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.Category"%>
<%@ page import="model.bean.DiscSeries"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.Const"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("AllCategories");
	DiscSeries failedDiscSeries = (DiscSeries) request.getAttribute("FailedDiscSeries");
	String discSeriesName = "", description = "", place = "";
	int totalDisc = 1, categoryId = 0;
	if (failedDiscSeries != null) {
		discSeriesName = failedDiscSeries.getDiscSeriesName();
		description = failedDiscSeries.getDescription();
		place = failedDiscSeries.getListDisc().get(0).getPlace();
		totalDisc = failedDiscSeries.getTotalDisc();
		categoryId = failedDiscSeries.getCategory().getCategoryId();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta author="LeMinh, NguyenBaAnh">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function validateForm() {
		var x1 = document.forms["createForm"]["DiscSeriesName"].value;
		var x2 = document.forms["createForm"]["Description"].value;
		var x3 = document.forms["createForm"]["TotalDisc"].value;
		var x4 = document.forms["createForm"]["Description"].value;
		var validated = true;
		if (x1 == null || x1 == "" || x2 == null || x2 == "") {
			validated = false;
		} else if (x1.length > <%=Const.MAXLENGTH_NAME%> 
			|| x2.length > <%=Const.MAXLENGTH_DESCRIPTION%>
			|| x4.length > <%=Const.MAXLENGTH_NAME%> ) {
			validated = false;
		} else if (x3 <= 0 || x3 > <%=Const.MAX_ITEM%>) {
			validated = false;
		}
		if (validated == false) {
			alert("Dữ liệu nhập vào không hợp lệ, xin nhập lại!");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid" style="margin-top: 15px;">
		<div class="row">
			<div class="col-md-10">
				<ol class="breadcrumb">
					<li><a href="ManageDiscSeriesList">Quản lý đĩa</a></li>
					<li><a href="ManageDiscSeriesList">Quản lí các bộ đĩa</a></li>
					<li class="active">Thêm mới một bộ đĩa</li>
				</ol>
				<p style="margin-top: -15px; margin-left: 50px;">
					<i>Thêm mới một bộ đĩa hoàn toàn mới vào hệ thống</i>
				</p>
			</div>
			<div class="col-md-2">
				<a class="btn btn-default btn-block" href="ManageDiscSeriesList">Đóng</a>
			</div>
		</div>
		<br>
		<form action="CreateNewDiscSeries" method="get" name="createForm"
			onSubmit="return validateForm()" class="form-horizontal">
			<div class="form-group">
				<label class="col-md-2 control-label">Tên bộ đĩa *</label>
				<div class="col-md-10">
					<input class="form-control" name="DiscSeriesName"
						value="<%=discSeriesName%>" required>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">Mô tả</label>
				<div class="col-md-10">
					<textarea class="form-control" name="Description" rows="2"><%=description%></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">Thể loại</label>
				<div class="col-md-4">
					<select class="form-control" name="CategoryId" required>
						<%
							if (listCategories.isEmpty()) {
								out.println("<option>Không thể chọn thể loại</option>");
							} else {
								for (Category cat : listCategories) {
									String selectedChoice = "";
									if (cat.getCategoryId() == categoryId) {
										selectedChoice = "selected";
									}
									out.println("<option " + selectedChoice + " value=\"" + cat.getCategoryId() + "\">"
											+ cat.getCategoryName() + "</option>");
								}
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">Số lượng đĩa *</label>
				<div class="col-md-4">
					<input type="number" class="form-control" name="TotalDisc"
						value="<%=totalDisc%>" required>
				</div>
				<div class="col-md-6">
					<label class="control-label"><i>Tất cả đĩa mới có chất
							lượng mặc định 3*</i></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 control-label">Vị trí *</label>
				<div class="col-md-10">
					<input class="form-control" name="Place" value="<%=place%>" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-3 col-md-offset-3">
					<input type="hidden" name="doCreate"> <input
						class="btn btn-primary btn-block" type="submit" value="Lưu">
				</div>
			</div>
		</form>
	</div>
</body>
</html>