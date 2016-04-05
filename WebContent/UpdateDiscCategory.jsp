<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<!-- FORM -->
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-sm-3 control-label">Tên bộ đĩa<span
									style="color: red">*</span></label>
								<div class="col-sm-9">
									<div class="col-sm-12">
										<input type="text" class="form-control" name="tenbodia">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">Mô tả <span
									style="color: red">*</span></label>
								<div class="col-sm-9">
									<div class="col-sm-12">
										<textarea class="form-control" rows="3" name="mota"></textarea>

									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">Thể loại</label>
								<div class="col-sm-9">
									<div class="col-sm-5">
										<select class="form-control" name="theloai">
											<option>Phim điện ảnh</option>
											<option>Nhạc việt</option>
											<option>Phim truyền hình</option>
											<option>Ca nhạc</option>
										</select>
									</div>

								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-2 col-sm-offset-6">
									<button type="submit" class="btn btn-default" name="capnhat">Cập
										nhật</button>
								</div>
							</div>
						</form>
					</div>
					<!-- /FORM -->
				</div>
			</div>
		</div>
		<!--/Modal -->

	</div>
</body>
</html>