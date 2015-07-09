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
    	function saveItem() {
    		var isSys = document.getElementsByName("user.isSys");
    		for(var i = 0; i < isSys.length; i++){
    			if(isSys[i].checked && isSys[i].value == "0") {
    				var employeeName = document.getElementById("employeeName").value;
    				if(employeeName == "") {
    					alert("��ϵͳ����Ա��ѡ���ӦԱ��!");
    					return;
    				}
    			}
    		}
    		if(document.getElementById("password").value != document.getElementById("againPassword").value) {
    			alert("�������벻һ��,����������!");
    			return;
    		}
    		
    		var frame = parent.document.getElementById("userLeftFrame");    		
    		with(document.getElementById("userForm")) {
    			method = "post";
    			action = "saveUserAction";
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
  				<sx:div id="baseinfo" label="�û����" >
  					<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
  						<tbody>
  							<s:hidden name="user.id" />
  							<tr>
  								<td class="TableLabel" width="35%">���:</td>
  								<td class="TableData">
  									<s:textfield id="code" name="user.code" maxlength="8" label="Code" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�Ƿ�Ϊϵͳ����Ա:</td>
  								<td class="TableData">
  									<input type="radio" name="user.isSys" value="1">��
  									<input type="radio" name="user.isSys" value="0" checked="checked">��
  									<!--<s:radio id="isSys" name="user.isSys" list="#{'1':'��','0':'��'}" listKey="key" listValue="value" value="0" label="IsSys" required="true" requiredposition="right" />-->
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">��ӦԱ��:</td>
  								<td class="TableData">
  									<s:textfield id="employeeName" name="employeeName" label="EmployeeName" readonly="true" /><img src="${ctx}/img/png-0023.png" height="18px" align="middle" width="18px" onclick="openWindow();" /><font color="red">��ϵͳ����Ա��ѡ���ӦԱ��!</font>
  									<s:hidden id="employeeId" name="user.employee.id" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">��������:</td>
  								<td class="TableData">
  									<s:label value="%{user.department.organ.name}" />
  									<s:hidden id="departmentId" name="user.department.id" value="%{user.department.id}" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">�û���:</td>
  								<td class="TableData">
  									<s:textfield id="name" name="user.name" label="Name" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">����:</td>
  								<td class="TableData">
  									<s:password id="password" name="user.password" label="Password" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">ȷ������:</td>
  								<td class="TableData">
  									<s:password id="againPassword" name="againPassword" label="AgainPassword" required="true" requiredposition="right" cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">Email:</td>
  								<td class="TableData">
  									<s:textfield id="email" name="user.email" maxlength="150" label="Email"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">MSN:</td>
  								<td class="TableData">
  									<s:textfield id="msn" name="user.msn" label="MSN"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">QQ:</td>
  								<td class="TableData">
  									<s:textfield id="qq" name="user.qq" label="QQ"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">PHONE:</td>
  								<td class="TableData">
  									<s:textfield id="phone" name="user.phone" label="Phone"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">MOBILE:</td>
  								<td class="TableData">
  									<s:textfield id="mobile" name="user.mobile" label="Mobile"cssClass="BigInput" />
  								</td>
  							</tr>
  							<tr>
  								<td class="TableLabel" width="35%">״̬:</td>
  								<td class="TableData">
  									<input type="radio" name="user.isEnabled" value="Y" checked="checked">��
  									<input type="radio" name="user.isEnabled" value="N">����
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
  											allowUpDownOnLeft="false"
  											doubleId="rightRoles"
  											doubleName="rightRoles"
  											rightTitle="����Ȩ��ɫ"  											
  											doubleList="rightRoleList" 
  											doubleListKey="id"
  											doubleListValue="name"
  											doubleMultiple="true"
  											doubleHeaderKey="-1"
  											doubleHeaderValue="--- ��ѡ���û���ɫ  ---"
  											doubleEmptyOption="true"
  											allowUpDownOnRight="false" />
  				</sx:div>
  			</sx:tabbedpanel>
  			<br/>
  			<s:submit id="save" name="save" value="����" cssClass="BigButton" onclick="saveItem();" />
  		</s:form>
  	</div>
  </body>
</html>
