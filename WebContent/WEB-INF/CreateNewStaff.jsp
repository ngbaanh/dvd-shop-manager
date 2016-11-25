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
	$(document).ready(function(){
		
		$("#staffId").change(function(){
			$("#staffIdMsg").addClass("hidden");
		});
		$("#staffName").change(function(){
			$("#staffNameMsg").addClass("hidden");
		});
		$("#password").change(function(){
			$("#passwordMsg").addClass("hidden");
		});
		$("#password2").change(function(){
			$("#pass").addClass("hidden");
		});
		$("#staffPhone").change(function(){
			$("#phone").addClass("hidden");
		});
		$("#staffAddress").change(function(){
			$("#staffAddressMsg").addClass("hidden");
		});
		$("#createstaffbtn").click(function(){
			var staffId = $("#staffId").val().trim();
			var staffName = $("#staffName").val().trim();
			var password = $("#password").val().trim();
			var password2 = $("#password2").val();
			var staffPhone = $("#staffPhone").val();
			var staffAddress = $("#staffAddress").val().trim();
			if(staffId != "" && password != "" && staffName != "" &&  staffAddress != "" && staffPhone != ""){
				if(staffId.length > <%=Const.MAXLENGTH_STAFFID%>) $("#staffIdMsg").removeClass("hidden");
				else if(password.length> <%=Const.MAXLENGTH_PASSWORD%>) $("#passwordMsg").removeClass("hidden");
				else if(password.localeCompare(password2) !=0) $("#pass").removeClass("hidden");
				else if(staffName.length > <%=Const.MAXLENGTH_NAME%>) $("#staffNameMsg").removeClass("hidden");
				else if(isNaN(staffPhone) || staffPhone < 1 || staffPhone > 99999999999) $("#phone").removeClass("hidden");
				else if(staffPhone.length<10) $("#phone").removeClass("hidden");
				else if(staffPhone.length>11) $("#phone").removeClass("hidden");
				else if(staffAddress.length > <%=Const.MAXLENGTH_ADDRESS%>) $("#staffAddressMsg").removeClass("hidden");
				else $("#addstaffform").submit();
			}
			else {
				alert("Dữ liệu nhập vào rỗng, xin nhập lại!");
			}
		});
	});

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
		<form class="form-horizontal" action="CreateNewStaff" method="post" id="addstaffform">
			<div class="form-group">
				<label class="col-md-2" for="staffId">Tài khoản <span style="color: red">*</span></label>
				<div class="col-md-6"><input type="text" class="form-control" name="staffId" id="staffId" required/></div>
			</div>
			<div class="row hidden" id="staffIdMsg"><p class="col-md-offset-2 text-danger">Tên tài khoản quá dài!</p></div>
			
			<div class="form-group">
				<label class="col-md-2" for="password">Mật khẩu  <span style="color: red">*</span></label>
				<div class="col-md-6"><input type="password" class="form-control" name="password" id="password" required/></div>
			</div>
			<div class="row hidden" id="passwordMsg"><p class="col-md-offset-2 text-danger">Mật khẩu quá dài!</p></div>
	
			<div class="form-group">
				<label class="col-md-2" for="password">Nhập lại mật khẩu<span style="color: red">*</span></label>
				<div class="col-md-6"><input type = "password" class="form-control" name="password2" id="password2" required/></div>
			</div>
			<div class="row hidden" id="pass"><p class="col-md-offset-2 text-danger">Mật khẩu không khớp</p></div>
			
			<div class="form-group">
				<label class="col-md-2" for="password">Tên nhân viên<span style="color: red">*</span></label>
				<div class="col-md-6"><input type = "text" class="form-control"  name="staffName" id="staffName" required/></div>
			</div>
			<div class="row hidden" id="staffNameMsg"><p class="col-md-offset-2 text-danger">Tên nhân viên quá dài!</p></div>
			
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
		    <div class="row hidden" id="phone"><p class="col-md-offset-5 text-danger">Số điện thoại không hợp lệ!</p></div>
		    
			<div class="form-group">
				<label class="col-md-2" for="password">Địa chỉ<span style="color: red">*</span></label>
				<div class="col-md-6"><input type = "text" class="form-control" name="staffAddress" id="staffAddress" required/></div>
			</div>
			<div class="row hidden" id="staffAddressMsg"><p class="col-md-offset-2 text-danger">Địa chỉ quá dài!</p></div>
			
			<div class="form-group">
				<div class="col-md-offset-2 col-md-3"><input class="btn btn-primary btn-block" type="button" id="createstaffbtn" value = "Tạo mới"/></div>
			</div>
		</form>
		<!-- -------------------------------------- -->
	</div>
</body>
</html>
