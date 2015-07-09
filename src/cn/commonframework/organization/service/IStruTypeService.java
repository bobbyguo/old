/**
 * @(#)IStruTypeService.java 2009-12-24 ����02:07:54
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
 * ��֯�ṹ���Ͳ����ӿڡ�
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 ����02:07:54 <br>
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
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param struType ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(StruType struType,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(StruType struType,PageParams pageParams,Order...defaultOrders );
}
