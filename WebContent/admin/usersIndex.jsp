<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="user-manage"></s:text> </title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery-1.10.2.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script>
    $(function () {
        var edit = 0;
        $('#left-nav-user').addClass("active");
        $('.glyphicon-cog').click(function () {
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
        $('.glyphicon-trash').click(function () {
            var eid = $(this).attr('att');
            $('#message-content').html(null);
            $('#message-content').append('确认删除？');
            $('#mes').modal('show');
            $('#model-btn').click(function () {
                $('#mes').modal('hide');
                $.post("../admin/userDelete", { 'user.u_id': eid }, function (data) {
                	data = $.parseJSON(data);
                	if (data.state == 'success') {
                        $('#message-content2').html(null);
                        $('#message-content2').append(data.message);
                        $('#mes2').modal('show');
                        $('#model-btn2').click(function () {
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
        $('.glyphicon-ok').click(function () {
            var flag = 0;
            var eid = $(this).attr('att');
            var euname = $('#username_' + eid + '').val();
            var eupass = $('#userpass_' + eid + '').val();
            var erole = $('#role_' + eid + '').val();
            var uname = $('#uname_' + eid + '').html();
            var upass = $('#upass_' + eid + '').html();
            var role = $('#urole_' + eid + '').attr('att');
            var regtime = $('#regtime_'+eid+'').html();
            if (euname.length < 3 || euname.length > 8) {
                $('#message-content').html(null);
                $('#message-content').append('用户名长度在3~8个字符之间');
                $('#mes').modal('show');
                flag = 0;
            } else {
                flag++;
                if (eupass.length < 3 || eupass.length > 8) {
                    $('#message-content').html(null);
                    $('#message-content').append('密码长度在3~8个字符之间');
                    $('#mes').modal('show');
                    flag = 0;
                } else {
                    flag++;
                }
            }
            if (euname == uname && eupass == upass && erole == role) {
                $('#message-content').html(null);
                $('#message-content').append('未做任何修改');
                $('#mes').modal('show');
                flag = 0;
            }
            if (flag == 2) {
                $.post("../admin/userUpdate", { 'user.u_id': eid, 'user.username': euname, 'user.password': eupass, 'user.role': erole ,'user.regTime':regtime,}, function (data) {
                	data = $.parseJSON(data);
                    $('#message-content').html(null);
                    $('#message-content').append(data.message);
                    $('#mes').modal('show');
                    $('#model-btn').click(function () {
                    	top.location.reload();
                    });
                });
            };
        });
        $('.glyphicon-remove').click(function () {
            var eid = $(this).attr('att');
            $('.show_' + eid + '').removeClass('hidden');
            $('.edit_' + eid + '').addClass('hidden');
            edit = 0;
        });
    });
</script>
<style>
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
					<th><s:text name="username"></s:text> </th>
					<th><s:text name="password"></s:text> </th>
					<th><s:text name="role"></s:text> </th>
					<th><s:text name="regtime"></s:text> </th>
					<th><s:text name="operation"></s:text> </th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="usersList" id="user">
					<tr>
						<td><s:property value="#user.u_id" /></td>
						<td>
							<span class="show_<s:property value="#user.u_id" />" id="uname_<s:property value="#user.u_id" />"><s:property value="#user.username" /></span>
							<input type="text" class="form-control hidden input-sm edit_<s:property value="#user.u_id" />"
							id="username_<s:property value="#user.u_id" />" value="<s:property value="#user.username" />" />
						</td>
						<td>
							<span class="show_<s:property value="#user.u_id" />" id="upass_<s:property value="#user.u_id" />"><s:property value="#user.password" /></span>
							<input type="text" class="form-control hidden input-sm edit_<s:property value="#user.u_id" />"
							id="userpass_<s:property value="#user.u_id" />" value="<s:property value="#user.password" />" />
						</td>
						<td>
							<span class="show_<s:property value="#user.u_id" />" id="urole_<s:property value="#user.u_id" />" att="<s:property value="#user.role" />">
							<s:if test="#user.role==1">
								<s:text name="admin"></s:text>
							</s:if>
							<s:else>
								<s:text name="normal"></s:text>
							</s:else>
							</span>
							<select class="form-control hidden input-sm edit_<s:property value="#user.u_id" />" id="role_<s:property value="#user.u_id" />">
								<option value="0">普通用户</option>
								<option value="1">管理员</option>
							</select>
						</td>
						<td>
							<span id="regtime_<s:property value="#user.u_id" />"><s:property value="#user.regTime" /></span>
						</td>
						<td>
							<div class="show_<s:property value="#user.u_id" />">
								<a href="#"><span class="glyphicon glyphicon-cog" att="<s:property value="#user.u_id" />"></span></a>&nbsp;&nbsp; 
								<a href="#"><span class="glyphicon glyphicon-trash" att="<s:property value="#user.u_id" />"></span></a>
							</div>
							<div class="hidden edit_<s:property value="#user.u_id" />">
								<a href="#"><span class="glyphicon glyphicon-ok" att="<s:property value="#user.u_id" />"></span></a>&nbsp;&nbsp; 
								<a href="#"><span class="glyphicon glyphicon-remove" att="<s:property value="#user.u_id" />"></span></a>
							</div>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="text-center page">
			<nav>
			<ul class="pagination">
				<li><a href="../admin/usersIndex?page=${page-1 }">&laquo;</a></li>

				<s:bean name="org.apache.struts2.util.Counter" var="Counter">
					<s:param name="first" value="1" />
					<s:param name="last" value="pageCount" />
					<s:iterator>
						<s:if test="current-1 == page">
							<li class="active"><a href="../admin/usersIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:if>
						<s:else>
							<li><a href="../admin/usersIndex?page=${current-1 } ">${current-1 }</a></li>
						</s:else>
					</s:iterator>
				</s:bean>

				<li><a href="../admin/usersIndex?page=${page+1 }">&raquo;</a></li>
			</ul>

			</nav>
		</div>
	</div>
</body>
</html>
