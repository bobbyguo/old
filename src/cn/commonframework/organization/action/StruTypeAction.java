/**
 * @(#)StruTypeAction.java 2009-12-24 下午02:37:42
 * Copyright 2009 Bobby_Guo, Inc. All rights reserved
 */
package cn.commonframework.organization.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.commonframework.organization.model.StruType;
import cn.commonframework.organization.model.Structure;
import cn.commonframework.organization.service.IStruTypeService;
import cn.commonframework.organization.service.IStructureService;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

import com.javaeedev.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 组织结构类型操作Action类。
 * @author  :Bobby_Guo <br>
 * @version :1.0    <br>
 * @date    :2009-12-24 下午02:37:42 <br>
 */
public class StruTypeAction extends ActionSupport implements ModelDriven<StruType> {

	/**
	 * serialVersionUID
	 * long
	 */
	private static final long serialVersionUID = -775557989416737899L;

	private IStruTypeService struTypeService;
	private StruType struType = new StruType();
	private String[] selectArray = null;
	private IStructureService structureService;
	private StringBuffer jsonDataBuffer = new StringBuffer();
	private List<Structure> rootList = new ArrayList<Structure>();
	/**
	 * 新增
	 * @return
	 */
	public String forAdd(){
		rootList = this.getStructureService().getAllByHql(" from Structure s where s.organ=s.parentOrgan");
		return "forAdd";
	}
	/**
	 * 修改
	 * @return
	 */
	public String forEdit(){
		rootList = this.getStructureService().getAllByHql(" from Structure s where s.organ=s.parentOrgan");
		if(this.getSelectArray() != null && this.getSelectArray().length > 0) {
			this.setStruType(this.getStruTypeService().findById(selectArray[0]));
		}
		if(struTypeNotNull()) {
			struType = this.getStruTypeService()
							.findById(struType.getId());
		}

		return "forEdit";
	}
	
	/**
	 * 保存
	 * @return
	 */
	public String save(){
		struType.setRoot(this.getStructureService().findById(getModel().getRoot().getId()));
		this.getStruTypeService().saveStruType(struType);
		return SUCCESS;
	}
	/**
	 * 修改
	 * @return
	 */
	public String edit(){
		
		this.getStruTypeService().updateStruType(getModel());
		return detail();
	}
	
	/**
	 * 明细
	 * @return
	 */
	public String detail(){
		if(this.getSelectArray() != null && this.getSelectArray().length > 0){
			struType = this.getStruTypeService().findById(selectArray[0]);
		}
		if(struTypeNotNull()) {
			struType = this.getStruTypeService()
							.findById(struType.getId());
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		List<StruType> deleteList = new ArrayList<StruType>();
		if(selectArray != null && selectArray.length > 0) {
			for(int i = 0; i < selectArray.length; i ++) {
				deleteList.add(this.getStruTypeService().findById(selectArray[i]));
			}			
		}		
		this.getStruTypeService().batchDeleteStruType(deleteList);
		return query();
	}
	
	/**
	 * 查询列表
	 * @return
	 */
	public String query(){		
		PageParams pageParams = new PageParams("row");
		Pagenation pagenation = this.getStruTypeService().fuzzyQuery(this.getModel(), pageParams);
		pagenation.initialize("list", "size");
		this.setStruType(new StruType());
		return "list";
	}
	
	/**
	 * 组织结构类型管理
	 * @return
	 * @throws Exception
	 */
	public String forManage() throws Exception {
		if(selectArray != null && selectArray.length > 0) {
			this.getModel().setId(selectArray[0]);
		}
		
		return "forManage";
	}
	
	/**
	 * 将json数据发送的页面，组装得到树
	 * @return
	 * @throws Exception
	 */
	public String manage() throws Exception {
		String jsonData = "[";
		if(struTypeNotNull()) {
			struType = this.getStruTypeService().findById(this.getModel().getId());
			Structure structure = struType.getRoot();
			jsonDataBuffer.append("{attributes:{id:")
						  .append(JsonUtil.toJson(structure.getId()))
						  .append("},data:")
						  .append(JsonUtil.toJson(structure.getOrgan().getName()));
			this.makeJsonData(structure);
			jsonDataBuffer.append("}");
		}
		jsonData += jsonDataBuffer.toString();		
		jsonData += "]";		
		this.renderJsonData(jsonData);		
		return null;
	}
	
	/**
	 * 组装json数据
	 * @return
	 * @throws Exception
	 */
	public String makeJsonData(Structure structure) {
		Structure parentStructure = new Structure();
		parentStructure.setParentOrgan(structure.getOrgan());
		List<Structure> list = this.getStructureService().getAllByExample(parentStructure);		
		if(list.contains(structure)) {
			list.remove(structure);
		}		
		if(list.size() > 0) {
			jsonDataBuffer.append(",state:\"close\",children:[");
			for(int i = 0; i < list.size(); i++) {
				Structure item = list.get(i);
				jsonDataBuffer.append("{attributes:{id:")
				  			  .append(JsonUtil.toJson(item.getId()))
				  			  .append("},data:")
				  			  .append(JsonUtil.toJson(item.getOrgan().getName()));
				this.makeJsonData(item);
				jsonDataBuffer.append("}");				
				if(i < list.size() - 1) {
					jsonDataBuffer.append(",");
				}
			}
			jsonDataBuffer.append("]");
		}else {
			jsonDataBuffer.append(",state:\"close\"");
		}
		return jsonDataBuffer.toString();
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
	 * 判断是否为空
	 * @return
	 */
	public boolean struTypeNotNull() {
		return this.getModel() != null && this.getModel().getId() != null && this.getModel().getId().trim().length() > 0;
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
	 * @return the struType
	 */
	public StruType getStruType() {
		return struType;
	}

	/**
	 * @param struType the struType to set
	 */
	public void setStruType(StruType struType) {
		this.struType = struType;
	}

	/**
	 * @return the selectArray
	 */
	public String[] getSelectArray() {
		return selectArray;
	}
	/**
	 * @param selectArray the selectArray to set
	 */
	public void setSelectArray(String[] selectArray) {
		this.selectArray = selectArray;
	}
	
	
	/**
	 * @return the rootList
	 */
	public List<Structure> getRootList() {
		return rootList;
	}
	/**
	 * @param rootList the rootList to set
	 */
	public void setRootList(List<Structure> rootList) {
		this.rootList = rootList;
	}
	public StruType getModel() {		
		return this.getStruType();
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
}
