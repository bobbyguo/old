/**
 * @(#)Structure.java 2009-12-24 上午10:20:43
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import cn.commonframework.security.model.Menu;

/**
 * 组织结构类
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 上午10:20:43 <br>
 */
public class Structure implements Serializable{
	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 7161439786956737816L;
	
	/**
	 * 组织结构内码
	 */
	private String id;
	/**
	 * 对应组织
	 */
	private Organ organ;
	/**
	 * 组织结构类型
	 */
	private StruType struType;
	/**
	 * 上级组织
	 */
	private Organ parentOrgan;
	/**
	 * 所属层次
	 */
	private int struLevel;
	/**
	 * 路径
	 */
	private String struPath;
	/**
	 * 是否有下级
	 */
	private String isLeaf;
	/**
	 * 是否在用
	 */
	private String inUse;
	/**
	 * 所属部门
	 */
	private Organ department;
	/**
	 * 对应菜单集合
	 */
	private Set<Menu> menus = new HashSet<Menu>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public StruType getStruType() {
		return struType;
	}
	public void setStruType(StruType struType) {
		this.struType = struType;
	}
	public Organ getParentOrgan() {
		return parentOrgan;
	}
	public void setParentOrgan(Organ parentOrgan) {
		this.parentOrgan = parentOrgan;
	}
	public int getStruLevel() {
		return struLevel;
	}
	public void setStruLevel(int struLevel) {
		this.struLevel = struLevel;
	}
	public String getStruPath() {
		return struPath;
	}
	public void setStruPath(String struPath) {
		this.struPath = struPath;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getInUse() {
		return inUse;
	}
	public void setInUse(String inUse) {
		this.inUse = inUse;
	}
	public Organ getDepartment() {
		return department;
	}
	public void setDepartment(Organ department) {
		this.department = department;
	}
	
	/**
	 * @return the menus
	 */
	public Set<Menu> getMenus() {
		return menus;
	}
	/**
	 * @param menus the menus to set
	 */
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
}
