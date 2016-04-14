<%@ page language="java" contentType="text/html; charset=UTF-8"
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
		alink = "".equals(tokens[2])
				? "<a class=\"btn btn-sm btn-danger\" href=\"HomePage\">Quay về trang chủ</a>"
				: "<a href=\"" + tokens[2] + "\">" + tokens[3] + "</a>";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông báo</title>
<jsp:include page="_bootstrap.jsp" />
</head>
<body>
	<div class="container" style="margin-top: 5px;">
		<div class="alert alert-danger fade in">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<strong><%=header%></strong> : <%=!"".equals(mesageContent)?mesageContent:""%> <small><%=!"#".equals(tokens[2])?alink:""%></small>
		</div>
		<!--
		<div class="row">
			<div class="col-xs-6 col-xs-offset-3">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h4 class="panel-title">
							<%=header%>
						</h4>
					</div>
					<%if (!"".equals(mesageContent)) {%>
					<div class="panel-body">
						<%=mesageContent%>
					</div>
					<%}
				if (!"#".equals(tokens[2])) {%>
					<div class="panel-footer">
						<%=alink%>
					</div>
					<%}%>
				</div>
			</div>
		</div>
		-->
	</div>
</body>
</html>
<%
	}
%>