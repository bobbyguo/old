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
				with($("#organForm")[0]) {
					method = "post";
	    			action = "organization/forEditOrganAction";
	    			submit();
				}
			});
			$("#detail").click(function() {
				if(!selectOne()) {
					return;
				}
				with($("#organForm")[0]) {
	    			method = "post";
	    			action = "organization/detailOrganAction";
	    			submit();
	    		}
			});
			$("#delete").click(function() {
				var $length = $("[name=selectArray]:checkbox:checked").length;
				if($length == 0) {
					alert("请选择要删除的记录");
					return;
				}
				if(window.confirm("确认删除吗?")) {
	    			with($("#organForm")[0]) {
	    				method = "post";
	    				action = "organization/deleteOrganAction";
	    				submit();
	    			}
	    		}
			});
        });
    	function selectOne() {
			var $length = $("[name=selectArray]:checkbox:checked").length;
			if($length == 0 || $length > 1) {
				alert("请选择一条记录");
				return false;
			}
			return true;
		}
    </script>
  </head>
  <body>
  	<div id="content" align="center">
	  	<s:form id="organForm" name="organForm" namespace="/organization" theme="simple">
	  		<table class="small" cellpadding="2" cellspacing="1" width="80%" align="center" border="0">
	  			<tbody>
	  				<tr align="center">
	  					<td class="TableData" align="center">
	  						组织名称:&nbsp;<s:textfield id="name" label="组织名称" name="name" />
	  						是否在用:&nbsp;<s:select id="inUse" label="是否在用" name="inUse" headerKey="" headerValue="---请选择---" list="#{'0':'停用', '1':'在用'}" />
	  						<s:submit id="query" name="query" value="查询" cssClass="BigButton" action="queryOrganAction" />
	  					</td>
	  				</tr>
	  			</tbody>
	  		</table>
	  		
		  	<display:table name="list" sort="external" partialList="true" size="size" requestURI="organization/queryOrganAction" pagesize="10" id="row" export="true" class="ITS">
		  		<display:caption>组织列表</display:caption>
		  		<display:column style="width: 50px;" title='<input type="checkbox" id="ifAll" name="ifAll" />'>
		  			<input type="checkbox" id="selectArray" name="selectArray" value="${row.id}" />
		  		</display:column>
		  		<display:column title="序号"><c:out value="${row_rowNum}" /></display:column>
		  		<display:column title="组织编号" property="code" sortable="true" sortName="code" sortProperty="code" />
		  		<display:column title="组织名称" property="name" sortable="true" sortName="name" sortProperty="name" />
		  		<display:column title="组织类型" value="${row.organType.name}" />
		  	</display:table>
		  	<s:submit id="forAdd" name="forAdd" value="增加" action="forAddOrganAction" cssClass="BigButton" align="center" />
		  	<input id="forEdit" name="forEdit" type="button" value="修改" class="BigButton" align="middle" />
		  	<input id="detail" name="detail" type="button" value="明细" class="BigButton" align="middle" />
		  	<input id="delete" name="delete" type="button" value="删除" class="BigButton" align="middle" />
		</s:form>
	</div>
  </body>
</html>