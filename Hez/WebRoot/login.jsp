<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/login.css">	
	
 <SCRIPT  type="text/javascript" language="javascript">
function check(){
	  var gh= document.getElementById("gh").value;
      var pass = document.getElementById("pass").value;
             if (gh.length <= 0) 
            {
                alert("请输入工号！");
                gh.focus();
            }
         else   if (pass.length <= 0) 
            {
                alert("请输入密码！");
                pass.focus();
	            return false;  
            }
            return true;	
}
</SCRIPT>
  </head>
  
  <body>
    <div id="login_top"><img src="images/login_top.png" /></div>
        <div id="login_cent">
          <div id="inner_right">
          <form action="sign_in"  name="myform"  method="post" >
            <table border="0">
            <tr><td><font color="red">${message }</font></td></tr>
              <tr><td id="staff_login">员工登录</td></tr>
              <tr>
                <td class="td_gh"><img id="people_img" src="images/people.png" />
                    <input id="gh" type="text" placeholder="工号" name="jobnumber" value="2015911101"/>              	
                </td>
              </tr>
              <tr>
                <td class="td_border"><img id="people_img" src="images/lock.png" />
                    <input  id="pass" type="text" placeholder="密码" name="password" name="password" value="123"/>
                </td>
              </tr>
              <tr><td id="forget_pass"><a href="js/password_forget.jsp">忘记密码</a></td></tr>
              <tr><td class="td_border"><input id="immediately_login" type="submit" value="立即登录" onclick="check();" /></td></tr>
            </table>        
         </form>                 
         </div>                             
        </div>
   <div id="login_bottom"><img src="images/login_bottom.png" /></div>
  </body>
</html>
