/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.dao.IUserDao;
import cn.commonframework.security.model.User;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-12 下午03:33:01
 */
public class UserServiceImpl implements IUserService{

	//需要通过spring注入的IUserDao
	private IUserDao userDao = null;
	
	public int batchUpdate(String hql, Object... o) {
		// TODO Auto-generated method stub
		return this.getUserDao().batchUpdate(hql, o);
	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		this.getUserDao().delete(user);
	}

	public User findById(String id) {
		// TODO Auto-generated method stub
		return this.getUserDao().findById(id);
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return this.getUserDao().getAll();
	}

	public List<User> getAllByCriteria(Criterion... criterion) {
		// TODO Auto-generated method stub
		return this.getUserDao().getAllByCriteria(criterion);
	}

	public List<User> getAllByExample(User user, boolean enableLike,
			String... properties) {
		// TODO Auto-generated method stub
		return this.getUserDao().getAllByExample(user, enableLike, properties);
	}

	public List<User> getAllByExample(User user) {
		// TODO Auto-generated method stub
		return this.getUserDao().getAllByExample(user);
	}

	public List<User> getAllByHql(String hql) {
		// TODO Auto-generated method stub
		return this.getUserDao().getAllByHql(hql);
	}

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		this.getUserDao().save(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.getUserDao().update(user);
	}
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public Pagenation getAllWithPage(User user, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getUserDao().getAllWithPage(user, pageParams, defaultOrders);
	}

	public Pagenation fuzzyQuery(User user, PageParams pageParams,
			Order... defaultOrders) {
		// TODO Auto-generated method stub
		return this.getUserDao().fuzzyQuery(user, pageParams, defaultOrders);
	}
}