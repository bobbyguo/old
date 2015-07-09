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
  				<sx:div id="baseinfo" label="组织机构类型修改" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="id"  value="%{model.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">类型名称:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="struType.name"  maxlength="50" label="类型名称" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">最高组织单位:</td>
  								<td class="TableData">
  									<s:select list="%{rootList}" name="model.root.id" id="id" label="最高组织单位" listKey="id" listValue="organ.name" headerKey="-1" headerValue="--请选择--" ></s:select>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">实施组织结构规则约束:</td>
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
  								<td class="TableLabel" width="35%">支持矩阵制:</td>
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
  								<td class="TableLabel" width="35%">保留历史记录:</td>
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
  								<td class="TableLabel" width="35%">默认组织结构类型:</td>
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
  								<td class="TableLabel" width="35%">状态:</td>
  								<td class="TableData">
  									<s:if test="model.inUse == 1">
  										<input type="radio" id="inUse" name="model.inUse" value="1" checked="checked">在用
  										<input type="radio" id="inUse" name="model.inUse" value="0">停用
  									</s:if>
  									<s:else>
  										<input type="radio" id="inUse" name="model.inUse" value="1">在用
  										<input type="radio" id="inUse" name="model.inUse" value="0" checked="checked">停用
  									</s:else>
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
