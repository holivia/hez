<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
        	oTable.rows[iRow].style.display = oTable.rows[iRow].style.display == "none"?"block":"none";   
        }
       </script>


	</head>

	<body>
		<div id="div_all">
			<div id="div_top">
				<div>
					<input type="button" onclick=setHiddenRow(document.getElementById(
						'table1'), 1);;
id="add_time" value="新增工时" />
				</div>
				<div id="">
					<a href="selectOvertimeTh?index=0"> <input
							type="button" id="out" value="模板导出" /> </a>
				</div>
				<div id="">
					<a href="daoru?hei.xlsx"> <input
							type="button" id="in" value="批量导入" /> </a>
				</div>
			</div>

			<div id="table_div">
				<form action="insertovertime" method="post">
					<table id="table1" border="5">
						<tr id="row1_info">
							<td>
								序号
							</td>
							<td>
								项目组
							</td>
							<td>
								日期
							</td>
							<td>
								餐券数
							</td>
							<td width="250px">
								开始时间-结束时间
							</td>
							<td>
								加班时长
							</td>
							<td>
								加班类型
							</td>
							<td>
								值班方式
							</td>
							<td>
								备注信息
							</td>
							<td style="width: 40px">
								操作
							</td>
						</tr>

						<tr id="row2_info">
							<!-- style="display:none;"  -->

							<td>

							</td>
							<td>
								<select name="teamid">
									<option value="">
										请选择
									</option>
									<c:forEach items="${requestScope.StaffTeamList}"
										var="StaffTeam">
										<option value="${StaffTeam.team.id }">
											${StaffTeam.team.name }
										</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input class="laydate-icon" id="demo" value="2018-9-10"
									name="date" style="width: 100px">
							</td>



							<td>
								<input id="c" type="text" name="mealcoupon" style="width: 60px">
							</td>

							<td>
								<input id="c" type="text" name="startime" style="width: 60px">
								&#126;
								<input type="text" name="endtime" style="width: 60px" id="c">
							</td>
							<td>
								<input id="c" type="text" name="duration" style="width: 50px">
							</td>
							<td>
								<select name="type" id="c">
									<option id="c" value="远程">
										远程
									</option>
									<option id="c" value="现场">
										现场
									</option>
								</select>

							</td>
							<td>
								<select name="dutymode" id="c">
									<option id="c" value="无">
										无
									</option>
									<option id="c" value="1">
										1
									</option>
								</select>
							</td>
							<td>
								<input id="c" type="text" name="notes"></input>
							</td>
							<td>
								<input id="c" type="submit" value="保存"></input>
							</td>

						</tr>

						<c:forEach items="${requestScope.OvertimeList}" var="overtime"
							varStatus="status">
							<tr id="trow">
								<td>
									${ status.index + 1}
								</td>


								<td>
									<!-- 团队 -->
									${overtime.team.name}
								</td>
								<td>
									<fmt:formatDate value="${overtime.date}" pattern="yyyy-MM-dd" />

								</td>
								<td>
									${overtime.mealcoupon}
								</td>
								<td>
									${overtime.startime}~${overtime.endtime}
								</td>
								<td>
									${overtime.duration}
								</td>
								<td>
									${overtime.type}

								</td>
								<td>
									${overtime.dutymode}

								</td>
								<td>
									${overtime.notes}
								</td>

								<td>
									<a
										href="updataOvertimeView?id=${ overtime.id}"
										target="_self">编辑</a>
									<a
										href="deleteOvertime?id=${overtime.id }"
										target="_self">
										删除</a>
								</td>

							</tr>



						</c:forEach>

					</table>
					<table align="right">

						<tr>
							<td align="right">
								${indexInfo}<a href="selectOvertimeTwo?index=1" target="_self"> <input
										type="button" value="上一页" /> </a>


								

								<a href="selectOvertimeTwo?index=2" target="_self"> <input
										type="button" value="下一页" /> </a>
							</td>
							
						</tr>
					</table>

				</form>
				<!-- 	<div>
					<ul>
						<li>
							<a href="selectOvertimeTwo?index=1" target="_self"><img
									id="left_img" src="images/left.png" /> </a>

						</li>
						<li>
							<select name="" id="selec_page">
								<option>
									第一页
								</option>
								<option>
									第二页
								</option>
								<option>
									第三页
								</option>
							</select>

						</li>
						<li>
							<a href="selectOvertime?index=2" target="_self"><img
									id="right_img" src="images/right.png" /> </a>
						</li>
					</ul>
				</div>
				 -->
			</div>

		</div>

		<div class="home_bottom">
			copyright&copy;中国电信股份有限公司福建分公司.All Right Reserverd
		</div>

		<script type="text/javascript">
	!function() {
		laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
		laydate( {
			elem : '#demo'
		});//绑定元素
	}();

	//日期范围限制
	var start = {
		elem : '#start',
		format : 'YYYY-MM-DD',
		min : laydate.now(), //设定最小日期为当前日期
		max : '2099-06-16', //最大日期
		istime : true,
		istoday : false,
		choose : function(datas) {
			end.min = datas; //开始日选好后，重置结束日的最小日期
		end.start = datas //将结束日的初始值设定为开始日
	}
	};

	var end = {
		elem : '#end',
		format : 'YYYY-MM-DD',
		min : laydate.now(),
		max : '2099-06-16',
		istime : true,
		istoday : false,
		choose : function(datas) {
			start.max = datas; //结束日选好后，充值开始日的最大日期
	}
	};
	laydate(start);
	laydate(end);

	//自定义日期格式
	laydate( {
		elem : '#test1',
		format : 'YYYY年MM月DD日',
		festival : true, //显示节日
		choose : function(datas) { //选择日期完毕的回调
			alert('得到：' + datas);
		}
	});

	//日期范围限定在昨天到明天
	laydate( {
		elem : '#hello3',
		min : laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
		max : laydate.now(+1)
	//+1代表明天，+2代表后天，以此类推
	});
</script>

	</body>
</html>
