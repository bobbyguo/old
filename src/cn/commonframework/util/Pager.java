/**
 * @(#)Pager.java 2009-11-20 上午11:05:24
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.util.List;
import java.util.Map;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.opensymphony.xwork2.ActionContext;

/**
 * 分页辅助工具类
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-11-20 上午11:05:24 <br>
 */
public class Pager {
	  private int pageSize;
	  private int start;
	  private int totalCount;
	  private String sortName;
	  private String order;
	  private List list;
	 

	public Pager(){
		  
	  }
	  
	  public Pager(List list,int totalCount,int pageSize ){
		  Map map = ActionContext.getContext().getParameters();
		  this.start = (Integer.parseInt((String) map.get(new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE)))-1)*pageSize;
		  this.pageSize = pageSize;
		  this.list = list;
		  this.totalCount = totalCount;
		  this.sortName = (String) map.get(new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_SORT));
		  this.order = (String) map.get(new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_ORDER));
	  }
	  public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	 public List getList() {
			return list;
		}

		public void setList(List list) {
			this.list = list;
		}
	String name = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
	  String sort =  new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_SORT);
	  String order2 = new ParamEncoder("row").encodeParameterName(TableTagParameters.PARAMETER_ORDER);
}
