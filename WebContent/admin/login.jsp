<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><s:text name="login"></s:text></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style type="text/css">
* {
	font-family: "微软雅黑";
}

body {
	background-color: #EEEEEE;
}

.login-form {
	width: 80%;
	margin-left: 10%;
}

.modal-dialog {
	padding-top: 120px;
	width: 350px;
}

.modal-content {
	width: 350px;
}
</style>
<script>
	$(function() {
		$('#logsub').click(function() {
			var uname = $('#username').val().trim();
			var upass = $('#userpass').val().trim();
			var logflag = 0
			if (uname == "" || uname == null) {
				$('#username-group label').removeClass('hidden');
				logflag = 0;
			} else {
				$('#username-group').removeClass('has-error');
				$('#username-group label').addClass('hidden');
				logflag++;
			}
			if (upass == "" || upass == null) {
				$('#userpass-group label').removeClass('hidden');
				logflag = 0;
			} else {
				$('#userpass-group').removeClass('has-error');
				$('#userpass-group label').addClass('hidden');
				logflag++;
			}
			if (logflag == 2) {
				$.post("../admin/login", {
					'user.username' : uname,
					'user.password' : upass
				}, function(data) {
					data = $.parseJSON(data);
					if (data.state == 'success') {
						location.href = "../admin/index";
					}
					if (data.state == 'error') {
						$('#message-content').html(null);
						$('#message-content').append(data.message);
						$('#mes').modal('show');
						$('#mes').on('shown.bs.modal');
					}
				})
			}
		});
	});
</script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4" style="margin-top: 200px;">
				<h3 align="center"><s:text name="admin-index-title"/></h3>
				<form action="" method="post" class="login-form">
					<div class="form-group" id="username-group">
						<label class="control-label hidden" for="username">用户名不能为空</label>
						<input type="text" class="form-control" name="username"
							id="username" value="" placeholder='<s:text name="adminname"></s:text>' />
					</div>
					<div class="form-group" id="userpass-group">
						<label class="control-label hidden" for="username">密码不能为空</label>
						<input type="password" class="form-control" name="userpass"
							id="userpass" value="" placeholder='<s:text name="adminpassword"></s:text>' />
					</div>
					<input type="button" class="btn btn-primary" style="width: 100%;"
						value='<s:text name="login"></s:text>' id="logsub" /> <a href="../index"
						class="btn btn-default" style="width: 100%;margin-top:20px"><s:text name="turnmainpage"></s:text></a>
				</form>
			</div>
		</div>
	</div>
	<!--提示框-->
	<div class="modal fade" aria-hidden="true" data-backdrop="static"
		id="mes">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="message-title">提示</h4>
				</div>
				<div class="modal-body">
					<p id="message-content"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="model-btn">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!--提示框-->
</body>
</html>
