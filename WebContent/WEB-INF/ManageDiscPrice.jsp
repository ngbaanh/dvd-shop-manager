<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.Category"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.Const"%>
<%
	int value1 = Integer.parseInt(request.getAttribute("value1").toString());
	int value2 = Integer.parseInt(request.getAttribute("value2").toString());
	int value3 = Integer.parseInt(request.getAttribute("value3").toString());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lí thể loại đĩa</title>
<jsp:include page="_bootstrap.jsp" />
<script type="text/javascript">
	function validateForm() {
		var v1 = document.forms["PriceForm"]["value1"].value;
		var v2 = document.forms["PriceForm"]["value2"].value;
		var v3 = document.forms["PriceForm"]["value3"].value;
		var validated = true;
		if (v1 == null || v1.trim() == "" || v1 < 1000
			|| v2 == null || v2.trim() == "" || v2 < 1000
			|| v3 == null || v3.trim() == "" || v3 < 1000
			|| v1 > v2 || v2 > v3 || v1 > v3) {
			alert("<%=Const.INVALID_FORM%>");
			return false;
		}
		return true;
	}
</script>
</head>
<jsp:include page="_header.jsp" />
<body>
	<jsp:include page="_top.jsp" />
	<br>
	<div class="container">
		<h2>Quản lí giá thuê đĩa</h2>
		<i>Đặt giá dựa trên đánh giá chất lượng đĩa</i>
		<div class="row">
			<form class="form form-horizontal" action="ManageDiscPrice" 
				method="post" name="PriceForm" onSubmit="return validateForm()">
				<input type="hidden" name="action" value="do">
				<table class="table table-striped" style="width:500px;">
					<tr>
						<th>Chất lượng</th>
						<th>Giá thuê (VNĐ)</th>
					</tr>
					<tr>
						<td>1*</td>
						<td><input type="number" name="value1" class="form-control" value="<%=value1%>" required></td>
					</tr>
					<tr>
						<td>2*</td>
						<td><input type="number" name="value2" class="form-control" value="<%=value2%>" required></td>
					</tr>
					<tr>
						<td>3*</td>
						<td><input type="number" name="value3" class="form-control" value="<%=value3%>" required></td>
					</tr>
					<tr>
						<td></td><td><input type="submit" value="Lưu thông tin" class="btn btn-success btn-block"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>