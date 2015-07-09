/**
 * @(#)IUserService.java 2009-9-25 下午03:37:44
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.model.User;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-25 下午03:37:44 <br>
 */
public interface IUserService {

	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(User user);
	
	public int batchUpdate(String hql,Object...o);
	
	public User findById(String id);
	
	public List<User> getAll();
	
	public List<User> getAllByHql(String hql);
	
	public List<User> getAllByCriteria(Criterion... criterion);
	
	public List<User> getAllByExample(User user,boolean enableLike,String... properties);
	
	public List<User> getAllByExample(User user);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param user 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(User user,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(User user,PageParams pageParams,Order...defaultOrders );
}
