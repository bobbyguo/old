/**
 * @(#)StruRule.java 2009-12-24 ����10:20:55
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ��֯�ṹ�����ࡣ
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 ����10:20:55 <br>
 */
public class StruRule implements Serializable{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -3503579069961391980L;

	/**
	 * ����
	 */
	private String id;
	/**
	 * ��֯�ṹ����
	 */
	private StruType struType;
	/**
	 * �ϼ���֯����
	 */
	private OrganType organType;
	/**
	 * �����õ��¼���֯����
	 */
	private Set<OrganType> targetOrganType = new HashSet<OrganType>();
	/**
	 * ˵��
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
