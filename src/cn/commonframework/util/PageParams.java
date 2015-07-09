/**
 * @(#)PageParams.java 2009-11-20 ����02:32:15
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 * ��ҳ����������
 * 
 * @author :Bobby_Guo <br>
 * @version :1.0 <br>
 * @date :2009-11-20 ����02:32:15 <br>
 */
public class PageParams {

	/**
	 * ҳ����<display:table/>��ǩ��id��ֵ
	 */
	private String tableId;
	private int pageSize;
	private HttpServletRequest request = ServletActionContext.getRequest();

	/**
	 * �޲ι��캯��
	 */
	public PageParams() {

	}

	/**
	 * ���캯����ʼ��tabelId
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
	 * �����ʼ��¼��
	 * 
	 * @return ��ʼ��¼��
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
	 * ��ȡ�����ֶ�
	 * 
	 * @return ���������ֶ�����
	 */
	public String getSortName() {
		if (tableId != null && !"".equals(tableId)) {
			return request.getParameter(new ParamEncoder(tableId)
					.encodeParameterName(TableTagParameters.PARAMETER_SORT));
		}
		return null;
	}

	/**
	 * ��ȡ����˳�������ǽ���
	 * 
	 * @return "1"Ϊ����"2"Ϊ����
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
