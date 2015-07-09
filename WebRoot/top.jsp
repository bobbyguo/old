<%@ page contentType="text/html;charset=Gb2312" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<%@ include file="/commons/meta.jsp" %>
	<link href="${ctx}/styles/admin/admin.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
	
	<style type="text/css">
		body{
  			background: url(images/top.jpg) no-repeat 0px 0px;
  			font-family: Arial, Helvetica, sans-serif;
  			color: #555555;
  			padding: 0px;
  			margin: 5px;
  			text-align: center;  
  			font-size: 9pt;
		}

		#nav{
  			text-align: center;
  			font-size: 11px;
  			font-family: Arial, Helvetica, sans-serif;
  			margin:28px auto;
		}
		
		#nav ul{
  			margin: 0px;
 			padding: 0px;
  			list-style-type: none;
		}
		
		#nav li{  
  			float: left;
  		}
  		
		#nav li a{
  			text-decoration: none;
  			color: #666;  
  			background: url(images/menubg.gif) no-repeat 0px 0px;
  			width: 100px;
  			line-height: 20px;
  			display: block;
		}
		
		#nav li a:hover{
  			color: #CC0000;  
  			background: url(images/menubg.gif) no-repeat 0px -20px;  
		}
	</style>
	<script type="text/javascript">     
    	function click(){   
        	window.event.returnValue=false;   
        }   
            
        document.oncontextmenu = click;   
  	</script>
	<script type="text/javascript">
		$(function() {
			$.getJSON("${ctx}/menuAction!jsonDataToJsp.action", function(data) {
				for(var index = 0; index < $(data).length; index++) {
					var menu = $(data).get(index);
					var li = "<li onmousedown=\"this.id='current'\" onmouseup=\"this.id=''\"><a href=\"" + menu.url + "\" target=\"" + ((menu.target == "_top") ? "_top" : "leftFrame") + "\"><span>" + menu.name + "</span></a></li>"
					$("ul").append(li);
				}
			})
		});
	</script>
</head>

<body>
	<div align="right"  >
		<img alt="山东省计算中心重信通用软件有限责任公司" src="images/logo.jpg">
	</div>
	<div id="nav">
		<ul>
		</ul>
	</div>
	<div align="left" id="my" style="margin:50px auto;background:yellow">
		显示登录信息、欢迎信息等等
	</div>
</body>
</html>