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
    <script type="text/javascript">
    	function editItem() {
    		var struTypeId = document.getElementById("struTypeId").value;
    		var frame = parent.document.getElementById("structureLeftFrame");    		
    		with(document.getElementById("structureForm")) {
    			method = "post";
    			action = "organization/editStructureAction";
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
  				<sx:div id="baseinfo" label="组织机构修改" >  					
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" value="%{structure.id}" />
  							<s:hidden name="organ.id" value="%{structure.organ.id}" />
  							<s:hidden name="organ.organType.id" value="%{model.organ.organType.id}" />
  							<s:hidden id="struTypeId" name="struType.id" value="%{model.struType.id}" />
  							<s:hidden name="parentOrgan.id" value="%{model.parentOrgan.id}" />
  							<s:hidden name="department.id" value="%{model.department.id}" />
  							<s:hidden name="organ.inUse" value="%{model.organ.inUse}" />
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
  									<s:textfield id="code" name="organ.code" value="%{model.organ.code}" label="Code" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">名称/姓名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="organ.name" value="%{model.organ.name}" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">所属部门:</td>
  								<td class="TableData">
  									<s:label id="department" name="department.name" value="%{model.department.name}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">路径:</td>
  								<td class="TableData">
  									<s:textfield id="struPath" name="struPath" value="%{model.struPath}" label="StruPath" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">是否叶子节点:</td>
  								<td class="TableData">
  									<s:if test='model.isLeaf == "1"'>
  										<input type="checkbox" id="isLeaf" name="isLeaf" value="1" checked="checked" />
  									</s:if>
  									<s:else>
  										<input type="checkbox" id="isLeaf" name="isLeaf" value="0" />
  									</s:else>  									
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">状态:</td>
  								<td class="TableData">
  									<s:if test='model.inUse == "1"'>
  										<input type="radio" name="inUse" value="1" checked="checked">在用
  										<input type="radio" name="inUse" value="0">停用
  									</s:if>
  									<s:else>
  										<input type="radio" name="inUse" value="1">在用
  										<input type="radio" name="inUse" value="0" checked="checked">停用
  									</s:else>
  									<!--<s:radio id="inUse" name="inUse" value="%{model.inUse}" list="#{'1':'在用','0':'停用'}" listKey="key" listValue="value" label="状&nbsp;&nbsp;&nbsp;态" required="true" requiredposition="right" />-->
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<input type="button" align="middle" name="update" onclick="editItem();" value="更新" class="BigButton" />
  		</s:form>
  	</div>
  </body>
</html>
