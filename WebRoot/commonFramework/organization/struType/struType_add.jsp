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
  		<s:form id="struTypeForm" name="struTypeForm" theme="simple" method="post" action="saveStruType">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="组织机构类型添加" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" />
  							<tr>
  								<td class="TableLabel" width="35%">类型名称:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="name" maxlength="50" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">最高组织单位:</td>
  								<td class="TableData">
  									<s:select list="%{rootList}" name="root.id" id="id" label="Root" listKey="id" listValue="organ.name" headerKey="" headerValue="--请选择--"></s:select>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">实施组织结构规则约束:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isApplyRule" name="isApplyRule" value="1" /> 
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">支持矩阵制:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isMatrix" name="isMatrix"  value="1" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">保留历史记录:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isKeepRecord" name="isKeepRecord" value="1" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">默认组织结构类型:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isDefault" name="isDefault" value="1" />
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
  			<s:submit align="center" name="save" value="保存" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
