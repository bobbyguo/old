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
 * @date    :2009-12-15 ����03:01:57 <br>
 *
 */
public interface IMenuService {

	/**
	 * ����
	 * @param menu
	 */
	public void saveMenu(Menu menu);
	
	/**
	 * ����
	 * @param menu
	 */
	public void updateMenu(Menu menu);
	
	/**
	 * ɾ��
	 * @param menu
	 */
	public void deleteMenu(Menu menu);
	
	/**
	 * ��������
	 * @param hql
	 * @param o
	 * @return
	 */
	public int batchUpdate(String hql,Object...o);
	
	/**
	 * ��������
	 * @param list
	 */
	public void batchUpdate(List<Menu> list);
	
	/**
	 * ͨ��ID����
	 * @param id
	 * @return
	 */
	public Menu findById(String id);
	
	/**
	 * ��������
	 * @return
	 */
	public List<Menu> getAll();
	
	/**
	 * ͨ��HQL��ѯ
	 * @param hql
	 * @return
	 */
	public List<Menu> getAllByHql(String hql);
	
	/**
	 * ͨ��QBC��ѯ
	 * @param criterion
	 * @return
	 */
	public List<Menu> getAllByCriteria(Criterion... criterion);
	
	/**
	 * ͨ��QBE��ѯ
	 * @param menu
	 * @param enableLike
	 * @param properties
	 * @return
	 */
	public List<Menu> getAllByExample(Menu menu,boolean enableLike,String... properties);
	
	/**
	 * ͨ��QBC��ѯ
	 * @param menu
	 * @return
	 */
	public List<Menu> getAllByExample(Menu menu);
	
	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param menu ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(Menu menu,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param menu ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(Menu menu,PageParams pageParams,Order...defaultOrders );
}
