<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>


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
       
       <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
       <script type="text/javascript" src="js/jquery.calendar.js"></script>    
       <script src="js/highcharts.js"></script>
       <script src="js/highcharts-more.js"></script>
       <script src="js/highcharts-zh_CN.js"></script>
       
       <link rel="stylesheet" type="text/css" href="css/sandian.css">
		<link rel="stylesheet" href="css/yuanhuan.css">
        <style type="text/css">
			*{margin:0;padding:0}
			input{margin-top:0px;margin-left:0px;border:1px solid #AFAFAF;
			line-height:25px;font-size:10pt;width:200px;height:30px; text-align:center; border-color:#535353}
		</style>
       
       <%
			String scatter_List=(String)request.getAttribute("scatter_List");
			String pie=(String)request.getAttribute("pie");
			String totalDuration=(String)request.getAttribute("sum");
		%>
		
		<script type="text/javascript">						
			var array = '<%=scatter_List %>';
			var pie = '<%=pie %>';
			var totalDuration = '<%=totalDuration %>';
			//document.write(pie);
						
		</script>
		<script type="text/javascript" src="js/yuanhuan.js"></script>
     	<script src="js/sandian.js"></script>
		
  </head>
	
		
	
  <body >

    
    <div id="backg"/><!--查询时间白色底纹                    -->
    <div class="date"/> <!--查询日历蓝色底纹                     -->
    
    
    <form action="selectForChart_Scatter" method="post">
    查询时间
       <input type="text" readonly="readonly" class="starttime" name="startdate"/>-
       <input type="text"  readonly="readonly" class="startTime" name="enddate"/>
       <input id="chaxun" type="submit" value="查询"/>
      </form>
   </div>
   
    
    
			<div id="bottom-left">
			    <div class="line"></div>
				<div class="title_information">团队工时统计				
				</div>
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
				<div class="title_information">员工工时统计
				<form action="selectForChart_List" method="post" >
						<div>
						<input type="hidden" value=<%=sd %> name="startdate" />
						<input type="hidden" value=<%=ed %> name="enddate" />
						<input type="submit" value="排序查看" style=" position:absolute;top:70px;right:100px;"></input>
						</div>
					</form>
				</div>
				<div class="td_border2">
                     <div id="container1" style="height: 370px;width:580px; margin: 0 auto; z-index:-1">
				     </div>
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
