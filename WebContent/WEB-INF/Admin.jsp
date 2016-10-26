<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="util.Const"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
<jsp:include page="_bootstrap.jsp" />
<style>
html {
	background: repeating-linear-gradient(-45deg, #222, #222 10px, #333 10px, #333 20px);
}
</style>
</head>
<body
	style="background-color: #fff; width: 800px; margin: 109px auto; border-radius: 10px;">
	<div class="container-fluid">
		<h3>
			<strong>Trang điều khiển hệ thống</strong> <small>[phục vụ
				kiểm thử - chỉ dành cho quản trị viên]</small>
		</h3>
		<hr>
		<form action="Admin" method="post" class="form-horizontal">
			<div class="form-group">
				<label class="col-xs-4 control-label">Số items / 1 trang</label>
				<div class="col-xs-2">
					<input class="form-control" type="number" name="items_per_page"
						value="<%=Const.ITEMS_PER_PAGE%>" required min="1" max="20">
				</div>
				<div class="col-xs-6">
					<i>(số bộ đĩa, số phiếu)</i>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label">Tổng số đĩa tối đa / 1
					bộ đĩa</label>
				<div class="col-xs-2">
					<input class="form-control" type="number" name="max_item"
						value="<%=Const.MAX_ITEM%>" min="1" max="100">
				</div>
				<div class="col-xs-6">
					<i>Giới hạn số đĩa mà 1 bộ đĩa có thể chứa, khi người dùng nhập
						quá hoặc thêm quá số lượng sẽ báo lỗi.</i>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label">Tài nguyên Boostrap và
					jQuery</label>
				<div class="col-xs-8 ">
					<input type="radio" <%=Const.USE_BOOTSTRAP_ONLINE ? "checked" : ""%>
						name="use_bootstrap_online" value="true"> <label>Sử
						dụng CDN</label> <i>(cần mạng internet)</i>
				</div>
				<div class="col-xs-8 col-xs-offset-4">
					<input type="radio" <%=Const.USE_BOOTSTRAP_ONLINE ? "" : "checked"%>
						name="use_bootstrap_online" value="false"> <label>Sử
						dụng nội bộ</label> <i>(khi thử trên localhost không có mạng internet)</i>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-4 control-label">Loading Screen</label>
				<div class="col-xs-8 ">
					<input type="radio" <%=Const.ENABLE_LOADING_SCREEN ? "checked" : ""%>
						name="enable_loading_screen" value="true"> <label>Có</label> <i>(Xuất hiện vòng tròn khi tải trang)</i>
				</div>
				<div class="col-xs-8 col-xs-offset-4">
					<input type="radio" <%=Const.ENABLE_LOADING_SCREEN ? "" : "checked"%>
						name="enable_loading_screen" value="false"> <label>Không</label> <i>(Bỏ qua và vào trực tiếp trang)</i>
				</div>
			</div>
			<div class="form-group">
				<input type="hidden" name="doSave">
				<div class="col-xs-2 col-xs-offset-2">
					<input class="btn btn-primary btn-block" type="submit" value="Lưu">
				</div>
				<div class="col-xs-3 col-xs-offset-3">
					<a class="btn btn-primary btn-danger btn-block" href="index.jsp">Quay
						về trang chủ</a>
				</div>
			</div>
		</form>
		<hr>
		<p class="text text-center text-muted">&copy; 2016 - Nguyễn Bá Anh - SE23</p>
	</div>
</body>
</html>
