/**
 * @(#)PagenationTag.java 2009-9-21 ����02:46:47
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @description: JSPͨ�÷�ҳ��ǩ��
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-21 ����02:46:47 <br>
 */
public class PagenationTag extends TagSupport{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 5346205964518851523L;
	/**
	 * ��ҳ��
	 */
	private int firstPage;
	/**
	 * ��ǰҳ��
	 */
	private int currentPage;
	/**
	 * βҳ��
	 */
	private int lastPage;
	/**
	 * ��һҳҳ��
	 */
	private int prePage;
	/**
	 * ��һҳҳ��
	 */
	private int nextPage;
	/**
	 * ת��ҳ��
	 */
	private int goPage;
	/**
	 * ��ҳ��
	 */
	private int totalPage;
	/**
	 * �ܼ�¼��
	 */
	private int totalRow;
	/**
	 * ҳ����ʾ��¼��
	 */
	private int pageSize;
	/**
	 * ��Ӧ��action
	 */
	private String action;
	/**
	 * ҳ���ҳ��������ʾ�ַ�����ͼƬ��ť
	 */
	private String style;
	
	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the firstPage
	 */
	public int getFirstPage() {
		return firstPage;
	}

	/**
	 * @param firstPage the firstPage to set
	 */
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the lastPage
	 */
	public int getLastPage() {
		return lastPage;
	}

	/**
	 * @param lastPage the lastPage to set
	 */
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	/**
	 * @return the prePage
	 */
	public int getPrePage() {
		return prePage;
	}

	/**
	 * @param prePage the prePage to set
	 */
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * @return the goPage
	 */
	public int getGoPage() {
		return goPage;
	}

	/**
	 * @param goPage the goPage to set
	 */
	public void setGoPage(int goPage) {
		this.goPage = goPage;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the totalRow
	 */
	public int getTotalRow() {
		return totalRow;
	}

	/**
	 * @param totalRow the totalRow to set
	 */
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		return super.doAfterBody();
	}

	@Override
	public int doEndTag() throws JspException {
		// ����ִ�к����jsp���ݡ�
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String curPage = this.pageContext.getRequest().getParameter("page");
		String firPage = this.pageContext.getRequest().getParameter("firstPage");
		StringBuffer footer = new StringBuffer();
		footer.append("<table><tr>");
		footer.append("<input type='hidden' name='currentPage'");
		//������ҳ
		if(null==firPage||"".equals(firPage)){
			this.setFirstPage(1);
		}else{
			this.setFirstPage(Integer.parseInt(firPage));
		}
		//���õ�ǰҳ
		if(null==curPage||"".equals(curPage)){
			this.setCurrentPage(1);
		}else{
			this.setCurrentPage(Integer.parseInt(curPage));
		}
		//������һҳ����ҳ
		if(this.getCurrentPage()>1){
			this.setPrePage(this.getCurrentPage()-1);
			if(!this.getStyle().equalsIgnoreCase("image")){
			footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=1").append("'\">").append("��ҳ").append("</a></td>");
			footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=").append(this.getPrePage()).append("'\">").append("��һҳ").append("</a></td>");
			}else{
				footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=1").append("'\">").append("<img alt='��ҳ' src='/images/*.jpg' class='pagenationImage'>").append("</a></td>");
				footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=").append(this.getPrePage()).append("'\">").append("<img alt='��һҳ' src='/images/*.jpg' class='pagenationImage'>").append("</a></td>");
				
			}
		}
		
		
		
		
		footer.append("</tr></table>");
		//pageContext.getOut().println(footer.toString());
		return SKIP_BODY;
	}
	
	

}
