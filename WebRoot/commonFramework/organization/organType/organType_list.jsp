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
    
    <title>${CompanyName}--${ProjectName}</title>
    <%@ include file="/commons/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
    <script type="text/javascript">
    	$(function() {
			$("#ifAll").click(function() {
				$("[name=selectArray]:checkbox").attr("checked", this.checked);
			});			
			$("[name=selectArray]:checkbox").click(function() {
				var $tmp = $("[name=selectArray]:checkbox");
				$("#ifAll").attr("checked", $tmp.length == $tmp.filter(":checked").length);
			});
			$("#forEdit").click(function() {
				if(!selectOne()) {
					return;
				}
				with($("#organTypeForm")[0]) {
					method = "post";
	    			action = "organization/forEditOrganTypeAction";
	    			submit();
				}
			});
			$("#detail").click(function() {
				if(!selectOne()) {
					return;
				}
				with($("#organTypeForm")[0]) {
	    			method = "post";
	    			action = "organization/detailOrganTypeAction";
	    			submit();
	    		}
			});
			$("#delete").click(function() {
				var $length = $("[name=selectArray]:checkbox:checked").length;
				if($length == 0) {
					alert("��ѡ��Ҫɾ���ļ�¼");
					return;
				}
				if(window.confirm("ȷ��ɾ����?")) {
	    			with($("#organTypeForm")[0]) {
	    				method = "post";
	    				action = "organization/deleteOrganTypeAction";
	    				submit();
	    			}
	    		}
			});
        });
    	function selectOne() {
			var $length = $("[name=selectArray]:checkbox:checked").length;
			if($length == 0 || $length > 1) {
				alert("��ѡ��һ����¼");
				return false;
			}
			return true;
		}
    </script>    
    <!--
    <script type="text/javascript">
    	var xmlHttp;
    	
    	function createXMLHTTP() {
    		if(window.XMLHTTPRequest) {
    			xmlHttp = new XMLHTTPRequest();
    			if(xmlHttp.overrideMimeType) {
    				xmlHttp.overrideMimeType("text/xml");
    			}
    		}else if(window.ActiveXObject) {
    			try {
    				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    			}catch(e) {
    				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    			}
    			
    			if(!xmlHttp) {
    				alert("���ܴ���XMLHttpRequest����ʵ��!");
    				return;
    			}
    		}
    	}
    	
    	function callBack() {
    		if(xmlHttp.readyState == 4) {
    			if(xmlHttp.status == 200) {
    				var valid = xmlHttp.responseText;
    				
    				if(valid == "false") {
    					deleteItem();
    				}else {
    					alert("����ɾ��!��Ҫɾ��,����ɾ�����¼���֯����!");
    				}
    			}
    		}
    	}
    	
    	function deleteValidate() {
    		createXMLHTTP();
    		var url = "organization/deleteValidateOrganTypeAction";
    		
    		with(document.getElementById("organTypeForm")) {
    			xmlHttp.open("POST", url, true);
    		}
    		    		
    		xmlHttp.onreadystatechange = callBack;
    		xmlHttp.send(null);
    	}
    </script>  -->
  </head>
  <body>
  	<div id="content" align="center">
	  	<s:form id="organTypeForm" name="organTypeForm" namespace="/organization" theme="simple">
	  		<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
	  			<tbody>
	  				<tr align="center">
	  					<td class="TableData" align="center">
	  						��֯����:&nbsp;<s:textfield id="name" label="��֯������" name="organType.name" />
	  						�Ƿ�����:&nbsp;<s:select id="inUse" label="�Ƿ�����" name="organType.inUse" headerKey="" headerValue="---��ѡ��---" list="#{'0':'ͣ��', '1':'����'}" />
	  						<s:submit id="query" name="query" value="��ѯ" cssClass="BigButton" action="queryOrganTypeAction" />
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  		
	  		<font color="red">
	  			<s:property value="organTypeTip" /><br />
	  			<s:property value="organTip" /><br />
	  			<s:property value="struRuleTip" />
	  		</font><br />
	  		<s:actionerror />
		  	<display:table name="list" sort="external" partialList="true" size="size" requestURI="organization/queryOrganTypeAction" pagesize="10" id="row" export="true" class="ITS">
		  		<display:caption>��֯�����б�</display:caption>
		  		<display:column style="width : 50px" title='<input type="checkbox" id="ifAll" name="ifAll" onclick="checkAll();" />'>
		  			<input type="checkbox" id="selectArray" name="selectArray" value="${row.id}" />
		  		</display:column>
		  		<display:column title="���"><c:out value="${row_rowNum}" /></display:column>
		  		<display:column title="��֯����" property="name" sortable="true" sortName="name" sortProperty="name" />
		  		<display:column title="�ϼ���֯����" value="${row.parentType.name}" />
		  		</display:table>
		  	<s:submit id="forAdd" name="forAdd" value="����" action="forAddOrganTypeAction" cssClass="BigButton" align="center" />
		  	<input id="forEdit" name="forEdit" type="button" value="�޸�" class="BigButton" align="middle" />
		  	<input id="detail" name="detail" type="button" value="��ϸ" class="BigButton" align="middle" />
		  	<input id="delete" name="delete" type="button" value="ɾ��" class="BigButton" align="middle" />
		</s:form>
	</div>
  </body>
</html>
