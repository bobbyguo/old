/**
 * @(#)IBaseDAO.java 2009-9-14 下午03:05:59
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * @description:公用DAO接口，包含基本的增、删、改、查操作.
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-14 下午03:12:53 <br>
 * @param <T>
 */
public interface IBaseDAO<T> {
	/**
	 * 保存一个实体对象
	 * @param t
	 */
	public void save(T t);
	/**
	 * 更新一个实体对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 批量更新
	 * @param hql
	 * @param o
	 * @return
	 */
	public int batchUpdate(String hql,Object... o);
	/**
	 * 批量更新
	 * @param list
	 */
	public void batchUpdate(List<T> list);
	/**
	 * 删除一个实体对象
	 * @param t
	 */
	public void delete(T t);
	/**
	 * 批量删除
	 * @param list
	 */
	public void batchDelete(List<T> list);
	/**
	 * 根据主键查找实体对象
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	/**
	 * 查找所有实体对象
	 * @return
	 */
	public List<T> getAll();
	/**
	 * HQL查询
	 * @param hql
	 * @return
	 */
	public List<T> getAllByHql(String hql);
	/**
	 * QBC查询
	 * @param criterion
	 * @return
	 */
	public List<T> getAllByCriteria(Criterion... criterion);
	/**
	 * QBE查询
	 * @return
	 */
	public List<T> getAllByExample(T t,boolean enableLike,String... properties);
	/**
	 * 默认的QBE查询
	 * @param t
	 * @return
	 */
	public List<T> getAllByExample(T t);
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(T t,PageParams pageParams,Order...defaultOrders );
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表
	 */
	public Pagenation fuzzyQuery(T t,PageParams pageParams,Order...defaultOrders );
	/**
	 * 得到查找记录总条数 精确查找
	 * @param t
	 * @return 记录总数
	 */
	public int count(T t);
	/**
	 * 得到查找记录总条数 模糊查找
	 * @param t
	 * @return
	 */
	public int fuzzyCount(T t);
	
	/**
	 * 查找记录总条数，支持条件查询  模糊查询。
	 * 支持业务数据类型(即自定义类型)作为查询条件的情况。
	 * 注：不支持集合类型作为查询条件的情况。
	 * @param t
	 * @return
	 */
	public int fuzzyCountWithUserType(T t);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用  支持查询条件中含有自定义类型的情况。
	 * 不支持集合类型作为查询条件的情况，如遇到此情况请重载或新增对应方法。
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQueryWithUserType(T t,PageParams pageParams,Order...defaultOrders );
}
