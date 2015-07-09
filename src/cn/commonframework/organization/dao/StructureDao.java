/**
 * @(#)StructureDao.java 2009-12-24 下午02:03:35
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import cn.commonframework.organization.model.Structure;
import cn.commonframework.util.BaseDAO;

/**
 * 组织结构DAO层操作类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午02:03:35 <br>
 */
public class StructureDao extends BaseDAO<Structure> implements IStructureDao{

	public StructureDao(){
		super(Structure.class);
	}
	
	/* (non-Javadoc)
	 * @see cn.commonframework.util.BaseDAO#getAllByExample(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Structure> getAllByExample(Structure structure) {
		DetachedCriteria criteria =  DetachedCriteria.forClass(Structure.class);
		criteria.add(Example.create(structure).ignoreCase().enableLike(MatchMode.ANYWHERE).excludeZeroes());
		if(structure.getParentOrgan()!=null){
			criteria.createCriteria("parentOrgan").add(Example.create(structure.getParentOrgan()));
		}
		return this.getHibernateTemplate().findByCriteria(criteria);
	}
}
