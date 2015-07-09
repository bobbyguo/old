/**
 * @(#)ServletContextLoaderListener.java 2009-9-19 下午06:04:02 
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;
/**
 * 
 * @description:安全认证监听器，用于初始化安全认证。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 下午06:04:02 <br>
 */
public class ServletContextLoaderListener implements ServletContextListener {

    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        SecurityManager securityManager = this.getSecurityManager(servletContext);
        
        Map<String, String> urlAuthorities = securityManager.loadUrlAuthorities();
        servletContext.setAttribute("urlAuthorities", urlAuthorities);
    }

    
    /* (non-Javadoc)
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().removeAttribute("urlAuthorities");
    }

    /**
     * Get SecurityManager from ApplicationContext
     * 
     * @param servletContext
     * @return  Return an object of SecurityManager.
     */
    protected SecurityManager getSecurityManager(ServletContext servletContext) {
       return (SecurityManager) WebApplicationContextUtils.getWebApplicationContext(servletContext).getBean("securityManager"); 
    }

}
