<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style type="text/css">
* {
	font-family: "微软雅黑";
}

body {
	background-color: #EEEEEE;
}

.navbar-header a {
	font-size: 30px;
}

.navbar-default {
	height: 70px;
}

.container-fluid {
	margin-top: 7px;
}

.left-nav {
	background-color: #F9F9F9;
	max-width: 225px;
	min-width: 225px;
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
		$('#logout').click(function() {
			$('#message-content').html(null);
			$('#message-content').append('确认退出？');
			$('#mes').modal('show');
			$('#model-btn').click(function() {
				$.post("../admin/logout", function(data) {
					data = $.parseJSON(data);
					$('#message-content2').html(null);
					$('#message-content2').append(data.message);
					$('#mes2').modal('show');
					$('#model-btn2').click(function() {
						location.href = '../index';
					});
				});
			});
		});
	});
</script>
<!--提示框-->
<div class="modal fade" aria-hidden="true" data-backdrop="static"
	id="mes">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
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
<!--提示框2-->
<div class="modal fade" aria-hidden="true" data-backdrop="static"
	id="mes2">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="message-title2">提示</h4>
			</div>
			<div class="modal-body">
				<p id="message-content2"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					id="model-btn2">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!--提示框-->
<!--导航开始-->
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href=""><s:text name="admin-index-title"></s:text> </a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<li>
				<p class="navbar-text">
					<s:text name="welcome-admin"></s:text> <span>${session.admin.username }</span>
				</p>
			</li>
			<li><a href="#" id="logout"><s:text name="exit-system"></s:text> </a></li>
		</ul>
	</div>
</nav>
<!--导航结束-->
<div class="container-fluid">
	<div class="row">
		<div class="col-md-2">
			<!--左侧导航开始-->
			<div class="left-nav">
				<ul class="nav nav-pills nav-stacked">
					<li role="presentation" id="left-nav-home">
						<a href="../admin/index"><span class="glyphicon glyphicon-home">
						</span>&nbsp;&nbsp;<s:text name="mainpage"></s:text> </a>
					</li>
					<li role="presentation" id="left-nav-user">
						<a href="../admin/usersIndex"><span class="glyphicon glyphicon-user">
						</span>&nbsp;&nbsp;<s:text name="user-manage"></s:text></a>
					</li>
					<li role="presentation" id="left-nav-forum">
						<a href="../admin/forumsIndex"><span class="glyphicon glyphicon-folder-open">
						</span>&nbsp;&nbsp;<s:text name="forum-manage"></s:text></a>
					</li>
					<li role="presentation" id="left-nav-part">
						<a href="../admin/partsIndex"><span class="glyphicon glyphicon-file">
						</span>&nbsp;&nbsp;<s:text name="part-manage"></s:text></a>
					</li>
					<li role="presentation" id="left-nav-post">
						<a href="../admin/postsIndex"><span class="glyphicon glyphicon-list">
						</span>&nbsp;&nbsp;<s:text name="post-manage"></s:text></a>
					</li>
				</ul>
			</div>
			<!--左侧导航结束-->
		</div>
		</div>
		</div>
		<!-- <div class="col-md-10"> -->