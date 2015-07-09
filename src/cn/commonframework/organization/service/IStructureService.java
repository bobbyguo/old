/**
 * @(#)IStructureService.java 2009-12-24 下午02:14:00
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.model.Structure;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织结构操作接口。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午02:14:00 <br>
 */
public interface IStructureService {

	public void saveStructure(Structure structure);
	
	public void updateStructure(Structure structure);
	
	public void deleteStructure(Structure structure);
	
	public int batchUpdate(String hql,Object...o);
	
	public Structure findById(String id);
	
	public List<Structure> getAll();
	
	public List<Structure> getAllByHql(String hql);
	
	public List<Structure> getAllByCriteria(Criterion... criterion);
	
	public List<Structure> getAllByExample(Structure structure,boolean enableLike,String... properties);
	
	public List<Structure> getAllByExample(Structure structure);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param structure 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(Structure structure,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(Structure structure,PageParams pageParams,Order...defaultOrders );
}
