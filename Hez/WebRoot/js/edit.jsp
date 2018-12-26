<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<div>
			<iframe src="top.jsp" id="kj" frameborder="0" scrolling="no"></iframe>
		</div>

		<div id="edit_content">
			<form action="updataOvertime" method="post">

				<table align="center" border="0">
					<tr>
						<td>
							<input type="hidden" name="id" value="${requestScope.overtime.id}"      />
							
						</td>
					</tr>
					
					<tr>
						<td>
							项目组
						</td>
						<td>
							<select id="c" name="teamid" id="select_team" >
								<c:forEach items="${requestScope.saffteamList}" var="StaffTeam">

									<option value="${StaffTeam.team.id }"
										<c:if test="${StaffTeam.team.id==requestScope.overtime.staffteam.team.id }">selected="selected" 
						 </c:if>>
										${StaffTeam.team.name }
									</option>

								</c:forEach>
							</select>
							
						</td>
					</tr>
					<tr>
						<td>
							日期
						</td>
						<td>
							<input class="laydate-icon" id="demo" name="date"
								 value='<fmt:formatDate value="${overtime.date}" pattern="yyyy-MM-dd" />'
								  />
								
						</td>
					</tr>
					<tr>
						<td>
							餐券数
						</td>
						<td>
							<input id="c" type="text" name="mealcoupon"
								value="${requestScope.overtime.mealcoupon}" style="width: 60px">
						</td>
					</tr>
					<tr>
						<td>
							开始时间
						</td>
						<td>
							<input id="c" type="text" name="startime"
								value="${requestScope.overtime.startime}" style="width: 60px">
						</td>
					</tr>
					<tr>
						<td>
							结束时间
						</td>
						<td>
							<input type="text" style="width: 60px" id="c"
								name="endtime" value="${overtime.endtime}">
						</td>
					</tr>
					<tr>
						<td>
							加班时长
						</td>
						<td>
							<input id="c" type="text" name="duration"
								value="${requestScope.overtime.duration}"></input>
						</td>
					</tr>
					<tr>
						<td>
							加班类型
						</td>
						<td>
							<select name="type" id="c">
							<c:if test="${overtime.type==requestScope.overtime.staffteam.team.id }">selected="selected" 
						 </c:if>
								<option id="c" value="远程">
									远程
								</option>
								<option id="c" value="现场">
									现场
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							值班方式
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
					</tr>
					<tr>
						<td>
							备注信息
						</td>
						<td>
							<input id="c" type="text" name="notes"
								value="${requestScope.overtime.notes}"></input>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="重置" />
						</td>
						<td>
							<input type="submit" value="保存" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
