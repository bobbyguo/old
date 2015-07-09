/**
 * @(#)Page.java 2009-9-15 上午11:40:29
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.util.List;

/**
 * @description:分页组件
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-15 上午11:40:29 <br>
 */
public class Page {
	/**
	 * 总记录数
	 */
	private int totalRow;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 当前页码
	 */
	private int currentPage;
	/**
	 * 每页显示记录数
	 */
	private int pageSize;
	/**
	 * 数据记录列表
	 */
	private List<?> dataList;
	/**
	 * 页脚信息
	 */
	private String footer;
	
	public Page(){
		
	}
	public Page(int totalRow,int pageCount,int currentPage,int pageSize,List<?> dataList,String footer){
		this.totalRow = totalRow;
		this.pageCount = pageCount;
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.dataList = dataList;
		this.footer = footer;
	}
	
	public Page(int totalRow,int currentPage,List<?> dataList){
		this.totalRow  = totalRow;
		this.currentPage = currentPage;
		this.dataList = dataList;
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
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}
	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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
	 * @return the dataList
	 */
	public List<?> getDataList() {
		return dataList;
	}
	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	/**
	 * @return the footer
	 */
	public String getFooter() {
		return footer;
	}
	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
		this.footer = footer;
	}
	

}
