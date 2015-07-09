/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.model.Menu;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-15 下午03:01:57 <br>
 *
 */
public interface IMenuService {

	/**
	 * 保存
	 * @param menu
	 */
	public void saveMenu(Menu menu);
	
	/**
	 * 更新
	 * @param menu
	 */
	public void updateMenu(Menu menu);
	
	/**
	 * 删除
	 * @param menu
	 */
	public void deleteMenu(Menu menu);
	
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
	public void batchUpdate(List<Menu> list);
	
	/**
	 * 通过ID查找
	 * @param id
	 * @return
	 */
	public Menu findById(String id);
	
	/**
	 * 查找所有
	 * @return
	 */
	public List<Menu> getAll();
	
	/**
	 * 通过HQL查询
	 * @param hql
	 * @return
	 */
	public List<Menu> getAllByHql(String hql);
	
	/**
	 * 通过QBC查询
	 * @param criterion
	 * @return
	 */
	public List<Menu> getAllByCriteria(Criterion... criterion);
	
	/**
	 * 通过QBE查询
	 * @param menu
	 * @param enableLike
	 * @param properties
	 * @return
	 */
	public List<Menu> getAllByExample(Menu menu,boolean enableLike,String... properties);
	
	/**
	 * 通过QBC查询
	 * @param menu
	 * @return
	 */
	public List<Menu> getAllByExample(Menu menu);
	
	/**
	 * 分页查询（精确查询），目前结合displaytag使用
	 * @param menu 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation getAllWithPage(Menu menu,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * 分页查询（模糊查询），目前结合displaytag使用
	 * @param menu 查询对象
	 * @param pageParams 翻页查询相关参数
	 * @param defaultOrders 默认排序组
	 * @return 分页并排序后的查询列表。
	 */
	public Pagenation fuzzyQuery(Menu menu,PageParams pageParams,Order...defaultOrders );
}
