/**
 * @(#)OrganType.java 2009-12-24 上午10:19:45
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.model;

import java.io.Serializable;

/**
 * 
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 上午10:19:45 <br>
 */
public class OrganType implements Serializable{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 9147909575715375720L;

	/**
	 * 内码
	 */
	private String id;
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 上级类型
	 */
	private OrganType parentType;
	/**
	 * 是否在用,1表示在用,0表示停用
	 */
	private String inUse;
	
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
	 * @return the parentType
	 */
	public OrganType getParentType() {
		return parentType;
	}
	
	/**
	 * @param parentType the parentType to set
	 */
	public void setParentType(OrganType parentType) {
		this.parentType = parentType;
	}
	
	/**
	 * @return the inUse
	 */
	public String getInUse() {
		return inUse;
	}
	
	/**
	 * @param inUse the inUse to set
	 */
	public void setInUse(String inUse) {
		this.inUse = inUse;
	}
}
