/**
 * 
 */
package cn.commonframework.organization.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import cn.commonframework.organization.model.OrganType;
import cn.commonframework.organization.model.StruRule;
import cn.commonframework.organization.model.StruType;
import cn.commonframework.organization.service.IOrganTypeService;
import cn.commonframework.organization.service.IStruRuleService;
import cn.commonframework.organization.service.IStruTypeService;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 组织操作action类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 下午03:57:59 <br>
 *
 */
public class StruRuleAction extends ActionSupport implements ModelDriven<StruRule>{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 1530388927105302668L;

	private StruRule struRule = new StruRule();
	private IStruRuleService struRuleService;
	private IOrganTypeService organTypeService;
	private IStruTypeService struTypeService;
	private List<OrganType> organTypeList;
	private List<StruType> struTypeList;
	private List<OrganType> leftOrganTypeList;
	private List<OrganType> rightOrganTypeList;
	private String[] selectArray = null;
	private String[] rightOrganTypes = null;
	
	/**
	 * 新增
	 * @return
	 * @throws Exception
	 */
	public String forAdd() throws Exception {
		List<OrganType> allOrganTypeList = new ArrayList<OrganType>(
												this.getOrganTypeService().getAll());
		
		organTypeList = new ArrayList<OrganType>(allOrganTypeList);
		struTypeList = new ArrayList<StruType>(this.getStruTypeService().getAll());
		leftOrganTypeList = new ArrayList<OrganType>(allOrganTypeList);
		rightOrganTypeList = new ArrayList<OrganType>();
		
		return "forAdd";
	}
	
