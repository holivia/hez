<%@ page language="java" import="java.util.*,com.hez.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/home.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/c.css">
  <link rel="stylesheet" type="text/css" href="css/option_card.css">
  <link rel="stylesheet" type="text/css" href="css/home.css">
<script src="javascript/jquery.js"></script>
<script src="javascript/highcharts.js"></script>

  </head>
  
 <script type="text/javascript" >
  		function f(){
  		//alert("dianjile");
  		document.getElementById("target_2").style.display="block";
  			//$("#target_2").style.display.show();
  		}
  		function hidden_menu(){ 		
  		  document.getElementById("target_2").style.display="none";  			
  		}
  </script>
  
   <% List<Overtime> OvertimeList =(List<Overtime>)request.getAttribute("durationt3MonthList");
	 int[] team1Overtimes= new int[3];	
	 int[] team2Overtimes= new int[3];	
	 String team1Name=null;
	 String team2Name=null;
	 String[] mon=new String[3];
	 int j=0,k=0,x=0;
  	 for(int i=0;i<OvertimeList.size();i++){
		Overtime overtime=(Overtime)OvertimeList.get(i);
		if(overtime.getTeam().getId()==1){
			team1Overtimes[j++] = overtime.getDuration();
			team1Name=overtime.getTeam().getName();
			mon[x++]=overtime.getMealcoupon();
		}else if(overtime.getTeam().getId()==2){
			team2Overtimes[k++] = overtime.getDuration();
			team2Name=overtime.getTeam().getName();
		}
	}
%>

  <script language="JavaScript">

$(document).ready(function() {  
   var chart = {
      type: 'column'
   };
   var title = {
      text: '我的近三个月工时加班统计'   
   };
 
   var xAxis = {
      categories: ['<%= mon[0]%>','<%= mon[1]%>','<%= mon[2]%>'],
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
        name: '<%= team1Name %>',
            data: [<%= team1Overtimes[0]%>, <%= team1Overtimes[1]%>, <%= team1Overtimes[2]%>]
        },{
            name: '<%= team2Name %>',
            data: [<%= team2Overtimes[0]%>, <%= team2Overtimes[1]%>, <%= team2Overtimes[2]%>]
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
   $('#container').highcharts(json);
});
  </script>
  <body>

	
		<div id="home_top">
		
			<div id="wo">

				<div id="menu_top">
					<ul>
						<li>
							<div id="menu_li">
								<a  onclick="f()">全部菜单</a>
							</div>
							
							<ul id="target_2">
								<li>
									<a href="js/personal_centre.jsp" onclick="hidden_menu()">个人中心</a>
								</li>
								<li>
									<a href="js/line_record.jsp" onclick="hidden_menu()">在线登记</a>
								</li>
								<li>
									<a href="js/jobTime_search.jsp" onclick="hidden_menu()">工时查询</a>
								</li>
								<li>
									<a href="#" onclick="hidden_menu()">统计报表</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<div id="top_center">
					<div id="font_fu">
						<img src="images/font.png" />
					</div>
					<div id="div_option">
						<div id="div6">
						    
							<!--  <span id="myself_info">个人中心</span>-->
							<ul class="uu"></ul>
						</div>
					
					</div>
				</div>

				<div id="top_right">
					<table id="top_right_table">
						<tr>
							<td id="td1">
								欢迎您，
							</td>
							<td id="td2">
								刘晓芸
							</td>

							<td id="td3">
								<img id="top_lock" src="images/top_lock.png">
							</td>

							<td>
								<img id="quit" src="images/quit.png">
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		
		<div id="div3"></div>


	<div class="fill-icon">
				<div class="icon"><img  src="images/line_login.png" /><span><a id="span-line" href="js/line_record.jsp"  target="_parent">在线登记</a></span></div>
				<div class="icon"><img src="images/gs_find.png"/><span><a id="span-gs" href="selectOvertime">工时查询</a></span></div>
				<div class="icon"><img src="images/count.png"/><span><a id="span-count" href="selectForChart_Scatter">统计报表</a></span></div>
				<div class="icon"><img src="images/team_information.png"/><span><a id="span-team" href="select_team_teamSub">团队信息</a></span></div>
			</div>
	
   
   
		<div id="bottom-left">
				<div class="line"></div>
				<div class="title_information">员工信息</div>
				<div class="update_all">
					<div class="update_div"><img class="update_img" src="images/update.png"/></div>
					<div class="update">更新</div>
				</div>
				
				<div class="td_border">
					<table>
						<tr>
							<td class="r1">姓&emsp;&emsp;名：</td>
							<td class="name"><a href="selectInfo">金莲莲</a></td>
							<td class="r1">工&emsp;&emsp;号：</td>
							<td class="content">11</td>
						</tr>
						<tr>
							<td class="r1">入司时间：</td>
							<td class="content">ff</td>
						</tr>
						<tr>
							<td class="r1">归属团队：</td>
							<td class="content"></td>
						</tr>				
					</table>
					<table class="table2">
						<tr>
							<td class="r1">联系电话：</td>
							<td class="content">11</td>
							<td class="r1">邮&emsp;&emsp;箱：</td>
							<td class="content">11</td>
						</tr>
						<tr>
							<td class="r1">RTX账号：</td>
							<td class="content">ff</td>
							<td class="r1">通知方式：</td>
							<td class="content">ff</td>
						</tr>
						<tr>
							<td class="r1">备注信息：</td>
							<td class="content"></td>
						</tr>				
					</table>
					<table >
						<tr>
							<td class="r1">我的岗位：</td>
							<td class="name"></td>
							<td class="r1">我的功劳：</td>
							<td class="content">5次</td>
						</tr>
						<tr>
							<td class="r1">我的积分：</td>
							<td class="content">ff</td>
							<td class="r1">我的苦劳：</td>
							<td class="content">
								20小时</td>
						</tr>	
						<%
     						 String info=(String)request.getAttribute("resetPassword");
     						 if(info!=null){
     					%>	
     						
     						<tr >
     							<td colspan="4"  height="30" id="updatePass"><font color="red">${requestScope.resetPassword }</font>
     							<a href="js/password_update.jsp" >立即前往</a>
     							</td>
     						</tr>
     					<%	 
     						 }
     					%>			
					</table>
				</div>
			</div>			
	  
	  
        <div style="position:absolute;top:169px;right:10px; width: 600px;" id="to">
				<div class="line"></div>
				<div class="title_information">统计信息</div>
				<div class="find_all">
					<div class="update_div"><img class="update_img" src="images/find.png"/></div>
					<div class="update">查看</div>
				</div>
		</div>
			  <div  style="position:absolute;top:209px;right:10px;width=100%;height:370px;" id="container" ></div>			
	  
	
    			


  	<div class="home_bottom">copyright&copy;中国电信股份有限公司福建分公司.All Right Reserverd</div>

	
  </body>
  <%-- 
  	<script type="text/javascript" src="javascript/jquery-1.11.0.js"></script>
   <script type="text/javascript" language="JavaScript" src="javascript/option_card.js" charset="gbk"></script>

--%>			


</html>
