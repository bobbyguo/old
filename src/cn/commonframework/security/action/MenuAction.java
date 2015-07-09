/**
 * 
 */
package cn.commonframework.security.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.commonframework.security.model.Menu;
import cn.commonframework.security.service.IMenuService;

import com.javaeedev.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-15 下午03:15:35 <br>
 *
 */
public class MenuAction extends ActionSupport {

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -410895372677970163L;

	private Menu menu = new Menu();	
	private IMenuService menuService = null;
	private StringBuffer jsonDataBuffer = new StringBuffer();
	private String parentId = null;
	private String type = null;
	private Integer size = 0;
	
	/**
	 * 保存新添菜单信息
	 * @return
	 * @throws Exception
	 */
	public String addMenu() throws Exception {
		if(this.getParentId() != null && this.getParentId().trim().length() > 0) {
//			Menu parentMenu = this.getMenuService().findById(this.getParentId());
			
//			if("主菜单".equals(parentMenu.getName())) {
//				menu.setSeq(menu.getSeq() - 1);
//			}
			
			menu.setParentMenu(this.getMenuService().findById(this.getParentId()));
			this.getMenuService().saveMenu(menu);
		}
		
		return "success";
	}
	
	/**
	 * 更新菜单信息
	 * @return
	 * @throws Exception
	 */
	public String modifyMenu() throws Exception {
		if(menu.getId() != null && menu.getId().trim().length() > 0) {
			Menu currentMenu = this.getMenuService().findById(menu.getId());
			
			currentMenu.setName(menu.getName());
			currentMenu.setTarget(menu.getTarget());
			currentMenu.setUrl(menu.getUrl());
			
			this.getMenuService().updateMenu(currentMenu);
		}
		
		return "success";
	}
	