	/**
	 * 修改
	 * @return
	 * @throws Exception
	 */
	public String forEdit() throws Exception {
		if(struRuleNotNull()) {
			struRule = this.getStruRuleService()
							.findById(this.getModel().getId());
		}
		
		if(selectArray != null && selectArray.length > 0) {
			struRule = this.getStruRuleService().findById(selectArray[0]);
		}
		
		List<OrganType> allOrganTypeList = new ArrayList<OrganType>(
												this.getOrganTypeService().getAll());
		organTypeList = new ArrayList<OrganType>(allOrganTypeList);
		struTypeList = new ArrayList<StruType>(this.getStruTypeService().getAll());
		leftOrganTypeList = new ArrayList<OrganType>(allOrganTypeList);
		rightOrganTypeList = new ArrayList<OrganType>(struRule.getTargetOrganType());
		
		if(!rightOrganTypeList.isEmpty()) {
			Iterator<OrganType> iter = rightOrganTypeList.iterator();
			
			while(iter.hasNext()) {
				OrganType organType = iter.next();
				
				for(OrganType item : leftOrganTypeList) {
					if(item.getId().equals(organType.getId())) {
						leftOrganTypeList.remove(item);
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
		if(this.getModel() != null) {
			if(this.getModel().getOrganType().getId() != null && this.getModel().getOrganType().getId().trim().length() > 0) {
				struRule.setOrganType(
						this.getOrganTypeService().findById(
								this.getModel().getOrganType().getId()));
			}
			
			if(this.getModel().getStruType().getId() != null && this.getModel().getStruType().getId().trim().length() > 0) {
				struRule.setStruType(
						this.getStruTypeService().findById(
								this.getModel().getStruType().getId()));
			}
			
			if(rightOrganTypes != null && rightOrganTypes.length > 0) {
				rightOrganTypeList = new ArrayList<OrganType>();
				
				for(int i = 0; i < rightOrganTypes.length; i++) {
					rightOrganTypeList.add(this.getOrganTypeService().findById(rightOrganTypes[i]));
				}
				
				struRule.setTargetOrganType(new HashSet<OrganType>(rightOrganTypeList));
			}
			
			this.getStruRuleService().saveStruRule(struRule);
		}
		
		return SUCCESS;
	}

	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		if(struRuleNotNull()) {
			if(this.getModel().getOrganType().getId() != null && this.getModel().getOrganType().getId().trim().length() > 0) {
				struRule.setOrganType(
						this.getOrganTypeService().findById(
								this.getModel().getOrganType().getId()));
			}
			
			if(this.getModel().getStruType().getId() != null && this.getModel().getStruType().getId().trim().length() > 0) {
				struRule.setStruType(
						this.getStruTypeService().findById(
								this.getModel().getStruType().getId()));
			}
			
			if(rightOrganTypes != null && rightOrganTypes.length > 0) {
				rightOrganTypeList = new ArrayList<OrganType>();
				
				for(int i = 0; i < rightOrganTypes.length; i++) {
					rightOrganTypeList.add(this.getOrganTypeService().findById(rightOrganTypes[i]));
				}
				
				struRule.getTargetOrganType().clear();
				struRule.setTargetOrganType(new HashSet<OrganType>(rightOrganTypeList));
			}else {
				struRule.getTargetOrganType().clear();
			}
			
			this.getStruRuleService().updateStruRule(struRule);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 明细
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception{
		if(struRuleNotNull()) {
			struRule = this.getStruRuleService()
							.findById(this.getModel().getId());
		}
		
		if(selectArray != null && selectArray.length > 0) {
			struRule = this.getStruRuleService().findById(selectArray[0]);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if(selectArray != null && selectArray.length > 0) {
			for(int i = 0; i < selectArray.length; i ++) {
				this.getStruRuleService().deleteStruRule(
						this.getStruRuleService().findById(selectArray[i]));
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
		struTypeList = new ArrayList<StruType>(this.getStruTypeService().getAll());
		organTypeList = new ArrayList<OrganType>(this.getOrganTypeService().getAll());
		PageParams pageParams = new PageParams("row");
		if(this.getModel().getStruType() != null && this.getModel().getStruType().getId() != null && this.getModel().getStruType().getId().trim().length() > 0) {
			struRule.setStruType(this.getStruTypeService().findById(this.getModel().getStruType().getId()));
		}
		if(this.getModel().getOrganType() != null && this.getModel().getOrganType().getId() != null && this.getModel().getOrganType().getId().trim().length() > 0) {
			struRule.setOrganType(this.getOrganTypeService().findById(this.getModel().getOrganType().getId()));
		}
		Pagenation pagenation = this.getStruRuleService().fuzzyQueryWithUserType(struRule, pageParams);  //模糊查询
		
		pagenation.initialize("list", "size");
		
		
		return "list";
	}
	
	/**
	 * 判断组织是否为空
	 * @return
	 */
	public boolean struRuleNotNull() {
		return (this.getModel() != null && this.getModel().getId() != null && this.getModel().getId().trim().length() > 0);
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
	 * @param deleteOrganId the deleteOrganId to set
	 */
	public void setSelectArray(String[] selectArray) {
		this.selectArray = selectArray;
	}

	public StruRule getModel() {
		// TODO Auto-generated method stub
		return struRule;
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
	 * @return the organTypeList
	 */
	public List<OrganType> getOrganTypeList() {
		return organTypeList;
	}

	/**
	 * @return the struTypeList
	 */
	public List<StruType> getStruTypeList() {
		return struTypeList;
	}

	/**
	 * @return the leftOrganTypeList
	 */
	public List<OrganType> getLeftOrganTypeList() {
		return leftOrganTypeList;
	}

	/**
	 * @param leftOrganTypeList the leftOrganTypeList to set
	 */
	public void setLeftOrganTypeList(List<OrganType> leftOrganTypeList) {
		this.leftOrganTypeList = leftOrganTypeList;
	}

	/**
	 * @return the rightOrganTypeList
	 */
	public List<OrganType> getRightOrganTypeList() {
		return rightOrganTypeList;
	}

	/**
	 * @param rightOrganTypeList the rightOrganTypeList to set
	 */
	public void setRightOrganTypeList(List<OrganType> rightOrganTypeList) {
		this.rightOrganTypeList = rightOrganTypeList;
	}

	/**
	 * @param deleteOrganId the deleteOrganId to set
	 */
	public void setRightOrganTypes(String[] rightOrganTypes) {
		this.rightOrganTypes = rightOrganTypes;
	}
}
