<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>个人中心</title>
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<link rel="stylesheet" href="../css/layout.css" />
<script src="../js/layout.js"></script>
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script>
// 		$('.btn-ok').click(function() {
// 			var files = $('.file').files;
// 			var form = new FormData();
// 	        form.append("file", files[0]);
// 	        $.post("../users/changePhoto", {
// 				'test'  : '123'
// 			}, function(data) {
// 				data = $.parseJSON(data);
// 				$('#message-content2').html(null);
// 				$('#message-content2').append(data.message);
// 				$('#mes2').modal('show');
// 				$('#model-btn2').click(function() {
// 					top.location.reload();
// 				});
// 			});
// 		});
// 	});
</script>
<style type="text/css">
.container{
	margin-top: 7%;
	max-width: 60%;
}
.inner {
	display:flex;
    justify-content:center;
    align-items:center;
}
.head {
	width: 200px;
}
.details {
	width: 65%;
}
.photo {
	clear: both; 
	display: block; 
	margin:auto;
	margin-top: 15px;
}
.p-text {
	display: block;
	text-align: center;
	margin: 3px 0 5px 0;
}
.d-text {
	display: block;
	margin: 5px 0 5px 15px;
}
.file {
	width: 100px;
	margin: auto;
	margin-bottom: 5px;
}
.btn {
	width: 85%;
	height: 30px;
	font-size: 13px;
	display: block;
	margin: auto;
}
</style>
</head>
<body>
	<jsp:include page="../layout/clientLayout.jsp" />
	<div class="container">
	<div class="inner">
	<table class="table table-bordered head" align="left" height="260px">
		<thead>
			<tr>
				<td class="col-md-10 info">头像
				<a href="#"><span class="edit-photo" style="float:right;font-size: 12px;padding-top: 3px;">更换头像</span></a>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<img class="photo" width="120px" height="120px" alt="${user.username }" src="../users/showphoto?uid=${user.u_id }" 
						title="${user.username } LV.${user.level }">
					<span class="p-text"><a href="#">${user.username }</a> LV.${user.level }</span>
					<form id="form" action="../users/changePhoto" method="post" enctype="multipart/form-data">  
						<input type="file" class="file hidden" name="photo" accept="image/*">
						<button type="submit" class="btn btn-default hidden btn-ok">确 定</button>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	<table border="0" cellspacing="0"  height="10px"cellpadding="0" align="left" width="27px"><tr><td></td></tr></table>
	<table class="table table-bordered details" align="left" height="260px">
		<thead>
			<tr>
				<td class="col-md-10 info">个人资料
				<a href="#"><span class="edit-info" style="float:right;font-size: 12px;padding-top: 3px;">更改资料</span></a>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<s:if test="user.gender == 'male'">
						<span class="d-text">性　　别 <span style="padding-left:50px">男</span></span>
					</s:if>
					<s:else >
						<span class="d-text">性　　别 <span style="padding-left:50px">女</span></span>
					</s:else>
					<s:if test="user.faculty == 'jsj'">
						<span class="d-text">院　　系 <span style="padding-left:50px">计算机工程学院</span></span>
					</s:if>
					<s:elseif test="user.faculty == 'dzzdh'">
						<span class="d-text">院　　系 <span style="padding-left:50px">电子与自动化学院</span></span>
					</s:elseif>
					<s:elseif test="user.faculty == 'gl'">
						<span class="d-text">院　　系 <span style="padding-left:50px">管理学院</span></span>
					</s:elseif>
					<s:elseif test="user.faculty == 'jz'">
						<span class="d-text">院　　系 <span style="padding-left:50px">建筑工程学院</span></span>
					</s:elseif>
					<s:elseif test="user.faculty == 'wgy'">
						<span class="d-text">院　　系 <span style="padding-left:50px">外国语学院</span></span>
					</s:elseif>
					<s:else >
						<span class="d-text">院　　系 <span style="padding-left:50px">艺术与传媒学院</span></span>
					</s:else>
					
					<span class="d-text">邮　　箱 <span style="padding-left:50px">${user.email }</span></span>
					<!-- ognl is cool! -->
					<!-- 截取日期长度 2018-06-15 00.00.00.0 -->
					<span class="d-text">注册时间 <span style="padding-left:50px"><s:property value="user.regTime.toString().substring(0,10)"/></span></span>
					<s:if test="user.role == 1">
						<span class="d-text">账号类型 <span style="padding-left:50px">管理员</span></span>
					</s:if>
					<s:else >
						<span class="d-text">账号类型 <span style="padding-left:50px">普通会员</span></span>
					</s:else>
					<span class="d-text">个人介绍 </span>
					<span class="d-text">　　　　　${user.disc }</span>
	</table>
	</div>
	</div>
	<jsp:include page="../layout/footer.jsp" />
</body>
</html>