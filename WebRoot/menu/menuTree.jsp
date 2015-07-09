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
			//�Ƿ��Ѿ��¿����� 
			if($obj.html() != '' ){ 
				openWindow = $("#newWindow");//���ڴ��� 
				openWindow.slideToggle("slow");//��ʾЧ�� 
			}else{ 
				if(TYPE == "add") {
					var openWindow = $(openwin.getAddHtml(NODE, TREE_OBJ));//ģ�ⵯ��������ʽ
				}else if(TYPE == "edit") {
					var openWindow = $(openwin.getEditHtml(NODE, TREE_OBJ));//ģ�ⵯ��������ʽ
				} 
				
				//����CSS��ʽ ģ��ؼ� �Զ�������CSS��ʽ 
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
			 
			//�󶨵����¼� ����
			openWindow.find("#save").click(function(){ 
				openwin.save(); 
			}).end();
			
			//�󶨵����¼� ���� 
			openWindow.find("#update").click(function(){ 
				openwin.update(); 
			}).end(); 
			
			//�󶨵����¼� ȡ�� 
			openWindow.find("#cancel").click(function(){ 
				openwin.cancel(); 
			}).end(); 
		}
		 
		//��ȡ����ҳ��Ԫ�� ģ�ⴰ�� 
		openwin.getAddHtml = function(NODE, TREE_OBJ){
			html = '<div id="newWindow">'; 
			html += '<form id="menuForm" name="menuForm">'; 
			html += '<p>�˵�������Ϣ</p>';
			html += '<input id="menuId" type="hidden" name="menu.id" />';
			html += '<input id="parentId" type="hidden" name="parentId" value="' + $(NODE).attr("id") + '" />';
			html += '<p>���˵����ƣ�' + $(NODE).children("a").text();
			html += '<p>�Ӳ˵����ƣ�<input id="name" type="text" name="menu.name" /></p>';
			html += '<p>�Ӳ˵����ӣ�<input id="url" type="text" name="menu.url" /></p>';
			html += '<input id="seq" type="hidden" name="menu.seq" value="' + ($(NODE).children("ul").children("li").size() + 1) + '" />'; 
			html += '<p><button id="save">����</button> <button id="cancel">ȡ��</button></p>'; 
			html += '</form>';
			html += '</div>';
			
			return html; 
		}
		
		//��ȡ����ҳ��Ԫ�� ģ�ⴰ�� 
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
			html += '<p>�˵�������Ϣ</p>';
			html += '<input id="menuId" type="hidden" name="menu.id" value="' +  $this.attr("id") + '" />';
			html += '<input id="parentId" type="hidden" name="parentId" value="' + $parent.attr("id") + '" />';
			html += '<p>���˵����ƣ�' + $parent.children("a").text();
			html += '<p>�Ӳ˵����ƣ�<input id="name" type="text" name="menu.name" value="' + $children.text() + '" /></p>';
			html += '<p>�Ӳ˵����ӣ�<input id="url" type="text" name="menu.url" value="' + url + '" /></p>'; 
			html += '<p><button id="update">����</button> <button id="cancel">ȡ��</button></p>'; 
			html += '</form>';
			html += '</div>';
			
			return html; 
		}
		 
		//�����¼� 
		openwin.save = function(){
			with($("#menuForm")[0]) {
    			method = "post";
    			action = "menuAction!addMenu.action";
    			submit();
    		}
    		
			$("#open").empty();  
			$("#newWindow").hide();
		}
		
		//�����¼� 
		openwin.update = function(){
			with($("#menuForm")[0]) {
    			method = "post";
    			action = "menuAction!modifyMenu.action";
    			submit();
    		}
    		
			$("#open").empty();  
			$("#newWindow").hide();
		} 
		 
		//ȡ���¼� 
		openwin.cancel = function(){
			$("#open").empty(); 
			$("#newWindow").hide(); 
		} 
	</script>	 
	<script type="text/javascript">
  		$(function () {
  			//����������Ϣ
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
							label	: "����¼��˵�", 
							icon	: "images/create.png",
							visible	: function (NODE, TREE_OBJ) { 
								if("�˳�ϵͳ" == NODE.children("a").text())
									return false; 
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								openwin.getWin(NODE, TREE_OBJ, "add");
							} 
						},
						{
							id		: "modify",
							label	: "�༭�˵���Ϣ", 
							icon	: "images/rename.png",
							visible	: function (NODE, TREE_OBJ) { 
								if("�˳�ϵͳ" == NODE.children("a").text())
									return false;
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) {
								openwin.getWin(NODE, TREE_OBJ, "edit");
							} 
						},
						{
							id		: "delete",
							label	: "ɾ���˵���Ϣ", 
							icon	: "images/remove.png",
							visible	: function (NODE, TREE_OBJ) {
								if("�˳�ϵͳ" == NODE.children("a").text())
									return false;
								if("���˵�" == NODE.children("a").text())
									return false;
								return true;
							}, 
							action	: function (NODE, TREE_OBJ) { 
								var $nodeId = $(NODE).attr("id");
								var $ul = $(NODE).children("ul");
								
								if($ul.children().is("li")) {
									alert( "\"" + $(NODE).children("a").text() + "\" �����Ӳ˵�����ɾ���Ӳ˵��ٽ��иò���!");
									return;
								}else {
									if (window.confirm("ȷ��ɾ����")) {
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
		      			if($(NODE).text() == "�˳�ϵͳ") {
		      				alert("�����ƶ��ý��!");
		      				return;
		      			}
		      			if($(REF_NODE).text() == "�˳�ϵͳ" && TYPE == "after") {
		      				alert("���ܽ��κν���ƶ����ý�����!")
		      				return;
		      			}		      			
		      			$.ajax({
		      				type : 'GET',
		      				url : 'menuAction!ajaxMoveMenuNode.action',
		      				data : {"type": TYPE, "parentId": $(REF_NODE).attr("id"), "menu.id": $(NODE).attr("id"), "size": $(REF_NODE).children("ul").children("li").size()},
		      				success : function() {
		      					alert("����ƶ��ɹ�!");
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
