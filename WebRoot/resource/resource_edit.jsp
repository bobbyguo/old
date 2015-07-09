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
  </head>
  <body>
  	<div id="content" align="center">
  		<br/><br/>
  		<s:form id="resourceForm" name="resourceForm" theme="simple" method="post" action="resourceAction!modifyResource.action">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="资源修改" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="resource.id" value="%{resource.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">资源名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="resource.name" value="%{resource.name}" label="Name" readonly="true" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">类型:</td>
  								<td class="TableData">
  									<s:select id="type" label="Type" name="resource.type" value="%{resource.type}" list="#{'URL':'URL'}" listKey="key" listValue="value" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">资源值:</td>
  								<td class="TableData">
  									<s:textfield id="value" name="resource.value" value="%{resource.value}" label="Value" cssClass="BigInput" size="50" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">描述:</td>
  								<td class="TableData">
  									<s:textarea rows="5" cols="50" id="description" name="resource.description" value="%{resource.description}" label="Description" />
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" name="update" value="更新" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
