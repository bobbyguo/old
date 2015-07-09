/**
 * @(#)StructureService.java 2009-12-24 下午02:26:59
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.dao.IStructureDao;
import cn.commonframework.organization.model.Structure;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织结构操作实现类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午02:26:59 <br>
 */
public class StructureService implements IStructureService {

	private IStructureDao structureDao;
	
	/**
	 * @return the structureDao
	 */
	public IStructureDao getStructureDao() {
		return structureDao;
	}

	/**
	 * @param structureDao the structureDao to set
	 */
	public void setStructureDao(IStructureDao structureDao) {
		this.structureDao = structureDao;
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#batchUpdate(java.lang.String, java.lang.Object[])
	 */
	public int batchUpdate(String hql, Object... o) {
		
		return this.getStructureDao().batchUpdate(hql, o);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#deleteStructure(cn.commonframework.organization.model.Structure)
	 */
	public void deleteStructure(Structure structure) {
		
		this.getStructureDao().delete(structure);

	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#findById(java.lang.String)
	 */
	public Structure findById(String id) {
		
		return this.getStructureDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#fuzzyQuery(cn.commonframework.organization.model.Structure, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation fuzzyQuery(Structure structure, PageParams pageParams,
			Order... defaultOrders) {
		
		return this.getStructureDao().fuzzyQuery(structure, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#getAll()
	 */
	public List<Structure> getAll() {
		
		return this.getStructureDao().getAll();
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#getAllByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<Structure> getAllByCriteria(Criterion... criterion) {
		
		return this.getStructureDao().getAllByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#getAllByExample(cn.commonframework.organization.model.Structure, boolean, java.lang.String[])
	 */
	public List<Structure> getAllByExample(Structure structure,
			boolean enableLike, String... properties) {
		
		return this.getStructureDao().getAllByExample(structure, enableLike, properties);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#getAllByExample(cn.commonframework.organization.model.Structure)
	 */
	public List<Structure> getAllByExample(Structure structure) {
		
		return this.getStructureDao().getAllByExample(structure);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#getAllByHql(java.lang.String)
	 */
	public List<Structure> getAllByHql(String hql) {
		
		return this.getStructureDao().getAllByHql(hql);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#getAllWithPage(cn.commonframework.organization.model.Structure, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation getAllWithPage(Structure structure,
			PageParams pageParams, Order... defaultOrders) {
		
		return this.getStructureDao().getAllWithPage(structure, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#saveStructure(cn.commonframework.organization.model.Structure)
	 */
	public void saveStructure(Structure structure) {
		
		this.getStructureDao().save(structure);

	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IStructureService#updateStructure(cn.commonframework.organization.model.Structure)
	 */
	public void updateStructure(Structure structure) {
		
		this.getStructureDao().update(structure);

	}

}
