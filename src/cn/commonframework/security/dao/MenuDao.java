/**
 * @(#)MenuDao.java 2009-12-14 下午05:00:32
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.dao;

import cn.commonframework.security.model.Menu;
import cn.commonframework.util.BaseDAO;

/**
 * 菜单DAO层操作类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-14 下午05:00:32 <br>
 */
public class MenuDao extends BaseDAO<Menu> implements IMenuDao {

	public MenuDao(){
		super(Menu.class);
	}

}
