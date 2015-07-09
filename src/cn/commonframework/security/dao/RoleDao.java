/**
 * @(#)RoleDao.java 2009-9-19 ����01:21:24
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.dao;

import cn.commonframework.security.model.Role;
import cn.commonframework.util.BaseDAO;

/**
 * @description:��ɫDAO��ʵ���࣬�̳���BaseDAO��ʵ����IRoleDao�ӿڡ�
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 ����01:21:24 <br>
 */
public class RoleDao extends BaseDAO<Role> implements IRoleDao{

	public RoleDao(){
		super(Role.class);
	}
}
