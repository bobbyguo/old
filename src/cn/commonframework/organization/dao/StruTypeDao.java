/**
 * @(#)StruTypeDao.java 2009-12-24 下午01:59:54
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.dao;

import cn.commonframework.organization.model.StruType;
import cn.commonframework.util.BaseDAO;

/**
 * 组织结构类型DAO层操作类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午01:59:54 <br>
 */
public class StruTypeDao extends BaseDAO<StruType> implements IStruTypeDao {

	public StruTypeDao(){
		super(StruType.class);
	}	
}
