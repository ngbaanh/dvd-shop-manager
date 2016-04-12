<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.DiscSeries"%>
<%@page import="model.bean.Category"%>
<%@page import="util.Const"%>
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
<title>Sửa thông tin bộ đĩa</title>
<jsp:include page="_bootstrap.jsp" />
<script type="text/javascript" language="JavaScript">
	window.onload = function() {
		document.updateForm.DiscSeriesName.focus();
	};
	function validateForm() {
		var x1 = document.forms["updateForm"]["DiscSeriesName"].value;
		var x2 = document.forms["updateForm"]["Description"].value;
		var validated = true;
		if (x1 == null || x1.trim() == "" || x2 == null || x2.trim() == "") {
			validated = false;
		} else if (x1.length > <%=Const.MAXLENGTH_NAME%> 
			|| x2.length > <%=Const.MAXLENGTH_DESCRIPTION%>) {
			validated = false;
		}
		if (validated) {
			return true;
		} else {
			alert("<%=Const.INVALID_FORM%>");
			return false;
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" name="updateForm"
			onSubmit="return validateForm()">
			<div class="form-group">
				<label class="col-xs-2 control-label">Tên bộ đĩa *</label>
				<div class="col-xs-10">
					<input type="text" class="form-control" name="DiscSeriesName"
						value="<%=discSeries.getDiscSeriesName()%>" required>
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
					<input type="hidden" name="doUpdate"> <input type="hidden"
						name="DiscSeriesId" value="<%=discSeries.getDiscSeriesId()%>">
					<button type="submit" class="btn btn-primary btn-block">Cập
						nhật</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
<%
	}
%>