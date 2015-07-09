/**
 * @(#)Organ.java 2009-12-24 上午10:19:57
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.model;

import java.io.Serializable;

/**
 * 
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 上午10:19:57 <br>
 */
public class Organ implements Serializable{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -1622092289617201760L;

	/**
	 * 内码
	 */
	private String id;
	/**
	 * 组织编号
	 */
	private String code;
	/**
	 * 组织名称
	 */
	private String name;
	/**
	 * 组织类型
	 */
	private OrganType organType;
	/**
	 * 是否在用
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return the organType
	 */
	public OrganType getOrganType() {
		return organType;
	}
	
	/**
	 * @param organType the organType to set
	 */
	public void setOrganType(OrganType organType) {
		this.organType = organType;
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
