/**
 * @(#)IStruRuleService.java 2009-12-25 ����02:31:45
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
 * ��֯�ṹ����Service������ӿڡ�
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-25 ����02:31:45 <br>
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
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param struRule ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(StruRule struRule,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(StruRule struRule,PageParams pageParams,Order...defaultOrders );
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��  ֧�ֲ�ѯ�����к����Զ������͵������
	 * ��֧�ּ���������Ϊ��ѯ���������������������������ػ�������Ӧ������
	 * @param struRule ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQueryWithUserType(StruRule struRule,PageParams pageParams,Order...defaultOrders );
}
