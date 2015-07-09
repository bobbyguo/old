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
  		<s:form id="struRuleForm" name="struRuleForm" theme="simple" namespace="/organization" method="post" action="editStruRuleAction">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="��֯�ṹ�������" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id" value="%{model.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">��֯�ṹ����:</td>
  								<td class="TableData">
  									<s:select id="struTypeId" label="struTypeId" name="struType.id" value="%{model.struType.id}" list="%{struTypeList}" listKey="id" listValue="name" headerKey="" headerValue="--��ѡ��--" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�ϼ���֯����:</td>
  								<td class="TableData">
  									<s:select id="organTypeId" label="organTypeId" name="organType.id" value="%{model.organType.id}" list="%{organTypeList}" listKey="id" listValue="name" headerKey="" headerValue="--��ѡ��--" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����˵��:</td>
  								<td class="TableData">
  									<s:textarea id="ruleNote" name="ruleNote" value="%{model.ruleNote}" label="ruleNote" rows="5" cols="50" />
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  				<sx:div id="organTypeInfo" label="�¼���֯����">
  					<s:optiontransferselect tooltip="�¼���֯����"
  											name="leftOrganTypes"
  											leftTitle="δ����֯����"
  											list="leftOrganTypeList"
  											listKey="id"
  											listValue="name"
  											multiple="true"
  											headerKey="-1"
  											headerValue="--- ��ѡ����֯����  ---"
  											emptyOption="true"
  											allowUpDownOnLeft="false"
  											doubleName="rightOrganTypes"
  											rightTitle="������֯����"  											
  											doubleList="rightOrganTypeList" 
  											doubleListKey="id"
  											doubleListValue="name"
  											doubleMultiple="true"
  											doubleHeaderKey="-1"
  											doubleHeaderValue="--- ��ѡ����֯����  ---"
  											doubleEmptyOption="true"
  											allowUpDownOnRight="false" />
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit align="center" name="save" value="����" cssClass="BigButton" theme="xhtml" />
  		</s:form>
  	</div>
  </body>
</html>
