/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.dao.IResourceDao;
import cn.commonframework.security.model.Resource;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 ÏÂÎç03:54:10
 *
 */
public class ResourceServiceImpl implements IResourceService {

	private IResourceDao resourceDao = null;
	
	public int batchUpdate(String hql, Object... o) {
		// TODO Auto-generated method stub
		return this.getResourceDao().batchUpdate(hql, o);
	}

	public void deleteResource(Resource resource) {
		// TODO Auto-generated method stub
		this.getResourceDao().delete(resource);
	}

	public Resource findById(String id) {
		// TODO Auto-generated method stub
		return this.getResourceDao().findById(id);
	}

	public List<Resource> getAll() {
		// TODO Auto-generated method stub
		return this.getResourceDao().getAll();
	}

	public List<Resource> getAllByCriteria(Criterion... criterion) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getAllByCriteria(criterion);
	}

	public List<Resource> getAllByExample(Resource resource,
			boolean enableLike, String... properties) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getAllByExample(resource, enableLike, properties);
	}

	public List<Resource> getAllByExample(Resource resource) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getAllByExample(resource);
	}

	public List<Resource> getAllByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getAllByHql(hql);
	}

	public void saveResource(Resource resource) {
		// TODO Auto-generated method stub
		this.getResourceDao().save(resource);
	}

	public void updateResource(Resource resource) {
		// TODO Auto-generated method stub
		this.getResourceDao().update(resource);
	}

	public Pagenation getAllWithPage(Resource resource, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getResourceDao().getAllWithPage(resource, pageParams, defaultOrders);
	}

	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	
	public IResourceDao getResourceDao() {
		return resourceDao;
	}

	public Pagenation fuzzyQuery(Resource resource, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getResourceDao().fuzzyQuery(resource, pageParams, defaultOrders);
	}
}
