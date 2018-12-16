<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'input.jsp' starting page</title>
    
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
    This is my input page.  <br>
    <form action="selectOvertime" method="post">
<table width="300" height="200" border="0" align="center">
	<tr>
          <td colspan="2"  height="60" ><font color="red">${requestScope.message}</font></td>
     </tr>
	
         <tr>
         	<td width="20%">startDate: </td>
             <td><input type="text"  name="startdate" ></td>
         </tr>
         <tr>
         	<td>endDate:  </td>
             <td><input type="text"  name="enddate" ></td>
         </tr>
         <tr>
         	<td width="20%">团队名: </td>            
              <td>
             	<select name="teamname">
             		<option value="">请选择</option>
             		<c:forEach items="${requestScope.StaffTeamList}" var="StaffTeam">			
             		<option value='${StaffTeam.team.name }'>${StaffTeam.team.name}</option>
             		</c:forEach>     			
         		</select>
             </td>            
         </tr>
         <tr>
         	<td>职工姓名/编号  </td>
            <td><input type="text"  name="staffmsg" ></td>
         </tr>
         <tr>
             <td colspan="2" align="center"><input type="submit" value="提交"></td>
         </tr>

</table>
</form>
  </body>
</html>
