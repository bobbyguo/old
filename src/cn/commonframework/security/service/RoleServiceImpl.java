/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.dao.IRoleDao;
import cn.commonframework.security.model.Role;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 ÉÏÎç11:45:23
 *
 */
public class RoleServiceImpl implements IRoleService{

	private IRoleDao roleDao = null;
	
	public int batchUpdate(String hql, Object... o) {
		// TODO Auto-generated method stub
		return this.getRoleDao().batchUpdate(hql, o);
	}

	public void deleteRole(Role role) {
		// TODO Auto-generated method stub
		this.getRoleDao().delete(role);
	}

	public Role findById(String id) {
		// TODO Auto-generated method stub
		return this.getRoleDao().findById(id);
	}

	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return this.getRoleDao().getAll();
	}

	public List<Role> getAllByCriteria(Criterion... criterion) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getAllByCriteria(criterion);
	}

	public List<Role> getAllByExample(Role role, boolean enableLike,
			String... properties) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getAllByExample(role, enableLike, properties);
	}

	public List<Role> getAllByExample(Role role) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getAllByExample(role);
	}

	public List<Role> getAllByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getAllByHql(hql);
	}

	public void saveRole(Role role) {
		// TODO Auto-generated method stub
		this.getRoleDao().save(role);
	}

	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		this.getRoleDao().update(role);
	}

	public Pagenation getAllWithPage(Role role, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getRoleDao().getAllWithPage(role, pageParams, defaultOrders);
	}
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public Pagenation fuzzyQuery(Role role, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getRoleDao().fuzzyQuery(role, pageParams, defaultOrders);
	}
}
