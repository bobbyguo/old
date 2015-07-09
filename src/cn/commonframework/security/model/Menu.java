/**
 * @(#)Menu.java 2009-12-14 ����02:40:15
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ��ܲ˵���  ���ڲ˵���չ��
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-14 ����02:40:15 <br>
 */
public class Menu implements Serializable{
	
	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -6314542644170695440L;
	
	/**
     * ����
     */
    private String id;
    /**
     * ����
     */
    private String name;
    /**
     * ���ӵ�ַ
     */
    private String url;
    /**
    * ��Ӧframe
    */
    private String target;
	/**
     * �ϼ��˵� һ�Զ�
     */
    private Menu parentMenu;
    /**
     * ����ʹ��
     */
    private Integer seq;
	/**
     * Ϊ��ʹ�÷��㣬����Ӳ˵�����
     */
    private Set<Menu> childMenus = new HashSet<Menu>();
     
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	/**
	 * @return the parentMenu
	 */
	public Menu getParentMenu() {
		return parentMenu;
	}
	
	/**
	 * @param parentMenu the parentMenu to set
	 */
	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}
	
	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	/**
	 * @return the childMenus
	 */
	public Set<Menu> getChildMenus() {
		return childMenus;
	}
	
	/**
	 * @param childMenus the childMenus to set
	 */
	public void setChildMenus(Set<Menu> childMenus) {
		this.childMenus = childMenus;
	}	
}
