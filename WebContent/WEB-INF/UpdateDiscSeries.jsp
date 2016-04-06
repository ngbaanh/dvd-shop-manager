<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Staff"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String staffName = "";
	Staff staff = new Staff();
	staff = (Staff) session.getAttribute("staff");
	if (staff == null) {
		// Nếu chưa login thì chuyển về giao diện chưa đăng nhập
		response.sendRedirect("default.jsp");
	} else {
		boolean isManager = staff.isManager() ? true : false;
	}
	DiscSeries discSeries = (DiscSeries) request.getAttribute("DiscSeries");
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
		document.form.DiscSeriesName.focus();
	};
	function validate() {
		var input = document.form.DiscSeriesName.value;
		var pattern = /^[\da-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]{1,100}$/
		if (pattern.test(input)) {
		} else {
			alert("Dữ liệu nhập vào không hợp lệ, xin nhập lại!");

		}
	}
</script>
</head>
<body>
	<div class="container">
		<form class="form-horizontal" name="form">
			<div class="form-group">
				<label class="col-sm-3 control-label">Tên bộ đĩa<span
					style="color: red">*</span></label>
				<div class="col-sm-9">
					<div class="col-sm-12">
						<input type="text" class="form-control" name="DiscSeriesName"
							value="<%=discSeries.getDiscSeriesName()%>">
					</div>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Mô tả</label>
				<div class="col-sm-9">
					<div class="col-sm-12">
						<textarea class="form-control" rows="3" name="Description"><%=discSeries.getDescription()%></textarea>

					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Thể loại</label>
				<div class="col-sm-9">
					<div class="col-sm-5">
						<select name="CategoryId">
							<%
								for (Category cat : listCategories) {
							%>
							<option
								<%=(discSeries.getCategory().getCategoryId() == cat.getCategoryId()) ? "selected" : ""%>
								value="<%=cat.getCategoryId()%>"><%=cat.getCategoryName()%></option>
							<%
								}
							%>
						</select>
					</div>

				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-2 col-sm-offset-6">
					<button type="submit" class="btn btn-default" name="updateButton"
						onclick="validate()">Cập nhật</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>