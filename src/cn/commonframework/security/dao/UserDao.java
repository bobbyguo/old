/**
 * @(#)UserDao.java 2009-9-19 下午01:15:37
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.dao;


//import java.util.List;

//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Example;
//import org.hibernate.criterion.MatchMode;

import cn.commonframework.security.model.User;
import cn.commonframework.util.BaseDAO;

/**
 * @description: 用户DAO实现类，继承自BaseDAO，实现了IUserDao接口。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 下午01:15:37 <br>
 */
public class UserDao extends BaseDAO<User> implements IUserDao {

	public UserDao(){
		super(User.class);
	}

//	/* (non-Javadoc)
//	 * @see cn.commonframework.util.BaseDAO#getAllByExample(java.lang.Object)
//	 */
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<User> getAllByExample(User user) {
//		DetachedCriteria criteria =  DetachedCriteria.forClass(User.class);
//		
//		criteria.add(Example.create(user).ignoreCase().enableLike(MatchMode.ANYWHERE).excludeZeroes());
//		if(user.getDepartment() != null){
//			criteria.createCriteria("department").add(Example.create(user.getDepartment()));
//		}
//		
//		return this.getHibernateTemplate().findByCriteria(criteria);
//	}
}
