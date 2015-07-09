/**
 * 
 */
package cn.commonframework.organization.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.organization.model.Organ;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * 组织service层接口类
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 下午03:49:11 <br>
 *
 */
public interface IOrganService {

	/**
	 * 保存
	 * @param organ
	 */
	public void saveOrgan(Organ organ);
	
	/**
	 * 更新
	 * @param organ
	 */
	public void updateOrgan(Organ organ);
	
	/**
	 * 删除
	 * @param organ
	 */
	public void deleteOrgan(Organ organ);
	
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
	public void batchUpdate(List<Organ> list);
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	public Organ findById(String id);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Organ> getAll();
	
	/**
	 * 通过HQL查询
	 * @param hql
	 * @return
	 */
	public List<Organ> getAllByHql(String hql);
	
	/**
	 * 通过QBC查询
	 * @param criterion
	 * @return
	 */
	public List<Organ> getAllByCriteria(Criterion... criterion);
	
	/**
	 * 通过QBE查询
	 * @param organ
	 * @param enableLike
	 * @param properties
	 * @return
	 */
	public List<Organ> getAllByExample(Organ organ,boolean enableLike,String... properties);
	
	/**
	 * 通过QBC查询
	 * @param organ
	 * @return
	 */
	public List<Organ> getAllByExample(Organ organ);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param organ 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(Organ organ,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param organ 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(Organ organ,PageParams pageParams,Order...defaultOrders );
}
