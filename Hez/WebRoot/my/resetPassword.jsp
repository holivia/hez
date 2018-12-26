<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'resetPassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%String code=null; %>
    This is my JSP page.<br>
    <%-- 
   <%code=(String)request.getAttribute("verificationCode");%> 
    <%=code %>--%>
    <font color="red">${requestScope.codeinfo }</font>
    <form action="resetPassword">
    <input type="hidden" value=${requestScope.verificationCode} name="verificationCode"></input>
    <input type="hidden" value=${requestScope.accountNumber} name="accountNumber"></input>
    输入验证码<input type="text" name="inputCode">
    新密码<input type="text" name="newPasswod">
    <input type="submit"/>
    </form>
    
  </body>
</html>
