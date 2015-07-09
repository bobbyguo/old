/**
 * 
 */
package cn.commonframework.security.action;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.ServletActionContext;

import cn.commonframework.organization.model.StruType;
import cn.commonframework.organization.model.Structure;
import cn.commonframework.organization.service.IStruTypeService;
import cn.commonframework.organization.service.IStructureService;
import cn.commonframework.security.EnCoder;
import cn.commonframework.security.model.Role;
import cn.commonframework.security.model.User;
import cn.commonframework.security.service.IRoleService;
import cn.commonframework.security.service.IUserService;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

import com.javaeedev.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;

/** 
 * @author  :qiang<br>
 * @version :1.0<br>
 * @date    :2009-11-12 下午03:50:49<br>
 * 
 */
public class UserAction extends ActionSupport {
	
	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -3401723882159926841L;
	
	private User user = new User();
	private Structure structure = new Structure();
	private IUserService userService;
	private IRoleService roleService;
	private IStruTypeService struTypeService;
	private IStructureService structureService;
	private List<Role> leftRoleList;
	private List<Role> rightRoleList;
	private String[] rightRoles = null;
	private StringBuffer jsonDataBuffer = new StringBuffer();
	
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String forAdd() throws Exception {
		structure = this.getStructureService().findById(this.getStructure().getId());
		user.setDepartment(structure);
		leftRoleList = new ArrayList<Role>(roleService.getAll());
		rightRoleList = new ArrayList<Role>();
		
		return "forAdd";
	}
	
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String forEdit() throws Exception {
		user = this.getUserService().findById(this.getUser().getId());
		leftRoleList = new ArrayList<Role>(this.getRoleService().getAll());
		rightRoleList = new ArrayList<Role>(user.getRoles());
		
		if(!rightRoleList.isEmpty()) {
			Iterator<Role> iter = rightRoleList.iterator();
			
			while(iter.hasNext()) {
				Role role = iter.next();
				
				for(int i = 0; i < leftRoleList.size(); i++) {
					if(leftRoleList.get(i).getId().equals(role.getId())) {
						leftRoleList.remove(i);
						break;
					}
				}
			}
		}
		
		return "forEdit";
	}
	
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if(this.getUser() != null) {
			if(rightRoles != null && rightRoles.length > 0) {
				rightRoleList = new ArrayList<Role>();
				
				for(int i = 0; i < rightRoles.length; i++) {
					rightRoleList.add(roleService.findById(rightRoles[i]));
				}
				
				user.setRoles(new HashSet<Role>(rightRoleList));
			}
			//MD5加密用户密码
			user.setPassword(EnCoder.MD5Encoding(this.getUser().getPassword()));
			if(this.getUser().getEmployee().getId() != null && this.getUser().getEmployee().getId().trim().length() > 0) {
				user.setEmployee(this.getStructureService().findById(this.getUser().getEmployee().getId()));
			}
			if(this.getUser().getDepartment().getId() != null && this.getUser().getDepartment().getId().trim().length() > 0) {
				user.setDepartment(this.getStructureService().findById(this.getUser().getDepartment().getId()));
			}
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			if("N".equals(this.getUser().getIsEnabled())) {
				user.setLockTime(new Timestamp(System.currentTimeMillis()).toString());
			}
			
			this.getUserService().saveUser(user);
		}
		
