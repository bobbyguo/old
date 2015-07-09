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
 * ��֯service��ӿ���
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 ����03:49:11 <br>
 *
 */
public interface IOrganService {

	/**
	 * ����
	 * @param organ
	 */
	public void saveOrgan(Organ organ);
	
	/**
	 * ����
	 * @param organ
	 */
	public void updateOrgan(Organ organ);
	
	/**
	 * ɾ��
	 * @param organ
	 */
	public void deleteOrgan(Organ organ);
	
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
	public void batchUpdate(List<Organ> list);
	
	/**
	 * ͨ��ID����
	 * @param id
	 * @return
	 */
	public Organ findById(String id);
	
	/**
	 * ��������
	 * @return
	 */
	public List<Organ> getAll();
	
	/**
	 * ͨ��HQL��ѯ
	 * @param hql
	 * @return
	 */
	public List<Organ> getAllByHql(String hql);
	
	/**
	 * ͨ��QBC��ѯ
	 * @param criterion
	 * @return
	 */
	public List<Organ> getAllByCriteria(Criterion... criterion);
	
	/**
	 * ͨ��QBE��ѯ
	 * @param organ
	 * @param enableLike
	 * @param properties
	 * @return
	 */
	public List<Organ> getAllByExample(Organ organ,boolean enableLike,String... properties);
	
	/**
	 * ͨ��QBC��ѯ
	 * @param organ
	 * @return
	 */
	public List<Organ> getAllByExample(Organ organ);
	
	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param organ ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(Organ organ,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param organ ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(Organ organ,PageParams pageParams,Order...defaultOrders );
}
