<%@page import="model.bean.Staff"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thêm mới một nhân viên</title>
<jsp:include page="_bootstrap.jsp" />
<script type="text/javascript">
	function validateForm(){
		//check valid for account
		var staffId = document.getElementById("staffId").value;
		if(staffId.length > 30){
			document.getElementById("staffIdMsg").innerHTML = "Tên tào khoản quá dài!";
	        return false;
	 	}
		
		//check valid for password
		var password = document.getElementById("password").value;
		var password2 = document.getElementById("password2").value;
		if (password.length>30) {
		 	document.getElementById("passwordMsg").innerHTML = "Mật khẩu quá dài!";
	        return false;
	 	}
		
		if(password.localeCompare(password2) !=0) {
			document.getElementById("pass").innerHTML = "Mật khẩu không khớp";
			return false; 
		}
		
		//check valid for phone number
		var staffPhone = document.getElementById("staffPhone").value;
		 if (isNaN(staffPhone) || staffPhone < 1 || staffPhone > 99999999999) {
			 	document.getElementById("phone").innerHTML = "Số điện thoại không hợp lệ!";
		        return false;
		 }
		 if (staffPhone.length<10) {
			 	document.getElementById("phone").innerHTML = "Số điện thoại không hợp lệ!";
		        return false;
		 }
		 if (staffPhone.length>11) {
			 	document.getElementById("phone").innerHTML = "Số điện thoại không hợp lệ!";
		        return false;
		 }
		 
		//check valid for name
		var staffName = document.getElementById("staffName").value;
		if(staffName.length > 30){
			document.getElementById("staffNameMsg").innerHTML = "Tên nhân viên quá dài!";
		    return false;
		}
		//check valid for address
		var staffAddress = document.getElementById("staffAddress").value;
		if(staffAddress.length > 100){
			document.getElementById("addressMsg").innerHTML = "Địa chỉ quá dài!";
		    return false;
		}
		 
		 return true;
	}

</script>
</head>
<jsp:include page="_header.jsp" />
<body>
<jsp:include page="_top.jsp" />
	<div class="container" style="margin-top: 20px">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="ViewStaffList">Quản lý nhân viên</a></li>
					<li class="active">Tạo mới một nhân viên</li>
				</ol>
			</div>
		</div>
		<br>
		<div class="row" style="margin-left: 20px">
			<h2>Thông tin nhân viên</h2>
		</div>
		<br>
		<form class="form-horizontal" action="CreateNewStaff" method="post" onsubmit="return validateForm()">
			<div class="form-group">
				<label class="col-md-2" for="staffId">Tài khoản <span style="color: red">*</span></label>
				<div class="col-md-6"><input type="text" class="form-control" name="staffId" id="staffId" required/></div>
			</div>
			<div class="row"><div class="col-md-offset-2 col-md-6"><p id="staffIdMsg" style="color:red"></p></div></div>
			
			<div class="form-group">
				<label class="col-md-2" for="password">Mật khẩu  <span style="color: red">*</span></label>
				<div class="col-md-6"><input type="password" class="form-control" name="password" id="password" required/></div>
			</div>
			<div class="row"><div class="col-md-offset-2 col-md-6"><p id="passwordMsg" style="color:red"></p></div></div>		
	
			<div class="form-group">
				<label class="col-md-2" for="password">Nhập lại mật khẩu<span style="color: red">*</span></label>
				<div class="col-md-6"><input type = "password" class="form-control" name="password2" id="password2" required/></div>
			</div>
			<div class="row"><div class="col-md-offset-2 col-md-6"><p id="pass" style="color:red"></p></div></div>
			
			<div class="form-group">
				<label class="col-md-2" for="password">Tên nhân viên<span style="color: red">*</span></label>
				<div class="col-md-6"><input type = "text" class="form-control"  name="staffName" id="staffName" required/></div>
			</div>
			<div class="row"><div class="col-md-offset-2 col-md-6"><p id="staffNameMsg" style="color:red"></p></div></div>
			
			<div class="form-group">
		      <label class="col-md-2" for="staffDOB">Ngày sinh <span style="color: red">*</span></label>
		      <div class="col-md-2">
		        <div class="input-group">
					<input type="date" class="form-control" name="staffDOB" id="staffDOB" required></input>
					<span class="input-group-addon glyphicon glyphicon-calendar"></span>
				</div>
		      </div>
		      <label class="col-md-offset-1 col-md-1" for="staffPhone">SDT<span style="color: red">*</span></label>
		      <div class="col-md-2">
		        <input type="number" class="form-control" name="staffPhone" id="staffPhone" required></input>
		      </div>
		    </div>
			<div class="row"><div class="col-md-offset-2 col-md-6"><p id="phone" style="color:red"></p></div></div>
			
			<div class="form-group">
				<label class="col-md-2" for="password">Địa chỉ<span style="color: red">*</span></label>
				<div class="col-md-6"><input type = "text" class="form-control" name="staffAddress" id="staffAddress" required/></div>
			</div>
			<div class="row"><div class="col-md-offset-2 col-md-6"><p id="addressMsg" style="color:red"></p></div>
			</div>
			
			<div class="form-group">
				<div class="col-md-offset-2 col-md-3"><input class="btn btn-primary btn-block" type = "submit" value = "Tạo mới"/></div>
			</div>
		</form>
		<!-- -------------------------------------- -->
	</div>
</body>
</html>
