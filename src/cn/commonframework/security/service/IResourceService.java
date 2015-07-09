/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.model.Resource;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 下午03:53:15
 *
 */
public interface IResourceService {

	public void saveResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	public void deleteResource(Resource resource);
	
	public int batchUpdate(String hql,Object...o);
	
	public Resource findById(String id);
	
	public List<Resource> getAll();
	
	public List<Resource> getAllByHql(String hql);
	
	public List<Resource> getAllByCriteria(Criterion... criterion);
	
	public List<Resource> getAllByExample(Resource resource,boolean enableLike,String... properties);
	
	public List<Resource> getAllByExample(Resource resource);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param resource 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(Resource resource,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(Resource resource,PageParams pageParams,Order...defaultOrders );
}
