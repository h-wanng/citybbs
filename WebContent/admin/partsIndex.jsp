<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="part-manage"></s:text> </title>

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
		$('#left-nav-part').addClass("active");
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
				$.post("../admin/partDelete", {
					'part.p_id' : eid,
				}, function(data) {
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
		$('.glyphicon-ok').click(function() {
			var flag = 0;
			var eid = $(this).attr('att');
			var eptitle = $('#eptitle_' + eid + '').val();
			var efid = $('#epforum_' + eid + '').val();
			var emana = $('#epmana_' + eid + '').val();
			var ptitle = $('#ptitle_' + eid + '').html();
			var fid = $('#pforum_' + eid + '').attr('att');
			var pmana = $('#pmana_' + eid + '').attr('att'); 
			if (eptitle == ptitle && efid == fid && emana == pmana) {
				$('#message-content').html(null);
				$('#message-content').append('未做任何修改');
				$('#mes').modal('show');
			} else {
				$.post("../admin/partUpdate", {
					'part.p_id' : eid,
					'part.title' : eptitle,
					'fid' : efid,
					'part.manager' : emana
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
		$('#addpart').click(function() {
			$('#message-content').html(null);
			$('#message-content').append('<input type="text" class="form-control add" id="addpartname" placeholder="请输入分板块名称">'
			+'<select class="form-control add" id="addpartofforum"><option value="" selected="true" disabled="true">请选择所属版块</option><s:iterator value="forumList" id="forum">'
			+'<option value="<s:property value="#forum.f_id" />"> <s:property value="#forum.title" />'
			+'</option> </s:iterator> </select>'
			+'<select class="form-control add" id="addmana"><option value="" selected="true" disabled="true">请选择分版主</option><s:iterator value="usersList" var="user">'
			+'<option value="<s:property value="#user.u_id" />"><s:property value="#user.username" />'
			+'</option> </s:iterator> </select>');
			$('#mes').modal('show');
			$('#model-btn').click(function() {
				var title = $('#addpartname').val();
				var pforum = $('#addpartofforum').val();
				var manager = $('#addmana').val();
				$.post("../admin/partAdd", {'part.title' : title, 'part.manager' : manager, 'fid' : pforum}, function(data) {
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
		$('.glyphicon-remove').click(function() {
			var eid = $(this).attr('att');
			$('.show_' + eid + '').removeClass('hidden');
			$('.edit_' + eid + '').addClass('hidden');
			edit = 0;
		});
	});
</script>
<style type="text/css">
.user-table {
	width: 80%;
	margin-left: 10%;
	margin-top: 40px;
}

.input-sm {
	width: 130px;
}
.page {
	position: absolute;
	bottom: 40px;
	left: 0;
	right: 0;
	margin:0 auto;
}

.add {
	margin-bottom: 10px;
}
</style>
</head>

<body>
	<jsp:include page="../layout/adminLayout.jsp" />
	<div class="user-table">
		<button class="btn btn-primary pull-right" id="addpart">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<s:text name="add-part"></s:text>
		</button>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th><s:text name="partname"></s:text> </th>
					<th><s:text name="forum-involved"></s:text></th>
					<th><s:text name="part-manager"></s:text></th>
					<th><s:text name="operation"></s:text></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="partList" var="part">
					<tr>
						<td>${part.p_id}</td>
						<td><span class="show_${part.p_id }" id="ptitle_${part.p_id }">${part.title }</span>
							<input type="text" class="form-control hidden input-sm edit_${part.p_id }"
							id="eptitle_${part.p_id }" value="${part.title }" />
						</td>
						<td>
							<span class="show_${part.p_id }" id="pforum_${part.p_id }" att="${part.forum.f_id}">
							<s:iterator value="forumList" var="forum">
								<s:if test="#forum.f_id==#part.forum.f_id">
									<s:property value="#forum.title" />
								</s:if>
							</s:iterator>
							</span>
							<select class="form-control hidden input-sm edit_${part.p_id }" id="epforum_${part.p_id }">
								<s:iterator value="forumList" id="forum">
									<option value="<s:property value="#forum.f_id" />">
									<s:property value="#forum.title" />
									</option>
								</s:iterator>
							</select>
						</td>
						<td>
							<span class="show_${part.p_id }" id="pmana_${part.p_id }" att="${part.manager}">
							<s:iterator value="usersList" var="user">
								<s:if test="#user.u_id==#part.manager">
									<s:property value="#user.username" />
								</s:if>
							</s:iterator>
							</span>
							<select class="form-control hidden input-sm edit_${part.p_id }" id="epmana_${part.p_id }">
								<s:iterator value="usersList" id="user">
									<option value="<s:property value="#user.u_id" />"><s:property value="#user.username" /></option>
								</s:iterator>
							</select>
						</td>
						<td>
							<!-- <div class="show_${part.p_id}">
								&nbsp;&nbsp; <a href="#"><span
									class="glyphicon glyphicon-trash" att="${l.id}"></span></a>
							</div> -->
							<div class="show_${part.p_id }">
								<a href="#"><span class="glyphicon glyphicon-cog" att="${part.p_id }"></span></a>&nbsp;&nbsp; 
								<a href="#"><span class="glyphicon glyphicon-trash" att="${part.p_id }"></span></a>
							</div>
							<div class="hidden edit_${part.p_id }">
								<a href="#"><span class="glyphicon glyphicon-ok" att="${part.p_id }"></span></a>&nbsp;&nbsp; 
								<a href="#"><span class="glyphicon glyphicon-remove" att="${part.p_id }"></span></a>
							</div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="text-center page">
			<nav>
			<ul class="pagination">
				<li><a href="../admin/partsIndex?page=${page-1 }">&laquo;</a></li>

				<s:bean name="org.apache.struts2.util.Counter" var="Counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="pageCount" />
					<s:iterator>
						<s:if test="current-1 == page">
							<li class="active"><a
								href="../admin/partsIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:if>
						<s:else>
							<li><a href="../admin/partsIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:else>
					</s:iterator>
				</s:bean>

				<li><a href="../admin/partsIndex?page=${page+1 }">&raquo;</a></li>
			</ul>

			</nav>
		</div>
	</div>
</body>
</html>
