<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/frame.css">
  </head>
  
  <body>
  <div ><iframe src="top.jsp" id="kj" frameborder="0" scrolling="no"></iframe></div>
  
    <div id="edit_content"><form>
    <table align="center" border="0">
    <tr>
      <td>项目组</td>
      <td>
        <select id="c" name="" id="select_team">
								<option id="c">CRM应用团队</option>
								<option id="c">七十二赫兹</option>
								<option id="c">七十二赫兹</option>
							</select>
      </td>
    </tr>
    <tr>
      <td>日期</td>
     <td><input class="laydate-icon" id="demo" value="2018-9-10" style="width:100px"></td>
    </tr>
    <tr>
      <td>餐券数</td>
      <td><input id="c" type="text" value="" style="width:60px"></td>	
    </tr>
    <tr>
      <td>开始时间
</td>
     <td><input id="c" type="text" value="" style="width:60px"></td>
    </tr>
    <tr> 
    <td>结束时间</td>
    <td><input type="text" style="width:60px" id="c"></td>
    </tr>
    <tr>
      <td>加班时长</td>
      <td><input id="c" type="text" value=""></input></td>
    </tr>
    <tr>
      <td>加班类型</td>
     <td><select name="work_type" id="c">
								<option id="c">远程</option>
								<option id="c">现场</option>
							</select></td>
    </tr>
    <tr>
      <td>值班方式</td>
      <td><select name="duty_ways" id="c">
								<option id="c">无</option>
								<option id="c">1</option>
							</select></td>
    </tr><tr>
      <td>备注信息</td>
      <td><input id="c" type="text" value=""></input></td>
    </tr>
    <tr>
    <td><input type="button" value="重置"/></td>
    <td><input type="button" value="保存"/></td>
    </tr>
    </table>
    </form>
    </div>
  </body>
</html>
