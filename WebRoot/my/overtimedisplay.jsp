<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    
    <title>My JSP 'test.jsp' starting page</title>
    
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
  	<% 
 	int i=1;	
 	%>
  <table>
    <c:forEach items="${requestScope.OvertimeList}" var="overtime">			
  		<tr>
  			<td><%=i %></td>
   			<td width="1px">${overtime.staffteam.team.name }</td>
			<td width="1px">${overtime.staffteam.staff.jobnumber }</td>
   			<td width="1px">${overtime.staffteam.staff.name }</td>
   			<td width="1px">
   				<fmt:formatDate value="${overtime.date}" pattern="yyyy-MM-dd"/>
   			</td>
   			<td width="1px">${overtime.mealcoupon }</td>
   			<td width="1px">${overtime.startime }</td>
   			<td width="1px">${overtime.endtime }</td>
   			<td>加班时长<%
   			
   			
   			
   			 %></td>
   			<td width="1px">${overtime.type }</td>
   			<td width="1px">${overtime.dutymode }</td>
   			<td width="1px">${overtime.notes }</td>
   			<!--  <td width="1px"><a href="updateEmployeeView?id=${employee.id}">修改 </a> <a href="deleteEmployee?id=${employee.id}"> 删除 </a></td>
   			-->
   		</tr>
   		<% 
   		 i=i+1;
 		%>  		
  	</c:forEach>
  	</table>
  </body>
</html>
