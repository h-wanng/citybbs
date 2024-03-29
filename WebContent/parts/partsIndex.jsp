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
<link rel="stylesheet" href="../css/layout.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<style>
.row {
	padding-bottom: 120px;
}
.forums {
	padding-left: 150px;
	margin-top: 30px;
}

.forums-list {
	margin-top: 50px;
}

.forums-content {
	margin-top: 30px;
}

.forums-content a {
	margin-top: 15px;
}
</style>
</head>
<body>
	<jsp:include page="../layout/clientLayout.jsp" />
	<div class="row">
		<div class="forums-list">
			<s:iterator value="partList" var="part">
				<div class="col-md-6 forums">
					<h3 class="forums-title">${part.title}</h3>
					<div class="forums-content">
						<p>
							帖子数:
							<s:iterator value="poCount" var="key">
								<s:if test="key==#part.p_id">
									<span>${value }</span>
								</s:if>
							</s:iterator>
						</p>
						<a href="../posts/index?partId=${part.p_id }"
							class=" btn btn-default enterforums">点击进入</a>
					</div>
				</div>
			</s:iterator>
		</div>
	</div>
	<jsp:include page="../layout/footer.jsp" />
</body>
</html>

