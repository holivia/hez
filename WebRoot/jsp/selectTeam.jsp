<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'selectTeam.jsp' starting page</title>
    
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
    
   	<c:forEach items="${teamList}" var="list" >
   		<table class="title_d">
			
				<tr>
					<td class="r1">团队编号：</td>
					<td class="p_content">${list.code}</td>
				</tr>
				<tr>
					<td class="r1">团队名称：</td>
					<td class="p_content">${list.name}</td>					
				</tr>
				<tr>
					<td class="r1">团队负责人：</td>
					<td class="p_content">${list.responsible}</td>					
				</tr>
				<tr>
					<td class="r1">所在区域</td>
					<td class="p_content">${list.place}</td>					
				</tr>
				<tr>
					<td class="r1">创建时间</td>
					<td class="p_content"><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
					<td></td>				
				</tr>
				
	     					
			</table>
			
			 <table>
				<c:forEach items="${list.teamSubs}"  var="sub">
					<tr>
						<td>${sub.name }</td>
						<td>${sub.place }</td>
						<td>${sub.responsible }</td>
						<td><fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd"/></td>
					</tr>
				</c:forEach>
			</table>  
   	</c:forEach>
    
  </body>
</html>
