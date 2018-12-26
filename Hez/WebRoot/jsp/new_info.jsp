<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head> <base href="<%=basePath%>">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/team.css">
		<style>
			
				input {
    margin-top: 0px;
    margin-left: 0px;
    border: 1px solid #AFAFAF;
    line-height: 25px;
    font-size: 10pt;
    width: 200px;
    height: 30px;
    text-align: center;
    border-color: #535353;}
		</style>		
	</head>
	<body>
	
   	<div id="div_pinfo">		
	<div id="table_out">
	<form action="insetTeam" method="post">
		<table align="center" cellspacing="0" cellpadding="0" width="500" height="50" border=1 >
            <tr>            	
                <td>团队名称</td>
                <td><input type="hidden" name="teamid" value="1">
                <input name="name" type="text"  value="请输入团队名" onfocus="javascript:if(this.value=='请输入团队名')this.value='';"/></td>
            </tr>
            <tr> 
       			<td>所在区域</td>
       			<td><input name="place" type="text"  value="请输入所在区域" onfocus="javascript:if(this.value=='请输入所在区域')this.value='';"/></td>
            </tr>
            <tr> 
       			<td>团队负责人</td>
       			<td><input name="responsible" type="text"  value="请输入团队负责人" onfocus="javascript:if(this.value=='请输入团队负责人')this.value='';"/></td>
            </tr>
            <tr> 
       			<td>团队编号</td>
       			<td><input name="code" type="text"  value="请输入团队编号" onfocus="javascript:if(this.value=='请输入团队编号')this.value='';"/></td>
            </tr>
            <tr>
       			<td>创建时间</td>
       			<td><input name="createTime" type="text"  value="请输入团队创建时间" onfocus="javascript:if(this.value=='请输入团队创建时间')this.value='';"/></td>
            </tr>
            <tr>
            	<td><input type="reset" value="取消"></td>
            	<td><input type="submit" value="确定"></td>
            </tr>
    </table>
    </form>
	</div>	
	</div>	
	</body>
</html>
