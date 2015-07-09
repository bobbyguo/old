/**
 * 
 */
package cn.commonframework.security.service;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import cn.commonframework.security.model.Role;
import cn.commonframework.util.PageParams;
import cn.commonframework.util.Pagenation;

/**
 * @author  :qiang
 * @version :1.0
 * @date    :2009-11-16 ����11:42:10
 *
 */
public interface IRoleService {

	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
	
	public int batchUpdate(String hql,Object...o);
	
	public Role findById(String id);
	
	public List<Role> getAll();
	
	public List<Role> getAllByHql(String hql);
	
	public List<Role> getAllByCriteria(Criterion... criterion);
	
	public List<Role> getAllByExample(Role role,boolean enableLike,String... properties);
	
	public List<Role> getAllByExample(Role role);
	
	/**
	 * ��ҳ��ѯ����ȷ��ѯ����Ŀǰ���displaytagʹ��
	 * @param role ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation getAllWithPage(Role role,PageParams pageParams,Order... defaultOrders);
	
	/**
	 * ��ҳ��ѯ��ģ����ѯ����Ŀǰ���displaytagʹ��
	 * @param t ��ѯ����
	 * @param pageParams ��ҳ��ѯ��ز���
	 * @param defaultOrders Ĭ��������
	 * @return ��ҳ�������Ĳ�ѯ�б�
	 */
	public Pagenation fuzzyQuery(Role role,PageParams pageParams,Order...defaultOrders );
}
