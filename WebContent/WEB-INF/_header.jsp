<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="util.Const"%>
<%
	if (Const.ENABLE_LOADING_SCREEN) {
%>
<link rel="stylesheet" href="main.css">
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function() {
			$('body').addClass('loaded');
		}, 700);
	});
</script>
<%
	}
%>