	/**
	 * 移动树结点
	 * @return
	 * @throws Exception
	 */
	public String ajaxMoveMenuNode() throws Exception {
		if("inside".equals(this.getType())) {
			//获取移动到的结点
			Menu parentMenu = this.getMenuService().findById(this.getParentId());
			//获取被移动的结点
			Menu childrenMenu = this.getMenuService().findById(menu.getId());
			
			//设置被移动结点需要更新的信息,并实现更新
			childrenMenu.setParentMenu(parentMenu);
			childrenMenu.setSeq(this.getSize());
			this.getMenuService().updateMenu(childrenMenu);
			
			return "success";
		}
		
		if("after".equals(this.getType())) {
			//获取被移动结点移动到位置的上一个结点
			Menu parentMenu = this.getMenuService().findById(this.getParentId());
			//获取被移动的结点
			Menu childrenMenu = this.getMenuService().findById(menu.getId());
			//获取两结点的ID
			String parentMenuId = parentMenu.getParentMenu().getId();
			String childrenMenuId = childrenMenu.getParentMenu().getId();
			//获取两结点的原始位置
			Integer refNodeSeq = parentMenu.getSeq();
			Integer nodeSeq = childrenMenu.getSeq();
			//存放需要更新的结点
			List<Menu> updateList = new ArrayList<Menu>();
			
			//判断两结点的父结点是否相同
			if(parentMenuId.equals(childrenMenuId)) {				
				List<Menu> middleMenu = new ArrayList<Menu>();
				
				//被移动结点的顺序位置大于引用结点的位置
				if(refNodeSeq < nodeSeq) {
					//查询得到两结点之间的结点
					middleMenu = this.getMenuService()
												.getAllByHql("from Menu where parent = '" + 
														parentMenuId + "' and seq < " + 
														nodeSeq + " and seq > " + 
														refNodeSeq + " order by seq");
					
					//为被移动结点设置新位置
					childrenMenu.setSeq(++refNodeSeq);
					updateList.add(childrenMenu);
					
					//更新两结点之间结点的位置
					for(Menu item : middleMenu) {
						item.setSeq(++refNodeSeq);
						updateList.add(item);
					}
				}else {
					//查询得到两结点之间的结点
					middleMenu = this.getMenuService()
									 .getAllByHql("from Menu where parent = '" + 
											 parentMenuId + "' and seq > " + 
											 nodeSeq + " and seq <= " + 
											 refNodeSeq  + " order by seq");

					//为得移动结点设置新位置
					childrenMenu.setSeq(refNodeSeq);
					updateList.add(childrenMenu);
					
					//更新两结点之间结点的位置
					for(Menu item : middleMenu) {
						item.setSeq(nodeSeq++);
						updateList.add(item);
					}
				}
			}else {
				List<Menu> refNodeMiddleMenu = new ArrayList<Menu>();
				List<Menu> nodeMiddleMenu = new ArrayList<Menu>();
				
				//查询得到引用结点后的结点
				refNodeMiddleMenu = this.getMenuService()
								 .getAllByHql("from Menu where parent = '" + 
										 parentMenuId + "' and seq > " + 
										 refNodeSeq  + " order by seq");
				
				//设置被移动结点的新父结点与新位置
				childrenMenu.setParentMenu(parentMenu.getParentMenu());
				childrenMenu.setSeq(++refNodeSeq);
				updateList.add(childrenMenu);
				
				for(Menu item : refNodeMiddleMenu) {
					item.setSeq(++refNodeSeq);
					updateList.add(item);
				}
				
				//查询得到被移动结点后的结点
				nodeMiddleMenu = this.getMenuService()
								 		.getAllByHql("from Menu where parent = '" + 
								 				childrenMenuId + "' and seq > " + 
								 				nodeSeq  + " order by seq");
				
				for(Menu item : nodeMiddleMenu) {
					item.setSeq(nodeSeq++);
					updateList.add(item);
				}
			}
			
			this.getMenuService().batchUpdate(updateList);
			
			return null;
		}
		
		if("before".equals(this.getType())) {
			Menu parentMenu = this.getMenuService().findById(this.getParentId());
			Menu childrenMenu = this.getMenuService().findById(menu.getId());
			String parentMenuId = parentMenu.getParentMenu().getId();
			String childrenMenuId = childrenMenu.getParentMenu().getId();
			Integer refNodeSeq = parentMenu.getSeq();
			Integer nodeSeq = childrenMenu.getSeq();
			List<Menu> updateList = new ArrayList<Menu>();
			
			if(parentMenuId.equals(childrenMenuId)) {				
				List<Menu> middleMenu = new ArrayList<Menu>();
				
				if(refNodeSeq < nodeSeq) {					
					middleMenu = this.getMenuService()
												.getAllByHql("from Menu where parent = '" + 
														parentMenuId + "' and seq >= " + 
														refNodeSeq + " and seq < " + 
														nodeSeq + " order by seq");
					
					childrenMenu.setSeq(refNodeSeq++);
					updateList.add(childrenMenu);
					
					for(Menu item : middleMenu) {
						item.setSeq(refNodeSeq++);
						updateList.add(item);
					}
				}else {
					middleMenu = this.getMenuService()
									 .getAllByHql("from Menu where parent = '" + 
											 parentMenuId + "' and seq > " + 
											 nodeSeq + " and seq < " + 
											 refNodeSeq  + " order by seq");

					childrenMenu.setSeq(--refNodeSeq);
					updateList.add(childrenMenu);
					
					for(Menu item : middleMenu) {
						item.setSeq(nodeSeq++);
						updateList.add(item);
					}
				}
			}else {
				List<Menu> refNodeMiddleMenu = new ArrayList<Menu>();
				List<Menu> nodeMiddleMenu = new ArrayList<Menu>();
				
				refNodeMiddleMenu = this.getMenuService()
								 .getAllByHql("from Menu where parent = '" + 
										 parentMenuId + "' and seq >= " + 
										 refNodeSeq  + " and seq < 99 order by seq");
				
				childrenMenu.setParentMenu(parentMenu.getParentMenu());
				childrenMenu.setSeq(refNodeSeq++);
				updateList.add(childrenMenu);
				
				for(Menu item : refNodeMiddleMenu) {
					item.setSeq(refNodeSeq++);
					updateList.add(item);
				}
				
				nodeMiddleMenu = this.getMenuService()
								 		.getAllByHql("from Menu where parent = '" + 
								 				childrenMenuId + "' and seq > " + 
								 				nodeSeq  + " and seq < 99 order by seq");
				
				for(Menu item : nodeMiddleMenu) {
					item.setSeq(nodeSeq++);
					updateList.add(item);
				}
			}
			
			this.getMenuService().batchUpdate(updateList);
		}
		
		return null;
	}
	
