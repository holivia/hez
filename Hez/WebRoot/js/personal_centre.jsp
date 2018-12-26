<%@ page language="java" import="java.util.*,com.hez.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
   <% 
   
   List<Overtime> durationList =(List<Overtime>)request.getAttribute("durationList");
	 int[] durationteam1Overtimes= new int[3];	
	 int[] durationteam2Overtimes= new int[3];	
	 String durationteam1Name=null;
	 String durationteam2Name=null;
	 String[] durationmon=new String[3];
	 int j=0,k=0,x=0;
  	 for(int i=0;i<6;i++){
		Overtime overtime=(Overtime)durationList.get(i);
		if(overtime.getTeam().getId()==1){
			durationteam1Overtimes[j++] = overtime.getDuration();
			durationteam1Name=overtime.getTeam().getName();
			durationmon[x++]=overtime.getMealcoupon();
		}else if(overtime.getTeam().getId()==2){
			durationteam2Overtimes[k++] = overtime.getDuration();
			durationteam2Name=overtime.getTeam().getName();
		}
	}
	
	
	List<Overtime> dayoffList =(List<Overtime>)request.getAttribute("dayoffList");
	 int[] dayoffteam1Overtimes= new int[3];	
	 int[] dayoffteam2Overtimes= new int[3];	
	 String dayoffteam1Name=null;
	 String dayoffteam2Name=null;
	 String[] dayoffmon=new String[3];
	 int dayoffj=0,dayoffk=0,dayoffx=0;
  	 for(int i=0;i<6;i++){
		Overtime overtime=(Overtime)dayoffList.get(i);
		if(overtime.getTeam().getId()==1){
			dayoffteam1Overtimes[dayoffj++] = overtime.getDayoff();
			dayoffteam1Name=overtime.getTeam().getName();
			dayoffmon[dayoffx++]=overtime.getMealcoupon();
		}else if(overtime.getTeam().getId()==2){
			dayoffteam2Overtimes[dayoffk++] = overtime.getDayoff();
			dayoffteam2Name=overtime.getTeam().getName();
		}
	}
	
	
	List<Overtime> assignmentList =(List<Overtime>)request.getAttribute("assignmentList");
	 int[] assignmentteam1Overtimes= new int[3];	
	 int[] assignmentteam2Overtimes= new int[3];	
	 String assignmentteam1Name=null;
	 String assignmentteam2Name=null;
	 String[] assignmentmon=new String[3];
	 int assignmentj=0,assignmentk=0,assignmentx=0;
  	 for(int i=0;i<6;i++){
		Overtime overtime=(Overtime)assignmentList.get(i);
		if(overtime.getTeam().getId()==1){
			assignmentteam1Overtimes[assignmentj++] = overtime.getAssignment();
			assignmentteam1Name=overtime.getTeam().getName();
			assignmentmon[assignmentx++]=overtime.getMealcoupon();
		}else if(overtime.getTeam().getId()==2){
			assignmentteam2Overtimes[assignmentk++] = overtime.getAssignment();
			assignmentteam2Name=overtime.getTeam().getName();
		}
	}
%>
  <body>
    <div id="divtop"></div>
    <div id="div_pinfo">
			
				<div id="personal_information_div">
					<table>
						<tr>
							<td id="td_name">${sessionScope.staff.name}</td>
							<td id="text_g">&#91;${sessionScope.staff.jobnumber}&#93;</td>
							<td id="text_c"><img id="center_lock" src="images/center_lock.png" /><a href="js/password_update.jsp">修改密码</a></td>
							<td id="text_c"><img id="update_img" src="images/update.png" />更新</td>
						</tr>
					</table>
				</div>
			
			<div id="div_out">
				<div id="p_information">
					<table>
						<tr>
							<td class="r1">联系电话：</td>
							<td class="p_content">${sessionScope.staff.phone}</td>
						</tr>
						<tr>
							<td class="r1">RTX账号：</td>
							<td class="p_content">${sessionScope.staff.rtxaccount}</td>
						</tr>
						<tr>
							<td class="r1">邮箱：</td>
							<td class="p_content">${sessionScope.staff.email}</td>
						</tr>
						<tr>
							<td class="r1">通知方式：</td>
							<td class="p_content">${sessionScope.staff.notificationway}</td>
						</tr>
					</table>
				</div>
				<div id="p_information_center">
					<table>
						<tr>
							<td class="r1">归属团队：</td>
							<td class="p_content"><c:forEach items="${requestScope.staffteam}" var="staffteam">${staffteam.team.name }
						
						</c:forEach>
						</tr>
						<tr>
							<td class="r1">入司时间：</td>
							<td class="p_content"><fmt:formatDate value="${sessionScope.staff.entrytime }"
							pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td class="r1">备注信息：</td>
							<td class="p_content">${sessionScope.staff.notes}</td>
						</tr>						
					</table>
				</div>
				<div id="p_information_right">
					<table>
						<tr>
							<td class="r1">我的岗位：</td>
							<td class="p_content">${sessionScope.staff.position}</td>
						</tr>
						<tr>
							<td class="r1">我的积分：</td>
							<td class="p_content">${sessionScope.staff.points}</td>
						</tr>
						<tr>
							<td class="r1">我的功劳：</td>
							<td class="p_content">${sessionScope.staff.merit}</td>
						</tr>
						<tr>
							<td class="r1">我的苦劳：</td>
							<td class="p_content">${sessionScope.staff.hard}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<div id="div_title">
			<div id="title_d"><a href="js/line_record.jsp">在线登记</a></div>
			<div id="title_d"><a href="selectOvertime">工时查询</a></div>
			<div id="title_d"><a href="selectForChart_Scatter">统计报表</a></div>
			<div id="title_d"><a href="employee_info">员工信息</a></div>
			<div id="title_d"><a href="select_team_teamSub">团队信息</a></div>			
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
      categories: ['<%= durationmon[0]%>','<%= durationmon[1]%>','<%= durationmon[2]%>'],
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
   			name: '<%= durationteam1Name %>',
            data: [<%= durationteam1Overtimes[0]%>, <%= durationteam1Overtimes[1]%>, <%= durationteam1Overtimes[2]%>]
        },{
            name: '<%= durationteam2Name %>',
            data: [<%= durationteam2Overtimes[0]%>, <%= durationteam2Overtimes[1]%>, <%= durationteam2Overtimes[2]%>]
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
      categories: ['<%= dayoffmon[0]%>','<%= dayoffmon[1]%>','<%= dayoffmon[2]%>'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '请假[h]'         
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
      		name: '<%= dayoffteam1Name %>',
            data: [<%= dayoffteam1Overtimes[0]%>, <%= dayoffteam1Overtimes[1]%>, <%= dayoffteam1Overtimes[2]%>]
        },{
            name: '<%= dayoffteam2Name %>',
            data: [<%= dayoffteam2Overtimes[0]%>, <%= dayoffteam2Overtimes[1]%>, <%= dayoffteam2Overtimes[2]%>]
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
      categories: ['<%= assignmentmon[0]%>','<%= assignmentmon[1]%>','<%= assignmentmon[2]%>'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '任务单[h]'         
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
       		name: '<%= assignmentteam1Name %>',
            data: [<%= assignmentteam1Overtimes[0]%>, <%= assignmentteam1Overtimes[1]%>, <%= assignmentteam1Overtimes[2]%>]
        },{
            name: '<%= assignmentteam2Name %>',
            data: [<%= assignmentteam2Overtimes[0]%>, <%= assignmentteam2Overtimes[1]%>, <%= assignmentteam2Overtimes[2]%>]
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
