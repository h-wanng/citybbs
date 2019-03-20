<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>城院论坛</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>
.container {
	margin-top: 40px;
}
.row {
	padding-bottom: 120px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('.forums-nav').hover(function() {
			$(this).addClass('active');
		}, function() {
			$(this).removeClass('active');
		});
	});
</script>
</head>
<body>

	<jsp:include page="../layout/clientLayout.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-md-2 col-sm-2 col-xs-2 forums-nav">
				<h4>板块导航</h4>
				<hr style="width: 100%;opacity:0.7;" />
				<ul class="nav nav-pills nav-stacked">
					<s:iterator value="partList" var="p">
						<li role="presentation" class="forums-nav">
							<a href="../posts/index?partId=${p.p_id }">${p.title }</a>
						</li>
					</s:iterator>
				</ul>
			</div>
			<div class="col-md-10 col-sm-10 col-xs-10">
				<div class="forums-info">
					<h3 class="forums-title">${part.title }</h3>
					<div class="forums-content">
						<a href="../posts/addPost?partId=${partId }"
							class="btn btn-primary pull-right" style="font-size: 20px;">&nbsp;&nbsp;发帖&nbsp;&nbsp;</a>
						<p>
							帖子数量：<span>${poCount }</span>
						</p>
						<p>
							版主：<span>${manager.username }</span>
						</p>

					</div>
				</div>
			</div>
			<div class="col-md-10 col-sm-10 col-xs-10">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th style="width: 500px;">帖子标题</th>
							<th>作者</th>
							<th style="width: 100px;">查看/回复</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="postList" id="post">
							<tr>
								<td><a href="../posts/checkPost?postId=<s:property value="#post.po_id" />"><s:property value="#post.title" /></a></td>
								<td>
									<a href=""><s:property value="#post.user.username" /></a>
								</td>
								<td>
									<span id="pclick"><s:property value="#post.clickNum" /></span>/ 
									<span><s:property value="#post.replies.size()" /></span>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
			<div class="text-center">
				<nav>
					<ul class="pagination">
						<li><a href="index?page=${page-1 }&partId=${partId }">&laquo;</a></li>
						<s:bean name="org.apache.struts2.util.Counter" var="Counter">
							<s:param name="first" value="1" />
							<s:param name="last" value="pageCount" />
							<s:iterator>
								<s:if test="current-1 == page">
									<li class="active"><a href="index?page=${current-1 }&partId=${partId }">${current-1 }</a></li>
								</s:if>
								<s:else>
									<li><a href="index?page=${current-1 }&partId=${partId }">${current-1 }</a></li>
								</s:else>
							</s:iterator>
						</s:bean>

						<li><a href="index?page=${page+1 }&partId=${partId }">&raquo;</a></li>
					</ul>

				</nav>
			</div>
		</div>
	</div>
	<jsp:include page="../layout/footer.jsp" />
</body>
</html>

