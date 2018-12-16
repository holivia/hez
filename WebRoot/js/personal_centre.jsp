<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'personal_centre.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/personal_centre.css">
<script src="javascript/jquery.js"></script>
<script src="javascript/highcharts.js"></script>
  </head>
  
  <body>
    <div id="divtop"></div>
    <div id="div_pinfo">
			
				<div id="personal_information_div">
					<table>
						<tr>
							<td id="td_name">金莲莲</td>
							<td id="text_g">&#91;200015&#93;</td>
							<td id="text_c"><img id="center_lock" src="images/center_lock.png" /><a href="js/password_update.jspjs/password_update.jsp">修改密码</a></td>
							<td id="text_c"><img id="update_img" src="images/update.png" />更新</td>
						</tr>
					</table>
				</div>
			
			<div id="div_out">
				<div id="p_information">
					<table>
						<tr>
							<td class="r1">联系电话：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">RTX账号：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">邮箱：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">通知方式：</td>
							<td class="p_content">15575994610</td>
						</tr>
					</table>
				</div>
				<div id="p_information_center">
					<table>
						<tr>
							<td class="r1">归属团队：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">入司时间：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">备注信息：</td>
							<td class="p_content">15575994610</td>
						</tr>						
					</table>
				</div>
				<div id="p_information_right">
					<table>
						<tr>
							<td class="r1">我的岗位：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">我的积分：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">我的功劳：</td>
							<td class="p_content">15575994610</td>
						</tr>
						<tr>
							<td class="r1">我的苦劳：</td>
							<td class="p_content">15575994610</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<div id="div_title">
			<div id="title_d">在线登记</div>
			<div id="title_d">工时查询</div>
			<div id="title_d">统计报表</div>
			<div id="title_d">员工信息</div>
			<div id="title_d">团队信息</div>			
		</div>
		
		<div id="div_left">
			
		</div>
		<div id="div_center">
			
		</div>
		<div id="div_right">
			
		</div>
		<script language="JavaScript">
$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '加班统计',
       align:'left'   
   };
 
   var xAxis = {
      categories: ['Jan','Feb','Mar'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '工时[h]'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   
   var series= [{
        name: 'Tokyo',
            data: [49.9, 71.5, 106.4]
        },{
            name: 'Berlin',
            data: [42.4, 33.2, 34.5]
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#div_left').highcharts(json);
  
});
</script>
		<script language="JavaScript">
$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '请假统计',
      align:'left'   
   };
 
   var xAxis = {
      categories: ['Jan','Feb','Mar'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '工时[h]'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   
   var series= [{
        name: 'Tokyo',
            data: [49.9, 71.5, 106.4]
        },{
            name: 'Berlin',
            data: [42.4, 33.2, 34.5]
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#div_center').highcharts(json);
  
});
</script>
	<script language="JavaScript">
$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '任务单统计',
      align:'left'
      
   };
 
   var xAxis = {
      categories: ['Jan','Feb','Mar'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '工时[h]'         
      }      
   };
   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };
   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };  
   var credits = {
      enabled: false
   };
   
   var series= [{
        name: 'Tokyo',
            data: [49.9, 71.5, 106.4]
        },{
            name: 'Berlin',
            data: [42.4, 33.2, 34.5]
   }];     
      
   var json = {};   
   json.chart = chart; 
   json.title = title;   
   
   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;  
   json.series = series;
   json.plotOptions = plotOptions;  
   json.credits = credits;
   $('#div_right').highcharts(json);
  
});
</script>
  </body>
</html>
