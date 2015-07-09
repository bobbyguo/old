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
  		<s:form id="roleForm" name="roleForm" theme="simple" method="post" action="roleAction!addRole.action">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="角色添加" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="role.id" value="%{user.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">角色名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="role.name" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">描述:</td>
  								<td class="TableData">
  									<s:textarea id="description" name="role.description" label="Description" cols="50" rows="5" />
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  				<sx:div id="roleinfo" label="资源绑定">
  					<s:optiontransferselect tooltip="绑定资源"
  											name="leftResources"
  											leftTitle="未绑定资源"
  											list="leftResourceList"
  											listKey="id"
  											listValue="name"
  											multiple="true"
  											headerKey="-1"
  											headerValue="--- 请选择角色资源  ---"
  											emptyOption="true"
  											allowUpDownOnLeft="false"
  											doubleName="rightResources"
  											rightTitle="已绑定资源"  											
  											doubleList="rightResourceList" 
  											doubleListKey="id"
  											doubleListValue="name"
  											doubleMultiple="true"
  											doubleHeaderKey="-1"
  											doubleHeaderValue="--- 请选择角色资源  ---"
  											doubleEmptyOption="true"
  											allowUpDownOnRight="false" />
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" name="save" value="保存" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
