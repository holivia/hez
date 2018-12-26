<%@ page language="java" import="java.util.*,com.hez.domain.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
	 <base href="<%=basePath%>">
     
    <title>My JSP 'worktime' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">	
       <script type="text/javascript" src="js/ichart.1.2.1.min.js" ></script>     
       <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
       <script type="text/javascript" src="js/jquery.calendar.js"></script>    
       <script src="js/highcharts.js"></script>
       <script src="js/highcharts-more.js"></script>
       <script src="js/highcharts-zh_CN.js"></script>
       <link rel="stylesheet" type="text/css" href="css/worktime.css">
       <link rel="stylesheet" type="text/css" href="css/sandian.css">
		<link rel="stylesheet" type="text/css" href="css/yuanhuan.css">
		<style type="text/css">
			*{margin:0;padding:0}
			input{margin-top:0px;margin-left:0px;border:1px solid #AFAFAF;
			line-height:25px;font-size:10pt;width:200px;height:30px; text-align:center; border-color:#535353}
		</style>
       
     	<%
			List<Overtime> scatter_List=(List<Overtime>)request.getAttribute("scatter_List");
			String pie=(String)request.getAttribute("pie");
			String totalDuration=(String)request.getAttribute("sum");
		%>
		
		<script type="text/javascript">						
			var pie = '<%=pie %>';
			var totalDuration = '<%=totalDuration %>';
			//document.write(pie);
			
			pie=pie.split(" "); //array分割，自动转为数组
		
			for(var i = 0; i < pie.length; i=i+1 ){
				//document.write(pie[i]+"</br>");
				pie[i]=pie[i].split("-"); 
				for(var j = 0; j < pie[i].length; j=j+1 ){
					//document.write(pie[i][j]+"</br>");
				}
		}
						
		</script>
		<script type="text/javascript" src="js/yuanhuan.js"></script>
     	
  </head>

  <body>
	<div id="home_top">   	  		
    			<div id="div_menu"><img src="../images/menu.png" /></div>
    			<div id="span_menu">全部菜单</div>
    			<div id="font_fu"><img src="../images/font.png"/></div>     	    
    </div>
   <div id="backg">
                    <!--查询时间白色底纹                    -->
    <div class="date">
                      <!--查询日历蓝色底纹                     -->
    查询时间
    <input type="text" readonly="readonly" class="startTime"/>-
       <input type="text"  readonly="readonly" class="startTime"/>
    <input id="chaxun" type="button" value="查询"/>
    </div>
    
    
		
			
			<div id="bottom-left">
			    <div class="line"></div>
				<div class="title_information">团队工时统计</div>
				<div class="td_border">
					<div id="canvasDiv" style="padding-right: 81px; padding-top: 40px;"></div>
				</div>
			</div>
			
			
			
			<div id="bottom-right">
				<div class="line"></div>
				<div class="title_information">统计信息</div>
				<div class="td_bordert">
				<div id="paixu" style="white-space: nowrap;">
	
				
				排序方式
				<input id="th1" type="button" value="工时降序" class="paixu" onclick="SortTable(this)"/>
				<input id="th2" type="button" value="次数升序" class="paixu" onclick="SortTable(this)" />
				
				
				<table border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
							<th>姓名</th>
							<th>工时[H]</th>
							<th>次数[次]</th>
						</tr>
				<c:forEach items="${scatter_List}" var="list">
						<tr>
							<td name="td0">${list.staff.name }</td>
							<td name="td1">${list.duration}</td>
							<td name="td2">${list.mealcoupon}</td>
						</tr>						
			     	<tr>	   				
              </c:forEach>
				</table>
				</div>
			</div>
			</div>
		</div>
		<div class="home_bottom">
		     <div class="r1">Copyright &copy; 2015 中国电信股份有限公司福建分公司 。All Rights Reserved</div>
		</div>
		<script src='js/paixu.js'></script>  
  </body>
   <script type="text/javascript">
	$(".startTime").showCalendar();
</script> 
    
  
    
</html>
