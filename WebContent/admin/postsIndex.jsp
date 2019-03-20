<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="part-manage"></s:text></title>

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
		$('#left-nav-post').addClass("active");
		$('.glyphicon-trash').click(function() {
			var eid = $(this).attr('att');
			$('#message-content').html(null);
			$('#message-content').append('确认删除？');
			$('#mes').modal('show');
			$('#model-btn').click(function() {
				$('#mes').modal('hide');
				$.post("../admin/postDelete", {
					'post.po_id' : eid,
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
	<div class="user-table">
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th><s:text name="title"></s:text></th>
					<th><s:text name="content"></s:text></th>
					<th><s:text name="author"></s:text></th>
					<th><s:text name="part-involved"></s:text></th>
					<th><s:text name="posttime"></s:text></th>
					<th><s:text name="browsenum"></s:text></th>
					<th><s:text name="replynum"></s:text></th>
					<th><s:text name="operation"></s:text></th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="postList" var="post">
					<tr>
						<td width="3%">${post.po_id}</td>
						<td width="10%"><span class="show_${post.po_id}" id="ptitle_${post.po_id}">${post.title }</span>
							<input type="text" class="form-control hidden input-sm edit_${post.po_id}"
							id="etitle_${post.po_id}" value="@item.Title" /></td>
						<td width="30%"><span class="show_${post.po_id}" id="pcontent_${post.po_id}">${post.content }</span>
							<input type="text" class="form-control hidden input-sm edit_${post.po_id}"
							id="econtent_${post.po_id}" value="@item.Content" />
						</td>
						<td width="7.5%">
							${post.user.username }
						</td>
						<td width="9%">
							${post.part.title }
						</td>
						<td width="10%">${post.createTime }</td>
						<td width="7.5%" align="center">${post.clickNum }</td>
						<td width="7.5%" align="center">
							${post.replies.size() }
						</td>
						<td width="6%">
							<div class="show_${post.po_id}">
								</a>&nbsp;&nbsp; 
								<a href="#">
									<span class="glyphicon glyphicon-trash" att="${post.po_id}"></span>
								</a>
							</div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="text-center page">
			<nav>
			<ul class="pagination">
				<li><a href="../admin/postsIndex?page=${page-1 }">&laquo;</a></li>

				<s:bean name="org.apache.struts2.util.Counter" var="Counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="pageCount" />
					<s:iterator>
						<s:if test="current-1 == page">
							<li class="active">
								<a href="../admin/postsIndex?page=${current-1 } ">${current-1 }</a>
							</li>
						</s:if>
						<s:else>
							<li><a href="../admin/postsIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:else>
					</s:iterator>
				</s:bean>

				<li><a href="../admin/postsIndex?page=${page+1 }">&raquo;</a></li>
			</ul>

			</nav>
		</div>
	</div>
</body>
</html>
