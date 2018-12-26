<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>		
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/employee_info.css">
	</head>
	<body>
	<form action="alter_infoView" method="post">
	
   	<div id="div_pinfo">
		<div>
			<div class="line"></div>
			<div class="title_information">基本信息</div>
		</div>
		<div id="div_out">
			<table>
				<tr>
					<td class="r1">员工姓名：</td>
					<td class="p_content">${staffinfo.name }</td>
					<td class="r1">员工工号：</td>
					<td class="p_content">${staffinfo.jobnumber }</td>
					<td class="r1">员工性别：</td>
					<td class="p_content">${staffinfo.gender }</td>
					<td class="r1">入司时间：</td>
					<td class="p_content"><fmt:formatDate value="${staffinfo.entrytime }" pattern="yyyy-MM-dd"/></td>
				</tr>
			</table>
		</div>
		<div>
			<div class="line"></div>
			<div class="title_information">联系方式:</div>
		</div>
		<div id="div_out">
			<table>
				<tr>
					<td class="r1">联系方式：</td>
					<td class="p_content">${staffinfo.phone }</td>
					<td class="r1">电子邮箱：</td>
					<td class="p_content">${staffinfo.email }</td>
					<td class="r1">RTX账号：</td>
					<td class="p_content">${staffinfo.rtxaccount }</td>
					<td class="r1">工时填写通知方式：</td>
					<td class="p_content">${staffinfo.notificationway }</td>
				</tr>
			</table>
		</div>
		<div>
			<div class="line"></div>
			<div class="title_information">归属团队:</div>
		</div>
		<div style="width:100%;float:left;">
		<c:forEach items="${staffTeamList}" var="list">
		<div id="p_information" style="float:left;">
			<table class="title_d">			
				<tr>
					<td class="r1">团队编号：</td>
					<td class="p_content">${list.team.code}</td>
				</tr>
				<tr>
					<td class="r1">团队名称：</td>
					<td class="p_content">${list.team.name}</td>					
				</tr>
				<tr>
					<td class="r1">团队负责人：</td>
					<td class="p_content">${list.team.responsible}</td>					
				</tr>
				<tr>
					<td class="r1">备&nbsp;&nbsp;注：</td>
					<td class="p_content">${list.team.notes}</td>					
				</tr>
	     	<tr>				
			</table>
		</div>	
      </c:forEach>
		</div>
		<div align="right">			 
    		<input id="immediately_check" type="submit" value="编辑"/>
    	</div>    	
    	</div>
    	<div class="home_bottom">copyright&copy;中国电信股份有限公司福建分公司.All Right Reserverd</div>
	</form>	
	</body>
</html>
