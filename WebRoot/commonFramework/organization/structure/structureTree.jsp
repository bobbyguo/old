<%@ page contentType="text/html;charset=Gb2312" %>
<%@ include file="/commons/taglibs.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<%@ include file="/commons/meta.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/styles/tree_component.css">
	<script type="text/javascript" src="${ctx}/scripts/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/css.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/tree_component.js"></script>
	<style type="text/css">
		a:HOVER {
			text-decoration: none;
		}
	</style>	
	<script type="text/javascript">
		function openWindow(NODE) {
			window.showModalDialog("organType_select.jsp?structureId=" + $(NODE).attr("id"), self, "DialogHeight:200px;DialogWidth:150px;location:no;menubar:no;toolbar:no;status:no;scroll:yes");
		}
	</script>	
	<script type="text/javascript" defer="defer">
		$(function() {
			//$.ajaxSetup({cache : false});
			$("#loading").ajaxStart(function() {
				$(this).show();
			});
			$("#loading").ajaxStop(function() {
				$(this).hide();
			});
			
			$("#divForTree").tree({
				data : {
					type : "json",
					url : "organization/manageStruType?struType.id=" + <%=request.getParameter("struTypeId")%>
				},
				ui : {
					theme_name	: "classic",
		        	context : [
						{
							id		: "add",
							label	: "增加下级", 
							icon	: "images/create.png",
							visible	: function (NODE, TREE_OBJ) { 
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								openWindow(NODE);
							} 
						},
						{
							id		: "modify",
							label	: "修改", 
							icon	: "images/rename.png",
							visible	: function (NODE, TREE_OBJ) {
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								var frame = parent.document.getElementById("structureRightFrame");
    							frame.src = "organization/forEditStructureAction?structure.id=" + $(NODE).attr("id");
							} 
						},
						{
							id		: "delete",
							label	: "撤销", 
							icon	: "images/remove.png",
							visible	: function (NODE, TREE_OBJ) {
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								var $nodeId = $(NODE).attr("id");
								var $ul = $(NODE).children("ul");
								
								if($ul.children().is("li")) {
									alert( "\"" + $(NODE).children("a").text() + "\" 含有下级，请删除其下级再进行该操作!");
									return;
								}else {
									if (window.confirm("确认撤销吗？")) {
										$.each(NODE, function () { 
											TREE_OBJ.remove(this);
											$.ajax({
							      				type : 'POST',
							      				url : 'organization/deleteStructureAction',
							      				data : {"structure.id": $nodeId}
							      			}); 
										}); 
									}
								} 
							}
						}
		        	]
		      	},
				callback : {
					onselect : function(NODE, TREE_OBJ) {
						var frame = parent.document.getElementById("structureRightFrame");
    					frame.src = "organization/detailStructureAction?structure.id=" + $(NODE).attr("id");
					} 
				}
			});
			
			$("#commit").click(function() {
				$("#struTypeId").attr("value", <%=request.getParameter("struTypeId")%>);
				with(document.getElementById("structureForm")) {
    				var frame = parent.document.getElementById("structureRightFrame");
    				frame.src = "organization/forAddStructureAction?structure.id=" + 
    								$("#structureId").attr("value") + 
    								"&structure.organ.organType.id=" + $("#structureOrganOrganTypeId").attr("value") + 
    								"&structure.struType.id=" + $("#struTypeId").attr("value");
    			}
			});
		})
	</script>
  </head>
  
  <body>
    <div id="divForTree" style="padding-top: 25px; padding-left: 10px;"></div>
    <div id="loading" style="padding-left: 30px;"><img src="${ctx}/images/loading.gif" /></div>
    <s:form id="structureForm" method="post" cssStyle="border: 0;">
    	<s:hidden id="struTypeId" name="structure.struType.id" />
    	<s:hidden id="structureId" name="structure.id" />
    	<s:hidden id="structureOrganOrganTypeId" name="structure.organ.organType.id" /> 
    </s:form>
    <input type="button" id="commit" name="commit" style="display: none;"/>
  </body>
</html>
