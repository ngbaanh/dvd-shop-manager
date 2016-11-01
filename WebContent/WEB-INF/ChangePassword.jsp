<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChangePassword</title>
<jsp:include page="_bootstrap.jsp" />
<jsp:include page="_header.jsp" />
</head>
<body>
	<jsp:include page="_top.jsp" />
	<div class="container-fluid">
		<div style="margin-top: 15px">
			<ol class="breadcrumb">
				<li><a href="#">Quản lí nhân viên</a></li>
				<li class="active">Đổi mật khẩu</li>
			</ol>
		</div>
		<div>
		  <%
	  if("1".equals(request.getParameter("msg"))){
	  %>
	  	<p style="color: red">Password can not blank!</p></br>
	  <%} %>
	    <%
	  if("0".equals(request.getParameter("msg"))){
	  %>
	  	<p style="color: red">Thực hiện thất bại!</p></br>
	  <%} %>
			<form class="form form-horizontal" action="/SE23/SubmitChangePassword" method="post">
				<div class="form-group">
					<div class="col-sm-3">
						<label class="control-label" for="password">
							Mật khẩu cũ:</label>
					</div>
					<div class="col-sm-3">
						<input type="password" name="password" id="password" value="" class="form-control">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-3">
						<label class="control-label" for="new_password">Mật khẩu
							mới:</label>
					</div>
					<div class="col-sm-3">
						<input type="password" id="new_password" name="new_password" value=""
							class="form-control" >
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-3">
						<label class="control-label" for="confirmed_password">Xác
							nhận mật khẩu mới:</label>
					</div>
					<div class="col-sm-3">
						<input type="password" id="confirmed_password" name="confirmed_password" value=""
							class="form-control" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-3 col-sm-offset-3">
						<button type="submit" class="btn btn-success btn-block">Thay đổi</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>