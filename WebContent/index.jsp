<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>城院论坛</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
* {
	font-family: "微软雅黑";
}

.pagebg {
	position: fixed;
	z-index: -1;
	background: url(img/indexbg.jpg) no-repeat;
	background-size: 100% 100%;
	width: 100%;
	height: 100%;
}

.page-content {
	opacity: 0.8;
	margin-top: 27%;
	color: #000000;
}

.page-content h4 {
	margin-top: 50px;
}

.pagebtn {
	font-size: 17px;
	margin-top: 20px;
}
</style>
</head>

<body>
	<div class="pagebg"></div>
	<div class="container">
		<div class="page-content text-center">
			<h1>欢迎访问城院论坛</h1>
			<h4>团结、进取、求实、创新</h4>
			<hr style="width: 700px;" />
			<a href="forums/getForums" class="btn btn-default pagebtn">点击进入</a> <a
				href="admin/tologin" class="btn btn-default pagebtn">进入管理系统</a>
		</div>
	</div>
</body>

</html>
