/**
 * @(#)Pagenation.java 2009-11-20 下午04:25:03
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * 分页工具，与displaytag配合使用。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-11-20 下午04:25:03 <br>
 */
public class Pagenation {
	/**
	 * 要显示的数据列表
	 */
	private List<?> list;
	/**
	 * 总记录数
	 */
	private int totalCount;
	
	public Pagenation(List<?> list,int totalCount){
		this.list = list;
		this.totalCount = totalCount;
	}
	/**
	 * 初始化分页
	 * @param name  列表名称
	 * @param size  记录总数
	 */
	public void initialize(String name,String size){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(name, list);
		request.setAttribute(size, totalCount);
	}
}
