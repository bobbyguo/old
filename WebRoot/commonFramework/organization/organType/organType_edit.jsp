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
  		<s:form id="organTypeForm" name="organTypeForm" theme="simple" namespace="/organization" method="post" action="editOrganTypeAction">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="组织类型修改" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="organType.id" value="%{organType.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">组织类型名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="organType.name" value="%{organType.name}" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">父组织类型:</td>
  								<td class="TableData">
  									<s:select id="parentTypeId" label="parentTypeId" name="organType.parentType.id"  list="%{organTypeList}" listKey="id" listValue="name" headerKey="" headerValue="--请选择--" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">是否启用:</td>
  								<td class="TableData">
  									<s:if test='organType.inUse == "1"'>
  										<input type="radio" name="organType.inUse" value="1" checked="checked">在用
  										<input type="radio" name="organType.inUse" value="0">停用
  									</s:if>
  									<s:else>
  										<input type="radio" name="organType.inUse" value="1">在用
  										<input type="radio" name="organType.inUse" value="0" checked="checked">停用
  									</s:else>
  									<!--<s:radio id="inUse" name="organType.inUse" value="%{organType.inUse}" list="#{'0':'停用', '1':'在用'}" listKey="key" listValue="value" label="inUse" required="true" requiredposition="right" />-->
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
