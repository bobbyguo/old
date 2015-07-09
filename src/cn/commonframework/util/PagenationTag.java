/**
 * @(#)PagenationTag.java 2009-9-21 下午02:46:47
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @description: JSP通用分页标签。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-21 下午02:46:47 <br>
 */
public class PagenationTag extends TagSupport{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 5346205964518851523L;
	/**
	 * 首页码
	 */
	private int firstPage;
	/**
	 * 当前页码
	 */
	private int currentPage;
	/**
	 * 尾页码
	 */
	private int lastPage;
	/**
	 * 上一页页码
	 */
	private int prePage;
	/**
	 * 下一页页码
	 */
	private int nextPage;
	/**
	 * 转向页码
	 */
	private int goPage;
	/**
	 * 总页数
	 */
	private int totalPage;
	/**
	 * 总记录数
	 */
	private int totalRow;
	/**
	 * 页面显示记录数
	 */
	private int pageSize;
	/**
	 * 响应的action
	 */
	private String action;
	/**
	 * 页面分页的链接显示字符还是图片按钮
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
		// 继续执行后面的jsp内容。
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		String curPage = this.pageContext.getRequest().getParameter("page");
		String firPage = this.pageContext.getRequest().getParameter("firstPage");
		StringBuffer footer = new StringBuffer();
		footer.append("<table><tr>");
		footer.append("<input type='hidden' name='currentPage'");
		//设置首页
		if(null==firPage||"".equals(firPage)){
			this.setFirstPage(1);
		}else{
			this.setFirstPage(Integer.parseInt(firPage));
		}
		//设置当前页
		if(null==curPage||"".equals(curPage)){
			this.setCurrentPage(1);
		}else{
			this.setCurrentPage(Integer.parseInt(curPage));
		}
		//设置上一页和首页
		if(this.getCurrentPage()>1){
			this.setPrePage(this.getCurrentPage()-1);
			if(!this.getStyle().equalsIgnoreCase("image")){
			footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=1").append("'\">").append("首页").append("</a></td>");
			footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=").append(this.getPrePage()).append("'\">").append("上一页").append("</a></td>");
			}else{
				footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=1").append("'\">").append("<img alt='首页' src='/images/*.jpg' class='pagenationImage'>").append("</a></td>");
				footer.append("<td><a href=\"location.href='").append(this.getAction()).append("?page=").append(this.getPrePage()).append("'\">").append("<img alt='上一页' src='/images/*.jpg' class='pagenationImage'>").append("</a></td>");
				
			}
		}
		
		
		
		
		footer.append("</tr></table>");
		//pageContext.getOut().println(footer.toString());
		return SKIP_BODY;
	}
	
	

}
