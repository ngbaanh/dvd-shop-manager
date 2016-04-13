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
		<ol class="breadcrumb">
			<li><a href="ManageDiscSeriesList">Quản lí đĩa</a></li>
			<li>Quản lí thể loại đĩa</li>
		</ol>
		<div class="row">
			<%
				if (listCategories == null || listCategories.isEmpty()) {
			%>
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-danger">
					<div class="panel-heading">Hiện tại chưa có thể loại nào!</div>
				</div>
			</div>
			<%
				} else {
			%>
			<script type="text/javascript">
				function setSource(Id) {
					document.getElementById('ModalFrame').src='UpdateCategory?CategoryId='+Id;
				}
				function confirmAct(Cat){
					return confirm('Bạn chắc chắn muốn xóa thể loại: '+Cat+"?");
				}
			</script>
			<table class="table table-striped">
				<caption>Danh sách thể loại</caption>
				<tr>
					<th>Mã thể loại</th>
					<th>Tên thể loại</th>
					<th>Thao tác</th>
				</tr>
				<%
					for (Category cat : listCategories) {
				%>
				<tr>
					<td><%=cat.getCategoryId()%></td>
					<td><%=cat.getCategoryName()%></td>
					<!-- // TODO -->
					<td><span class="glyphicon glyphicon-edit"></span> <a
						data-toggle="modal" data-target="#UpdateCategory"
						onClick="setSource(<%=cat.getCategoryId()%>)">Sửa</a> <a
						href="RemoveCategory?CategoryId=<%=cat.getCategoryId()%>"
						onClick="return confirmAct('<%=cat.getCategoryName()%>')">Xóa</a>
						<span class="glyphicon glyphicon-remove"></span></td>
				</tr>
				<%
					}
				%>
			</table>
			<%
				}
			%>
			<div class="col-md-6 col-md-offset-3">

				<form action="AddNewCategory" method="get" name="addForm" onSubmit="return validateForm()">
					<div class="row">
						<div class="input-group input-group-md">
							<input type="text" class="form-control"
								placeholder="Nhập tên thể loại mới *" name="CategoryName" required>
							<div class="input-group-btn">
								<button type="submit" class="btn btn-primary">Thêm</button>
							</div>
							<!-- /btn-group -->
						</div>
						<!-- /input-group -->
					</div>
				</form>
			</div>
		</div>
		<!-- -----------UpdateCategory Modal------------ -->
		<div id="UpdateCategory" class="modal fade" role="dialog">
			<div class="modal-dialog modal-lg"
				style="width: 600px; height: 300px;">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<div class="col-xs-10">
							<h4 class="modal-title">Sửa thể loại</h4>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-sm btn-block btn-default"
								data-dismiss="modal" onFocus="location.reload();">Đóng</button>
						</div>

					</div>
					<div class="modal-body">
						<iframe id="ModalFrame" src=""
							style="border: none; width: 100%; height: 250px;"></iframe>
					</div>
				</div>

			</div>
		</div>

		<!-- -------------------------------------- -->
	</div>
</body>
</html>