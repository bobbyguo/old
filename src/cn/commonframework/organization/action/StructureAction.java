/**
 * 
 */
package cn.commonframework.organization.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.commonframework.organization.model.OrganType;
import cn.commonframework.organization.model.StruRule;
import cn.commonframework.organization.model.Structure;
import cn.commonframework.organization.service.IOrganService;
import cn.commonframework.organization.service.IOrganTypeService;
import cn.commonframework.organization.service.IStruRuleService;
import cn.commonframework.organization.service.IStruTypeService;
import cn.commonframework.organization.service.IStructureService;

import com.javaeedev.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-29 上午10:54:04 <br>
 *
 */
public class StructureAction extends ActionSupport implements ModelDriven<Structure> {

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 1691318157050836614L;
	
	private Structure structure = new Structure();
	private StruRule struRule = new StruRule();
	private IStructureService structureService;	
	private IStruRuleService struRuleService;
	private IOrganService organService;
	private IOrganTypeService organTypeService;
	private IStruTypeService struTypeService;
	
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String forAdd() throws Exception {
		Structure parentStructure = this.getStructureService().findById(this.getModel().getId());
		
		structure.setParentOrgan(parentStructure.getOrgan());
		structure.setStruLevel(parentStructure.getStruLevel() + 1);
		
		if("岗位".equals(parentStructure.getOrgan().getOrganType().getName())) {
			structure.setDepartment(parentStructure.getDepartment());
		}else {			
			structure.setDepartment(parentStructure.getOrgan());
		}
		
		return "forAdd";
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	public String forEdit() throws Exception {
		structure = this.getStructureService().findById(this.getModel().getId());
		
		return "forEdit";
	}
	
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if(this.getModel().getOrgan().getOrganType().getId() != null && this.getModel().getOrgan().getOrganType().getId().trim().length() > 0) {
			this.getStructure().getOrgan().setOrganType(this.getOrganTypeService().findById(this.getModel().getOrgan().getOrganType().getId()));
		}
		if(this.getModel().getStruType().getId() != null && this.getModel().getStruType().getId().trim().length() > 0) {
			this.getStructure().setStruType(this.getStruTypeService().findById(this.getModel().getStruType().getId()));
		}
		if(this.getModel().getParentOrgan().getId() != null && this.getModel().getParentOrgan().getId().trim().length() > 0) {
			this.getStructure().setParentOrgan(this.getOrganService().findById(this.getModel().getParentOrgan().getId()));
		}
		if(this.getModel().getDepartment().getId() != null && this.getModel().getDepartment().getId().trim().length() > 0) {
			this.getStructure().setDepartment(this.getOrganService().findById(this.getModel().getDepartment().getId()));
		}
		if(this.getModel().getIsLeaf() == null) {
			this.getStructure().setIsLeaf("0");
		}
		
		Structure parentStructure = this.getStructureService().findById(this.getModel().getId());
		parentStructure.setIsLeaf("0");
		this.getStructureService().updateStructure(parentStructure);		
		this.getStructureService().saveStructure(structure);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("[")
			  .append("{\"id\":")
			  .append(JsonUtil.toJson(structure.getId()))
			  .append("}")
			  .append("]");
		this.renderJsonData(buffer.toString());
		
		return null;
	}
	
	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		if(this.getModel().getOrgan().getOrganType().getId() != null && this.getModel().getOrgan().getOrganType().getId().trim().length() > 0) {
			this.getStructure().getOrgan().setOrganType(this.getOrganTypeService().findById(this.getModel().getOrgan().getOrganType().getId()));
		}
		if(this.getModel().getStruType().getId() != null && this.getModel().getStruType().getId().trim().length() > 0) {
			this.getStructure().setStruType(this.getStruTypeService().findById(this.getModel().getStruType().getId()));
		}
		if(this.getModel().getParentOrgan().getId() != null && this.getModel().getParentOrgan().getId().trim().length() > 0) {
			this.getStructure().setParentOrgan(this.getOrganService().findById(this.getModel().getParentOrgan().getId()));
		}
		if(this.getModel().getDepartment().getId() != null && this.getModel().getDepartment().getId().trim().length() > 0) {
			this.getStructure().setDepartment(this.getOrganService().findById(this.getModel().getDepartment().getId()));
		}
		if(this.getModel().getIsLeaf() == null) {
			this.getStructure().setIsLeaf("0");
		}
		
		this.getStructureService().updateStructure(structure);
		
		return SUCCESS;
	}
	
	/**
	 * 明细
	 * @return
	 */
	public String detail() throws Exception {
		structure = this.getStructureService().findById(this.getModel().getId());
		
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		this.getStructureService().deleteStructure(this.getStructureService().findById(this.getModel().getId()));
		
		return "delete";
	}
	
	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		return "list";
	}
	
	/**
	 * 获取组织类型
	 * @return
	 * @throws Exception
	 */
	public String getRule() throws Exception {
		structure = this.getStructureService().findById(this.getModel().getId());
		struRule.setOrganType(structure.getOrgan().getOrganType());
		
		List<StruRule> struRuleList = new ArrayList<StruRule>(this.getStruRuleService().getAllByExample(struRule));
		List<OrganType> organTypeList = null;
		StringBuffer buffer = new StringBuffer();		
		buffer.append("[");
		
		if(struRuleList.size() > 0) {
			organTypeList = new ArrayList<OrganType>(struRuleList.get(0).getTargetOrganType());
			
			for(int i = 0; i < organTypeList.size(); i++) {
				OrganType item = organTypeList.get(i);
				buffer.append("{\"id\":")
					  .append(JsonUtil.toJson(item.getId()))
					  .append(",\"name\":")
					  .append(JsonUtil.toJson(item.getName()))
					  .append("}");
				
				if(i < organTypeList.size() - 1) {
					buffer.append(",");
				}
			}
		}
		
		buffer.append("]");		
		this.renderJsonData(buffer.toString());
		
		return null;
	}
	
	/**
	 * 处理组装得到的json数据
	 * @param jsonData
	 * @throws Exception
	 */
	public void renderJsonData(String jsonData) throws Exception {
		ServletActionContext.getResponse().setContentType("application/json; charset=GBK");
		PrintWriter printWriter = ServletActionContext.getResponse().getWriter();
		
		printWriter.write(jsonData);
		printWriter.flush();
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
	 * @return the struRuleService
	 */
	public IStruRuleService getStruRuleService() {
		return struRuleService;
	}

	/**
	 * @param struRuleService the struRuleService to set
	 */
	public void setStruRuleService(IStruRuleService struRuleService) {
		this.struRuleService = struRuleService;
	}
	
	/**
	 * @return the organService
	 */
	public IOrganService getOrganService() {
		return organService;
	}

	/**
	 * @param organService the organService to set
	 */
	public void setOrganService(IOrganService organService) {
		this.organService = organService;
	}	
	
	/**
	 * @return the organTypeService
	 */
	public IOrganTypeService getOrganTypeService() {
		return organTypeService;
	}

	/**
	 * @param organTypeService the organTypeService to set
	 */
	public void setOrganTypeService(IOrganTypeService organTypeService) {
		this.organTypeService = organTypeService;
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
	 * @return the struRule
	 */
	public StruRule getStruRule() {
		return struRule;
	}

	/**
	 * @param struRule the struRule to set
	 */
	public void setStruRule(StruRule struRule) {
		this.struRule = struRule;
	}

	/**
	 * 实现模型驱动的接口
	 */
	public Structure getModel() {
		// TODO Auto-generated method stub
		return this.getStructure();
	}
}