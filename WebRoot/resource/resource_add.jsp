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
  		<s:form id="resourceForm" name="resourceForm" theme="simple" method="post" action="resourceAction!addResource.action">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="资源添加" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="resource.id" />
  							<tr>
  								<td class="TableLabel" width="35%">资源名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="resource.name" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">类型:</td>
  								<td class="TableData">
  									<s:select id="type" label="Type" name="resource.type" list="#{'URL':'URL'}" listKey="key" listValue="value" required="true" requiredposition="right" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">资源值:</td>
  								<td class="TableData">
  									<s:textfield id="value" name="resource.value" label="Value" size="50" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">描述:</td>
  								<td class="TableData">
  									<s:textarea id="description" name="resource.description" label="Description" cols="50" rows="5" />
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" name="save" value="保存" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
