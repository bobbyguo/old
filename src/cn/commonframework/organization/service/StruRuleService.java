/**
 * @(#)StruRuleService.java 2009-12-25 下午02:35:00
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.dao.IStruRuleDao;
import cn.commonframework.organization.model.StruRule;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织结构规则Service层操作实现类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-25 下午02:35:00 <br>
 */
public class StruRuleService implements IStruRuleService {

	private IStruRuleDao struRuleDao;
	
	/**
	 * @return the struRuleDao
	 */
	public IStruRuleDao getStruRuleDao() {
		return struRuleDao;
	}

	/**
	 * @param struRuleDao the struRuleDao to set
	 */
	public void setStruRuleDao(IStruRuleDao struRuleDao) {
		this.struRuleDao = struRuleDao;
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#batchUpdate(java.lang.String, java.lang.Object[])
	 */
	public int batchUpdate(String hql, Object... o) {		
		return this.getStruRuleDao().batchUpdate(hql, o);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#deleteStruRule(cn.commonframework.organization.model.StruRule)
	 */
	public void deleteStruRule(StruRule struRule) {		
		this.getStruRuleDao().delete(struRule);

	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#findById(java.lang.String)
	 */
	public StruRule findById(String id) {		
		return this.getStruRuleDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#fuzzyQuery(cn.commonframework.organization.model.StruRule, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation fuzzyQuery(StruRule struRule, PageParams pageParams,
			Order... defaultOrders) {		
		return this.getStruRuleDao().fuzzyQuery(struRule, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#getAll()
	 */
	public List<StruRule> getAll() {		
		return this.getStruRuleDao().getAll();
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#getAllByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<StruRule> getAllByCriteria(Criterion... criterion) {		
		return this.getAllByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#getAllByExample(cn.commonframework.organization.model.StruRule, boolean, java.lang.String[])
	 */
	public List<StruRule> getAllByExample(StruRule struRule,
			boolean enableLike, String... properties) {		
		return this.getStruRuleDao().getAllByExample(struRule, enableLike, properties);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#getAllByExample(cn.commonframework.organization.model.StruRule)
	 */
	public List<StruRule> getAllByExample(StruRule struRule) {		
		return this.getStruRuleDao().getAllByExample(struRule);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#getAllByHql(java.lang.String)
	 */
	public List<StruRule> getAllByHql(String hql) {
		return this.getStruRuleDao().getAllByHql(hql);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#getAllWithPage(cn.commonframework.organization.model.StruRule, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation getAllWithPage(StruRule struRule, PageParams pageParams,
			Order... defaultOrders) {		
		return this.getStruRuleDao().getAllWithPage(struRule, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#saveStruRule(cn.commonframework.organization.model.StruRule)
	 */
	public void saveStruRule(StruRule struRule) {		
		this.getStruRuleDao().save(struRule);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#updateStruRule(cn.commonframework.organization.model.StruRule)
	 */
	public void updateStruRule(StruRule struRule) {		
		this.getStruRuleDao().update(struRule);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruRuleService#fuzzyQueryWithUserType(cn.commonframework.organization.model.StruRule)
	 */
	public Pagenation fuzzyQueryWithUserType(StruRule struRule,
			PageParams pageParams, Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getStruRuleDao().fuzzyQueryWithUserType(struRule, pageParams, defaultOrders);
	}
}
