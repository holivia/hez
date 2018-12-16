<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'line_record.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
		<script type="text/javascript" src="javascript/laydate.js"></script>
		<link rel="stylesheet" type="text/css" href="css/line_record.css">
		<script type="text/javascript" src="javascript/jquery-1.9.1.min.js"></script>
<!-- 显示隐藏行 -->
       <script language="javascript" type="text/javascript">
             function setHiddenRow(oTable,iRow){  
        	oTable.rows[iRow].style.display = oTable.rows[iRow].style.display == "none"?"":"none";   
        }
       </script>


  </head>
  
  <body>
		 <div id="div_all">
			<div id="div_top">
				<div ><input type="button" onclick="setHiddenRow(document.getElementById('table1'),1)" id="add_time" value="新增工时"/></div>
				<div id=""><input type="button" id="out" value="模板导出"/></div>
				<div id=""><input type="button" id="in" value="批量导入"/></div>
			</div>
			
			<div id="table_div">
				<table id="table1">
					<tr id="row1_info">
						<td >序号</td>
						<td >项目组</td>
						<td >日期</td>
						<td >餐券数</td>
						<td width="250px">开始时间-结束时间</td>
						<td >加班时长</td>
						<td >加班类型</td>
						<td >值班方式</td>
						<td >备注信息</td>
						<td style="width:40px">操作</td>
					</tr>
					<tr id="row2_info" style="display:none">
						<td>1</td>
						<td>
							<select id="c" name="" id="select_team">
								<option id="c">CRM应用团队</option>
								<option id="c">七十二赫兹</option>
								<option id="c">七十二赫兹</option>
							</select>
						</td>
						<td><input class="laydate-icon" id="demo" value="2018-9-10" style="width:100px"></td>					
						<td><input id="c" type="text" value="" style="width:60px"></td>	
						
						<td><input id="c" type="text" value="" style="width:60px">&#126;<input type="text" style="width:60px" id="c"></td>
						<td><input id="c" type="text" value="" style="width:50px"></td>
						<td>
							<select name="work_type" id="c">
								<option id="c">远程</option>
								<option id="c">现场</option>
							</select>
						</td>
						<td>
							<select name="duty_ways" id="c">
								<option id="c">无</option>
								<option id="c">1</option>
							</select>
						</td>						
						<td><input id="c" type="text" value=""></input></td>
						<td></td>
					</tr>
					
					 
		<!--       	
        <c:forEach items="${requestScope.deptList}" var="dept"> </c:forEach>  
			-->	
             <tr id="trow">
             <td>1</td>
             <td>1</td>
             <td>1</td>
             <td>1</td> 
             <td>1</td>
             <td>1</td>
             <td>1</td>
             <td>1</td>
             <td></td>
             <td>            
               <a href="js/edit.jsp" target="_blank">编辑</a>                   
               <a> 删除</a>
             </td>
         </tr>          
       		<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >2</td>
					</tr>
					<tr>
						<td >10</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="home_bottom">copyright&copy;中国电信股份有限公司福建分公司.All Right Reserverd</div>
		
<script type="text/javascript">
!function(){
	laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
	laydate({elem: '#demo'});//绑定元素
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
