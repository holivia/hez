<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="../css/team.css">
		<style>
			input[type="text"]{
				text-align: center; 
				background-color: #6CADE3;
				border:0px solid #6CADE3;
				}
		</style>		
	</head>
	<body>
	<div id="home_top">   	  		
    	<div id="div_menu"><img src="../images/menu.png" /></div>
    	<div id="span_menu">全部菜单</div>
    	<div id="font_fu"><img src="../images/font.png"/></div>     	    
   </div>
   	<div id="div_pinfo">		
	<div id="table_out">
		<table align="center" cellspacing="20" cellpadding="20" width="500" height="50" bgcolor="#6CADE3">
            <tr>            	
                <td>团队名称</td>
                <td><input id="name" type="text"  value="请输入团队名"/></td>
            </tr>
            <tr> 
       			<td>所在区域</td>
       			<td><input id="area" type="text"  value="请输入所在区域"/></td>
            </tr>
            <tr> 
       			<td>团队负责人</td>
       			<td><input id="responsibe" type="text"  value="请输入团队负责人"/></td>
            </tr>
            <tr>
       			<td>创建时间</td>
       			<td><input id="time" type="text"  value="请输入团队创建时间"/></td>
            </tr>
            <tr>
            	<td><input type="reset" value="取消"></td>
            	<td><input type="submit" value="确定"></td>
            </tr>
    </table>
	</div>	
	</div>	
	</body>
</html>