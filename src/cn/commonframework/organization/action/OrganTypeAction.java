/**
 * 
 */
package cn.commonframework.organization.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.commonframework.organization.model.Organ;
import cn.commonframework.organization.model.OrganType;
import cn.commonframework.organization.model.StruRule;
import cn.commonframework.organization.service.IOrganService;
import cn.commonframework.organization.service.IOrganTypeService;
import cn.commonframework.organization.service.IStruRuleService;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 组织类型操作action类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 上午11:08:08 <br>
 *
 */
public class OrganTypeAction extends ActionSupport {

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -5198877883188264095L;

	private OrganType organType = new OrganType();
	private IOrganTypeService organTypeService;
	private IOrganService organService;
	private IStruRuleService struRuleService;
	private String[] selectArray = null;
	private List<OrganType> organTypeList = null;
	private String organTypeTip = null;
	private String organTip = null;
	private String struRuleTip = null;

	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String forAdd() throws Exception {
		organTypeList = new ArrayList<OrganType>();
		organTypeList = this.getOrganTypeService().getAll();
		
		return "forAdd";
	}
	
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String forEdit() throws Exception {
		organTypeList = new ArrayList<OrganType>();
		organTypeList = this.getOrganTypeService().getAll();
		
		if(organTypeNotNull()) {
			organType = this.getOrganTypeService()
							.findById(organType.getId());
		}
		
		if(selectArray != null && selectArray.length > 0) {
			organType = this.getOrganTypeService().findById(selectArray[0]);
		}
		
		return "forEdit";
	}
	
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if(organType != null) {
			if(organType.getParentType().getId() != null && organType.getParentType().getId().trim().length() > 0) {
				organType.setParentType(
						this.getOrganTypeService().findById(organType.getParentType().getId()));
			}
			
			this.getOrganTypeService().saveOrganType(organType);
		}
		
