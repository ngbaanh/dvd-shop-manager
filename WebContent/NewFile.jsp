<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String dataArray[];
    String data = "";
	data = request.getParameter("data");
    int tong = 0;
    if (data!=null)  {
        dataArray = data.split("\\,");
        for (String s:dataArray) {
            tong += Integer.parseInt(s);
        }
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta author="Nguyen Ba Anh">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tính tổng</title>
</head>
<body>
<div style="width:300px;height:100px;color:white;background-color:navy;
margin:100px auto;padding:30px;font-size:28px;">
	<form action="#" method="get">
		<input type="text" name="data" value="<%=data%>">
		<input type="submit" value="Tính phát coi">
	</form>
	<hr>
	<b>Tổng = <%=tong%></b>
</div>
</body>
</html>

