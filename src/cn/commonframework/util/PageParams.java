/**
 * @(#)PageParams.java 2009-11-20 下午02:32:15
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 * 分页辅助参数类
 * 
 * @author :Bobby_Guo <br>
 * @version :1.0 <br>
 * @date :2009-11-20 下午02:32:15 <br>
 */
public class PageParams {

	/**
	 * 页面上<display:table/>标签中id的值
	 */
	private String tableId;
	private int pageSize;
	private HttpServletRequest request = ServletActionContext.getRequest();

	/**
	 * 无参构造函数
	 */
	public PageParams() {

	}

	/**
	 * 构造函数初始化tabelId
	 * 
	 * @param tableId
	 */
	public PageParams(String tableId) {
		this.tableId = tableId;
	}

	public int getPageSize() {
		return 10;
	}

	/**
	 * 获得起始记录号
	 * 
	 * @return 起始记录号
	 */
	public int getStart() {

		String pageNo = null;
		if (tableId != null && !"".equals(tableId)) {
			pageNo = request.getParameter(new ParamEncoder(tableId)
					.encodeParameterName(TableTagParameters.PARAMETER_PAGE));
		}
		int page = pageNo == null ? 1 : Integer.parseInt(pageNo);

		return (page - 1) * this.getPageSize();
	}

	/**
	 * 获取排序字段
	 * 
	 * @return 返回排序字段名称
	 */
	public String getSortName() {
		if (tableId != null && !"".equals(tableId)) {
			return request.getParameter(new ParamEncoder(tableId)
					.encodeParameterName(TableTagParameters.PARAMETER_SORT));
		}
		return null;
	}

	/**
	 * 获取排序顺序，升序还是降序
	 * 
	 * @return "1"为升序，"2"为降序
	 */
	public String getOrder() {
		if (tableId != null && !"".equals(tableId)) {
			System.out.println("!!!!!!!!!!!!!"+ request.getParameter(new ParamEncoder(tableId)
											           .encodeParameterName(TableTagParameters.PARAMETER_ORDER)));
			return request.getParameter(new ParamEncoder(tableId)
					.encodeParameterName(TableTagParameters.PARAMETER_ORDER));
		}
		return null;
	}
}
