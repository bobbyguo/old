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
    
    <script type="text/javascript" defer="defer">
    	function editItem() {
    		window.self.location = "roleAction!editRole.action";
    	}
    	
    	function deleteItem() {
    		var flag = false;
    		
    		for(var i = 0; i < document.getElementsByName("deleteRoleId").length; i++) {
    			if(document.getElementsByName("deleteRoleId")[i].checked) {	
    				flag = true;
    			}
    		}
    		
    		if(!flag) {
    			alert("��ѡ��Ҫɾ���Ľ�ɫ");
    			return;
    		}
    		
    		if(window.confirm("ȷ��ɾ����?")) {
    			with(document.getElementById("roleForm")) {
    				method = "post";
    				action = "roleAction!deleteRole.action";
    				submit();
    			}
    		}
    	}
    	
    	function checkAll() {
    		for(var i = 0; i < document.getElementsByName("deleteRoleId").length; i++) {
    			document.getElementsByName("deleteRoleId")[i].checked = document.getElementById("ifAll").checked;
    		}
    	}
    	
    	function query() {
    		with(document.getElementById("roleForm")) {
    				method = "post";
    				action = "roleAction!getAllRole.action";
    				submit();
    		}
    	}
    </script>
  </head>
  <body>
  	<div id="content" align="center">
	  	<s:form id="roleForm" name="roleForm" theme="simple">
	  		<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
	  			<tbody>
	  				<tr align="center">
	  					<td class="TableData" align="center">
	  						��ɫ����:&nbsp;<s:textfield id="name" label="��ɫ����" name="role.name" />
	  						<input type="button" value="��ѯ" onclick="query()" />
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  		
		  	<display:table name="list" sort="external" partialList="true" size="size" requestURI="roleAction!getAllRole.action" pagesize="10" id="row" export="true" class="ITS">
		  		<display:caption>��ɫ�б�</display:caption>
		  		<display:column title='<input type="checkbox" id="ifAll" name="ifAll" onclick="checkAll();" />'>
		  			<input type="checkbox" id="deleteRoleId" name="deleteRoleId" value="${row.id}" />
		  		</display:column>
		  		<display:column title="���"><c:out value="${row_rowNum}" /></display:column>
		  		<display:column title="��ɫ����" property="name" sortable="true" sortName="name" sortProperty="name" />
		  		<display:column title="����" property="description" />
		  		<display:column title="����">
		  			<s:url id="update" action="roleAction!editRole.action">
		  				<s:param name="role.id">${row.id}</s:param>
		  			</s:url>
		  			<s:url id="delete" action="roleAction!deleteRole.action">
		  				<s:param name="role.id">${row.id}</s:param>
		  			</s:url>
		  			<s:a href="%{update}">����</s:a>&nbsp;|&nbsp;
		  			<s:a href="%{delete}">ɾ��</s:a>
		  		</display:column>
		  	</display:table>
		  	<input type="button" value="ɾ��ѡ��" onclick="deleteItem();" align="middle" class="BigButton" />
		  	<input type="button" value="����" onclick="editItem();" align="middle" class="BigButton" />
		</s:form>
	</div>
  </body>
</html>