		return SUCCESS;
	}

	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		if(organTypeNotNull()) {
			if(organType.getParentType().getId() != null && organType.getParentType().getId().trim().length() > 0) {
				organType.setParentType(
						this.getOrganTypeService().findById(organType.getParentType().getId()));
			}
			
			this.getOrganTypeService().updateOrganType(organType);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 明细
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception{
		if(organTypeNotNull()) {
			organType = this.getOrganTypeService()
							.findById(organType.getId());
		}
		
		if(selectArray != null && selectArray.length > 0) {
			organType = this.getOrganTypeService().findById(selectArray[0]);
		}
		
		return SUCCESS;
	}
	
	/**
	 * ajax验证删除组织类型是否还有下级组织类型
	 * @return
	 * @throws Exception
	 */
	public String deleteValidate() throws Exception {
		boolean isValid = false;
		
		if(selectArray != null && selectArray.length > 0) {
			for(int i = 0; i < selectArray.length; i ++) {
				if(this.getOrganTypeService().getAllByExample(
						this.getOrganTypeService().findById(selectArray[i])).size() > 0) {
					isValid = true;
				}
			}			
		}
		
		ServletActionContext.getResponse().getWriter().print(isValid);
		
		return NONE;
	}
	/**
	 * 删除前的验证逻辑
	 */
	/*	public void validateDelete(){
		if(selectArray != null && selectArray.length > 0) {
			StringBuffer organTypeBuffer = new StringBuffer();
			StringBuffer organBuffer = new StringBuffer();
			StringBuffer struRuleBuffer = new StringBuffer();
			OrganType deleteOrganType = null;
			OrganType deleteChildren = null;
			Organ organ = null;
			StruRule struRule = null;
			
			for(int i = 0; i < selectArray.length; i ++) {
				deleteChildren = new OrganType();
				organ = new Organ();
				struRule = new StruRule();
				deleteOrganType = this.getOrganTypeService().findById(selectArray[i]);
				
				deleteChildren.setParentType(deleteOrganType);
				organ.setOrganType(deleteOrganType);
				struRule.setOrganType(deleteOrganType);
				
				if(this.getOrganTypeService().getAllByExample(deleteChildren).size() > 0) {
					organTypeBuffer.append(deleteOrganType.getName() + " ");
					
				}
				
				if(this.getOrganService().getAllByExample(organ).size() > 0) {
					organBuffer.append(deleteOrganType.getName() + " ");
					
				}
				
				if(this.getStruRuleService().getAllByExample(struRule).size() > 0) {
					struRuleBuffer.append(deleteOrganType.getName() + " ");
					
				}
				
			}
			List<String> errorMessages = new ArrayList<String>();
			if(organTypeBuffer.toString().length() > 0) {
				
				errorMessages.add("以下组织类型不能删除:" + organTypeBuffer.toString() + ".如若删除请先删除或者断开与它们关联的下级组织类型!");
				
			}
			
			if(organBuffer.toString().length() > 0) {
				errorMessages.add("以下组织类型不能删除:" + organBuffer.toString() + ".如若要删除请先删除或者断开与它们关联的组织!");
			}
			
			if(struRuleBuffer.toString().length() > 0) {
				errorMessages.add("以下组织类型不能删除:" + struRuleBuffer.toString() + ".如若要删除请先删除或者断开与它们关联的组织结构规则!");
			}
			this.setActionErrors(errorMessages);
		}
	}*/
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if(selectArray != null && selectArray.length > 0) {
			StringBuffer organTypeBuffer = new StringBuffer();
			StringBuffer organBuffer = new StringBuffer();
			StringBuffer struRuleBuffer = new StringBuffer();
			OrganType deleteOrganType = null;
			OrganType deleteChildren = null;
			Organ organ = null;
			StruRule struRule = null;
			
			for(int i = 0; i < selectArray.length; i ++) {
				deleteChildren = new OrganType();
				organ = new Organ();
				struRule = new StruRule();
				deleteOrganType = this.getOrganTypeService().findById(selectArray[i]);
				
				deleteChildren.setParentType(deleteOrganType);
				organ.setOrganType(deleteOrganType);
				struRule.setOrganType(deleteOrganType);
				
				if(this.getOrganTypeService().getAllByExample(deleteChildren).size() > 0) {
					organTypeBuffer.append(deleteOrganType.getName() + " ");
					continue;
				}
				
				if(this.getOrganService().getAllByExample(organ).size() > 0) {
					organBuffer.append(deleteOrganType.getName() + " ");
					continue;
				}
				
				if(this.getStruRuleService().getAllByExample(struRule).size() > 0) {
					struRuleBuffer.append(deleteOrganType.getName() + " ");
					continue;
				}
				
				this.getOrganTypeService().deleteOrganType(deleteOrganType);
			}
			
			if(organTypeBuffer.toString().length() > 0) {
				this.setOrganTypeTip("以下组织类型不能删除:" + organTypeBuffer.toString() + ".如若删除请先删除或者断开与它们关联的下级组织类型!");
				/*List<String> errorMessages = new ArrayList<String>();
				errorMessages.add("以下组织类型不能删除:" + organTypeBuffer.toString() + ".如若删除请先删除或者断开与它们关联的下级组织类型!");
				this.setActionErrors(errorMessages);*/
			}
			
			if(organBuffer.toString().length() > 0) {
				this.setOrganTip("以下组织类型不能删除:" + organBuffer.toString() + ".如若要删除请先删除或者断开与它们关联的组织!");
			}
			
			if(struRuleBuffer.toString().length() > 0) {
				this.setStruRuleTip("以下组织类型不能删除:" + struRuleBuffer.toString() + ".如若要删除请先删除或者断开与它们关联的组织结构规则!");
			}
		}
		
		return query();
	}
	
	/**
	 * 查询列表
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception{
		
		PageParams pageParams = new PageParams("row");
		Pagenation pagenation = this.getOrganTypeService().fuzzyQuery(organType, pageParams);  //模糊查询
		pagenation.initialize("list", "size");
		
		
		return "list";
	}
	
	/**
	 * 判断组织类型是否为空
	 * @return
	 */
	public boolean organTypeNotNull() {
		return (organType != null && organType.getId() != null && organType.getId().trim().length() > 0);
	}

	/**
	 * @return the organType
	 */
	public OrganType getOrganType() {
		return organType;
	}

	/**
	 * @param organType the organType to set
	 */
	public void setOrganType(OrganType organType) {
		this.organType = organType;
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
	 * @return the organTypeList
	 */
	public List<OrganType> getOrganTypeList() {
		return organTypeList;
	}
	
	/**
	 * @param selectArray the selectArray to set
	 */
	public void setSelectArray(String[] selectArray) {
		this.selectArray = selectArray;
	}
	
	/**
	 * @return the organTypeTip
	 */
	public String getOrganTypeTip() {
		return organTypeTip;
	}

	/**
	 * @param tip the tip to set
	 */
	public void setOrganTypeTip(String organTypeTip) {
		this.organTypeTip = organTypeTip;
	}
	
	/**
	 * @return the organTip
	 */
	public String getOrganTip() {
		return organTip;
	}

	/**
	 * @param organTip the organTip to set
	 */
	public void setOrganTip(String organTip) {
		this.organTip = organTip;
	}
	
	/**
	 * @return the struRuleTip
	 */
	public String getStruRuleTip() {
		return struRuleTip;
	}

	/**
	 * @param struRuleTip the struRuleTip to set
	 */
	public void setStruRuleTip(String struRuleTip) {
		this.struRuleTip = struRuleTip;
	}
}