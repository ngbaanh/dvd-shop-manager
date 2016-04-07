<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset = "utf-8">
	<meta name="description" content="Manager disc series list">

	
    
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<form action="AddNewDiscSeries" method="post">
<div class="container-fluid">

	<div class="row">
		<div class="col-md-9">
    		<ol class="breadcrumb" style="margin-bottom: 5px;">
     			<li><a href="#">Quản lý phiếu thuê</a></li>
     			<li><a href="#">Xem danh sách phiếu</a></li>
     			<li class="active">Xem chi tiết phiếu</li>
   		 	</ol>
    	</div><!--col-md9-->
    	<div class="col-md-2">
    		<button type="button" class="btn btn-sm btn-info">Đóng</button>
    	</div><!--col-md2-->
	</div><!--row-->
    
	<h5> <em>Thêm một đĩa hoàn toàn mới vào hệ thống</em> </h5>
    
	<div class="row">
		<div class="col-md-2">
    		<p>Tên bộ đĩa*</p>
    	</div><!--col-md2-->
    	<div class="col-md-3">
    		<input type="Name" name="tenDia" class="form-control" >
    	</div><!--col-md3-->
	</div><!--row--> 
       
	<div class="row">
		<div class="col-md-2">
    		<p>Mô tả</p>
    	</div><!--col-md2-->
    	<div class="col-md-3">
    		<input type="Text" name="moTa" class="form-control" >
    	</div><!--col-md3-->
	</div><!--row--> 
    
	<div class="row">
		<div class="col-md-2">
    		<p>Thể loại</p>
    	</div><!--col-md2-->
    		<div class="row">    
    			<div class="col-md-2">
    				<input type="checkbox" name="TheLoai" value = "Phim điện ảnh">
    			</div><!--col-md2-->
        		<div class="col-md-2">
    				<input type="checkbox" name="TheLoai" value = "Nhạc việt">
    			</div><!--col-md2-->
        		<div class="col-md-2">
    				<input type="checkbox" name="TheLoai" value="Phim truyền hình">
    			</div><!--col-md2-->
        		<div class="col-md-2">
    				<input type="checkbox" name="TheLoai" value="Ca nhạc">
    			</div><!--col-md2-->
     		</div><!--row-->
		</div><!--row-->
         
	<div class="row">
		<div class="col-md-2">
    		<p>Số lượng đĩa</p>
    	</div><!--col-md2-->
    	<div class="col-md-2">
    		<input type="Text" name="soLuong" class="form-control" >
   	 	</div><!--col-md2-->
    	<div class="col-md-4">
    		<p>Tất cả đĩa mới có chất lượng mặc định 3*</p>
    	</div><!--col-md4-->
	</div><!--row-->
    
	<div class="row">
		<div class="col-md-2">
    		<p>Vị trí</p>
    	</div><!--col-md2-->
    	<div class="col-md-3">
    		<input type="Text" name="viTri" class="form-control" ><br>
           <button type="submit" class="btn btn-sm btn-info" name="Luu">Lưu</button>
    	</div><!--col-md3-->
	</div><!--row-->
     
</div><!--container--> 
</form>
</body>
</html>