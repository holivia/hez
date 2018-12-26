<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Forget_password.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/alter_password.css">
 <script language="javascript" type="text/javascript">

        
        function checkPassword(){
            var p = document.getElementById("p").value;
            var pass = document.getElementById("pass").value;
             if (p.length <= 0) 
            {
                alert("请输入新密码！");
                return false;
            }
         else   if (pass.length <= 0) 
            {
                alert("请输入确认密码！");
                return false;
            }
            else if (p!= pass) 
            {
                alert("两次密码不一致！");  
                return false;            
            }
            else{
            	return true;
            }
        
        }    
     </script>
  </head>

  <body>
    
         <form id="form2" onsubmit="return checkPassword()" action="staffUpdatePassword" method="post">
        <div id="login_cent1">
        <div id="d"><div id="td2">修改密码</div> <hr/></div>
           <div id="div_in">      
           <table border="0" cellspacing="3" cellpadding="3" align="center" >
           <tr><td><font color="red">${requestScope.codeinfo }</font></td></tr>
           <tr><td><input type="hidden" value=${requestScope.verificationCode} name="verificationCode"></input></td></tr>
           <tr><td><input type="hidden" value=${requestScope.accountNumber} name="accountNumber"></input></td></tr>
            <tr><td><font color="red">${ message}</font></td></tr>
    		<tr>
            	<td height="30">请输入原密码：</td>
            	<td height="30"><input  type="text" placeholder="邮箱验证码" name="oldpassword" /></td>
            </tr>
            <tr>
            	<td height="30">请输入新密码：</td>
            	<td height="30"><input id="p" type="text" placeholder="密码不能为空" name="newpassword" /></td>
            </tr>
           <tr>
           <td>请确认新密码:</td>
           <td height="50"><input type="text" id="pass" type="text" placeholder="两次密码需一致"/></td>
          
           </tr>
           <tr>
           <td  height="50"><input id="Button2"  type="reset" value="重置" /></td>
           <td  height="50"><input id="Button2"   type="submit" value="确定" /></td>
           </tr>
           </table>                       
        </div>
        </div> 
        </form>
      
 
  </body>
</html>