	/**
	 * 删除菜单信息
	 * @return
	 * @throws Exception
	 */
	public String deleteMenu() throws Exception {
		if(menu != null && menu.getId() != null && menu.getId().trim().length() > 0) {
			this.getMenuService().deleteMenu(this.getMenuService().findById(menu.getId()));
		}
		
		return "success";
	}
	
	/**
	 * 获取json数据并发送的json数据,顶层菜单数据
	 * @return
	 * @throws Exception
	 */
	public String jsonDataToJsp() throws Exception {
		this.renderJsonData(this.makeParentJsonData("1"));
		
		return null;
	}
	
	/**
	 *  获取json数据并发送json数据,左侧菜单数据
	 * @return
	 * @throws Exception
	 */
	public String jsonDataToLeftFrame() throws Exception {
		if(menu != null && menu.getId() != null && menu.getId().trim().length() > 0) {
			this.renderJsonData(this.makeLeftJsonData(menu.getId()));
		}
		
		return null;
	}
	
	/**
	 * 获取json数据并发送json数据,左侧菜单的下层菜单的数据
	 * @return
	 * @throws Exception
	 */
	public String getDataForSecondLeftFrame() throws Exception {
		String jsonData = "[";
		
		if(menu != null && menu.getId() != null && menu.getId().trim().length() > 0) {
			jsonData += this.getJsonData(menu.getId());
		}
		
		jsonData += "]";
		
		this.renderJsonData(jsonData);
		
		return null;
	}
	
	/**
	 * 获取json数据并发送json数据,菜单树的数据
	 * @return
	 * @throws Exception
	 */
	public String makeMenuTree() throws Exception {
		String jsonData = "[";
		
		jsonData += this.getJsonData("0");
		
		jsonData += "]";
		
		this.renderJsonData(jsonData);
		
		return null;
	}
	
	/**
	 * 组装左侧菜单的下层菜单的json数据
	 * @param parentId
	 * @return
	 */
	public String getJsonData(String parentId) {
//		List<Menu> list = new ArrayList<Menu>(this.getMenuService().findById(parentId).getChildMenus());
		List<Menu> list = this.getMenuService()
							  .getAllByHql("from Menu where parent='" + parentId + "' order by seq");

		for(int i = 0; i < list.size(); i++) {
			Menu item = list.get(i);
//			List<Menu> otherList = new ArrayList<Menu>(item.getChildMenus());
			List<Menu> otherList = this.getMenuService()
			 						   .getAllByHql("from Menu where parent='" + item.getId() + "' order by seq");
			
			if(otherList.size() > 0) {
				jsonDataBuffer.append("{attributes: {id:")
							  .append(JsonUtil.toJson(item.getId()))
							  .append("},state:\"close\",data: {title:")
							  .append(JsonUtil.toJson(item.getName()))
							  .append(",attributes:{\"href\":")
							  .append(JsonUtil.toJson(item.getUrl()))
							  .append("}},children:[");
				this.getJsonData(item.getId());
				jsonDataBuffer.append("]")
							  .append("    }");
			}else {
				jsonDataBuffer.append("{attributes: {id:")
							  .append(JsonUtil.toJson(item.getId()))
							  .append("},state:\"close\",data: {title:")
							  .append(JsonUtil.toJson(item.getName()))
							  .append(",attributes:{\"href\":")
							  .append(JsonUtil.toJson(item.getUrl()))
				  			  .append("}}}");
			}
			
			if(i < list.size() - 1) {
				jsonDataBuffer.append(",");
			}
			
		}
		
		return jsonDataBuffer.toString();
	}
	
