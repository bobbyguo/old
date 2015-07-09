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
				with($("#struRuleForm")[0]) {
					method = "post";
	    			action = "organization/forEditStruRuleAction";
	    			submit();
				}
			});
			$("#detail").click(function() {
				if(!selectOne()) {
					return;
				}
				with($("#struRuleForm")[0]) {
	    			method = "post";
	    			action = "organization/detailStruRuleAction";
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
	    			with($("#struRuleForm")[0]) {
	    				method = "post";
	    				action = "organization/deleteStruRuleAction";
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
  </head>
  <body>
  	<div id="content" align="center">
	  	<s:form id="struRuleForm" name="struRuleForm" namespace="/organization" theme="simple">
	  		<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
	  			<tbody>
	  				<tr align="center">
	  					<td class="TableData" align="center">
	  						��֯�ṹ����:&nbsp;<s:select id="struType" label="StruType" name="model.struType.id" headerKey="" headerValue="---��ѡ��---" list="struTypeList" listKey="id" listValue="name" />
	  						�ϼ���֯����:&nbsp;<s:select id="organType" label="OrganType" name="model.organType.id" headerKey="" headerValue="---��ѡ��---" list="organTypeList" listKey="id" listValue="name" />
	  						<s:submit id="query" name="query" value="��ѯ" cssClass="BigButton" action="queryStruRuleAction" />
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  		
		  	<display:table name="list" sort="external" partialList="true" size="size" requestURI="organization/queryStruRuleAction" pagesize="10" id="row" export="true" class="ITS">
		  		<display:caption>��֯�ṹ�����б�</display:caption>
		  		<display:column style="width: 50px;" title='<input type="checkbox" id="ifAll" name="ifAll" onclick="checkAll();" />'>
		  			<input type="checkbox" id="selectArray" name="selectArray" value="${row.id}" />
		  		</display:column>
		  		<display:column title="���"><c:out value="${row_rowNum}" /></display:column>
		  		<display:column title="��֯�ṹ����" value="${row.struType.name}" />
		  		<display:column title="�ϼ���֯����" value="${row.organType.name}" />
		  		<display:column title="�¼���֯����"><c:forEach items="${row.targetOrganType}" var="organType"><c:out value="${organType.name} "></c:out></c:forEach> </display:column>
		  		<display:column title="����˵��" property="ruleNote" />
		  	</display:table>
		  	<s:submit id="forAdd" name="forAdd" value="����" action="forAddStruRuleAction" cssClass="BigButton" align="center" />
		  	<input id="forEdit" name="forEdit" type="button" value="�޸�" class="BigButton" align="middle" />
		  	<input id="detail" name="detail" type="button" value="��ϸ" class="BigButton" align="middle" />
		  	<input id="delete" name="delete" type="button" value="ɾ��" class="BigButton" align="middle" />
		</s:form>
	</div>
  </body>
</html>
