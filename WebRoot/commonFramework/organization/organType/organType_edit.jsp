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
  				<sx:div id="baseinfo" label="��֯�����޸�" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="organType.id" value="%{organType.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">��֯������:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="organType.name" value="%{organType.name}" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����֯����:</td>
  								<td class="TableData">
  									<s:select id="parentTypeId" label="parentTypeId" name="organType.parentType.id"  list="%{organTypeList}" listKey="id" listValue="name" headerKey="" headerValue="--��ѡ��--" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�Ƿ�����:</td>
  								<td class="TableData">
  									<s:if test='organType.inUse == "1"'>
  										<input type="radio" name="organType.inUse" value="1" checked="checked">����
  										<input type="radio" name="organType.inUse" value="0">ͣ��
  									</s:if>
  									<s:else>
  										<input type="radio" name="organType.inUse" value="1">����
  										<input type="radio" name="organType.inUse" value="0" checked="checked">ͣ��
  									</s:else>
  									<!--<s:radio id="inUse" name="organType.inUse" value="%{organType.inUse}" list="#{'0':'ͣ��', '1':'����'}" listKey="key" listValue="value" label="inUse" required="true" requiredposition="right" />-->
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" name="update" value="����" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
