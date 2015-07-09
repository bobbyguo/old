<%@ page contentType="text/html;charset=Gb2312" %>
<%@ include file="/commons/taglibs.jsp" %>

<html>
<head>	
	<%@ include file="/commons/meta.jsp" %>
	
	<link href="${ctx}/styles/admin/admin.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/GooAccordion.css"/>
	<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>	
	<script type="text/javascript" src="${ctx}/scripts/GooAccordion.js"></script>
	  
	<script type="text/javascript">     
    	function click(){   
        	window.event.returnValue=false;   
        }   
            
        document.oncontextmenu = click;   
  	</script>
</head>

<body>
  	<div id="leftMenu" style="margin-top:5px;"></div>
  	<script type="text/javascript">
  		var menuId = '<%=request.getParameter("menu.id")%>';
  		if(menuId == "null") {
  			menuId = 2;
  		}
  		
  		$(function() {
  		
  			$.getJSON("menuAction!jsonDataToLeftFrame.action?menu.id=" + menuId, function(data) {
  				var leftMenu = $.createGooAccordion($("#leftMenu"),0,$(window).height(),$(data));
  				//leftMenu.openItem(0);
  			})
  			
  		});
  		
	</script>
</body>
</html>