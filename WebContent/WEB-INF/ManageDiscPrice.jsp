<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.bean.Category"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="util.Const"%>
<%
	@SuppressWarnings("unchecked")
	ArrayList<Category> listCategories = (ArrayList<Category>) request.getAttribute("AllCategories");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lí thể loại đĩa</title>
<jsp:include page="_bootstrap.jsp" />
<script type="text/javascript">
	function validateForm() {
		var x1 = document.forms["addForm"]["CategoryName"].value;
		var validated = true;
		if (x1 == null || x1.trim() == "" || x1.length > <%=Const.MAXLENGTH_NAME%>) {
			alert("<%=Const.INVALID_FORM%>
	");
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
			<form class="form form-horizontal" action="#">
				<table class="table table-striped" style="width:500px;">
					<tr>
						<th>Chất lượng</th>
						<th>Giá thuê (VNĐ)</th>
					</tr>
					<tr>
						<td>1*</td>
						<td><input type="number" name="" class="form-control" value="20000"></td>
					</tr>
					<tr>
						<td>2*</td>
						<td><input type="number" name="" class="form-control" value="25000"></td>
					</tr>
					<tr>
						<td>3*</td>
						<td><input type="number" name="" class="form-control" value="30000"></td>
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