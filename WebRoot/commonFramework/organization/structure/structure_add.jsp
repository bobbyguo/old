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
    <title>${CompanyName}--${ProjectName}</title>
    <%@ include file="/commons/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
    <script type="text/javascript">
    	$(function() {
    		var struTypeId = document.getElementById("struTypeId").value;
    		var frame = parent.document.getElementById("structureLeftFrame");
    		$("#save").click(function() {
    			$.get("organization/saveStructureAction", $("#structureForm").serialize(), function(data, textStatus) {
    				window.self.location = "${ctx}/organization/detailStructureAction?structure.id=" + $(data).get(0).id;
    				frame.src = "${ctx}/commonFramework/organization/structure/structureTree.jsp?struTypeId=" + struTypeId;
    			}, "json");
    			//$.ajax({
    				//type : "POST",
    				//url : "organization/saveStructureAction",
    				//data : $("#structureForm").serialize(),
    				//success : function(data) {
    					//frame.src = "${ctx}/commonFramework/organization/structure/structureTree.jsp?struTypeId=" + struTypeId;
    				//}
    			//});
    		});
    	});
    	function saveItem() {
    		var struTypeId = document.getElementById("struTypeId").value;
    		var frame = parent.document.getElementById("structureLeftFrame");    		
    		with(document.getElementById("structureForm")) {
    			method = "post";
    			action = "organization/saveStructureAction";
    			submit();
    		}
    		
    		frame.src = "${ctx}/commonFramework/organization/structure/structureTree.jsp?struTypeId=" + struTypeId;
    	}
    </script>
  </head>
  <body>
  	<div id="content" align="center">
  		<br/><br/>
  		<s:form id="structureForm" name="structureForm" theme="simple">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 800px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="组织机构添加" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" value="%{model.id}" />
  							<s:hidden name="organ.organType.id" value="%{model.organ.organType.id}" />
  							<s:hidden id="struTypeId" name="struType.id" value="%{model.struType.id}" />
  							<s:hidden name="parentOrgan.id" value="%{model.parentOrgan.id}" />
  							<s:hidden name="department.id" value="%{model.department.id}" />
  							<s:hidden name="organ.inUse" value="1" />
  							<s:hidden name="struLevel" value="%{model.struLevel}" />
  							<tr>
  								<td class="TableLabel" width="35%">上级组织:</td>
  								<td class="TableData">
  									<s:label id="parentOrganname" name="parentOrgan.name" value="%{model.parentOrgan.name}" label="ParentOrganname" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">编码:</td>
  								<td class="TableData">
  									<s:textfield id="code" name="organ.code" label="Code" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">名称/姓名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="organ.name" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">所属部门:</td>
  								<td class="TableData">
  									<s:label id="department" name="model.department.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">路径:</td>
  								<td class="TableData">
  									<s:textfield id="struPath" name="struPath" label="StruPath" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">是否叶子节点:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isLeaf" name="isLeaf" value="1" checked="checked" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">状态:</td>
  								<td class="TableData">
  									<input type="radio" name="inUse" value="1" checked="checked">在用
  									<input type="radio" name="inUse" value="0">停用
  									<!--<s:radio id="inUse" name="inUse" list="#{'1':'在用','0':'停用'}" listKey="key" listValue="value" label="状&nbsp;&nbsp;&nbsp;态" value="1" required="true" requiredposition="right" />-->
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<input type="button" id="save" align="middle" name="save" value="保存" class="BigButton" />
  		</s:form>
  	</div>
  </body>
</html>
