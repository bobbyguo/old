/**
 * @(#)StruRule.java 2009-12-24 上午10:20:55
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 组织结构规则类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 上午10:20:55 <br>
 */
public class StruRule implements Serializable{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -3503579069961391980L;

	/**
	 * 内码
	 */
	private String id;
	/**
	 * 组织结构类型
	 */
	private StruType struType;
	/**
	 * 上级组织类型
	 */
	private OrganType organType;
	/**
	 * 可引用的下级组织类型
	 */
	private Set<OrganType> targetOrganType = new HashSet<OrganType>();
	/**
	 * 说明
	 */
	private String ruleNote;
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
	 * @return the struType
	 */
	public StruType getStruType() {
		return struType;
	}
	/**
	 * @param struType the struType to set
	 */
	public void setStruType(StruType struType) {
		this.struType = struType;
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
	 * @return the targetOrganType
	 */
	public Set<OrganType> getTargetOrganType() {
		return targetOrganType;
	}
	/**
	 * @param targetOrganType the targetOrganType to set
	 */
	public void setTargetOrganType(Set<OrganType> targetOrganType) {
		this.targetOrganType = targetOrganType;
	}
	/**
	 * @return the ruleNote
	 */
	public String getRuleNote() {
		return ruleNote;
	}
	/**
	 * @param ruleNote the ruleNote to set
	 */
	public void setRuleNote(String ruleNote) {
		this.ruleNote = ruleNote;
	}
	
	
}
