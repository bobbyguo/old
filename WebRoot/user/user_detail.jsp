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
  				<sx:div id="baseinfo" label="用户明细" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="user.id" value="%{user.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">编号:</td>
  								<td class="TableData">
  									<s:property value="user.code" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">是否为系统管理员:</td>
  								<td class="TableData">
  									<s:if test='user.isSys == "1"'>是</s:if>
  									<s:else>否</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">对应员工:</td>
  								<td class="TableData">
  									<s:property value="user.employee.organ.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">用户名:</td>
  								<td class="TableData">
  									<s:property value="user.name" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">密码:</td>
  								<td class="TableData">
  									<s:label value="******" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">所属部门:</td>
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
  								<td class="TableLabel" width="35%">创建时间:</td>
  								<td class="TableData">
  									<s:property value="user.createTime" />
  								</td>
  							</tr>
  							<s:if test='user.isEnabled == "N"'>
  								<tr>
	  								<td class="TableLabel" width="35%">锁定时间:</td>
	  								<td class="TableData">
	  									<s:property value="user.lockTime" />
	  								</td>
	  							</tr>
  							</s:if>
  							<tr>
  								<td class="TableLabel" width="35%">状态:</td>
  								<td class="TableData">
  									<s:if test='user.isEnabled == "Y"'>打开</s:if>
  									<s:else>锁定</s:else>
  								</td>
  							</tr>
  						</tbody>
  					</table>
  				</sx:div>
  				<sx:div id="roleinfo" label="角色授权">
  					<s:optiontransferselect tooltip="绑定角色"
  											name="leftRoles"
  											leftTitle="未授权角色"
  											list="leftRoleList"
  											listKey="id"
  											listValue="name"
  											multiple="true"
  											headerKey="-1"
  											headerValue="--- 请选择用户角色  ---"
  											emptyOption="true"
  											disabled="true"
  											allowUpDownOnLeft="false"
  											doubleName="rightRoles"
  											rightTitle="已授权角色"  											
  											doubleList="rightRoleList" 
  											doubleListKey="id"
  											doubleListValue="name"
  											doubleMultiple="true"
  											doubleHeaderKey="-1"
  											doubleHeaderValue="--- 请选择用户角色  ---"
  											doubleEmptyOption="true"
  											doubleDisabled="true"
  											allowUpDownOnRight="false" />
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit id="forEdit" name="forEdit" value="修改" cssClass="BigButton" align="center" />
  			<!--<input type="button" id="save" name="save" value="保存" class="BigButton" onclick="saveItem();" />-->
  		</s:form>
  	</div>
  </body>
</html>
