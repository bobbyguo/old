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
  				<sx:div id="baseinfo" label="��֯����������ϸ" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" value="%{model.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">��֯�ṹ����:</td>
  								<td class="TableData">
  									<s:property value="%{model.struType.name}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�ϼ���֯����:</td>
  								<td class="TableData">
  									<s:property value="%{model.organType.name}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�¼���֯����:</td>
  								<td class="TableData">
  									<s:iterator value="%{model.targetOrganType}" id="organType" >
  										<s:property value="name" />
  									</s:iterator>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����˵��:</td>
  								<td class="TableData">
  									<s:property value="%{model.ruleNote}" />
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" id="forEdit" name="forEdit" value="�޸�" action="forEditStruRuleAction" cssClass="BigButton" theme="xhtml" />
  			<!--<s:submit align="right" id="back" name="back" value="����" action="queryOrganTypeAction" cssClass="BigButton" theme="xhtml" />-->
  		</s:form>
  	</div>
  </body>
</html>
