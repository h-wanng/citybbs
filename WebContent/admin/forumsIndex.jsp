<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="forum-manage"></s:text></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script>
	$(function() {
		var edit = 0;
		$('#left-nav-forum').addClass("active");
		$('.glyphicon-cog').click(function() {
			if (edit == 0) {
				var eid = $(this).attr('att');
				$('.show_' + eid + '').addClass('hidden');
				$('.edit_' + eid + '').removeClass('hidden');
				edit = 1;
			} else {
				$('#message-content').html(null);
				$('#message-content').append('请先完成当前编辑');
				$('#mes').modal('show');
			}
		});
		$('.glyphicon-trash').click(function() {
			var eid = $(this).attr('att');
			$('#message-content').html(null);
			$('#message-content').append('确认删除？');
			$('#mes').modal('show');
			$('#model-btn').click(function() {
				$('#mes').modal('hide');
				$.post("../admin/forumDelete", {
					'forum.f_id' : eid
				}, function(data) {
					data = $.parseJSON(data);
					if (data.state == 'success') {
						$('#message-content2').html(null);
						$('#message-content2').append(data.message);
						$('#mes2').modal('show');
						$('#model-btn2').click(function() {
							top.location.reload();
						});
					} else {
						$('#message-content2').html(null);
						$('#message-content2').append(data.message);
						$('#mes2').modal('show');
					}
				});
			});
		});
		$('.glyphicon-ok').click(function() {
			var flag = 0;
			var eid = $(this).attr('att');
			var etitle = $('#forumstitle_' + eid + '').val();
			var ftitle = $('#ftitle_' + eid + '').html();
			if (etitle == ftitle) {
				$('#message-content').html(null);
				$('#message-content').append('未做任何修改');
				$('#mes').modal('show');
			} else {
				$.post("../admin/forumUpdate", {
					'forum.f_id' : eid,
					'forum.title' : etitle
				}, function(data) {
					data = $.parseJSON(data);
					if (data.state == 'success') {
						$('#message-content2').html(null);
						$('#message-content2').append(data.message);
						$('#mes2').modal('show');
						$('#model-btn2').click(function() {
							top.location.reload();
						});
					} else {
						$('#message-content').html(null);
						$('#message-content').append(data.message);
						$('#mes').modal('show');
					}
				})
			}
		});
		$('.glyphicon-remove').click(function() {
			var eid = $(this).attr('att');
			$('.show_' + eid + '').removeClass('hidden');
			$('.edit_' + eid + '').addClass('hidden');
			edit = 0;
		});
		$('#addforum').click(function() {
			$('#message-content').html(null);
			$('#message-content').append('<input type="text" class="form-control" id="addforumname" placeholder="请输入板块名称">');
			$('#mes').modal('show');
			$('#model-btn').click(function() {
				var title = $('#addforumname').val();
				$.post("../admin/forumAdd", {'forum.title' : title}, function(data) {
					data = $.parseJSON(data);
					$('#message-content2').html(null);
					$('#message-content2').append(data.message);
					$('#mes2').modal('show');
					$('#model-btn2').click(function() {
						top.location.reload();
					});
				});
			});
		});
	});
</script>
<style>
.forums-table {
	width: 80%;
	margin-left: 10%;
	margin-top: 40px;
}

.input-sm {
	width: 100px;
}

.page {
	position: absolute;
	bottom: 40px;
	left: 0;
	right: 0;
	margin:0 auto;
}
</style>
</head>

<body>
	<jsp:include page="../layout/adminLayout.jsp" />
	<div class="forums-table">
		<button class="btn btn-primary pull-right" id="addforum">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<s:text name="add-forum"></s:text>
		</button>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th><s:text name="forumname"></s:text> </th>
					<th><s:text name="operation"></s:text> </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="forumList" var="forum">
					<tr>
						<td>${forum.f_id }</td>
						<td><span class="show_${forum.f_id }" id="ftitle_${forum.f_id }">${forum.title }</span>
							<input type="text"
							class="form-control hidden input-sm edit_${forum.f_id }"
							id="forumstitle_${forum.f_id }" value="${forum.title }" /></td>
						<td>
							<div class="show_${forum.f_id }">
								<a href="#"><span class="glyphicon glyphicon-cog"
									att="${forum.f_id }"></span></a>&nbsp;&nbsp; <a href="#"><span
									class="glyphicon glyphicon-trash" att="${forum.f_id }"></span></a>
							</div>
							<div class="hidden edit_${forum.f_id }">
								<a href="#"><span class="glyphicon glyphicon-ok"
									att="${forum.f_id }"></span></a>&nbsp;&nbsp; <a href="#"><span
									class="glyphicon glyphicon-remove" att="${forum.f_id }"></span></a>
							</div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="text-center page">
			<nav>
			<ul class="pagination">
				<li><a href="../admin/forumsIndex?page=${page-1 }">&laquo;</a></li>

				<s:bean name="org.apache.struts2.util.Counter" var="Counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="pageCount" />
					<s:iterator>
						<s:if test="current-1 == page">
							<li class="active"><a
								href="../admin/forumsIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:if>
						<s:else>
							<li><a href="../admin/forumsIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:else>
					</s:iterator>
				</s:bean>

				<li><a href="../admin/forumsIndex?page=${page+1 }">&raquo;</a></li>
			</ul>
			</nav>
		</div>
	</div>
</body>
</html>
