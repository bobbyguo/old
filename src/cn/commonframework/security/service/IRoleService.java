/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.model.Role;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 上午11:42:10
 *
 */
public interface IRoleService {

	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
	
	public int batchUpdate(String hql,Object...o);
	
	public Role findById(String id);
	
	public List<Role> getAll();
	
	public List<Role> getAllByHql(String hql);
	
	public List<Role> getAllByCriteria(Criterion... criterion);
	
	public List<Role> getAllByExample(Role role,boolean enableLike,String... properties);
	
	public List<Role> getAllByExample(Role role);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param role 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(Role role,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param t 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(Role role,PageParams pageParams,Order...defaultOrders );
}
