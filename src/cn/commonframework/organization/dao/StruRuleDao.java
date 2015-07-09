/**
 * @(#)StruRuleDao.java 2009-12-25 下午02:28:51
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import cn.commonframework.organization.model.StruRule;
import cn.commonframework.util.BaseDAO;

/**
 * 组织结构规则DAO层操作实现类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-25 下午02:28:51 <br>
 */
public class StruRuleDao extends BaseDAO<StruRule> implements IStruRuleDao{

	public StruRuleDao(){
		super(StruRule.class);
	}
	
	/* (non-Javadoc)
	 * @see cn.commonframework.util.BaseDAO#getAllByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StruRule> getAllByExample(StruRule struRule) {
		DetachedCriteria criteria =  DetachedCriteria.forClass(StruRule.class);
		criteria.add(Example.create(struRule).ignoreCase().enableLike(MatchMode.ANYWHERE).excludeZeroes());
		if(struRule.getOrganType() != null){
			criteria.createCriteria("organType").add(Example.create(struRule.getOrganType()));
		}
		
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
}
