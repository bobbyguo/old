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
  				<sx:div id="baseinfo" label="��֯�����������" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" />
  							<tr>
  								<td class="TableLabel" width="35%">��������:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="name" maxlength="50" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�����֯��λ:</td>
  								<td class="TableData">
  									<s:select list="%{rootList}" name="root.id" id="id" label="Root" listKey="id" listValue="organ.name" headerKey="" headerValue="--��ѡ��--"></s:select>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">ʵʩ��֯�ṹ����Լ��:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isApplyRule" name="isApplyRule" value="1" /> 
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">֧�־�����:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isMatrix" name="isMatrix"  value="1" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">������ʷ��¼:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isKeepRecord" name="isKeepRecord" value="1" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">Ĭ����֯�ṹ����:</td>
  								<td class="TableData">
  									<input type="checkbox" id="isDefault" name="isDefault" value="1" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">״̬:</td>
  								<td class="TableData">
  									<input type="radio" name="inUse" value="1" checked="checked">����
  									<input type="radio" name="inUse" value="0">ͣ��
  									<!--<s:radio id="inUse" name="inUse" list="#{'1':'����','0':'ͣ��'}" listKey="key" listValue="value" label="״&nbsp;&nbsp;&nbsp;̬" value="1" required="true" requiredposition="right" />-->
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" name="save" value="����" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
