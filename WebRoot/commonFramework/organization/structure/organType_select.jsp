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
    <script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
    <script type="text/javascript">
    	var structureId = '<%=request.getParameter("structureId")%>';
    	
    	$(function() {    		    		
    		$.getJSON("organization/getRuleStructureAction?structure.id=" + structureId, function(data) {
    			var $content = $("#baseinfo");
    			if($(data).length == 0) {
    				$content.append("<br />");
    				$content.append("<font color='red'>该成员下不能再添加下级成员!</font>");
    				$content.append("<br /><br />");
    				$content.append('<input type="button" align="middle" name="close" value="返回" onclick="window.close();" class="BigButton" />');
    			}else {
    				$content.append("<br />类型:");
    				$content.append("<select id='organTypeId'></select><br /><br />");
    				$content.append('<input type="button" align="middle" id="sure" name="sure" value="确定" onclick="makeSure();" class="BigButton" />');
    				$content.append('<input type="button" align="middle" name="close" value="关闭" onclick="window.close();" class="BigButton" />');
    				for(var i = 0; i < $(data).length; i++) {
	    				var organType = $(data).get(i);    				
	    				var option = "<option value='" + organType.id + "'>" + organType.name + "</option>";
	    				$("select").append(option);
	    			}
    			} 			
    		});
    		
    		$("#sure").click(function() {
    			window.dialogArguments.document.getElementById("structureOrganOrganTypeId").value = document.all.organTypeId.value;
    			window.dialogArguments.document.getElementById("structureId").value = structureId;
    			window.dialogArguments.document.getElementById("commit").click();
    			window.close();    			
    		});
    	});
    	
    	function makeSure() {
    		window.dialogArguments.document.getElementById("structureOrganOrganTypeId").value = document.all.organTypeId.value;
    		window.dialogArguments.document.getElementById("structureId").value = structureId;
    		window.dialogArguments.document.getElementById("commit").click();
    		window.close();
    	}
    </script>
  </head>
  <body>
  	<div id="content" style="padding: 10px;" align="center">
  		<s:form id="struForm" name="struForm" theme="simple">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 230px; height: 130px;" doLayout="true">
  				<sx:div id="baseinfo" label="请选择需要增加的下级类型" ></sx:div>
  			</sx:tabbedpanel>
  		</s:form>
  	</div>
  </body>
</html>
