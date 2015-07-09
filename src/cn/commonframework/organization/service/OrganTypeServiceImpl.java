/**
 * 
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.dao.IOrganTypeDao;
import cn.commonframework.organization.model.OrganType;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织类型service层操作类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 上午11:03:30 <br>
 *
 */
public class OrganTypeServiceImpl implements IOrganTypeService {

	private IOrganTypeDao organTypeDao;	

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#batchUpdate(java.lang.String, java.lang.Object[])
	 */
	public int batchUpdate(String hql, Object... o) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().batchUpdate(hql, o);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#batchUpdate(java.util.List)
	 */
	public void batchUpdate(List<OrganType> list) {
		// TODO Auto-generated method stub
		this.getOrganTypeDao().batchUpdate(list);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#deleteOrganType(cn.commonframework.organization.model.OrganType)
	 */
	public void deleteOrganType(OrganType organType) {
		// TODO Auto-generated method stub
		this.getOrganTypeDao().delete(organType);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#findById(java.lang.String)
	 */
	public OrganType findById(String id) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#fuzzyQuery(cn.commonframework.organization.model.OrganType, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation fuzzyQuery(OrganType organType, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().fuzzyQuery(organType, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#getAll()
	 */
	public List<OrganType> getAll() {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().getAll();
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#getAllByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<OrganType> getAllByCriteria(Criterion... criterion) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().getAllByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#getAllByExample(cn.commonframework.organization.model.OrganType, boolean, java.lang.String[])
	 */
	public List<OrganType> getAllByExample(OrganType organType,
			boolean enableLike, String... properties) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().getAllByExample(organType, enableLike, properties);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#getAllByExample(cn.commonframework.organization.model.OrganType)
	 */
	public List<OrganType> getAllByExample(OrganType organType) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().getAllByExample(organType);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#getAllByHql(java.lang.String)
	 */
	public List<OrganType> getAllByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().getAllByHql(hql);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#getAllWithPage(cn.commonframework.organization.model.OrganType, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation getAllWithPage(OrganType organType,
			PageParams pageParams, Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getOrganTypeDao().getAllWithPage(organType, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#saveOrganType(cn.commonframework.organization.model.OrganType)
	 */
	public void saveOrganType(OrganType organType) {
		// TODO Auto-generated method stub
		this.getOrganTypeDao().save(organType);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganTypeService#updateOrganType(cn.commonframework.organization.model.OrganType)
	 */
	public void updateOrganType(OrganType organType) {
		// TODO Auto-generated method stub
		this.getOrganTypeDao().update(organType);
	}

	/**
	 * @return the organTypeDao
	 */
	public IOrganTypeDao getOrganTypeDao() {
		return organTypeDao;
	}

	/**
	 * @param organTypeDao the organTypeDao to set
	 */
	public void setOrganTypeDao(IOrganTypeDao organTypeDao) {
		this.organTypeDao = organTypeDao;
	}
}
