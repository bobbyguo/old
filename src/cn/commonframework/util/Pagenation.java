/**
 * @(#)Pagenation.java 2009-11-20 ����04:25:03
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * ��ҳ���ߣ���displaytag���ʹ�á�
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-11-20 ����04:25:03 <br>
 */
public class Pagenation {
	/**
	 * Ҫ��ʾ�������б�
	 */
	private List<?> list;
	/**
	 * �ܼ�¼��
	 */
	private int totalCount;
	
	public Pagenation(List<?> list,int totalCount){
		this.list = list;
		this.totalCount = totalCount;
	}
	/**
	 * ��ʼ����ҳ
	 * @param name  �б�����
	 * @param size  ��¼����
	 */
	public void initialize(String name,String size){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(name, list);
		request.setAttribute(size, totalCount);
	}
}
