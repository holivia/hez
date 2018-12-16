<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'y.jsp' starting page</title>
    
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
  
    <c:forEach items="${requestScope.durationtList}" var="List">
  月份：${List.mealcoupon },
  团队号：${List.team.id },
  团队名：${List.team.name },
 加班 工时 ${List.duration }<br/><br/>
  </c:forEach> <br><br/>
  
  
   <c:forEach items="${requestScope.dayoffList}" var="List">
   月份：${List.mealcoupon },
  团队号：${List.team.id },
  团队名：${List.team.name },
  请假工时 ${List.dayoff }<br/><br/>
  </c:forEach> <br>
  
  
   <c:forEach items="${requestScope.assignmentList}" var="List">
    月份：${List.mealcoupon },
  团队号：${List.team.id },
  团队名：${List.team.name },
   任务单工时 ${List.assignment }<br/><br/>
  </c:forEach> <br>
  </body>
</html>
