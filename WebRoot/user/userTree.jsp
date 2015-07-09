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
					url : "makeUserListTreeUserAction"//,
					//async : true,
					//async_data : function(NODE) {
						//return { id : $(NODE).attr("id") || 0 }
					//}
				},
				ui : {
					theme_name	: "classic",
		        	context : [
						{
							id		: "add",
							label	: "增加下级", 
							icon	: "images/create.png",
							visible	: function (NODE, TREE_OBJ) {
								if(NODE.hasClass("leaf")) {
									return false;
								} 
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								var frame = parent.document.getElementById("userRightFrame");
								frame.src = "forAddUserAction?structure.id=" + $(NODE).attr("id");
							} 
						},
						{
							id		: "modify",
							label	: "修改", 
							icon	: "images/rename.png",
							visible	: function (NODE, TREE_OBJ) {
								if(NODE.hasClass("leaf")) {
									return true;
								} 
								return false;
							}, 
							action	: function (NODE, TREE_OBJ) {
								var frame = parent.document.getElementById("userRightFrame");
    							frame.src = "forEditUserAction?user.id=" + $(NODE).attr("id");
							} 
						},
						{
							id		: "delete",
							label	: "撤销", 
							icon	: "images/remove.png",
							visible	: function (NODE, TREE_OBJ) {
								if(NODE.hasClass("leaf")) {
									return true;
								} 
								return false;
							}, 
							action	: function (NODE, TREE_OBJ) {
								var $nodeId = $(NODE).attr("id");
								
								if (window.confirm("确认撤销吗？")) {
									$.each(NODE, function () { 
										TREE_OBJ.remove(this);
										$.ajax({
							      			type : 'POST',
							      			url : 'deleteUserAction',
							      			data : {"user.id": $nodeId}
							      		}); 
									}); 
								}
							}
						}
		        	]
		      	},
				callback : {
					onselect : function(NODE, TREE_OBJ) {
						var frame = parent.document.getElementById("userRightFrame");
						if($(NODE).hasClass("leaf")) {
							frame.src = "detailUserAction?user.id=" + $(NODE).attr("id");
						}else {
							frame.src = "${ctx}/user/userContent.jsp"
						}
					} 
				}
			});
		});
	</script>
  </head>
  
  <body>
    <div id="divForTree" style="padding-top: 25px; padding-left: 10px;"></div>
    <div id="loading" style="padding-left: 30px;"><img src="${ctx}/images/loading.gif" /></div>
  </body>
</html>
