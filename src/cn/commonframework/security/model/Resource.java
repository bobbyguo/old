/**
 * @(#)Resource.java 2009-9-18 上午10:05:12
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description:功能权限类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-18 上午10:58:33 <br>
 */
public class Resource implements Serializable{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -5260232981377640158L;

	private String id;
	
	private String name;
	
	private String type;
	
	private String value;
	
	private String description;

	private Set<Role> roles;
	
	/**
	 * The default constructor
	 */
	public Resource() {
		
	}
	
    /**
     * Get role authorities as string
     * 
     * @return
     */
    
	public String getRoleAuthorities() {
    	List<String> roleAuthorities = new ArrayList<String>();
    	for(Role role : roles) {
    		roleAuthorities.add(role.getName());
    	}
        return StringUtils.join(roleAuthorities, ",");
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
