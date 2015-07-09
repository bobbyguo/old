<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="/commons/taglibs.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%@ include file="/commons/meta.jsp" %>
    
    <title>jsTree</title>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/tree_component.css">
	<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
	<!--  <script type="text/javascript" src="${ctx}/scripts/jquery.tree.js"></script>-->
	<script type="text/javascript" src="${ctx}/scripts/css.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/tree_component.js"></script>
	
	<style type="text/css">
		a:HOVER {
			text-decoration: none;
		}
	</style>
	 
	<script type="text/javascript">		     
    	function click(){   
        	window.event.returnValue=false;   
        }   
            
        document.oncontextmenu = click;
  	</script>
	
	<script type="text/javascript">
  		$(function () {
  			//清理缓存树信息
  			$.ajaxSetup({cache: false});
  			
	    	$("#divForTree").tree({
		      	data  : {
		      		type  : "json",
		      		url : "menuAction!getDataForSecondLeftFrame.action?menu.id=" + '<%=request.getParameter("menu.id")%>'
		      	},
		      	ui : {
		        	context : []
		        },
		        callback : {
		      		onselect : function(NODE, TREE_OBJ) {
		      			var $this = $(NODE).is("li") ? $(NODE) : $(NODE).parent();
		      			var mainFrame = parent.parent.document.getElementById("mainFrame");
		      			
		      			if($this.children("a").attr("href").indexOf("null") == -1) {
		      				mainFrame.src = $this.children("a").attr("href");
		      			}
		      		}
		      	}
		    });
		});
  	</script>
  </head>
  
  <body>
    <div id="divForTree"></div>
  </body>
</html>
