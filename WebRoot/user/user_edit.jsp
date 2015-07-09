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
    <script type="text/javascript">
    	function openWindow() {
			window.showModalDialog("${ctx}/user/employee_select.jsp?structureId=" + document.getElementById("departmentId").value, self, "DialogHeight:400px;DialogWidth:300px;location:no;menubar:no;toolbar:no;status:no;scroll:yes");
		}
		
    	function editItem() {
    		var isSys = document.getElementsByName("user.isSys");
    		for(var i = 0; i < isSys.length; i++){
    			if(isSys[i].checked && isSys[i].value == "0") {
    				var employeeName = document.getElementById("employeeName").value;
    				if(employeeName == "") {
    					alert("非系统管理员请选择对应员工!");
    					return;
    				}
    			}
    		}
    		if(document.getElementById("password").value != document.getElementById("againPassword").value) {
    			alert("两次密码不一致,请重新输入!");
    			return;
    		}
    		
    		var frame = parent.document.getElementById("userLeftFrame");    		
    		with(document.getElementById("userForm")) {
    			method = "post";
    			action = "editUserAction";
    			submit();
    		}
    		
    		frame.src = "${ctx}/user/userTree.jsp";
    	}
    </script>
  </head>
  <body>
  	<div id="content" align="center">
  		<br/>
  		<s:form id="userForm" name="userForm" theme="simple">
  			<sx:tabbedpanel id="tabContainer" cssStyle="width: 800px; height: 500px;" doLayout="true">
  				<sx:div id="baseinfo" label="用户修改" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="user.id" value="%{user.id}" />
  							<tr>
  								<td class="TableLabel" width="35%">编号:</td>
  								<td class="TableData">
  									<s:label name="user.code" value="%{user.code}" />
  									<s:hidden id="code" name="user.code" value="%{user.code}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">是否为系统管理员:</td>
  								<td class="TableData">
  									<s:if test='user.isSys == "1"'>
  										<input type="radio" name="user.isSys" value="1" checked="checked">是
  										<input type="radio" name="user.isSys" value="0">否
  									</s:if>
  									<s:else>
  										<input type="radio" name="user.isSys" value="1">是
  										<input type="radio" name="user.isSys" value="0" checked="checked">否
  									</s:else>
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">对应员工:</td>
  								<td class="TableData">
  									<s:textfield id="employeeName" name="employeeName" value="%{user.employee.organ.name}" label="EmployeeName" readonly="true" />
  									<img src="${ctx}/img/png-0023.png" height="18px" align="middle" width="18px" onclick="openWindow();" />
  									<font color="red">非系统管理员请选择对应员工!</font>
  									<s:hidden id="employeeId" name="user.employee.id" value="%{user.employee.id}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">所属部门:</td>
  								<td class="TableData">
  									<s:label value="%{user.department.organ.name}" />
  									<s:hidden id="departmentId" name="user.department.id" value="%{user.department.id}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">用户名:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="user.name" value="%{user.name}" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">密码:</td>
  								<td class="TableData">
  									<s:password id="password" name="user.password" label="Password" showPassword="true" value="%{user.password}" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">确认密码:</td>
  								<td class="TableData">
  									<s:password id="againPassword" name="againPassword" label="AgainPassword" showPassword="true" value="%{user.password}" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">Email:</td>
  								<td class="TableData">
  									<s:textfield id="email" name="user.email" value="%{user.email}" label="Email"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">MSN:</td>
  								<td class="TableData">
  									<s:textfield id="msn" name="user.msn" value="%{user.email}" label="MSN"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">QQ:</td>
  								<td class="TableData">
  									<s:textfield id="qq" name="user.qq" value="%{user.qq}" label="QQ"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">PHONE:</td>
  								<td class="TableData">
  									<s:textfield id="phone" name="user.phone" value="%{user.phone}" label="Phone"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">MOBILE:</td>
  								<td class="TableData">
  									<s:textfield id="mobile" name="user.mobile" value="%{user.mobile}" label="Mobile"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">状态:</td>
  								<td class="TableData">
  									<s:if test='user.isEnabled == "Y"'>
  										<input type="radio" name="user.isEnabled" value="Y" checked="checked">打开
  										<input type="radio" name="user.isEnabled" value="N">锁定
  										<s:hidden id="createTime" name="user.createTime" value="%{user.createTime}" />
  									</s:if>
  									<s:else>
  										<input type="radio" name="user.isEnabled" value="Y">打开
  										<input type="radio" name="user.isEnabled" value="N" checked="checked">锁定
  									</s:else>
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
  											allowUpDownOnLeft="false"
  											doubleId="rightRole"
  											doubleName="rightRole"
  											rightTitle="已授权角色"  											
  											doubleList="rightRoleList" 
  											doubleListKey="id"
  											doubleListValue="name"
  											doubleMultiple="true"
  											doubleHeaderKey="-1"
  											doubleHeaderValue="--- 请选择用户角色  ---"
  											doubleEmptyOption="true"
  											allowUpDownOnRight="false" />
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit id="edit" name="edit" value="修改" cssClass="BigButton" onclick="editItem();" />
  		</s:form>
  	</div>
  </body>
</html>