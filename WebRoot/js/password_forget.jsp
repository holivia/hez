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
	<link rel="stylesheet" type="text/css" href="css/forget_password.css">
 <script language="javascript" type="text/javascript">

        var code;
        function createCode() {
       
            code = "";
            var codeLength = 6; //验证码的长度
            var checkCode = document.getElementById("checkCode");
            var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
            for (var i = 0; i < codeLength; i++) 
            {
                var charNum = Math.floor(Math.random() * 52);
                code += codeChars[charNum];
            }
            if (checkCode) 
            {
                checkCode.className = "code";
                checkCode.innerHTML = code;
            }
             document.getElementById("login_cent1").style.display="none";
        }
        function validateCode() 
        {
            var inputCode = document.getElementById("inputCode").value;
            var inputMail = document.getElementById("inputMail").value; 
             if (inputMail.length <= 0) 
            {
                alert("请输入邮箱！");
                return false;
            }
          else   if (inputCode.length <= 0) 
            {
                alert("请输入验证码！");
                return false;
            }
           else if (inputCode.toUpperCase() != code.toUpperCase()) 
            {
                alert("验证码输入有误！");
                createCode();
                return false;
            }
            else 
            {
                alert("验证码正确！");
            	return true;        
            }                                     
        }
        function checkPassword(){
            var p = document.getElementById("p").value;
            var pass = document.getElementById("pass").value;
             if (p.length <= 0) 
            {
                alert("请输入新密码！");
            }
         else   if (pass.length <= 0) 
            {
                alert("请输入确认密码！");
            }
            else if (p!= pass) 
            {
                alert("两次密码不一致！");              
            }
        
        }    
   </script>
  </head>

  <body onload="createCode()">
    <div id="login_top"><img src="images/login_top.png" /></div>
    
   
    <form id="form1"  onsubmit='return validateCode()' action="forgetPassword" method="post">
        <div id="login_cent">
        <div id="d"><div id="td1">1确认账号</div> <hr/></div>
           <div id="div_in">      
           <table border="0" cellspacing="3" cellpadding="3" align="center" >
           <tr><td><font color="red">${requestScope.accountinfo }</font></td></tr>
            <tr><td><font color="red">${requestScope.codeinfo }</font></td></tr>
           <tr><td >请填写您需要找回的账号：</td><td><input id="inputMail" type="text"  placeholder="请输入您的账号" name="accountNumber" /></td></tr>
            <tr><td >请填写您用于验证的邮箱：</td><td ><input id="inputMail" type="text" placeholder="请输入您的邮箱" name="receiverMail"/></td></tr>
           <tr>
           <td height="50"><input type="text"   id="inputCode" type="text" placeholder="请输入验证码"/></td>
           <td height="50"><div class="code" id="checkCode" onclick="createCode()" ></div></td>
           <td><a  href="Forget_password.jsp" onclick="createCode()">看不清换一张</a></td>
           </tr>
           <tr><td colspan="3" height="50"><input id="Button1"  type="submit" value="下一步" /></td></tr>
           </table>                       
        </div>
        </div> 
        </form>
        
         
      
   <div id="login_bottom"><img src="images/login_bottom.png" /></div>
 	</body>
</html>
