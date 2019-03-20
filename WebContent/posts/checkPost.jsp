<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>城院论坛</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>
.container {
	padding-right: 150px;
	padding-left: 150px;
	margin-right: auto;
	margin-left: auto;
	margin-top: 50px;
}

tbody tr {
	height: 230px;
}
td p {
	margin: 2%;
}
td form {
	margin: 2%;
}
.username {
	display: block;
	margin: 10px 0 0 20px;
}

.photo {
	clear: both; 
	display: block; 
	margin:auto;
}

.faculty{
	display: block;
	text-align: center;
	margin-top: 35px;
}
.level {
	display: block;
	text-align: center;
}
.row {
	padding-bottom: 120px;
}

.modal-dialog {
	padding-top: 120px;
	width: 350px;
}

.modal-content {
	width: 350px;
}

.rtop {
	position: fixed;
	z-index: 50;
	bottom: 10%;
	right: 5%;
}
#rep {
	margin-top: 10px;
}
</style>
<script>
	$(function() {
		$('#rep').click(function() {
			$.post("../posts/addReply", {
				'postId' : ${post.po_id },
				'reply.content' : $('#rcontent').val()
			}, function(data) {
				data = $.parseJSON(data);
				if (data.state == 'success') {
					location.reload();
				}
				if (data.state == 'error') {
					$('#Log').modal('show');
					$('#Log').on('shown.bs.modal');
				}
			});
		});
		$('#back').click(function() {
			$(location).attr('href', '../posts/index?partId=${part.p_id }');
		});
	});
</script>
</head>
<body>

	<jsp:include page="../layout/clientLayout.jsp" />
	<div class="row">
		<div class="rtop btn-group-vertical">
			<a href="#" class="btn btn-default btn-lg">
				<span class="glyphicon" style="font-size: 30px">▲</span>
			</a>
			<a href="#reform" class="btn btn-default btn-lg">
				<span class="glyphicon" style="font-size: 30px">▼</span>
			</a>
		</div>
		<h3>${part.title }</h3>
		<button class="btn btn-primary pull-right" id="back"
			style="margin-bottom:5px;font-size:18px">返回</button>
		<table class="table table-bordered">
			<thead>
				<tr>
					<td class="col-md-2 info">查看：<span style="color:red;">${post.clickNum }</span>
						回复：<span style="color:red;">${reCount }</span></td>
					<th class="col-md-10 info">${post.title }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<span class="username"><s:property value="post.user.username" /></span>
						<hr style="border: 1px dashed #A3A3A3;" />
						<img class="photo" width="120px" height="120px" alt="${post.user.username }" src="../users/showphoto?uid=${post.user.u_id }" 
						title="${post.user.username } LV.${post.user.level }">
						<s:if test="post.user.faculty=='jsj'">
							<span class="faculty">计算机工程学院</span>
						</s:if>
						<s:elseif test="post.user.faculty=='dzzdh'">
							<span class="faculty">电子与自动化学院</span>
						</s:elseif>
						<s:elseif test="post.user.faculty=='gl'">
							<span class="faculty">管理学院</span>
						</s:elseif>
						<s:elseif test="post.user.faculty=='jz'">
							<span class="faculty">建筑工程学院</span>
						</s:elseif>
						<s:elseif test="post.user.faculty=='wgy'">
							<span class="faculty">外国语学院</span>
						</s:elseif>
						<s:else>
							<span class="faculty">艺术与传媒学院</span>
						</s:else>
						<span class="level">LV.${post.user.level }</span>
					</td>
					<td>
						<p>${post.content }</p>
						<p class="pull-right text-muted" style="margin-top: 160px;">${post.createTime }</p>
					</td>
				</tr>
				<s:iterator value="replyList" var="re">
					<tr>
						<td>
						<span class="username"><s:property value="#re.user.username" /></span>
						<hr style="border: 1px dashed #A3A3A3;" />
						<img class="photo" width="120px" height="120px" alt="${re.user.username }" src="../users/showphoto?uid=${re.user.u_id }" 
						title="${re.user.username } LV.${re.user.level }">
						<s:if test="#re.user.faculty=='jsj'">
							<span class="faculty">计算机工程学院</span>
						</s:if>
						<s:elseif test="#re.user.faculty=='dzzdh'">
							<span class="faculty">电子与自动化学院</span>
						</s:elseif>
						<s:elseif test="#re.user.faculty=='gl'">
							<span class="faculty">管理学院</span>
						</s:elseif>
						<s:elseif test="#re.user.faculty=='jz'">
							<span class="faculty">建筑工程学院</span>
						</s:elseif>
						<s:elseif test="#re.user.faculty=='wgy'">
							<span class="faculty">外国语学院</span>
						</s:elseif>
						<s:else>
							<span class="faculty">艺术与传媒学院</span>
						</s:else>
						<span class="level">LV.${re.user.level }</span>
						</td>
						<td>
							<p>${re.content }</p>
							<p class="pull-right text-muted" style="margin-top: 160px;">${re.replyTime }</p>
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td></td>
					<td>
						<form id="reform" action="" method="post">
							<textarea class="form-control" name="rcontent" id="rcontent"
								style="width: 65%;height: 200px;resize: none;"></textarea>
							<input type="button" id="rep" value="发表回复" class="btn btn-primary" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<jsp:include page="../layout/footer.jsp" />
</body>
</html>

