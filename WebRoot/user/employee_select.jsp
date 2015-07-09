<%@ page language="java" pageEncoding="gbk"%>
<%@ include file="/commons/taglibs.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <sx:head debug="false" cache="false" compressed="false" />
    <%@ include file="/commons/meta.jsp" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/styles/tree_component.css">
	<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/css.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/tree_component.js"></script>
	<style type="text/css">
		a:HOVER {
			text-decoration: none;
		}
	</style>
    <script type="text/javascript">    	
    	$(function () {
    		var $node;
    		var structureId = '<%=request.getParameter("structureId")%>';
    		var parentDocument = window.dialogArguments.document;
    		
	    	$("#divForTree").tree({
		      	data  : {
		        	type  : "json",		        	
		        	url : "organization/selectEmployeeUserAction?structure.id=" + structureId
		      	},
		      	ui : {
		      		theme_name	: "classic",
		        	context : []
		      	},
		      	callback : {
		      		onselect : function(NODE, TREE_OBJ) {
		      			if($(NODE).hasClass("leaf")) {		      				
			      			$node = $(NODE);
	    					$("#sure").removeAttr("disabled");
		      			}else {
		      				$("#sure").attr("disabled", "disabled");
		      			}
		      		}
		      	}
		    });
		    
		    $("#sure").click(function() {
		    	parentDocument.getElementById("employeeName").value = $node.text();
	    		parentDocument.getElementById("employeeId").value = $node.attr("id");
	    		window.close();
		    });
		    
		    $("#cancel").click(function() {
    			window.close();
		    });
		});
  	</script>
  </head>
  <body>
  	<div style="padding: 10px;">
  		<sx:tabbedpanel id="tabContainer" cssStyle="width: 280px; height: 350px;" doLayout="true">
  			<sx:div id="baseinfo" label="请选择员工" >
  				<div id="divForTree"></div>
  			</sx:div>
  		</sx:tabbedpanel>
  		<input type="button" align="middle" id="sure" name="sure" value="确定" disabled="disabled" class="BigButton" />
  		<input type="button" align="middle" id="cancel" name="cancel" value="取消" class="BigButton" />
  	</div>
  </body>
</html>
