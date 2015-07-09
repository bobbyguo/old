/**
 * 
 */
package cn.commonframework.security.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.commonframework.security.model.Resource;
import cn.commonframework.security.model.Role;
import cn.commonframework.security.service.IResourceService;
import cn.commonframework.security.service.IRoleService;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 上午11:53:35
 *
 */
public class RoleAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3009518867776194266L;
	
	private Role role = new Role();
	private IRoleService roleService = null;
	private IResourceService resourceService = null;
	private List<Resource> leftResourceList;
	private List<Resource> rightResourceList;
	private String[] rightResources = null;
	private String[] deleteRoleId = null;
	
	/**
	 * 添加新角色信息
	 * @return
	 * @throws Exception
	 */
	public String addRole() throws Exception {
		if(role != null) {
			if(rightResources != null && rightResources.length > 0) {
				rightResourceList = new ArrayList<Resource>();
				
				for(int i = 0; i < rightResources.length; i++) {
					rightResourceList.add(resourceService.findById(rightResources[i]));
				}
				
				role.setResources(new HashSet<Resource>(rightResourceList));
			}
		
			roleService.saveRole(role);
		}
		
		role = new Role();
		
		return SUCCESS;
	}
	
	/**
	 * 编辑角色信息
	 * @return
	 * @throws Exception
	 */
	public String editRole() throws Exception {		
		if(roleNotNull()) {
			role = roleService.findById(role.getId());
			leftResourceList = new ArrayList<Resource>(resourceService.getAll());
			rightResourceList = new ArrayList<Resource>(role.getResources());
			
			if(!rightResourceList.isEmpty()) {
				Iterator<Resource> iter = rightResourceList.iterator();
				
				while(iter.hasNext()) {
					Resource resource = iter.next();
					
					for(int i = 0; i < leftResourceList.size(); i++) {
						if(resource.getId().equals(leftResourceList.get(i).getId())) {
							leftResourceList.remove(i);
							break;
						}
					}
				}
			}
			
			return "edit";
		}else {
			leftResourceList = new ArrayList<Resource>(resourceService.getAll());
			rightResourceList = new ArrayList<Resource>();
			
			return "add";
		}
	}
	
	/**
	 * 更新角色信息
	 * @return
	 * @throws Exception
	 */
	public String modifyRole() throws Exception {
		if(roleNotNull()) {
			Role roleDB = this.roleService.findById(this.role.getId());
			BeanUtils.copyProperties(roleDB, role);
			//role.getUsers().addAll(this.roleService.findById(this.role.getId()).getUsers());
			if(rightResources != null && rightResources.length > 0) {
				rightResourceList = new ArrayList<Resource>();
				
				for(int i = 0; i < rightResources.length; i++) {
					rightResourceList.add(resourceService.findById(rightResources[i]));
				}
				roleDB.getResources().clear();
				roleDB.setResources(new HashSet<Resource>(rightResourceList));
			}
			
			roleService.updateRole(roleDB);
		}
		
		role = new Role();
		
		return SUCCESS;
	}
	
	/**
	 * 删除角色信息
	 * @return
	 * @throws Exception
	 */
	public String deleteRole() throws Exception {
		if(deleteRoleId != null && deleteRoleId.length > 0){
			for(int i = 0; i < deleteRoleId.length; i++) {
				roleService.deleteRole(roleService.findById(deleteRoleId[i]));
			}
		}
		
		if(roleNotNull()) {
			roleService.deleteRole(roleService.findById(role.getId()));
		}
		
		role = new Role();
		
		return SUCCESS;
	}
	
	/**
	 * 获取所有角色的信息
	 * @return
	 * @throws Exception
	 */
	public String getAllRole() throws Exception {
		if("".equals(role.getName())) {
			role.setName(null);
		}
		
		if(role.getId() != null) {
			role = new Role();
		}
		
		PageParams pageParams = new PageParams("row");
		//精确查询
//		Pagenation pagenation = roleService.getAllWithPage(role, pageParams);
		//模糊查询
		Pagenation pagenation = roleService.fuzzyQuery(role, pageParams);
		
		pagenation.initialize("list", "size");
		
		return "getAll";
	}
	
	/**
	 * 判断角色是否为空
	 * @return
	 */
	public boolean roleNotNull() {
		return (role != null && role.getId() != null && role.getId().trim().length() > 0);
	}
	
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	
	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	public List<Resource> getLeftResourceList() {
		return leftResourceList;
	}
	
	public List<Resource> getRightResourceList() {
		return rightResourceList;
	}
	
	public void setRightResources(String[] rightResources) {
		this.rightResources = rightResources;
	}
	
	public void setDeleteRoleId(String[] deleteRoleId) {
		this.deleteRoleId = deleteRoleId;
	}
}