/*列表式侧栏定义、义--GooAccordion类*/
//acdDiv:JQUERY对象，要渲染的DOM
//acdWidth：数字，控件的宽度 0为"auto";
//acdHeight：数字，控件的高度 0为"auto";
//items：控件的列表项目集，只有获取列表项目集数目和高度都被定好后，才能决定出每个列表单元的内容框高度
//列表标题栏的高一律定为22PX+2px的边框
//构造完后，如果传入的items为null,则还需要行另外的设定列表项目的操作后才能用

function GooAccordion(acdDiv,acdWidth,acdHeight,items){
	//初始化背景框
	this.$acdDiv=acdDiv;
	this.$acdDiv.addClass("Accordion");
	if(acdWidth!=0)
		this.$acdDiv.css("width",(acdWidth-4)+"px");
	else
		this.$acdDiv.css("width","auto");
	if(acdHeight!=0)
		this.$acdDiv.css("height",(acdHeight-3)+"px");
	else
		this.$acdDiv.css("height","auto");
	//考虑到宽和高都有auto的可能，记录控件的真实宽高不能以传入值作为参考，而应该用JS去获得真实值
	this.$acdWidth=this.$acdDiv.attr("offsetWidth");
	this.$acdHeight=this.$acdDiv.attr("offsetHeight");
	this.$items={};
	this.$itemsNum=0;
	this.$contentWidth=0;
	this.$selectedItemId="";
	////////////////////
	//下拉列表单元
	this.openItem=function(index){
		var temp=this.$acdDiv.children("div:eq("+index+")");
		if(temp){
			temp.children("div:eq(0)").removeClass("acdLabel");
			temp.children("div:eq(0)").addClass("selectLabel");
			temp.children("div:eq(1)").css("height",(this.$contentWidth-5)+"px");
			temp.children("div:eq(1)").slideDown("fast");
			if(this.$selectedItemId!="")
				this.foldItem2(this.$selectedItemId);//先收起前一个打开的列表单元
			this.$selectedItemId=temp.attr("id");
		}
	};
	this.openItem2=function(id){
		var temp=this.$items[id];
		if(temp){
			temp.children("div:eq(0)").removeClass("acdLabel");
			temp.children("div:eq(0)").addClass("selectLabel");
			temp.children("div:eq(1)").css("height",(this.$contentWidth-5)+"px");
			temp.children("div:eq(1)").slideDown("fast");
			if(this.$selectedItemId!="")
				this.foldItem2(this.$selectedItemId);//收起前一个打开的列表单元		
			this.$selectedItemId=id;
		}
	};
	//收起列表单元
	this.foldItem=function(index){
		var temp=this.$acdDiv.children("div:eq("+index+")");
		if(temp.length>0){
			temp.children("div:eq(1)").slideUp("fast",function(){
				temp.children("div:eq(0)").removeClass("selectLabel");
				temp.children("div:eq(0)").addClass("acdLabel");
			});
			this.$selectedItemId="";
		}
	};
	this.foldItem2=function(id){
		var temp=this.$items[id];
		if(temp.length>0){
			temp.children("div:eq(1)").slideUp("fast",function(){
				temp.children("div:eq(0)").removeClass("selectLabel");
				temp.children("div:eq(0)").addClass("acdLabel");
			});
			this.$selectedItemId="";
		}
	};
	//先设计初始化列表项目单元的函数
	//这里的items与构造函数中的items数据结构一样
	var inthis=this;
	this.initItems=function(items){
		for(i=0;i<items.length;i++){
			this.$items[items[i].id]=$("<div class=\"item\" id=\""+items[i].id+"\"><div class=\"acdLabel\"><span class=\"title\">"
									+items[i].title+"</span><b></b></div><div class=\"acdContent\">"+(items[i].content||"")+"</div></div>");

			this.$acdDiv.append(this.$items[items[i].id]);
			this.$items[items[i].id].children("div:eq(0)").bind("click",{foo:items[i]},function(e){
				id=$(this).parent().attr("id");
				//如果点击的不是前一个被打开的
				if(id!=inthis.$selectedItemId){
					inthis.openItem2(id);
					if(!$(this).parent().data("isReady")){
					switch(e.data.foo.autoLoad){
						case "html":
							inthis.loadData2(id,$("#"+e.data.foo.dataId).html());
							break;
						case "ajax":
							inthis.loadData2(id,"载入中……");
							$.ajax({type:e.data.foo.ajax.type,
									url:e.data.foo.ajax.url,
									data:e.data.foo.ajax.para||"",
									success: function(msg){
     									inthis.loadData2(id,msg);
									}
							});
							break;
						case "iframe":
							inthis.loadUrl2(id,e.data.foo.url);
							break;
					}
					}
					$(this).parent().data("isReady",1);//防止过度重复载入
				}
				else
					inthis.foldItem2(id);
			});
		}
		this.$itemsNum=items.length;
		this.$contentWidth=this.$acdHeight-25*this.$itemsNum-3;		//确定内容框的高度
	};
	if(items) this.initItems(items);
	//构造函数完毕
	
	/////////////////////////////////////
	//设置一个列表单元的标题：
	//参数index第一个为1，第N个为N
	this.setTitle=function(index,title){
		this.$acdDiv.children("div:eq("+index+")").children("div:eq(0)").children("span").html(title);
	};
	//参数id
	this.setTitle2=function(id,title){
		this.$items[id].children("div:eq(0)").children("span").html(title);
	};
	//获取一个列表单元的标题：
	//参数index第一个为1，第N个为N
	this.getTitle=function(index){
		return this.$acdDiv.children("div:eq("+index+")").children("div:eq(0)").children("span").html();
	};
	//参数id
	this.getTitle2=function(id){
		return this.$items[id].children("div:eq(0)").children("span").html();
	};
	//给一个列表单元载入内容
	//index:序列号第一个为1，第N个为N
	//data:JSON数据，可以是IFRAME
	this.loadData=function(index,data){
		var temp=this.$acdDiv.children("div:eq("+index+")").children("div:eq(1)");
		temp.empty();
		temp.append(data);
	};
	//id:唯一ID
	//data:JSON数据，可以是IFRAME
	this.loadData2=function(id,data){
		var temp= this.$items[id].children("div:eq(1)");
		temp.empty();
		temp.append(data);
	};
	//给一个列表单元载入一个URL下的作为子框架的内容
	//index:序列号第一个为1，第N个为N
	//url:链接网址
	this.loadUrl=function(index,url){
		var temp=this.$acdDiv.children("div:eq("+index+")").children("div:eq(1)");
		temp.empty();
		temp.append("<iframe style='border:0px;width:100%;height:100%;' name='"
					+temp.parent().attr("id")+"' src='"+url+"' frameborder='0'></iframe>");
	};
	//id:唯一ID
	//url:链接网址
	this.loadUrl2=function(id,url){
		var temp= this.$items[id].children("div:eq(1)");
		temp.empty();
		temp.append("<iframe style='border:0px;width:100%;height:100%;' name='"
					+temp.parent().attr("id")+"' src='"+url+"' frameborder='0'></iframe>");
	};
	//获取一个列表单元内容的JQUERY对象
	//index:序列号第一个为1，第N个为N
	this.getData=function(index){
		return this.$acdDiv.children("div:eq("+index+")").children("div:eq(1)").children();
	};
	//id:唯一ID
	this.getData2=function(id){
		return this.$items[id].children("div:eq(1)").children();
	};

	//在第N个项目单元后边加入一组新的列表单元若干个
	//参数index:插到最前面为0，第一个为1，第N个为N
	//这里的items与构造函数中的items数据结构一样
	this.addItems=function(index,items){
		var temp=null;
			if(index>0) temp=this.$acdDiv.children("div:eq("+(index-1)+")");
		for(i=0;i<items.length;i++){
			//如果要求下拉时才自动载入
			this.$items[items[i].id]=$("<div class=\"item\" id=\""+items[i].id+"\"><div class=\"acdLabel\"><span class=\"title\">"
									+items[i].title+"</span><b></b></div><div class=\"acdContent\">"+(items[i].content||"")+"</div></div>");
			this.$items[items[i].id].children("div:eq(0)").bind("click",{foo:items[i]},function(e){
				id=$(this).parent().attr("id");
				//如果点击的不是前一个被打开的
				if(id!=inthis.$selectedItemId){
					inthis.openItem2(id);
					if(!$(this).parent().data("isReady")){
					switch(e.data.foo.autoLoad){
						case "html":
							inthis.loadData2(id,$("#"+e.data.foo.dataId).html());
							break;
						case "ajax":
							inthis.loadData2(id,"载入中……");
							$.ajax({type:e.data.foo.ajax.type,
									url:e.data.foo.ajax.url,
									data:e.data.foo.ajax.para||"",
									success: function(msg){
     									inthis.loadData2(id,msg);
									}
							});
							break;
						case "iframe":
							inthis.loadUrl2(id,e.data.foo.url);
							break;
					}
					}
					$(this).parent().data("isReady",1);//防止过度重复载入
				}
				else
					inthis.foldItem2(id);
			});
			if(temp!=null)
				temp.after(this.$items[items[i].id]);
			else
				this.$acdDiv.append(this.$items[items[i].id]);
			temp=this.$items[items[i].id];
			this.$itemsNum++;
		}
		this.$contentWidth=this.$contentWidth-items.length*25;		//确定内容框的高度
	};
	//清空所有列表单元
	this.clearItems=function(){
		this.$acdDiv.empty();
	};
	//删除某个列表单元
	this.delItem=function(index){
		var temp=this.$acdDiv.children("div:eq("+(index-1)+")");
		if(temp.length>0){
			temp.remove();
			this.$contentWidth+=25;
		}
	};
	this.delItem2=function(id){
		if(this.$items[id]!=null){
			this.$items[id].remove();
			this.$contentWidth+=25;
		}
	};
	//重构Accordion控件的大小，当控件被放在可调整的LAYOUT中时或者DIV容器中时可用得上(不用传参，让控件再一次重构以自动适应外框的实际宽高)
	this.resize=function(){
		this.$acdWidth=this.$acdDiv.attr("offsetWidth");
		this.$acdHeight=this.$acdDiv.attr("offsetHeight");
		this.$acdDiv.css("width",(this.$acdWidth-4)+"px");
		this.$acdDiv.css("height",(this.$acdHeight-3)+"px");
		this.$contentWidth=this.$acdHeight-25*this.$itemsNum-3;
	};
}

//将此类的构造函数加入至JQUERY对象中
jQuery.extend({
	createGooAccordion: function(acdDiv,acdWidth,acdHeight,items) {
		return new GooAccordion(acdDiv,acdWidth,acdHeight,items);
  }
}); 