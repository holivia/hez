<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/team.css">
		    <script src="js/jquery.min.js"></script>
    
	</head>
	<body>
	<div id="home_top">   	  		
    	<div id="div_menu"><img src="images/menu.png" /></div>
    	<div id="span_menu">全部菜单</div>
    	<div id="font_fu"><img src="images/font.png"/></div>     	    
   </div>
   	<div id="div_pinfo">
		
		<div id="div_out">
			<table>
				<tr>
					<td class="r1"><input size="30" type="text" value="请输入团队名称/负责人"/></td>
					<td class="p_content"><input id="immediately_check" type="button" value="查询"/></td>
				</tr>
			</table>
		</div>
	<div class="table-a" id="table_out">
		<table align="center" border="0" cellspacing="0" cellpadding="0" width="1000" height="50">
        <thead>
            <tr bgcolor="#dcdcdc">            	
                <th>团队名称</th>
       			<th>所在区域</th>
       			<th>团队负责人</th>
       			<th>创建时间</th>
       			<th>操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${teamList}" var="list" >
            <tr class="parent" >
                <td>
                <input type="hidden" value="${list.code}">
        			<img src="images/bg09.png" id="image"/>
        			${list.name}</td>
                <td>${list.place}</td>
                <td>${list.responsible}</td>
                <td><fmt:formatDate value="${list.createTime }" pattern="yyyy-MM-dd"/></td>
                <td>
                	<div id="title_d"><a href="new_team.jsp">新增子节点</a></div>
					<div id="title_d"><a href="alter_team.jsp">修改</a></div>
					<div id="title_d"><a href="delete">删除</a></div>
                </td>
            </tr>
            
            <c:forEach items="${list.teamSubs}"  var="sub">
            <tr class="child_row_01" id="row_02">            
                <td>
                	<img src="images/bg09.png" id="img1"/>
        			${sub.name }</td>
                <td>${sub.place }</td>
                <td>${sub.responsible }</td>
                <td><fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd"/></td>
                <td>
                	<div id="title_d"><a href="new_team.jsp">新增子节点</a></div>
					<div id="title_d"><a href="alter_team.jsp">修改</a></div>
					<div id="title_d"><a href="delete">删除</a></div>
                </td>                
            </tr>
            <tr class="thchild_row_01 row_02">            
                <td>${sub.name }</td>
                <td>${sub.place }</td>
                <td>${sub.responsible }</td>
                <td><fmt:formatDate value="${sub.createTime }" pattern="yyyy-MM-dd"/></td>
                <td>
                	<div id="title_d"><a href="new_team.jsp">新增子节点</a></div>
					<div id="title_d"><a href="alter_team.jsp">修改</a></div>
					<div id="title_d"><a href="delete">删除</a></div>
                </td>                
            </tr>
            </c:forEach>
                   
            </c:forEach>
        </tbody>
    </table>
	</div>	
	</div>	
	</body>
	<script>
    	
        $(function ()
        {        	
        	$(".child_row_01,.thchild_row_01").hide();
            $("table tbody .parent:nth-child(1)").attr("id","row_01");
            $("tr.parent").click(function (){
                $(this).toggleClass("selected").siblings('.child_' + this.id).fadeToggle();
                var elem=document.getElementsByClassName('thchild_' + this.id);
                if(elem){
                	$(elem).hide();
                }
                var el = document.getElementById('image');
                var ele1 = document.getElementById('img1');
                var ele2 = document.getElementById('img2');
                var ele3 = document.getElementById('img3');
            	if(el.src.match("bg09")){
            		el.src = "images/bg10.png";
            	}				
				else{					
					el.src = "images/bg09.png";
					ele1.src = "images/bg09.png";
					ele2.src = "images/bg09.png";
					ele3.src = "images/bg09.png";
				}
            });
            $("#row_02").click(function (){
                $(this).toggleClass("selected").siblings('.'+this.id).fadeToggle();
                var ele1 = document.getElementById('img1');
            	if(ele1.src.match("bg09")){
            		ele1.src = "images/bg10.png";
            	}				
				else
					ele1.src = "images/bg09.png";				
			});
			$("#row_03").click(function (){
                $(this).toggleClass("selected").siblings('.'+this.id).fadeToggle();
                
				var ele2 = document.getElementById('img2');
            	if(ele2.src.match("bg09")){
            		ele2.src = "images/bg10.png";
            	}				
				else
					ele2.src = "images/bg09.png";				
			});
			$("#row_04").click(function (){
                $(this).toggleClass("selected").siblings('.'+this.id).fadeToggle();
                
				var ele2 = document.getElementById('img3');
            	if(ele2.src.match("bg09")){
            		ele2.src = "images/bg10.png";
            	}				
				else
					ele2.src = "images/bg09.png";				
			});
        });        
    </script> 
</html>
