/**
 * @(#)IBaseDAO.java 2009-9-14 ����03:05:59
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.util;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

/**
 * @description:����DAO�ӿڣ���������������ɾ���ġ������.
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-14 ����03:12:53 <br>
 * @param <T>
 */
public interface IBaseDAO<T> {
	/**
	 * ����һ��ʵ�����
	 * @param t
	 */
	public void save(T t);
	/**
	 * ����һ��ʵ�����
	 * @param t
	 */
	public void update(T t);
	/**
	 * ��������
	 * @param hql
	 * @param o
	 * @return
	 */
	public int batchUpdate(String hql,Object... o);
	/**
	 * ��������
	 * @param list
	 */
	public void batchUpdate(List<T> list);
	/**
	 * ɾ��һ��ʵ�����
	 * @param t
	 */
	public void delete(T t);
	/**
	 * ����ɾ��
	 * @param list
	 */
	public void batchDelete(List<T> list);
	/**
	 * ������������ʵ�����
	 * @param id
	 * @return
	 */
	public T findById(Serializable id);
	/**
	 * ��������ʵ�����
	 * @return
	 */
	public List<T> getAll();
	/**
	 * HQL��ѯ
	 * @param hql
	 * @return
	 */
	public List<T> getAllByHql(String hql);
	/**
	 * QBC��ѯ
	 * @param criterion
	 * @return
	 */
	public List<T> getAllByCriteria(Criterion... criterion);
	/**
	 * QBE��ѯ
	 * @return
	 */
	public List<T> getAllByExample(T t,boolean enableLike,String... properties);
	/**
	 * Ĭ�ϵ�QBE��ѯ
	 * @param t
	 * @return
	 */
	public List<T> getAllByExample(T t);
	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(T t,PageParams pageParams,Order...defaultOrders );
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(T t,PageParams pageParams,Order...defaultOrders );
	/**
	 * �õ����Ҽ�¼������ ��ȷ����
	 * @param t
	 * @return ��¼����
	 */
	public int count(T t);
	/**
	 * �õ����Ҽ�¼������ ģ������
	 * @param t
	 * @return
	 */
	public int fuzzyCount(T t);
	
	/**
	 * ���Ҽ�¼��������֧��������ѯ  ģ����ѯ��
	 * ֧��ҵ����������(���Զ�������)��Ϊ��ѯ�����������
	 * ע����֧�ּ���������Ϊ��ѯ�����������
	 * @param t
	 * @return
	 */
	public int fuzzyCountWithUserType(T t);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��  ֧�ֲ�ѯ�����к����Զ������͵������
	 * ��֧�ּ���������Ϊ��ѯ���������������������������ػ�������Ӧ������
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQueryWithUserType(T t,PageParams pageParams,Order...defaultOrders );
}
