-jmn bng5 tyo=<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String message = (String) request.getAttribute("message");
	if (message == null) {
		response.sendRedirect("HomePage");
	} else {
		String tokens[] = message.split(";");
		String header, mesageContent, alink;
		if (tokens.length != 4) {
			out.print("Thông báo định dạng sai!");
			return;
		}
		header = "".equals(tokens[0]) ? "Thông báo" : tokens[0];
		mesageContent = tokens[1];
		alink = "".equals(tokens[2]) ? "<a href=\"HomePage\">Quay về trang chủ</a>"
				: "<a href=\"" + tokens[2] + "\">" + tokens[3] + "</a>";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông báo</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h4 class="panel-title">
					<%=header%>
				</h4>
			</div>
			<%
				if (!"".equals(mesageContent)) {
			%>
			<div class="panel-body">
				<%=mesageContent%>
			</div>
			<%
				}
			%>
			<div class="panel-footer">
				<%=alink%>
			</div>
		</div>
	</div>
</body>
</html>
<%
	}
%>