		return detail();
	}
	
	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		User user = this.getUserService().findById(this.getUser().getId());
		BeanUtils.copyProperties(user, this.getUser());
		
		if(rightRoles != null && rightRoles.length > 0) {
			rightRoleList = new ArrayList<Role>();
			
			for(int i = 0; i < rightRoles.length; i++) {
				rightRoleList.add(this.getRoleService().findById(rightRoles[i]));
			}
			
			user.getRoles().clear();				
			user.setRoles(new HashSet<Role>(rightRoleList));
		}else {
			user.getRoles().clear();
		}
		
		if(this.getUser().getEmployee().getId() != null && this.getUser().getEmployee().getId().trim().length() > 0) {
			user.setEmployee(this.getStructureService().findById(this.getUser().getEmployee().getId()));
		}
		if(this.getUser().getDepartment().getId() != null && this.getUser().getDepartment().getId().trim().length() > 0) {
			user.setDepartment(this.getStructureService().findById(this.getUser().getDepartment().getId()));
		}
		if("N".equals(this.getUser().getIsEnabled())) {
			user.setLockTime(new Timestamp(System.currentTimeMillis()).toString());
		}
		if(!this.getUser().getPassword().equals(user.getPassword())){
			user.setPassword(EnCoder.MD5Encoding(this.getUser().getPassword()));
		}
		
		userService.updateUser(user);
		
		return detail();
	}
	
	/**
	 * 明细
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception {
		user = this.getUserService().findById(this.getUser().getId());
		leftRoleList = new ArrayList<Role>(this.getRoleService().getAll());
		rightRoleList = new ArrayList<Role>(user.getRoles());
		
		if(!rightRoleList.isEmpty()) {
			Iterator<Role> iter = rightRoleList.iterator();
			
			while(iter.hasNext()) {
				Role role = iter.next();
				
				for(int i = 0; i < leftRoleList.size(); i++) {
					if(leftRoleList.get(i).getId().equals(role.getId())) {
						leftRoleList.remove(i);
						break;
					}
				}
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {		
		this.getUserService().deleteUser(this.getUserService().findById(this.getUser().getId()));
		
		return null;
	}
	
	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		PageParams pageParams = new PageParams("row");
		Pagenation pagenation = userService.fuzzyQuery(user, pageParams);    //模糊查询		
		pagenation.initialize("list", "size");
		
		return "list";
	}
	
	/**
	 * 用户列表树
	 * @return
	 * @throws Exception
	 */
	public String makeUserListTree() throws Exception {
		String jsonData = "[";
		List<StruType> struTypeList = this.getStruTypeService().getAll();			
		jsonDataBuffer.append("{attributes:{id:")
			  		  .append(JsonUtil.toJson("userTreeRoot"))
			  		  .append("}, state:")
			  		  .append(JsonUtil.toJson("open"))
			  		  .append(", data:")
			  		  .append(JsonUtil.toJson("用户列表"));
			
		if(struTypeList.size() > 0) {
			jsonDataBuffer.append(",children:[");				
			for(int i = 0; i < struTypeList.size(); i++) {
				StruType struType = struTypeList.get(i);
				Structure structure = struType.getRoot();
				jsonDataBuffer.append("{attributes:{id:")
					  		  .append(JsonUtil.toJson(structure.getId()))
					  		  .append("}, data:")
					  		  .append(JsonUtil.toJson(structure.getOrgan().getName()));
				this.makeStructureJsonData(structure);
				jsonDataBuffer.append("}");					
				if(i < struTypeList.size() - 1) {
					jsonDataBuffer.append(",");
				}
			}				
			jsonDataBuffer.append("]");
		}
		
		jsonData += jsonDataBuffer.toString();
		jsonData += "}]";		
		this.renderJsonData(jsonData);
		
		return null;
	}
	
	/**
	 * 递归组装用户列表树json数据
	 * @param structure
	 * @throws Exception
	 */
	private void makeStructureJsonData(Structure structure) throws Exception {
		List<Structure> structureList = this.getStructureService().getAllByHql("from Structure where parent_organ_id='" + structure.getOrgan().getId() + "'");
		List<User> userList = this.getUserService().getAllByHql("from User where department_id='" +structure.getId() + "'");
		
		if(structureList.size() > 0 && structureList.contains(structure)) {
			structureList.remove(structure);
		}		
		if(structureList.size() > 0) {
			List<Structure> removeList = new ArrayList<Structure>();
			for(Structure item : structureList) {
				if("岗位".equals(item.getOrgan().getOrganType().getName())) {
					removeList.add(item);
				}
			}
			structureList.removeAll(removeList);
		}		
		if(structureList.size() > 0 || userList.size() > 0) {
			jsonDataBuffer.append(", state:")
						  .append(JsonUtil.toJson("close"))
						  .append(", children:[");
		}else {
			jsonDataBuffer.append(", state:")
			  			  .append(JsonUtil.toJson("open"));
		}		
		if(userList.size() > 0) {
			for(int j = 0; j < userList.size(); j++) {
				User item = userList.get(j);
				jsonDataBuffer.append("{attributes:{id:")
	  			  			  .append(JsonUtil.toJson(item.getId()))
	  			  			  .append("}, state:")
	  			  			  .append(JsonUtil.toJson("close"))
	  			  			  .append(", data:")
	  			  			  .append(JsonUtil.toJson(item.getName()))
	  			  			  .append("}");				
				if(j < userList.size() - 1) {
					jsonDataBuffer.append(",");
				}
			}
			if(structureList.size() == 0) {
				jsonDataBuffer.append("]");
			}
		}		
		if(structureList.size() > 0) {
			if(userList.size() > 0) {
				jsonDataBuffer.append(",");
			}			
			for(int i = 0; i < structureList.size(); i++) {
				Structure item = structureList.get(i);				
				jsonDataBuffer.append("{attributes:{id:")
	  			  			  .append(JsonUtil.toJson(item.getId()))
	  			  			  .append("}, data:")
	  			  			  .append(JsonUtil.toJson(item.getOrgan().getName()));				
				this.makeStructureJsonData(item);
				jsonDataBuffer.append("}");				
				if(i < structureList.size() - 1) {
					jsonDataBuffer.append(",");
				}
			}			
			jsonDataBuffer.append("]");
		}
	}
	
	/**
	 * 将json数据发送的页面，组装得到树,该树用于员工的选择
	 * @return
	 * @throws Exception
	 */
	public String selectEmployee() throws Exception {
		String jsonData = "[";		
		if(this.getStructure().getId() != null) {
			structure = this.getStructureService().findById(this.getStructure().getId());
			jsonDataBuffer.append("{attributes:{id:")
						  .append(JsonUtil.toJson(structure.getId()))
						  .append("},data:")
						  .append(JsonUtil.toJson(structure.getOrgan().getName()));
			this.makeSelectEmployeeJsonData(structure);
			jsonDataBuffer.append("}");
		}		
		jsonData += jsonDataBuffer.toString();		
		jsonData += "]";		
		this.renderJsonData(jsonData);
		
		return null;
	}
	
	/**
	 * 为员工选择树组装json数据
	 * @param structure
	 * @throws Exception
	 */
	private void makeSelectEmployeeJsonData(Structure structure) throws Exception {
		if("职工".equals(structure.getOrgan().getOrganType().getName())) {
			jsonDataBuffer.append(",state:\"close\"");
			return;
		}
		Structure parentStructure = new Structure();
		parentStructure.setParentOrgan(structure.getOrgan());
		List<Structure> structureList = this.getStructureService().getAllByExample(parentStructure);
		if(structureList.contains(structure)) {
			structureList.remove(structure);
		}		
		if(structureList.size() > 0) {
			jsonDataBuffer.append(",state:\"close\"")
			  			  .append(",children:[");
			for(int i = 0; i < structureList.size(); i++){
				Structure item = structureList.get(i);
				jsonDataBuffer.append("{attributes:{id:")
							  .append(JsonUtil.toJson(item.getId()))
							  .append("},data:")
							  .append(JsonUtil.toJson(item.getOrgan().getName()));
				this.makeSelectEmployeeJsonData(item);
				jsonDataBuffer.append("}");				
				if(i < structureList.size() - 1) {
					jsonDataBuffer.append(",");
				}
			}
			jsonDataBuffer.append("]");
		}else {
			jsonDataBuffer.append(",state:\"open\"");
		}
	}
	
	/**
	 * 处理组装得到的json数据
	 * @param jsonData
	 * @throws Exception
	 */
	private void renderJsonData(String jsonData) throws Exception {
		ServletActionContext.getResponse().setContentType("application/json; charset=GBK");
		PrintWriter printWriter = ServletActionContext.getResponse().getWriter();
		
		printWriter.write(jsonData);
		printWriter.flush();
	}
	
	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the roleService
	 */
	public IRoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * @return the struTypeService
	 */
	public IStruTypeService getStruTypeService() {
		return struTypeService;
	}

	/**
	 * @param struTypeService the struTypeService to set
	 */
	public void setStruTypeService(IStruTypeService struTypeService) {
		this.struTypeService = struTypeService;
	}

	/**
	 * @return the structureService
	 */
	public IStructureService getStructureService() {
		return structureService;
	}

	/**
	 * @param structureService the structureService to set
	 */
	public void setStructureService(IStructureService structureService) {
		this.structureService = structureService;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * @return the structure
	 */
	public Structure getStructure() {
		return structure;
	}

	/**
	 * @param structure the structure to set
	 */
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	/**
	 * @return the leftRoleList
	 */
	public List<Role> getLeftRoleList() {
		return leftRoleList;
	}

	/**
	 * @return the rightRoleList
	 */
	public List<Role> getRightRoleList() {
		return rightRoleList;
	}
	
	/**
	 * @param rightRoles the rightRoles to set
	 */
	public void setRightRoles(String[] rightRoles) {
		this.rightRoles = rightRoles;
	}
}