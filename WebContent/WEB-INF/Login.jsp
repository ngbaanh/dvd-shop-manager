<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
<%
	String feedBack = (String) request.getParameter("FeedBack");
	if (feedBack == null) {
		feedBack = "HomePage";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
<script type="text/javascript">
	function validateForm() {
		var x1 = document.forms["loginForm"]["username"].value;
		var x2 = document.forms["loginForm"]["password"].value;
		var validated = true;
		if (x1 == null || x1.trim() == "" || x2 == null || x2.trim() == "") {
			validated = false;
		} else if (x1.length > <%=Const.MAXLENGTH_STAFFID%> 
			|| x2.length > <%=Const.MAXLENGTH_PASSWORD%>) {
			validated = false;
		}
		if (validated) {
			return true;
		} else {
			alert('<%=Const.INVALID_FORM%>');
			return false;
		}
	}
</script>
<jsp:include page="_bootstrap.jsp" />
</head>
<body>
	<div class="container" style="width: 400px; margin: 10px auto;">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4>Đăng nhập</h4>
			</div>
			<div class="panel-body">
				<form action="Login" target="_top" class="form-horizontal" method="post"
					name="loginForm" onSubmit="return validateForm()">
					<div class="form-group">
						<label class="col-xs-4 control-label">Tài khoản</label>
						<div class="col-xs-8">
							<input type="text" class="form-control" name="username">
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-4 control-label">Tài khoản</label>
						<div class="col-xs-8">
							<input type="password" class="form-control" name="password">
						</div>
					</div>
					<div class="form-group">
						<input type="hidden" name="doLogin"> <input type="hidden" name="FeedBack" value="<%=feedBack%>"> 
						<div class="col-xs-4 col-xs-offset-4">
							<input type="submit"
								class="btn btn-block btn-success" value="Đăng nhập">
						</div>
						<div class="col-xs-4">
							<input type="reset" class="btn btn-block btn-warning" value="Xóa">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>