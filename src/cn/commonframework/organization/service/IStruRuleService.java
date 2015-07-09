/**
 * @(#)IStruRuleService.java 2009-12-25 下午02:31:45
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.model.StruRule;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织结构规则Service层操作接口。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-25 下午02:31:45 <br>
 */
public interface IStruRuleService {

	public void saveStruRule(StruRule struRule);
	
	public void updateStruRule(StruRule struRule);
	
	public void deleteStruRule(StruRule struRule);
	
	public int batchUpdate(String hql,Object...o);
	
	public StruRule findById(String id);
	
	public List<StruRule> getAll();
	
	public List<StruRule> getAllByHql(String hql);
	
	public List<StruRule> getAllByCriteria(Criterion... criterion);
	
	public List<StruRule> getAllByExample(StruRule struRule,boolean enableLike,String... properties);
	
	public List<StruRule> getAllByExample(StruRule struRule);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param struRule 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(StruRule struRule,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(StruRule struRule,PageParams pageParams,Order...defaultOrders );
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用  支持查询条件中含有自定义类型的情况。
	 * 不支持集合类型作为查询条件的情况，如遇到此情况请重载或新增对应方法。
	 * @param struRule 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQueryWithUserType(StruRule struRule,PageParams pageParams,Order...defaultOrders );
}