	/**
	 * 组装顶层菜单的json数据
	 * @param mainMenuId
	 * @return
	 * @throws Exception
	 */
	public String makeParentJsonData(String mainMenuId) throws Exception {
		//存放json数据
		StringBuffer jsonData = new StringBuffer();
		//存放顶层菜单的List
//		List<Menu> parentMenuList = new ArrayList<Menu>(this.getMenuService().findById(mainMenuId).getChildMenus());
		List<Menu> parentMenuList = new ArrayList<Menu>();
		parentMenuList = this.getMenuService()
		 					 .getAllByHql("from Menu where parent='" + mainMenuId + "' order by seq");
		
		jsonData.append("[");
		
		for(int i = 0; i < parentMenuList.size(); i++) {
			Menu item = parentMenuList.get(i);
			jsonData.append("{")
					.append("\"id\":")
					.append(JsonUtil.toJson(item.getId()))
					.append(",\"name\":")
					.append(JsonUtil.toJson(item.getName()))
					.append(",\"url\":\"");
			
			if("_top".equals(item.getTarget())) {
				jsonData.append(item.getUrl());
			}else {
				jsonData.append(item.getUrl())
						.append("?menu.id=")
						.append(item.getId());
			}
			
			jsonData.append("\",\"target\":")
					.append(JsonUtil.toJson(item.getTarget()))
					.append("}");
			
			if( i < parentMenuList.size() - 1) {
				jsonData.append(",");
			} 
		}
		
		jsonData.append("]");
		
		System.out.println(jsonData.toString());
		
		return jsonData.toString();
	}
	
	/**
	 * 组装左侧菜单栏的json数据
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public String makeLeftJsonData(String parentId) throws Exception {
		//存放json数据
		StringBuffer jsonData = new StringBuffer();
		//存放顶层菜单的List
//		List<Menu> leftMenuList = new ArrayList<Menu>(this.getMenuService().findById(parentId).getChildMenus());
		List<Menu> leftMenuList = new ArrayList<Menu>();
		leftMenuList = this.getMenuService()
		 				   .getAllByHql("from Menu where parent='" + parentId + "' order by seq");
		
		jsonData.append("[");
		
		for(int i = 0; i < leftMenuList.size(); i++) {
			Menu item = leftMenuList.get(i);
			jsonData.append("{")
					.append("id:\"")
					.append(item.getId())
					.append("\",title:\"")
					.append(item.getName())
					.append("\",autoLoad:\"iframe\",url:\"")
					.append(item.getUrl())
					.append("?menu.id=")
					.append(item.getId())
					.append("\"}");
			
			if( i < leftMenuList.size() - 1) {
				jsonData.append(",");
			} 
		}
		
		jsonData.append("]");
		
		System.out.println(jsonData.toString());
		
		return jsonData.toString();
	}
	
	/**
	 * 处理组装得到的json数据
	 * @param jsonData
	 * @throws Exception
	 */
	public void renderJsonData(String jsonData) throws Exception {
		ServletActionContext.getResponse().setContentType("application/json; charset=GBK");
		PrintWriter printWriter = ServletActionContext.getResponse().getWriter();
		
		printWriter.write(jsonData);
		printWriter.flush();
	}
	
	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	/**
	 * @return the menuService
	 */
	public IMenuService getMenuService() {
		return menuService;
	}
	
	/**
	 * @param menuService the menuService to set
	 */
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}
	
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}	

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
}