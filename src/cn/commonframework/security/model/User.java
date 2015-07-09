/**
 * @(#)User.java 2009-9-18 上午10:05:12
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import cn.commonframework.organization.model.Structure;


/**
 * @description:系统用户类，为使用Spring Security，故实现UserDetails接口。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-18 上午10:05:12 <br>
 */
public class User implements UserDetails {

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -6319452278898726118L;
	/**
	 * 内码
	 */
	private String id;
	/**
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 对应员工
	 */
	private Structure employee;
	/**
	 * 对应部门
	 */
	private Structure department;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 锁定时间
	 */
	private String lockTime;
	/**
	 * 是否系统管理员,1是,0不是
	 */
	private String isSys;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * MSN
	 */
	private String msn;
	/**
	 * QQ
	 */
	private String qq;
	/**
	 * 固定电话
	 */
	private String phone;
	/**
	 * 移动电话
	 */
	private String mobile;
	/**
	 * 状态,1在用,0停用
	 */
	private String isEnabled;
	/**
	 * 角色集合
	 */
	private Set<Role> roles = new HashSet<Role>();
	/**
	 * 角色资源集合
	 */
	private Map<String, List<Resource>> roleResources;
	
	/**
	 * The default constructor
	 */
	public User() {
		
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>(roles.size());
    	for(Role role : roles) {
    		grantedAuthorities.add(new GrantedAuthorityImpl(role.getName()));
    	}
        return grantedAuthorities.toArray(new GrantedAuthority[roles.size()]);
	}
	
	/**
	 * Returns the authorites string
	 * 
	 * eg. 
	 *    downpour --- ROLE_ADMIN,ROLE_USER
	 *    robbin --- ROLE_ADMIN
	 * 
	 * @return
	 */
	public String getAuthoritiesString() {
	   /* List<String> authorities = new ArrayList<String>();
	    for(GrantedAuthority authority : this.getAuthorities()) {
	        authorities.add(authority.getAuthority());
	    }*/
	    return StringUtils.join(this.getAuthorities(), ",");
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		/*if(this.getIsEnabled().equals("Y")){
			return true;
		}else{
			return false;
		}*/
		return this.getIsEnabled().equals("Y")?true:false;
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
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @return the roleResources
	 */
	public Map<String, List<Resource>> getRoleResources() {
		// init roleResources for the first time
		if(this.roleResources == null) {
			
			this.roleResources = new HashMap<String, List<Resource>>();
			
			for(Role role : this.roles) {
				String roleName = role.getName();
				Set<Resource> resources = role.getResources();
				for(Resource resource : resources) {
					String key = roleName + "_" + resource.getType();
					if(!this.roleResources.containsKey(key)) {
						this.roleResources.put(key, new ArrayList<Resource>());
					}
					this.roleResources.get(key).add(resource);					
				}
			}
			
		}
		return this.roleResources;
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
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return the employee
	 */
	public Structure getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Structure employee) {
		this.employee = employee;
	}

	/**
	 * @return the department
	 */
	public Structure getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Structure department) {
		this.department = department;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lockTime
	 */
	public String getLockTime() {
		return lockTime;
	}

	/**
	 * @param lockTime the lockTime to set
	 */
	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * @return the isSys
	 */
	public String getIsSys() {
		return isSys;
	}

	/**
	 * @param isSys the isSys to set
	 */
	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the msn
	 */
	public String getMsn() {
		return msn;
	}

	/**
	 * @param msn the msn to set
	 */
	public void setMsn(String msn) {
		this.msn = msn;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	
}