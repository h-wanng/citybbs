<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- from 腾讯云 未备案错误页面 -->
<title>错误</title>
<style>  
        body{font-size: 14px;font-family: 'helvetica neue',tahoma,arial,'hiragino sans gb','microsoft yahei','Simsun',sans-serif; background-color:#fff; color:#808080;}  
        .wrap{margin:200px auto;max-width:560px;}  
        td{text-align:left; padding:2px 10px;}  
        td.header{font-size:22px; padding-bottom:10px; color:#000;}  
        td.check-info{padding-top:20px;}  
        a{color:#328ce5; text-decoration:none;}  
        a:hover{text-decoration:underline;}  
        .debug{max-width: 510px;padding-top: 20px;}
</style>  
</head>
<body>
	<div class="wrap">  
        <table>  
            <tr>  
                <td rowspan="4" style=""><img src="/img/iperror.jpg" alt=""></td>  
                <td class="header">很抱歉！当前访问出错 <s:property value="exception.message"/> <!-- 输出throw抛出的异常对象 --></td>  
            </tr> 
            <tr><td>原因一：网址输入错误</td></tr> 
            <tr><td>原因二：该页面已经被删除</td></tr>  
            <tr><td>请检查网址。如果刷新页面没能解决问题，你可以联系管理组成员反馈</td></tr>  
            <tr>
            	<td colspan="2" class="debug">
    			<s:property value="exceptionStack"/><br/>     <!-- 输出异常堆栈详细信息，利于调试 -->
    			<s:debug />      <!-- 输出上下文值栈信息 -->
    			</td>
    		</tr>
        </table>  
    </div> 
</body>
</html>