/**
 * @(#)IUserService.java 2009-9-25 ����03:37:44
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
 * @date    :2009-9-25 ����03:37:44 <br>
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
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param user ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(User user,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(User user,PageParams pageParams,Order...defaultOrders );
}
