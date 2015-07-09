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
    		window.self.location = "resourceAction!editResource.action";
    	}
    	
    	function deleteItem() {
    		var flag = false;
    		
    		for(var i = 0; i < document.getElementsByName("deleteResourceId").length; i++) {
    			if(document.getElementsByName("deleteResourceId")[i].checked) {	
    				flag = true;
    			}
    		}
    		
    		if(!flag) {
    			alert("��ѡ��Ҫɾ������Դ");
    			return;
    		}
    		
    		if(window.confirm("ȷ��ɾ����?")) {
    			with(document.getElementById("resourceForm")) {
    				method = "post";
    				action = "resourceAction!deleteResource.action";
    				submit();
    			}
    		}
    	}
    	
    	function checkAll() {
    		for(var i = 0; i < document.getElementsByName("deleteResourceId").length; i++) {
    			document.getElementsByName("deleteResourceId")[i].checked = document.getElementById("ifAll").checked;
    		}
    	}
    	
    	function query() {
    		with(document.getElementById("resourceForm")) {
    				method = "post";
    				action = "resourceAction!getAllResource.action";
    				submit();
    		}
    	}
    </script>
  </head>
  <body>
  	<div id="content" align="center">
	  	<s:form id="resourceForm" name="resourceForm" theme="simple">
	  		<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
	  			<tbody>
	  				<tr align="center">
	  					<td class="TableData" align="center">
	  						��Դ��:&nbsp;<s:textfield id="name" label="��Դ��" name="resource.name" />
	  						����:&nbsp;<s:select id="type" label="����" name="resource.type" headerKey="" headerValue="---��ѡ��---" list="#{'URL':'URL'}" />
	  						<input type="button" value="��ѯ" onclick="query()" />
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  		
		  	<display:table name="list" sort="external" partialList="true" size="size" requestURI="resourceAction!getAllResource.action" pagesize="10" id="row" export="true" class="ITS">
		  		<display:caption>�û��б�</display:caption>
		  		<display:column title='<input type="checkbox" id="ifAll" name="ifAll" onclick="checkAll();" />'>
		  			<input type="checkbox" id="deleteResourceId" name="deleteResourceId" value="${row.id}" />
		  		</display:column>
		  		<display:column title="���"><c:out value="${row_rowNum}" /></display:column>
		  		<display:column title="��Դ��" property="name" sortable="true" sortName="name" sortProperty="name" />
		  		<display:column title="����" property="type" />
		  		<display:column title="��Դֵ" property="value" />
		  		<display:column title="����" property="description" />
		  		<display:column title="����">
		  			<s:url id="update" action="resourceAction!editResource.action">
		  				<s:param name="resource.id">${row.id}</s:param>
		  			</s:url>
		  			<s:url id="delete" action="resourceAction!deleteResource.action">
		  				<s:param name="resource.id">${row.id}</s:param>
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
