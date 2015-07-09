/**
 * 
 */
package cn.commonframework.security.action;

import org.apache.commons.beanutils.BeanUtils;

import cn.commonframework.security.model.Resource;
import cn.commonframework.security.service.IResourceService;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 ����03:54:37
 *
 */
public class ResourceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Resource resource = new Resource();
	private IResourceService resourceService = null;
	private String[] deleteResourceId = null;
	
	/**
	 * �������Դ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String addResource() throws Exception {
		if(resource != null) {
			resourceService.saveResource(resource);
		}
		
		resource = new Resource();
		
		return SUCCESS;
	}
	
	/**
	 * �༭��Դ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String editResource() throws Exception {
		if(resourceNotNull()) {
			resource = resourceService.findById(resource.getId());
			
			return "edit";
		}else {
			return "add";
		}
	}
	
	/**
	 * ������Դ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String modifyResource() throws Exception {
		if(resourceNotNull()) {
		
			Resource resourceDB = resourceService.findById(this.resource.getId());
			BeanUtils.copyProperties(resourceDB, resource);
			resourceService.updateResource(resourceDB);
		}
		
		resource = new Resource();
		
		return SUCCESS;
	}
	
	/**
	 * ɾ����Դ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String deleteResource() throws Exception {
		//ɾ��������Ϣ
		if(deleteResourceId != null && deleteResourceId.length > 0) {
			for(int i = 0; i < deleteResourceId.length; i ++) {
				resourceService.deleteResource(resourceService.findById(deleteResourceId[i]));
			}			
		}
		
		//ɾ��һ����Ϣ
		if(resourceNotNull()) {
			resourceService.deleteResource(resourceService.findById(resource.getId()));
		}
		
		resource = new Resource();
		
		return SUCCESS;
	}
	
	/**
	 * ��ȡ������Դ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String getAllResource() throws Exception {
		if("".equals(resource.getName())) {
			resource.setName(null);
		}
		
		if("".equals(resource.getType())) {
			resource.setType(null);
		}
		
		if(resource.getId() != null) {
			resource = new Resource();
		}
		
		PageParams pageParams = new PageParams("row");
		//��ȷ��ѯ
//		Pagenation pagenation = resourceService.getAllWithPage(resource, pageParams);
		//ģ����ѯ
		Pagenation pagenation = resourceService.fuzzyQuery(resource, pageParams);
		
		pagenation.initialize("list", "size");
		
		return "getAll";
	}
	
	/**
	 * �ж���Դ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean resourceNotNull() {
		return (resource != null && resource.getId() != null && resource.getId().trim().length() > 0);
	}
	
	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public Resource getResource() {
		return resource;
	}
	
	public void setDeleteResourceId(String[] deleteResourceId) {
		this.deleteResourceId = deleteResourceId;
	}
}