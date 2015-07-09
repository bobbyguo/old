<%@ page language="java" pageEncoding="GBK"%>
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
		.hidden {
			display : none;
		}
		a:HOVER {
			text-decoration: none;
		}
	</style>	
	<script type="text/javascript"> 		 
		openwin = {};
		 
		openwin.getWin = function(NODE, TREE_OBJ, TYPE){ 
			var $obj = $("#open");
			$obj.empty(); 
			//是否已经新开窗口 
			if($obj.html() != '' ){ 
				openWindow = $("#newWindow");//存在窗口 
				openWindow.slideToggle("slow");//显示效果 
			}else{ 
				if(TYPE == "add") {
					var openWindow = $(openwin.getAddHtml(NODE, TREE_OBJ));//模拟弹出窗口样式
				}else if(TYPE == "edit") {
					var openWindow = $(openwin.getEditHtml(NODE, TREE_OBJ));//模拟弹出窗口样式
				} 
				
				//定义CSS样式 模拟关键 自定义设置CSS样式 
				openWindow.css({ 
					'font-size':'12px', 
					'position':'absolute', 
					'margin':'20px', 
					'right':'650px', 
					'top':'50px', 
					'width':'300px', 
					'background-color':'#f0f5FF', 
					'border': '1px solid #000', 
					'z-index': '50', 
					'padding':'10px'}); 
				openWindow.appendTo($("#open")); 
			}
			 
			//绑定单击事件 保存
			openWindow.find("#save").click(function(){ 
				openwin.save(); 
			}).end();
			
			//绑定单击事件 更新 
			openWindow.find("#update").click(function(){ 
				openwin.update(); 
			}).end(); 
			
			//绑定单击事件 取消 
			openWindow.find("#cancel").click(function(){ 
				openwin.cancel(); 
			}).end(); 
		}
		 
		//获取窗口页面元素 模拟窗口 
		openwin.getAddHtml = function(NODE, TREE_OBJ){
			html = '<div id="newWindow">'; 
			html += '<form id="menuForm" name="menuForm">'; 
			html += '<p>菜单基本信息</p>';
			html += '<input id="menuId" type="hidden" name="menu.id" />';
			html += '<input id="parentId" type="hidden" name="parentId" value="' + $(NODE).attr("id") + '" />';
			html += '<p>父菜单名称：' + $(NODE).children("a").text();
			html += '<p>子菜单名称：<input id="name" type="text" name="menu.name" /></p>';
			html += '<p>子菜单链接：<input id="url" type="text" name="menu.url" /></p>';
			html += '<input id="seq" type="hidden" name="menu.seq" value="' + ($(NODE).children("ul").children("li").size() + 1) + '" />'; 
			html += '<p><button id="save">保存</button> <button id="cancel">取消</button></p>'; 
			html += '</form>';
			html += '</div>';
			
			return html; 
		}
		
		//获取窗口页面元素 模拟窗口 
		openwin.getEditHtml = function(NODE, TREE_OBJ){
			var $this = $(NODE);
			var $children = $this.children("a");
			var location = "<%=basePath%>";
			var url = $children.attr("href");
			if(url.indexOf(location) != -1) {
				url = url.substring(location.length);
				if(url == "null") {
					url = "";
				}
			}
			var $parent = $this.parent().parent();
			html = '<div id="newWindow">'; 
			html += '<form id="menuForm" name="menuForm">'; 
			html += '<p>菜单基本信息</p>';
			html += '<input id="menuId" type="hidden" name="menu.id" value="' +  $this.attr("id") + '" />';
			html += '<input id="parentId" type="hidden" name="parentId" value="' + $parent.attr("id") + '" />';
			html += '<p>父菜单名称：' + $parent.children("a").text();
			html += '<p>子菜单名称：<input id="name" type="text" name="menu.name" value="' + $children.text() + '" /></p>';
			html += '<p>子菜单链接：<input id="url" type="text" name="menu.url" value="' + url + '" /></p>'; 
			html += '<p><button id="update">更新</button> <button id="cancel">取消</button></p>'; 
			html += '</form>';
			html += '</div>';
			
			return html; 
		}
		 
		//保存事件 
		openwin.save = function(){
			with($("#menuForm")[0]) {
    			method = "post";
    			action = "menuAction!addMenu.action";
    			submit();
    		}
    		
			$("#open").empty();  
			$("#newWindow").hide();
		}
		
		//保存事件 
		openwin.update = function(){
			with($("#menuForm")[0]) {
    			method = "post";
    			action = "menuAction!modifyMenu.action";
    			submit();
    		}
    		
			$("#open").empty();  
			$("#newWindow").hide();
		} 
		 
		//取消事件 
		openwin.cancel = function(){
			$("#open").empty(); 
			$("#newWindow").hide(); 
		} 
	</script>	 
	<script type="text/javascript">
  		$(function () {
  			//清理缓存树信息
  			$.ajaxSetup({cache: true});
  			$("#loading").ajaxStart(function() {
				$(this).show();
			});
			$("#loading").ajaxStop(function() {
				$(this).hide();
			});
  			
	    	$("#divForTree").tree({
		      	data  : {
		        	type  : "json",		        	
		        	url : "menuAction!makeMenuTree.action"
		      	},
		      	ui : {
		      		theme_name	: "classic",
		        	context : [
						{
							id		: "add",
							label	: "添加下级菜单", 
							icon	: "images/create.png",
							visible	: function (NODE, TREE_OBJ) { 
								if("退出系统" == NODE.children("a").text())
									return false; 
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								openwin.getWin(NODE, TREE_OBJ, "add");
							} 
						},
						{
							id		: "modify",
							label	: "编辑菜单信息", 
							icon	: "images/rename.png",
							visible	: function (NODE, TREE_OBJ) { 
								if("退出系统" == NODE.children("a").text())
									return false;
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								openwin.getWin(NODE, TREE_OBJ, "edit");
							} 
						},
						{
							id		: "delete",
							label	: "删除菜单信息", 
							icon	: "images/remove.png",
							visible	: function (NODE, TREE_OBJ) {
								if("退出系统" == NODE.children("a").text())
									return false;
								if("主菜单" == NODE.children("a").text())
									return false;
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) { 
								var $nodeId = $(NODE).attr("id");
								var $ul = $(NODE).children("ul");
								
								if($ul.children().is("li")) {
									alert( "\"" + $(NODE).children("a").text() + "\" 含有子菜单，请删除子菜单再进行该操作!");
									return;
								}else {
									if (window.confirm("确认删除吗？")) {
										$.each(NODE, function () { 
											TREE_OBJ.remove(this);
											$.ajax({
								      			type : 'POST',
								      			url : 'menuAction!deleteMenu.action',
								      			data : {"menu.id": $nodeId}
								      		}); 
										}); 
									}
								}
							} 
						}
		        	]
		      	},
		      	rules : {
		      		draggable : "all"
		      	},
		      	callback : {
		      		onselect : function(NODE, TREE_OBJ) {
		      		
		      		},
		      		onmove : function(NODE, REF_NODE, TYPE, TREE_OBJ, RB) {
		      			if($(NODE).text() == "退出系统") {
		      				alert("不能移动该结点!");
		      				return;
		      			}
		      			if($(REF_NODE).text() == "退出系统" && TYPE == "after") {
		      				alert("不能将任何结点移动到该结点后面!")
		      				return;
		      			}		      			
		      			$.ajax({
		      				type : 'GET',
		      				url : 'menuAction!ajaxMoveMenuNode.action',
		      				data : {"type": TYPE, "parentId": $(REF_NODE).attr("id"), "menu.id": $(NODE).attr("id"), "size": $(REF_NODE).children("ul").children("li").size()},
		      				success : function() {
		      					alert("结点移动成功!");
		      				},
		      				error : function() {
		      					alert("error");
		      				}
		      			});
		      		}
		      	}
		    });
		});
  	</script>
  </head>  
  <body>
    <div id="divForTree" style="padding-top: 25px; padding-left: 10px;"></div>
    <div id="loading" style="padding-left: 30px;"><img src="${ctx}/images/loading.gif" /></div> 
	<div id="open"></div>     
  </body>
</html>
