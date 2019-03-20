<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><s:text name="admin-index-title"></s:text> </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script>
	$(function() {
		$('#left-nav-home').addClass('active');
	});
</script>
</head>

<body>
	<jsp:include page="../layout/adminLayout.jsp" />
	<h3 style="text-align:center"><s:text name="welcome"></s:text> </h3>
</body>
</html>
