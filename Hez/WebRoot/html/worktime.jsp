<%@ page language="java" import="java.util.*,com.hez.domain.*" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
	 <base href="<%=basePath%>">
    
    <title>My JSP 'Forget_password.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
       <script type="text/javascript" src="js/ichart.1.2.1.min.js" ></script>
       
       <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
       <script type="text/javascript" src="js/jquery.calendar.js"></script>    
       <script src="js/highcharts.js"></script>
       <script src="js/highcharts-more.js"></script>
       <script src="js/highcharts-zh_CN.js"></script>
       <script src='js/paixu.js'></script>  
       <link rel="stylesheet" type="text/css" href="css/worktime.css">
		<link rel="stylesheet" href="css/yuanhuan.css">
        <style type="text/css">
			*{margin:0;padding:0}
			input{margin-top:0px;margin-left:0px;border:1px solid #AFAFAF;
			line-height:25px;font-size:10pt;width:200px;height:30px; text-align:center; border-color:#535353}
		</style>
       
       <%
			List<Overtime> list=(List<Overtime>)request.getAttribute("scatter_List");
			String pie=(String)request.getAttribute("pie");
			String totalDuration=(String)request.getAttribute("sum");
		%>
		
		<script type="text/javascript">						
			
			var pie = '<%=pie %>';
			var totalDuration = '<%=totalDuration %>';
			//document.write(pie);
						
		</script>
		<script type="text/javascript" src="js/yuanhuan.js"></script>
     	<script src="js/sandian.js"></script>
		
  </head>
	
		
	
  <body ">   
    <div id="backg"/><!--查询时间白色底纹                    -->
    <div class="date"/> <!--查询日历蓝色底纹                     -->
    
    
    <form action="selectForChart_List" method="post" >
    查询时间
       <input type="text" readonly="readonly" class="starttime" name="startdate"/>-
       <input type="text"  readonly="readonly" class="startTime" name="enddate"/>
       <input id="chaxun" type="submit" value="查询"/>
      </form>
   </div>
   
    
    
			<div id="bottom-left">
			    <div class="line"></div>
				<div class="title_information">团队工时统计</div>
				<div class="td_border">
					<div id="canvasDiv" style="padding-right: 81px; padding-top: 40px;"></div>
				</div>
			</div>
	 <%
		String sd= "null";String ed="null";
		String startdate = ( (String)request.getAttribute("startdate") );
		String enddate = ( (String)request.getAttribute("enddate") );
		if( startdate != null && !startdate.equals("") )
			sd = (String)request.getAttribute("startdate");
		if( enddate != null && !enddate.equals("") )
			ed = (String) request.getAttribute("enddate");			
	 %>
	<div id="bottom-right">
		<div class="line"></div>
		<div class="title_information">排序方式
			<form action="selectForChart_Scatter" method="get" >
				<div >
				<input type="hidden" value=<%=sd %> name="startdate" />
				<input type="hidden" value=<%=ed %> name="enddate" />
				<input type="submit" value="散点图查看" style=" position:absolute;top:70px;right:100px;z-index:2"></input>
				</div>
			</form>
					
					
				</div>
			<div class="td_border2">
				      	
				<input id="th1" type="button" value="工时降序" class="paixu" onclick="SortTable(this)"/>
				<input id="th2" type="button" value="次数升序" class="paixu" onclick="SortTable(this)" />
				<%
				
				 %>
                  	 	
			<%
			int i=0;int count=1;
			while(true){
				if(count>2) break;
				if(i==0){			
           	 %> 
             	<div id="container1" style="height: 370px;width: 600px;margin: 0 auto;top: 130;position: absolute;"> 
             	<table border="0" cellspacing="0" cellpadding="2" align="center"  >	
				 	<tr>
						<th>姓名</th>
						<th>工时[H]</th>
						<th>次数[次]</th>
					</tr>
             	<%
             	}else if(i==9){
             	%>
  
   
             	<table border="0" cellspacing="0" cellpadding="2" align="center"  style=" margin-top: -350;right: 100px;position: absolute;">	
				 	<tr>
						<th>姓名</th>
						<th>工时[H]</th>
						<th>次数[次]</th>
					</tr>
             	<%
             	}
             	 %>
				 	
				<%
             	for(int j=i;j<list.size();j++){
             		if(i==0&&j==9){
             			i=9;
             			break;
             		}
             		
					Overtime ot=list.get(j);
				
             	 %> 
             	 	<tr>
						<td name="td0"><%=ot.getStaff().getName() %></td>
						<td name="td1"><%=ot.getDuration() %></td>
						<td name="td2"><%=ot.getMealcoupon() %></td>
					</tr>	
				<%
				}
				 %>					
										         
			   	</table>
			<%
				count=count+1;
				
			}
			 %>
			
			
		</div>
			
	</div>		
</div>			
		
		
		<div class="home_bottom">
		     <div class="r1">Copyright &copy; 2015 中国电信股份有限公司福建分公司 。All Rights Reserved</div>
		</div>
		<script type="text/javascript">
			$(".startTime").showCalendar();
		</script>
  </body>
    
</html>
