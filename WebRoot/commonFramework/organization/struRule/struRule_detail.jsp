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
  		<s:form id="struRuleForm" name="struRuleForm" theme="simple" namespace="/organization" method="post">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="组织机构规则明细" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" value="%{model.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">组织结构类型:</td>
  								<td class="TableData">
  									<s:property value="%{model.struType.name}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">上级组织类型:</td>
  								<td class="TableData">
  									<s:property value="%{model.organType.name}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">下级组织类型:</td>
  								<td class="TableData">
  									<s:iterator value="%{model.targetOrganType}" id="organType" >
  										<s:property value="name" />
  									</s:iterator>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">规则说明:</td>
  								<td class="TableData">
  									<s:property value="%{model.ruleNote}" />
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" id="forEdit" name="forEdit" value="修改" action="forEditStruRuleAction" cssClass="BigButton" theme="xhtml" />
  			<!--<s:submit align="right" id="back" name="back" value="返回" action="queryOrganTypeAction" cssClass="BigButton" theme="xhtml" />-->
  		</s:form>
  	</div>
  </body>
</html>
