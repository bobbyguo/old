<%@ page language="java" contentType="text/html; charset=gb2312"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>重信公司快速开发平台</title>
 
    <style type="text/css">
		* { 
			margin:0 auto; 
			padding:0; 
			border:0;
		}
		
		body { 
			background:url(images/bg.jpg); 
			font:12px "宋体"; 
			color:#004C7E;
		}
		
		input { 
			border:1px solid #004C7E;
		}
		
		.main {
			padding-top:200px; 
			margin-top: 200px;
			text-align: center
			
		}
		
		.login { 
			background:#DDF1FE; 
			width:468px; 
			height:262px; 
			border:1px solid #000; 
			text-align:center
		}
		
		.top { 
			background:url(images/login_bg.jpg) repeat-x; 
			width:468px; 
			height:113px; 
			border:1px solid #2376B1; 
			margin-top:1px;
		}
		
		.logo { 
			background:url(images/logo.jpg) no-repeat; 
			width:214px; 
			height:50px; 
			float:left; 
			margin:29px 0 0 14px;
		}
		
		.lable { 
			background:url(images/lable.gif) no-repeat; 
			width:157px; 
			height:32px; 
			float:right; 
			margin:81px 31px 0 0;
		}
		
		.lable1 { 
			width:157px; 
			height:32px; 
			float:right; 
			margin:81px 31px 0 0;
		}
		
		.submit { 
			background:url(images/submit.gif) no-repeat; 
			width:71px; 
			height:24px; 
			border:0;
		} 
		
		.reset { 
			background:url(images/reset.gif) no-repeat; 
			width:71px; 
			height:24px; 
			border:0;
		} 
	</style>
  </head>

<body >
	<form method="post" id="loginForm" action="${pageContext.request.contextPath}/j_spring_security_check">
		<table width="100%" class="main" cellpadding="0" cellspacing="0">
	  		<tr>
	   			<td>
	    			<div class="login">
	     				<div class="top">
		  					<div class="logo"></div>
		  					<div class="lable1"><h3>ZXGS Framework</h3></div>
		 				</div>
		 				<table width="468" cellpadding="0" cellspacing="0" >
		   					<tr style="position: relative; left: 90px;">
		    					<td width="282" style="padding-top:17px;">
			   						<table width="100%" cellpadding="0" cellspacing="0">
			     						<tr align="center">
				   							<td  height="27">用户名:</td>
				   							<td ><input type="text" name="j_username" id="j_username" style="BigInput"/></td>
				 						</tr>
			     						<tr align="center">
				   							<td height="27">密&nbsp;&nbsp;码:</td>
				   							<td><input type="password" name="j_password" id="j_password" style="BigInput"/></td>
				 						</tr>
			   						</table>
			 					</td>		 
		   					</tr>
		   					<tr style="position: relative; left: 350px;">
		   						<td><input type="submit" value="登录" /></td>
		  					</tr>
		 				</table>
		 				<table width="100%" cellpadding="0" cellspacing="0" style="margin-top:28px;">
		   					<tr>
		     					<td align="center";>Copyright 2009 ShanDong ZhongXin General Software Co.Ltd</td>
		   					</tr>
		 				</table>
	    			</div>
	   			</td>
	  		</tr>  
		</table>
	</form>
</body>

</html> 