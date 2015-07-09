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
  		<s:form id="struTypeForm" name="struTypeForm" theme="simple" method="post" namespace="/organization" action="editStruType">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 1000px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="��֯���������޸�" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id"  value="%{model.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">��������:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="struType.name"  maxlength="50" label="��������" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�����֯��λ:</td>
  								<td class="TableData">
  									<s:select list="%{rootList}" name="model.root.id" id="id" label="�����֯��λ" listKey="id" listValue="organ.name" headerKey="-1" headerValue="--��ѡ��--" ></s:select>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">ʵʩ��֯�ṹ����Լ��:</td>
  								<td class="TableData">
  									<s:if test="model.isApplyRule == 1">
  										<input type="checkbox" id="isApplyRule" name="model.isApplyRule" value="1" checked="checked"/>
  									</s:if>
  									<s:else>
  										<input type="checkbox" id="isApplyRule" name="model.isApplyRule" value="1" />
  									</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">֧�־�����:</td>
  								<td class="TableData">
  									<s:if test="model.isMatrix == 1">
  										<input type="checkbox" id="isMatrix" name="model.isMatrix" value="1" checked="checked"/>
  									</s:if>
  									<s:else>
  										<input type="checkbox" id="isMatrix" name="model.isMatrix" value="1" />
  									</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">������ʷ��¼:</td>
  								<td class="TableData">
  									<s:if test="model.isKeepRecord == 1">
  										<input type="checkbox" id="isKeepRecord" name="model.isKeepRecord" value="1" checked="checked" />
  									</s:if>
  									<s:else>
  										<input type="checkbox" id="isKeepRecord" name="model.isKeepRecord" value="1" />
  									</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">Ĭ����֯�ṹ����:</td>
  								<td class="TableData">
  									<s:if test="model.isDefault == 1">
  										<input type="checkbox" id="isDefault" name="model.isDefault" value="1" checked="checked" />
  									</s:if>
  									<s:else>
  										<input type="checkbox" id="isDefault" name="model.isDefault" value="1" />
  									</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">״̬:</td>
  								<td class="TableData">
  									<s:if test="model.inUse == 1">
  										<input type="radio" id="inUse" name="model.inUse" value="1" checked="checked">����
  										<input type="radio" id="inUse" name="model.inUse" value="0">ͣ��
  									</s:if>
  									<s:else>
  										<input type="radio" id="inUse" name="model.inUse" value="1">����
  										<input type="radio" id="inUse" name="model.inUse" value="0" checked="checked">ͣ��
  									</s:else>
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
