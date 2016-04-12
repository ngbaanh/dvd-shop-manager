<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
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
<style>
fieldset {
	border-radius: 3px;
}

input {
	border-radius: 3px;
	display: block;
	height: 30px;
	line-height: 1.5;
	width: 250px;
}

input[type="submit"], input[type="reset"] {
	border-radius: 3px;
	height: 30px;
	line-height: 1.5;
	width: 120px;
	float: left;
	margin-right: 5px;
	color: #333;
	background-color: #fff;
	border-color: #ccc;
}

input[type="submit"]:hover, input[type="reset"]:hover {
	color: #333;
	background-color: #e6e6e6;
	border-color: #adadad;
}

label {
	font-weight: bold;
	width: 150px;
}

table, td, tr {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div
		style="width: 400px; height: 200px; margin: 100px auto; font-family: Arial; font-size: 20px;">
		<form action="Login" method="post" name="loginForm" onSubmit="return validateForm()">
			<fieldset>
				<legend>Đăng nhập</legend>
				<table>
					<tr>
						<td><label>Tài khoản</label></td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td><label>Mật khẩu</label></td>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="hidden" name="loginAct" value="yes">
							<input type="submit" value="Đăng nhập"> <input
							type="reset" value="Xóa"></td>
					</tr>
				</table>
			</fieldset>
		</form>
	</div>
</body>
</html>