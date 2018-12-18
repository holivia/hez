<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'jobTime_search.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<script type="text/javascript" src="javascript/laydate.js"></script>
		<link rel="stylesheet" type="text/css" href="css/jobTime_search.css">

  </head>
  
  	<body>
   <div id="div_all">
			<div id="div_top">
				<form action="selectOvertime" method="post">
					<table id="t1">
						<tr>
							<td id="tr_font"> 查询时间</td>
							<td id="tr_double">
							<input class="laydate-icon" id="demo" style="height:24px;width:100px;border-radius:5px 5px 5px 5px" value="2017-12-30" name="startdate" >-
	                        <input class="laydate-icon" id="demo1" style="height:24px;width:100px;border-radius:5px 5px 5px 5px" value="2018-12-12" name="enddate"></td>
							<td id="tr_font">项目团队</td>
							<td id="tr_font"><select id="select_team" name="teamname" >
								<option value="">请选择</option>
             					<c:forEach items="${requestScope.StaffTeamList}" var="StaffTeam">			
             						<option value='${StaffTeam.team.name }'>${StaffTeam.team.name}</option>
             					</c:forEach>   
								
							</select></td>
							<td id="tr_font">员工姓名/编号</td>
							<td id="tr_font"><input id="demo2" placeholder="输入姓名/工号" type="text" name="staffmsg"></td>
							<td id="tr_font"><input type="submit" value="查询" id="search_button"></td>						
						</tr>
					</table>
				</form>
				<div id=""><input type="button" id="moban_out" value="导出查询结果"></div>
			</div>
			
			<div id="table_div">
				<table id="t2" border="1px">
					<tr id="row1_info">
						<td >序号</td>
						<td width="220px">项目组</td>
						<td >工号</td>
						<td >姓名</td>
						<td >日期</td>
						<td >餐券数</td>
						<td width="150px">开始时间-结束时间</td>
						<td >加班时长</td>
						<td >加班类型</td>
						<td >值班方式</td>
						<td width="200px">备注信息</td>						
					</tr>
					<% 
 						int i=1;	
 					%>
 					<c:forEach items="${requestScope.OvertimeList}" var="overtime">
					<tr id="tr2">
						<td ><%=i %></td>
						<td >${overtime.staffteam.team.name }</td>
						<td >${overtime.staffteam.staff.jobnumber }</td>
						<td >${overtime.staffteam.staff.name }</td>
						<td ><fmt:formatDate value="${overtime.date}" pattern="yyyy-MM-dd"/></td>
						<td >${overtime.mealcoupon }</td>
						<td >${overtime.startime }-${overtime.endtime }</td>
						<td >${overtime.duration }</td>
						<td >${overtime.type }</td>
						<td >${overtime.dutymode }</td>
						<td >${overtime.notes }</td>
					</tr>
					<%
						i=i+1;
					 %>
					 </c:forEach>
					</table>
				</div>
				<div id="auto">
					<table border="0">
						<tr>
							<td width="1150px"><strong>本次查询加班:<font color="#FF6F11">${duration }</font>小时</strong></td>
							<td width="20px"><img id="left_img" src="images/left.png" /></td>
							<td width="40px">
								
								<select name="" id="selec_page">
								<option>第一页</option>
								<option>第二页</option>
								<option>第三页</option>
							</select></td>
							<td ><img id="right_img" src="images/right.png" /></td>
							
						
						</tr>
					</table>
					
					
				</div>
			</div>
			
			
            	<div class="home_bottom">copyright&copy;中国电信股份有限公司福建分公司.All Right Reserverd</div>
           
			
	
	<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
	laydate({elem: '#demo1'});
	
}();

//日期范围限制
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: false,
    choose: function(datas){
         end.min = datas; //开始日选好后，重置结束日的最小日期
         end.start = datas //将结束日的初始值设定为开始日
    }
};

var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
    }
};
laydate(start);
laydate(end);

//自定义日期格式
laydate({
    elem: '#test1',
    format: 'YYYY年MM月DD日',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
        alert('得到：'+datas);
    }
});

//日期范围限定在昨天到明天
laydate({
    elem: '#hello3',
    min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
    max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
});
</script>
	</body>
</html>
