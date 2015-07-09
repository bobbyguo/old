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
  		<s:form id="userForm" name="userForm" theme="simple" action="forEditUserAction">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 800px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="�û���ϸ" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="user.id" value="%{user.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">���:</td>
  								<td class="TableData">
  									<s:property value="user.code" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�Ƿ�Ϊϵͳ����Ա:</td>
  								<td class="TableData">
  									<s:if test='user.isSys == "1"'>��</s:if>
  									<s:else>��</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">��ӦԱ��:</td>
  								<td class="TableData">
  									<s:property value="user.employee.organ.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�û���:</td>
  								<td class="TableData">
  									<s:property value="user.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����:</td>
  								<td class="TableData">
  									<s:label value="******" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">��������:</td>
  								<td class="TableData">
  									<s:property value="user.department.organ.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">Email:</td>
  								<td class="TableData">
  									<s:property value="user.email" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">MSN:</td>
  								<td class="TableData">
  									<s:property value="user.msn" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">QQ:</td>
  								<td class="TableData">
  									<s:property value="user.qq" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">PHONE:</td>
  								<td class="TableData">
  									<s:property value="user.phone" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">MOBILE:</td>
  								<td class="TableData">
  									<s:property value="user.mobile" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����ʱ��:</td>
  								<td class="TableData">
  									<s:property value="user.createTime" />
  								</td>
  							</tr>
  							<s:if test='user.isEnabled == "N"'>
  								<tr>
	  								<td class="TableLabel" width="35%">����ʱ��:</td>
	  								<td class="TableData">
	  									<s:property value="user.lockTime" />
	  								</td>
	  							</tr>
  							</s:if>
  							<tr>
  								<td class="TableLabel" width="35%">״̬:</td>
  								<td class="TableData">
  									<s:if test='user.isEnabled == "Y"'>��</s:if>
  									<s:else>����</s:else>
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  				<sx:div id="roleinfo" label="��ɫ��Ȩ">
  					<s:optiontransferselect tooltip="�󶨽�ɫ"
  											name="leftRoles"
  											leftTitle="δ��Ȩ��ɫ"
  											list="leftRoleList"
  											listKey="id"
  											listValue="name"
  											multiple="true"
  											headerKey="-1"
  											headerValue="--- ��ѡ���û���ɫ  ---"
  											emptyOption="true"
  											disabled="true"
  											allowUpDownOnLeft="false"
  											doubleName="rightRoles"
  											rightTitle="����Ȩ��ɫ"  											
  											doubleList="rightRoleList" 
  											doubleListKey="id"
  											doubleListValue="name"
  											doubleMultiple="true"
  											doubleHeaderKey="-1"
  											doubleHeaderValue="--- ��ѡ���û���ɫ  ---"
  											doubleEmptyOption="true"
  											doubleDisabled="true"
  											allowUpDownOnRight="false" />
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit id="forEdit" name="forEdit" value="�޸�" cssClass="BigButton" align="center" />
  			<!--<input type="button" id="save" name="save" value="����" class="BigButton" onclick="saveItem();" />-->
  		</s:form>
  	</div>
  </body>
</html>
