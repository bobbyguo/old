/**
 * @(#)SecurityUserHolder.java 2009-9-19 ����05:28:09
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;

import cn.commonframework.security.model.User;

/**
 * @description:���ڻ�ȡ��ǰ��¼���û���
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 ����05:28:09 <br>
 */
public class SecurityUserHolder {
	
	/**
	 * Returns the current user
	 * 
	 * @return
	 */
	public static User getCurrentUser() {
		
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	/**
	 * Returns the current username
	 * @return
	 */
	public static String getUserName(){
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String username = null;
		if (obj instanceof UserDetails) {
		   username = ((UserDetails)obj).getUsername();
		} else {
		   username = obj.toString();
		}
		return username;
	}


}
