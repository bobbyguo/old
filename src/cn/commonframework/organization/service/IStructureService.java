/**
 * @(#)IStructureService.java 2009-12-24 ����02:14:00
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
 * ��֯�ṹ�����ӿڡ�
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 ����02:14:00 <br>
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
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param structure ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(Structure structure,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(Structure structure,PageParams pageParams,Order...defaultOrders );
}
