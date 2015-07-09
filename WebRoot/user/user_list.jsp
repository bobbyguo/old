<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    	function editItem() {
    		window.self.location = "userAction!editUser.action";
    	}
    	
    	function deleteItem() {
    		var flag = false;
    		
    		for(var i = 0; i < document.getElementsByName("deleteUserId").length; i++) {
    			if(document.getElementsByName("deleteUserId")[i].checked) {	
    				flag = true;
    			}
    		}
    		
    		if(!flag) {
    			alert("请选择要删除的用户");
    			return;
    		}
    		
    		if(window.confirm("确认删除吗?")) {
    			with(document.getElementById("userForm")) {
    				method = "post";
    				action = "userAction!deleteUser.action";
    				submit();
    			}
    		}
    	}
    	
    	function checkAll() {
    		for(var i = 0; i < document.getElementsByName("deleteUserId").length; i++) {
    			document.getElementsByName("deleteUserId")[i].checked = document.getElementById("ifAll").checked;
    		}
    	}
    	
    	function query() {
    		with(document.getElementById("userForm")) {
    				method = "post";
    				action = "userAction!getAllUser.action";
    				submit();
    		}
    	}
    	
    	function reset() {
    		document.getElementById("name").value = "";
    	}
    </script>
  </head>
  <body>
  	<div id="content" align="center">
	  	<s:form id="userForm" name="userForm" theme="simple">
	  		<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
	  			<tbody>
	  				<tr align="center">
	  					<td class="TableData" align="center">
	  						用户名:&nbsp;<s:textfield id="name" label="用户名" name="user.name" />
	  						状态:&nbsp;<s:select id="isEnabled" label="状态" name="user.isEnabled" headerKey="" headerValue="--请选择--" list="#{'Y':'启用','N':'停用'}" listKey="key" listValue="value" />
	  						<input type="button" value="查询" onclick="query()" />
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  		
		  	<display:table name="list" sort="external" partialList="true" size="size" requestURI="userAction!getAllUser.action" pagesize="10" id="row" export="true" class="ITS">
		  		<display:caption>用户列表</display:caption>
		  		<display:column title='<input type="checkbox" id="ifAll" name="ifAll" onclick="checkAll();" />'>
		  			<input type="checkbox" id="deleteUserId" name="deleteUserId" value="${row.id}" />
		  		</display:column>
		  		<display:column title="序号"><c:out value="${row_rowNum}" /></display:column>
		  		<display:column title="编号" property="code" sortable="true" sortName="code" sortProperty="code"></display:column>
		  		<display:column title="用户名" property="name" sortable="true" sortName="name" sortProperty="name"></display:column>
		  		<display:column title="是否有效" property="isEnabled"></display:column>
		  		<display:column title="操作">
		  			<s:url id="update" action="userAction!editUser.action">
		  				<s:param name="user.id">${row.id}</s:param>
		  			</s:url>
		  			<s:url id="delete" action="userAction!deleteUser.action">
		  				<s:param name="user.id">${row.id}</s:param>
		  			</s:url>
		  			<s:a href="%{update}">更新</s:a>&nbsp;|&nbsp;
		  			<s:a href="%{delete}">删除</s:a>
		  		</display:column>
		  	</display:table>
		  	<input type="checkbox" id="ifAll" name="ifAll" onclick="checkAll();" />
		  	<input type="button" value="删除选中" onclick="deleteItem();" align="middle" />
		  	<input type="button" value="增加" onclick="editItem();" align="middle" />
		</s:form>
	</div>
  </body>
</html>
