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
  		<s:form id="structureForm" name="structureForm" theme="simple" namespace="/organization" method="post" action="saveStructureAction">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 800px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="��֯������ϸ" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden id="struTypeId" value="%{model.struType.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">����:</td>
  								<td class="TableData">
  									<s:property value="model.organ.code" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����/����:</td>
  								<td class="TableData">
  									<s:property value="model.organ.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����:</td>
  								<td class="TableData">
  									<s:property value="model.organ.organType.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�ϼ���֯:</td>
  								<td class="TableData">
  									<s:property value="model.parentOrgan.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">��������:</td>
  								<td class="TableData">
  									<s:property value="model.department.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�������:</td>
  								<td class="TableData">
  									<s:property value="model.struLevel" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">·��:</td>
  								<td class="TableData">
  									<s:property value="model.struPath" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�Ƿ�Ҷ�ӽڵ�:</td>
  								<td class="TableData">
  									<s:if test='model.isLeaf == "1"'>��</s:if>
  									<s:else>��</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">״̬:</td>
  								<td class="TableData">
  									<s:if test='model.inUse == "1"'>����</s:if>
  									<s:else>ͣ��</s:else>
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  			</sx:tabbedpanel>
  		</s:form>
  	</div>
  </body>
</html>
