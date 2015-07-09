/**
 * 
 */
package cn.commonframework.organization.action;

import java.util.ArrayList;
import java.util.List;

import cn.commonframework.organization.model.Organ;
import cn.commonframework.organization.model.OrganType;
import cn.commonframework.organization.service.IOrganService;
import cn.commonframework.organization.service.IOrganTypeService;
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
public class OrganAction extends ActionSupport implements ModelDriven<Organ>{

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = 1530388927105302668L;

	private Organ organ = new Organ();
	private IOrganService organService;
	private IOrganTypeService organTypeService;
	private List<OrganType> organTypeList = null;
	private String[] selectArray = null;
	
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
		
		if(organNotNull()) {
			organ = this.getOrganService()
							.findById(this.getModel().getId());
		}
		
		if(selectArray != null && selectArray.length > 0) {
			organ = this.getOrganService().findById(selectArray[0]);
		}
		
		return "forEdit";
	}
	
	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		if(organ != null) {
			if(organ.getOrganType().getId() != null && organ.getOrganType().getId().trim().length() > 0) {
				organ.setOrganType(
						this.getOrganTypeService().findById(organ.getOrganType().getId()));
			}
			
			this.getOrganService().saveOrgan(organ);
		}
		
		return SUCCESS;
	}

	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		if(organNotNull()) {
			if(organ.getOrganType().getId() != null && organ.getOrganType().getId().trim().length() > 0) {
				organ.setOrganType(
						this.getOrganTypeService().findById(organ.getOrganType().getId()));
			}
			
			this.getOrganService().updateOrgan(organ);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 明细
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception{
		if(organNotNull()) {
			organ = this.getOrganService()
							.findById(organ.getId());
		}
		
		if(selectArray != null && selectArray.length > 0) {
			organ = this.getOrganService().findById(selectArray[0]);
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
				this.getOrganService().deleteOrgan(
						this.getOrganService().findById(selectArray[i]));
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
		Pagenation pagenation = this.getOrganService().fuzzyQuery(this.getModel(), pageParams);  //模糊查询
		
		pagenation.initialize("list", "size");
		
		
		return "list";
	}
	
	/**
	 * 判断组织是否为空
	 * @return
	 */
	public boolean organNotNull() {
		return (organ != null && organ.getId() != null && organ.getId().trim().length() > 0);
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
	 * @param deleteOrganId the deleteOrganId to set
	 */
	public void setSelectArray(String[] selectArray) {
		this.selectArray = selectArray;
	}
	
	/**
	 * @return the organTypeList
	 */
	public List<OrganType> getOrganTypeList() {
		return organTypeList;
	}

	public Organ getModel() {
		// TODO Auto-generated method stub
		return organ;
	}
}
