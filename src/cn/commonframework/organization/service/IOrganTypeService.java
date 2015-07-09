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
 * ��֯����service��ӿ�
 * @author  :qiang         <br>
 * @version :1.0             <br>
 * @date    :2009-12-24 ����10:59:17 <br>
 *
 */
public interface IOrganTypeService {

	/**
	 * ����
	 * @param organType
	 */
	public void saveOrganType(OrganType organType);
	
	/**
	 * ����
	 * @param organType
	 */
	public void updateOrganType(OrganType organType);
	
	/**
	 * ɾ��
	 * @param organType
	 */
	public void deleteOrganType(OrganType organType);
	
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
	public void batchUpdate(List<OrganType> list);
	
	/**
	 * ͨ��ID����
	 * @param id
	 * @return
	 */
	public OrganType findById(String id);
	
	/**
	 * ��������
	 * @return
	 */
	public List<OrganType> getAll();
	
	/**
	 * ͨ��HQL��ѯ
	 * @param hql
	 * @return
	 */
	public List<OrganType> getAllByHql(String hql);
	
	/**
	 * ͨ��QBC��ѯ
	 * @param criterion
	 * @return
	 */
	public List<OrganType> getAllByCriteria(Criterion... criterion);
	
	/**
	 * ͨ��QBE��ѯ
	 * @param organType
	 * @param enableLike
	 * @param properties
	 * @return
	 */
	public List<OrganType> getAllByExample(OrganType organType,boolean enableLike,String... properties);
	
	/**
	 * ͨ��QBC��ѯ
	 * @param organType
	 * @return
	 */
	public List<OrganType> getAllByExample(OrganType organType);
	
	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param organType ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(OrganType organType,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param organType ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(OrganType organType,PageParams pageParams,Order...defaultOrders );
}
