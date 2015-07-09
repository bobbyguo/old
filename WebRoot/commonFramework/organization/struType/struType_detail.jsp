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
  		<br/>
  		<s:form id="struTypeForm" name="struTypeForm" theme="simple" namespace="/organization" method="post">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="组织结构类型明细" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="model.id" />
  							<tr>
  								<td class="TableLabel" width="35%">组织结构类型名称:</td>
  								<td class="TableData">
  									<s:property value="model.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">最高组织单位:</td>
  								<td class="TableData">
  									<s:property value="model.root.organ.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">实施组织结构规则约束:</td>
  								<td class="TableData">
  									<s:if test='model.isApplyRule == "1"'>是</s:if>
  									<s:else>否</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">支持矩阵制:</td>
  								<td class="TableData">
  									<s:if test='model.isMatrix == "1"'>是</s:if>
  									<s:else>否</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">保留历史记录:</td>
  								<td class="TableData">
  									<s:if test='model.isKeepRecord == "1"'>是</s:if>
  									<s:else>否</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">默认组织结构类型:</td>
  								<td class="TableData">
  									<s:if test='model.isDefault == "1"'>是</s:if>
  									<s:else>否</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">状态:</td>
  								<td class="TableData">
  								<s:if test='model.inUse == "1"'>在用</s:if>
  									<s:else>停用</s:else>
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" id="forEdit" name="forEdit" value="修改" action="forEditStruType" cssClass="BigButton" theme="xhtml" />
  			<!--<s:submit align="right" id="back" name="back" value="返回" action="queryOrganTypeAction" cssClass="BigButton" theme="xhtml" />-->
  		</s:form>
  	</div>
  </body>
</html>
