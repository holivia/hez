<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="css/home.css">
    <style>
			#div6 ul{
				width: auto;
				height: auto;
			}
				#div6 ul li {
					list-style: none;
					margin-left: 5px;
					text-align: center;
				}
				
				#div6 ul li>a {
					border-top-left-radius: 3px;
					border-top-right-radius: 3px;
					display: block;
					background: #ededed;
					width: 100px;
					height: 30px;
					line-height: 30px;
					border: 1px solid #d3d3d3;
				}
				#div6 ul li>a:hover{
					border: 1px solid #AAAAAA;
					background: #dadada;
					cursor: pointer;
				}
				#div6 ul li {
					float: left;
				}
				#div6 {
				width: 70px; 
	height: 30px;    
	background: #E9EDF1;  
	border-radius:5px 6px 0px 0px;
	padding-top:10px;
				}
				#target_2 {
					clear: both;
				}
				#div3 {
				width:100%;
				height:80%;
					clear: both;
					overflow: hidden;
				}
				.now{
					/*background: white !important;*/
					border: 1px solid #aaaaaa;
					color:#4E93E5;
	                font-weight: bold;
	                font-size: 12px;	
	                margin-left:10px;
	                

				}
				#div6 ul li a span{
					cursor: pointer;
				}
				iframe{
					width: 99%;
					height: 98%;					
					position: fixed;
                    top: 80px;
                    border:0px;
				}
		</style>
  </head>
 <script type="text/javascript" >
  		function f(){
  		//alert("dianjile");
  		document.getElementById("target_2").style.display="block";
  			//$("#target_2").style.display.show();
  		}
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
									<a href="js/personal_centre.jsp">个人中心</a>
								</li>
								<li>
									<a href="js/line_record.jsp">在线登记</a>
								</li>
								<li>
									<a href="js/jobTime_search.jsp">工时查询</a>
								</li>
								<li>
									<a href="">统计报表</a>
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
						    
							<span id="myself_info">个人中心</span>
							<ul class="uu"></ul>
						</div>
						<!--  <div id="angele"></div> -->
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
	</body>
	<script type="text/javascript" src="javascript/jquery-1.11.0.js"></script>
	<script>
		$(function() {
			var click = $('#target_2 a');
			function add(Name) {
				var add_li = $('<li><a class="now"><span>' + Name + '</span><span>×</span></a></li>');//点击连接添加一个选项
				var add_ifame = $('<iframe name="' + Name + '" src="#"></iframe>');//添加页面
				$('#div3 iframe').hide();
				$('.uu').append(add_li);//追加到后面
				$('#div3').append(add_ifame);
			}
			click.click(function() {
				var texts = $(this).text();
				var show1a = $('#div6 ul li a span:first-child');
				var nowgeshu = $('.uu li').length;
				for (var i = 0; i < show1a.length; i++) {
					if (show1a.eq(i).text() == texts) {
						alert('您已打开了一个相同的标签页！')
						return false;
					}
				}
				if (nowgeshu < 8) {
					$(this).attr('target', texts);
					$('.uu li a').removeClass('now');
					add(texts);
				} else if (nowgeshu == 8) {
					alert('您已打开了8个标签。请关闭部分标签后再打开新标签！');
					return false;
				}
			})
			$('#div6').on('click','ul li a span:last-child',function(event){
				if($(this).parent().hasClass('now')){
					$('#div6 ul li a').eq($('#div6 ul li').length-2).addClass('now');
				}
				var index = $('#div6 ul li').index($(this).parent().parent());
				$(this).parent().parent().remove();
				$('#div3 iframe').eq(index).remove();
				if($('#div3 iframe:visible').length==0){
					$('#div3 iframe:last-child').show();
				}
				event.stopPropagation();
			})
			$('#div6').on('click','ul li a',function(){
				if($(this).hasClass('now')){
					return false;
				}else{
					 $(this).addClass('now').parent().siblings().children().removeClass('now');
					 var index = $('#div6 ul li').index($(this).parent());
					 $('#div3 iframe').hide().eq(index).show();
				}
			})
		})
	</script>
</html>
