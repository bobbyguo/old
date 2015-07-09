/**
 * @(#)StruType.java 2009-12-24 ����10:20:10
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.model;

import java.io.Serializable;

/**
 * ��֯�ṹ����
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 ����10:20:10 <br>
 */
public class StruType implements Serializable{
	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 91225840325407931L;
	/**
	 * ��֯�ṹ��������
	 */
	private String id;
	/**
	 * ��֯�ṹ��������
	 */
	private String name;
	/**
	 * �����֯��λ
	 */
	private Structure root;
	/**
	 * �Ƿ�����
	 */
	private String inUse;
	/**
	 * �Ƿ�Ӧ�ýṹ����
	 */
	private String isApplyRule;
	/**
	 * �Ƿ�֧�־�����
	 */
	private String isMatrix;
	/**
	 * �Ƿ񱣴���ʷ��¼
	 */
	private String isKeepRecord;
	/**
	 * �Ƿ�Ĭ��
	 */
	private String isDefault;
	/**
	 * ˵��
	 */
	private String note;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Structure getRoot() {
		return root;
	}
	public void setRoot(Structure root) {
		this.root = root;
	}
	public String getInUse() {
		return inUse;
	}
	public void setInUse(String inUse) {
		this.inUse = inUse;
	}
	public String getIsApplyRule() {
		return isApplyRule;
	}
	public void setIsApplyRule(String isApplyRule) {
		this.isApplyRule = isApplyRule;
	}
	public String getIsMatrix() {
		return isMatrix;
	}
	public void setIsMatrix(String isMatrix) {
		this.isMatrix = isMatrix;
	}
	public String getIsKeepRecord() {
		return isKeepRecord;
	}
	public void setIsKeepRecord(String isKeepRecord) {
		this.isKeepRecord = isKeepRecord;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	

}
