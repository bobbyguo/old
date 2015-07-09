/**
 * @(#)SecureResourceFilterInvocationDefinitionSource.java 2009-9-19 下午06:00:17
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.ConfigAttributeEditor;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.util.AntUrlPathMatcher;
import org.springframework.security.util.RegexUrlPathMatcher;
import org.springframework.security.util.UrlMatcher;

import cn.commonframework.debug.Debug;
/**
 * 
 * @description:
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-9-19 下午06:00:17 <br>
 */
public class SecureResourceFilterInvocationDefinitionSource implements FilterInvocationDefinitionSource, InitializingBean {
    
    private UrlMatcher urlMatcher;

    private boolean useAntPath = true;
    
    private boolean lowercaseComparisons = false;
    
    /**
     * @param useAntPath the useAntPath to set
     */
    public void setUseAntPath(boolean useAntPath) {
        this.useAntPath = useAntPath;
    }
    
    /**
     * @param lowercaseComparisons
     */
    public void setLowercaseComparisons(boolean lowercaseComparisons) {
        this.lowercaseComparisons = lowercaseComparisons;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        
        // default url matcher will be RegexUrlPathMatcher
        this.urlMatcher = new RegexUrlPathMatcher();
        
        if (useAntPath) {  // change the implementation if required
            this.urlMatcher = new AntUrlPathMatcher();
        }
        
        // Only change from the defaults if the attribute has been set
        if (lowercaseComparisons) {
        	System.out.println("--------The SecureResourceFilterInvocationDefinitionSource initializing...");
            if (!this.useAntPath) {
                ((RegexUrlPathMatcher) this.urlMatcher).setRequiresLowerCaseUrl(true);
            }
        } else if (!lowercaseComparisons) {
        	System.out.println("--------The SecureResourceFilterInvocationDefinitionSource initializing...");
            if (this.useAntPath) {
                ((AntUrlPathMatcher) this.urlMatcher).setRequiresLowerCaseUrl(false);
            }
        }
        
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getAttributes(java.lang.Object)
     */
    public ConfigAttributeDefinition getAttributes(Object filter) throws IllegalArgumentException {
        
        FilterInvocation filterInvocation = (FilterInvocation) filter;
        String requestURI = filterInvocation.getRequestUrl();
        Map<String, String> urlAuthorities = this.getUrlAuthorities(filterInvocation);        
        String grantedAuthorities = null;
        
        //过滤requestURI中含有的参数
        if(requestURI.indexOf('?') != -1) {
        	requestURI = requestURI.substring(0, requestURI.indexOf('?'));
        }
        
        for(Iterator<Map.Entry<String, String>> iter = urlAuthorities.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> entry = iter.next();
            String url = entry.getKey();
            Debug.println("-------url:"+url);
            if(urlMatcher.pathMatchesUrl(url, requestURI)) {
                grantedAuthorities = entry.getValue();
                break;
            }
            
        }
        
        if(grantedAuthorities != null) {
            ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
            configAttrEditor.setAsText(grantedAuthorities);
            return (ConfigAttributeDefinition) configAttrEditor.getValue();
        }
        	System.out.println("--------------------You do not hava the permission to access the requestURI:"+requestURI);
        
        
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#getConfigAttributeDefinitions()
     */
    @SuppressWarnings("unchecked")
	public Collection getConfigAttributeDefinitions() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.intercept.ObjectDefinitionSource#supports(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {
        return true;
    }
    
    /**
     * 
     * @param filterInvocation
     * @return
     */
    @SuppressWarnings("unchecked")
	private Map<String, String> getUrlAuthorities(FilterInvocation filterInvocation) {
        ServletContext servletContext = filterInvocation.getHttpRequest().getSession().getServletContext();
        return (Map<String, String>)servletContext.getAttribute("urlAuthorities");
    }

}
