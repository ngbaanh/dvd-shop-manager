<%@page import="model.bean.Staff"%>
<%@page import="java.util.ArrayList"%>
<%@page import="util.Const"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ArrayList<Staff> staffList = (ArrayList<Staff>)request.getAttribute("StaffList");
	String message = (String)request.getAttribute("message");
	String error = (String)request.getAttribute("error");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Xem danh sách nhân viên</title>
<jsp:include page="_bootstrap.jsp" />
<script type="text/javascript">
	function addModal(staffId, staffName, staffPhone, staffAddress, staffDOB){
		document.getElementById("staffId").value = staffId;
		document.getElementById("staffName").value = staffName;
		document.getElementById("staffPhone").value = staffPhone;
		document.getElementById("staffAddress").value = staffAddress;
		document.getElementById("staffDOB").value =  staffDOB;
	}
</script>
</head>
<jsp:include page="_header.jsp" />
<body>
<jsp:include page="_top.jsp" />
	<div class="container-fluid" style="margin-top: 20px">
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="ViewStaffList">Quản lý nhân viên</a></li>
					<li class="active">Danh sách nhân viên</li>
				</ol>
			</div>
		</div>
		<%
		if(message != null){
		%>
		<div class="row">
			<div class="alert alert-info">
				<strong>Alert </strong> <%=message %>
			</div>
		</div>
		<%
		}
		if(error != null){
		%>
		<div class="row">
			<div class="alert alert-danger">
				<strong>Error</strong> <%=error %>
			</div>
		</div>
		<%
		}
		if(staffList != null){
		%>
		<table class="table table-striped">
			<thead>
				<tr class="active">
					<th>STT</th>
					<th>Mã nhân viên</th>
					<th>Tên nhân viên</th>
					<th>Số điện thoại</th>
					<th>Thao tác</th>
				</tr>
			</thead>
			<%
			if(!staffList.isEmpty())
			for(int i=0; i<staffList.size(); i++){
			%>
			<tr>
				<td><%=i+1 %></td>
				<td><%=staffList.get(i).getStaffId() %></td>
				<td><%=staffList.get(i).getStaffName() %></td>
				<td><%=staffList.get(i).getStaffPhone() %></td>
				<td><a href="#" data-toggle="modal" data-target="#myModal"
					onclick="addModal('<%=staffList.get(i).getStaffId() %>', '<%=staffList.get(i).getStaffName() %>',
					 '<%=staffList.get(i).getStaffPhone() %>', '<%=staffList.get(i).getStaffAddress() %>', 
					 '<%=staffList.get(i).getDateOB()%>')">Sửa</a> 
					<a	href="RemoveStaff?staffId=<%= staffList.get(i).getStaffId()%>" onclick="return confirm('Are you sure you want to delete this staff?');">Xóa</a></td>
			</tr>
			<%
			}
			else{
				out.print("<tr>The data is empty</tr>");
			}
			%>
		</table>
		<%
		}
		else {
		%>
		<div class="row">
		<p class="bg-danger"><h2> The page has an errror!</h2></p>
		</div>
		<%
		}
		%>
		<!-- -------------------------------------- -->
		<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog" style = "width: 60%">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thông tin chi tiết nhân viên</h4>
				</div>
				<div class="modal-body">
					<form action="UpdateStaff" method="post" class="form form-horizontal">
					<div class="form-group">
						<div class="col-md-3">
							<label class="control-label">Tài khoản <span style="color: red">*</span></label>
						</div>
						<div class="col-md-9">
							<input type="text" class="form-control" name = "staffId" id="staffId" disabled value="abc"></input>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-md-3">
							<label class="control-label">Tên nhân viên <span style="color: red">*</span></label>
						</div>
						<div class="col-md-9">
							<input type="text" class="form-control" name = "staffName" id = "staffName" required></input>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-md-3">
							<label class="control-label">Ngày sinh <span style="color: red">*</span></label>
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<input type="date" class="form-control" name="staffDOB" id="staffDOB" ></input>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
						</div>
						<div class="col-md-1 col-md-offset-1">
							<label class="control-label">SĐT<span style="color: red">*</span></label>
						</div>
						<div class="col-md-3">
							<input type="text" class="form-control" name = "staffPhone" id = "staffPhone" ></input>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-md-3">
							<label class="control-label">Địa chỉ<span style="color: red">*</span></label>
						</div>
						<div class="col-md-9">
							<input type="text" class="form-control" name = "staffAddress" id = "staffAddress" ></input>
						</div>
					</div>
					<br>
					<div class="form-group">
						<div class="col-md-3 col-md-offset-4">
							<input type="submit" value="Lưu" class="btn btn-success btn-block">
						</div>
					</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	</div>
</body>
</html>
