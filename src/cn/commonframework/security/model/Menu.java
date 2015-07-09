/**
 * @(#)Menu.java 2009-12-14 下午02:40:15
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 框架菜单类  用于菜单的展现
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-14 下午02:40:15 <br>
 */
public class Menu implements Serializable{
	
	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -6314542644170695440L;
	
	/**
     * 内码
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 链接地址
     */
    private String url;
    /**
    * 响应frame
    */
    private String target;
	/**
     * 上级菜单 一对多
     */
    private Menu parentMenu;
    /**
     * 排序使用
     */
    private Integer seq;
	/**
     * 为了使用方便，添加子菜单集合
     */
    private Set<Menu> childMenus = new HashSet<Menu>();
     
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	/**
	 * @return the parentMenu
	 */
	public Menu getParentMenu() {
		return parentMenu;
	}
	
	/**
	 * @param parentMenu the parentMenu to set
	 */
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	/**
	 * @return the childMenus
	 */
	public Set<Menu> getChildMenus() {
		return childMenus;
	}
	
	/**
	 * @param childMenus the childMenus to set
	 */
	public void setChildMenus(Set<Menu> childMenus) {
		this.childMenus = childMenus;
	}	
}
