<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	DiscSeries discSeries = (DiscSeries) request.getAttribute("DiscSeries");
	if (discSeries == null) {
		String message = "Lỗi;Không thể lấy thông tin của bộ đĩa;#; ";
		request.setAttribute("message", message);
		request.getRequestDispatcher("/Message").forward(request, response);
	} else {
		@SuppressWarnings("unchecked")
		ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("AllCategories");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript" language="JavaScript">
	window.onload = function() {
		document.updateForm.DiscSeriesName.focus();
	};
	function validate() {
		var input = document.form.DiscSeriesName.value;
		var pattern = /^[\da-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]{1,100}$/
		if (pattern.test(input)) {
			return true;
		} else {
			alert("Dữ liệu nhập vào không hợp lệ, xin nhập lại!");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" name="updateForm">
			<div class="form-group">
				<label class="col-xs-2 control-label">Tên bộ đĩa *</label>
				<div class="col-xs-10">
					<input type="text" class="form-control" name="DiscSeriesName"
						value="<%=discSeries.getDiscSeriesName()%>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 control-label">Mô tả</label>
				<div class="col-xs-10">
					<textarea class="form-control" rows="3" name="Description"><%=discSeries.getDescription()%></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 control-label">Thể loại</label>
				<div class="col-xs-10">
					<select name="CategoryId" class="form-control">
						<%
							for (Category cat : listCategories) {
						%>
						<option
							<%=(discSeries.getCategory().getCategoryId() == cat.getCategoryId()) ? "selected" : ""%>
							value="<%=cat.getCategoryId()%>">
							<%=cat.getCategoryName()%>
						</option>
						<%
							}
						%>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-4 col-xs-offset-4">
					<input type="hidden" name="doUpdate">
					<input type="hidden" name="DiscSeriesId" value="<%=discSeries.getDiscSeriesId()%>">
					<button type="submit" class="btn btn-primary btn-block"
						onClick="return validate()">Cập nhật</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
<%
	}
%>