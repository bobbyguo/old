/**
 * @(#)SecurityManager.java 2009-9-19 下午02:58:20
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import cn.commonframework.debug.Debug;
import cn.commonframework.security.dao.IResourceDao;
import cn.commonframework.security.dao.IUserDao;
import cn.commonframework.security.model.Resource;
import cn.commonframework.security.model.User;

/**
 * @description:安全管理类，根据用户名获得一个UserDetails对象，供安全机制使用。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 下午02:58:20 <br>
 */
public class SecurityManager implements UserDetailsService {

	private IUserDao userDao = null;
	private IResourceDao resourceDao = null;
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException, DataAccessException {
		User user = new User();
		user.setName(name);
		List<User> list = this.getUserDao().getAllByExample(user);
		if(list.isEmpty()){
			throw new UsernameNotFoundException("User: " + name + " has no GrantedAuthority");
		}
		return list.get(0);
	}
	/**
	 * 获得所有资源类型为URL的资源，并把该资源值与该资源对应的角色组成键值对放到map中。
	 * @return 返回 资源-角色 键值对。
	 */
	public Map<String, String> loadUrlAuthorities() {
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		Resource res = new Resource();
		res.setType("URL");
        List<Resource> urlResources = this.getResourceDao().getAllByExample(res);
        for(Resource resource : urlResources) {
        	Debug.println("------resource.getValue():"+"/"+resource.getValue());
            urlAuthorities.put(resource.getValue(), resource.getRoleAuthorities());
        }
        return urlAuthorities;
	}
	
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(IResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	

}
