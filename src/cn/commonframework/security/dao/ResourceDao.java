/**
 * @(#)ResourceDao.java 2009-9-19 ����01:22:56
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.dao;

import cn.commonframework.security.model.Resource;
import cn.commonframework.util.BaseDAO;

/**
 * @description: ��ԴDAO��ʵ���࣬�̳���BaseDAO��ʵ����IResourceDao�ӿڡ�
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 ����01:22:56 <br>
 */
public class ResourceDao extends BaseDAO<Resource> implements IResourceDao{

	public ResourceDao(){
		super(Resource.class);
	}
}
