/**
 * @(#)StruTypeService.java 2009-12-24 下午02:17:25
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.dao.IStruTypeDao;
import cn.commonframework.organization.model.StruType;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织结构类型操作实现类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午02:17:25 <br>
 */
public class StruTypeService implements IStruTypeService {
	
	private IStruTypeDao struTypeDao;

	public IStruTypeDao getStruTypeDao() {
		return struTypeDao;
	}

	public void setStruTypeDao(IStruTypeDao struTypeDao) {
		this.struTypeDao = struTypeDao;
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#batchUpdate(java.lang.String, java.lang.Object[])
	 */
	public int batchUpdate(String hql, Object... o) {
		
		return this.getStruTypeDao().batchUpdate(hql, o);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#deleteStruType(cn.commonframework.organization.model.StruType)
	 */
	public void deleteStruType(StruType struType) {

		this.getStruTypeDao().delete(struType);
	}

	public void batchDeleteStruType(List<StruType> list){
		this.getStruTypeDao().batchDelete(list);
	}
	
	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#findById(java.lang.String)
	 */
	public StruType findById(String id) {
		
		return this.getStruTypeDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#fuzzyQuery(cn.commonframework.organization.model.StruType, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation fuzzyQuery(StruType struType, PageParams pageParams,
			Order... defaultOrders) {
		
		return this.getStruTypeDao().fuzzyQuery(struType, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#getAll()
	 */
	public List<StruType> getAll() {
		
		return this.getStruTypeDao().getAll();
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#getAllByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<StruType> getAllByCriteria(Criterion... criterion) {
		
		return this.getStruTypeDao().getAllByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#getAllByExample(cn.commonframework.organization.model.StruType, boolean, java.lang.String[])
	 */
	public List<StruType> getAllByExample(StruType struType,
			boolean enableLike, String... properties) {
		
		return this.getStruTypeDao().getAllByExample(struType, enableLike, properties);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#getAllByExample(cn.commonframework.organization.model.StruType)
	 */
	public List<StruType> getAllByExample(StruType struType) {
		
		return this.getStruTypeDao().getAllByExample(struType);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#getAllByHql(java.lang.String)
	 */
	public List<StruType> getAllByHql(String hql) {
		
		return this.getStruTypeDao().getAllByHql(hql);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#getAllWithPage(cn.commonframework.organization.model.StruType, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation getAllWithPage(StruType struType, PageParams pageParams,
			Order... defaultOrders) {
		
		return this.getStruTypeDao().getAllWithPage(struType, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#saveStruType(cn.commonframework.organization.model.StruType)
	 */
	public void saveStruType(StruType struType) {
		
		this.getStruTypeDao().save(struType);

	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStruTypeService#updateStruType(cn.commonframework.organization.model.StruType)
	 */
	public void updateStruType(StruType struType) {
		
		this.getStruTypeDao().update(struType);

	}

}
