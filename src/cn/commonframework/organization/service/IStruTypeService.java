/**
 * @(#)IStruTypeService.java 2009-12-24 下午02:07:54
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.model.StruType;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织结构类型操作接口。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午02:07:54 <br>
 */
public interface IStruTypeService {

	public void saveStruType(StruType struType);
		
	public void updateStruType(StruType struType);
	
	public void deleteStruType(StruType struType);
	
	public void batchDeleteStruType(List<StruType> list);
	
	public int batchUpdate(String hql,Object...o);
	
	public StruType findById(String id);
	
	public List<StruType> getAll();
	
	public List<StruType> getAllByHql(String hql);
	
	public List<StruType> getAllByCriteria(Criterion... criterion);
	
	public List<StruType> getAllByExample(StruType struType,boolean enableLike,String... properties);
	
	public List<StruType> getAllByExample(StruType struType);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param struType 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(StruType struType,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(StruType struType,PageParams pageParams,Order...defaultOrders );
}
