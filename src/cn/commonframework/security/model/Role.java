/**
 * @(#)Role.java 2009-9-18 上午10:05:12
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @description:系统角色类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-18 上午10:51:17 <br>
 */
public class Role implements Serializable{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 8703327489419792716L;

	private String id;
	
	private String name;
	
	private String description;
	
	private Set<Resource> resources = new HashSet<Resource>();
	
	private Set<User> users = new HashSet<User>();
	
	/**
	 * The default constructor
	 */
	public Role() {
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the resources
	 */
	public Set<Resource> getResources() {
		return resources;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


}
