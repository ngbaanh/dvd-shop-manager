<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.bean.Disc"%>
<%@page import="util.Const"%>
<%
	Disc disc = (Disc) request.getAttribute("Disc");
	if (disc != null) {
		String discSeriesName = (String) request.getAttribute("DiscSeriesName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sửa thông tin đĩa</title>
<jsp:include page="_bootstrap.jsp" />
<script type="text/javascript">
	function validateForm() {
		var x1 = document.forms["updateForm"]["Place"].value;
		var validated = true;
		if (x1 == null || x1.trim() == "") {
			validated = false;
		} else if (x1.length > <%=Const.MAXLENGTH_NAME%>) {
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
		<form class="form-horizontal" action="UpdateDisc" method="get" name="updateForm" onSubmit="return validateForm()">
			<div class="form-group">
				<div class="col-xs-3">
					<label>Mã đĩa</label>
				</div>
				<div class="col-xs-2">
					<input type="text" class="form-control" name="DiscId"
						value="<%=disc.getDiscId()%>" readonly>
				</div>
				<div class="col-xs-2 col-xs-offset-1">
					<label>Tình trạng</label>
				</div>
				<div class="col-xs-2">
					<input type="radio"
						<%=disc.isAvailable() ? "" : "checked"%> disabled name="Available"> N/A
				</div>
				<div class="col-xs-2">
					<input type="radio"
						<%=disc.isAvailable() ? "checked" : ""%> disabled name="Available"> Sẵn
					sàng
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">
					<label>Thuộc bộ đĩa</label>
				</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" value="<%=discSeriesName%>"
						disabled>
				</div>
			</div>
			<div class="form-group" style="margin-bottom: 30px">
				<div class="col-xs-3">
					<label>Vị trí <span style="color: red">*</span></label>
				</div>
				<div class="col-xs-9">
					<input type="text" class="form-control" name="Place"
						value="<%=disc.getPlace()%>" required>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-3">
					<label>Chất lượng</label>
				</div>
				<div class="col-xs-2">
					<input type="radio" <%=disc.getQualityId()==1?"checked":"" %> name="QualityId" value="1"> 1*
				</div>
				<div class="col-xs-2">
					<input type="radio" <%=disc.getQualityId()==2?"checked":"" %> name="QualityId" value="2"> 2*
				</div>
				<div class="col-xs-2">
					<input type="radio" <%=disc.getQualityId()==3?"checked":"" %> name="QualityId" value="3"> 3*
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-4 col-xs-offset-4">
					<input type="hidden" name="doUpdate">
					<button type="submit" class="btn btn-success btn-block">Cập nhật</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
<%
	}
%>