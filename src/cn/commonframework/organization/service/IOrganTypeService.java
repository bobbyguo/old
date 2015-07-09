/**
 * 
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.model.OrganType;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织类型service层接口
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 上午10:59:17 <br>
 *
 */
public interface IOrganTypeService {

	/**
	 * 保存
	 * @param organType
	 */
	public void saveOrganType(OrganType organType);
	
	/**
	 * 更新
	 * @param organType
	 */
	public void updateOrganType(OrganType organType);
	
	/**
	 * 删除
	 * @param organType
	 */
	public void deleteOrganType(OrganType organType);
	
	/**
	 * 批量更新
	 * @param hql
	 * @param o
	 * @return
	 */
	public int batchUpdate(String hql,Object...o);
	
	/**
	 * 批量更新
	 * @param list
	 */
	public void batchUpdate(List<OrganType> list);
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	public OrganType findById(String id);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<OrganType> getAll();
	
	/**
	 * 通过HQL查询
	 * @param hql
	 * @return
	 */
	public List<OrganType> getAllByHql(String hql);
	
	/**
	 * 通过QBC查询
	 * @param criterion
	 * @return
	 */
	public List<OrganType> getAllByCriteria(Criterion... criterion);
	
	/**
	 * 通过QBE查询
	 * @param organType
	 * @param enableLike
	 * @param properties
	 * @return
	 */
	public List<OrganType> getAllByExample(OrganType organType,boolean enableLike,String... properties);
	
	/**
	 * 通过QBC查询
	 * @param organType
	 * @return
	 */
	public List<OrganType> getAllByExample(OrganType organType);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param organType 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(OrganType organType,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param organType 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(OrganType organType,PageParams pageParams,Order...defaultOrders );
}
