/**
 * 
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.dao.IOrganDao;
import cn.commonframework.organization.model.Organ;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织service层实现类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 下午03:52:00 <br>
 *
 */
public class OrganServiceImpl implements IOrganService {

	private IOrganDao organDao;

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#batchUpdate(java.lang.String, java.lang.Object[])
	 */
	public int batchUpdate(String hql, Object... o) {
		// TODO Auto-generated method stub
		return this.getOrganDao().batchUpdate(hql, o);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#batchUpdate(java.util.List)
	 */
	public void batchUpdate(List<Organ> list) {
		// TODO Auto-generated method stub
		this.getOrganDao().batchDelete(list);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#deleteOrgan(cn.commonframework.organization.model.Organ)
	 */
	public void deleteOrgan(Organ organ) {
		// TODO Auto-generated method stub
		this.getOrganDao().delete(organ);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#findById(java.lang.String)
	 */
	public Organ findById(String id) {
		// TODO Auto-generated method stub
		return this.getOrganDao().findById(id);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#fuzzyQuery(cn.commonframework.organization.model.Organ, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation fuzzyQuery(Organ organ, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getOrganDao().fuzzyQuery(organ, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#getAll()
	 */
	public List<Organ> getAll() {
		// TODO Auto-generated method stub
		return this.getOrganDao().getAll();
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#getAllByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<Organ> getAllByCriteria(Criterion... criterion) {
		// TODO Auto-generated method stub
		return this.getOrganDao().getAllByCriteria(criterion);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#getAllByExample(cn.commonframework.organization.model.Organ, boolean, java.lang.String[])
	 */
	public List<Organ> getAllByExample(Organ organ, boolean enableLike,
			String... properties) {
		// TODO Auto-generated method stub
		return this.getAllByExample(organ, enableLike, properties);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#getAllByExample(cn.commonframework.organization.model.Organ)
	 */
	public List<Organ> getAllByExample(Organ organ) {
		// TODO Auto-generated method stub
		return this.getOrganDao().getAllByExample(organ);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#getAllByHql(java.lang.String)
	 */
	public List<Organ> getAllByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getAllByHql(hql);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#getAllWithPage(cn.commonframework.organization.model.Organ, cn.commonframework.util.PageParams, org.hibernate.criterion.Order[])
	 */
	public Pagenation getAllWithPage(Organ organ, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getOrganDao().getAllWithPage(organ, pageParams, defaultOrders);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#saveOrgan(cn.commonframework.organization.model.Organ)
	 */
	public void saveOrgan(Organ organ) {
		// TODO Auto-generated method stub
		this.getOrganDao().save(organ);
	}

	/* (non-Javadoc)
	 * @see cn.commonframework.organization.service.IOrganService#updateOrgan(cn.commonframework.organization.model.Organ)
	 */
	public void updateOrgan(Organ organ) {
		// TODO Auto-generated method stub
		this.getOrganDao().update(organ);
	}
	
	/**
	 * @return the organDao
	 */
	public IOrganDao getOrganDao() {
		return organDao;
	}

	/**
	 * @param organDao the organDao to set
	 */
	public void setOrganDao(IOrganDao organDao) {
		this.organDao = organDao;
	}
}
